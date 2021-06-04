package ba.etf.rma21.projekat


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPoruka
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.viewmodel.OdgovorViewModel
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import ba.etf.rma21.projekat.viewmodel.TakeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigation : BottomNavigationView
    private var uradjen = 0
    private var odgovorViewModel = OdgovorViewModel()
    private var pitanjeKvizViewModel = PitanjeKvizViewModel()
    private var kvizViewModel = KvizViewModel()
    private var takeKvizViewModel = TakeKvizViewModel()
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val bundle = Bundle()
                bundle.putString("uradjen", uradjen.toString())
                val kvizoviFragment = FragmentKvizovi.newInstance()
                kvizoviFragment.arguments = bundle
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val predmetiFragments = FragmentPredmeti.newInstance()
                openFragment(predmetiFragments)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predajKviz -> {
                val idKviza = vratiIdKviza()
                var result: Deferred<Unit>
                var tacnoOdg: Float = 0F
                var percent: Float = 0F

                scope.launch{
                    result = async {
                        val odgovori = odgovorViewModel.getOdgovoriKviz(idKviza)
                        val pitanja = pitanjeKvizViewModel.getPitanja(idKviza)

                        val pokusajKviza = takeKvizViewModel.getPocetiKvizovi()!!.find { it.KvizId == idKviza }

                        if (odgovori.size != pitanja.size) {
                            for (p in pitanja) {
                                val odg = odgovorViewModel.getOdgovoriKviz(idKviza).find { it.PitanjeId == p.id }

                                if (odg == null) {
                                    println("postavljamo fejk odgovor za pitanj " + p.tekstPitanja + " sifra kviza je " + pokusajKviza!!.id)
                                    odgovorViewModel.postaviOdgovorKviz(pokusajKviza!!.id, p.id, 1000) //fake odgovor postavljen
                                }
                            }
                        }
//                        for(p in pitanja){
//                            val odg = odgovorViewModel.getOdgovoriKviz(idKviza).find { it.PitanjeId == p.id }
//                            if (odg != null) {
//                                if(odg.odgovoreno == p.tacan) tacnoOdg += 1
//                            }
//                        }
//
//                        percent = tacnoOdg / (pitanja.size)
//
//                        if (pokusajKviza != null) {
//                            println("Osvojeni bodovi su " + percent)
//                            pokusajKviza.osvojeniBodovi = percent
//                        }
                    }
                    result.await()
                }//scope zatvoren
                uradjen = 1
                val newFragment = FragmentPoruka.newInstance()
                openFragment(newFragment)
                bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
                bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
                bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
                bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.zaustaviKviz -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                bottomNavigation.selectedItemId = R.id.kvizovi
                bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
                bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
                bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
                bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
//        if(supportFragmentManager.backStackEntryCount > 0){
//            supportFragmentManager.popBackStack(supportFragmentManager.getBackStackEntryAt(0).id, POP_BACK_STACK_INCLUSIVE)
//        }
//        else super.onBackPressed()
        if(bottomNavigation.selectedItemId != R.id.kvizovi)
            bottomNavigation.selectedItemId = R.id.kvizovi
        else{
            bottomNavigation.selectedItemId = R.id.kvizovi
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottomNav)

        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //Defaultni fragment
        bottomNavigation.selectedItemId = R.id.kvizovi
        val kvizFragment = FragmentKvizovi.newInstance()
        openFragment(kvizFragment)
    }//onCreate end

    fun getBottomNavigation(): BottomNavigationView{
        return bottomNavigation
    }

    private fun openFragment(fragment: Fragment, tag: String = "") {
        val transaction = supportFragmentManager.beginTransaction()
        if(fragment !is FragmentKvizovi || supportFragmentManager.backStackEntryCount > 0) {
            transaction.replace(R.id.container, fragment, tag)
            transaction.addToBackStack(null)
        }
        else transaction.replace(R.id.container, fragment, tag)
        transaction.commit()
    }

    companion object {
        private var idKviza: Int = 0
        fun primiPodatke(bundle: Bundle) {
            idKviza = bundle.getInt("idKviza")
        }

        fun vratiIdKviza(): Int { return idKviza }
    }


}//main activiry closed

