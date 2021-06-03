package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.OdgovorViewModel
import ba.etf.rma21.projekat.viewmodel.TakeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FragmentPokusaj ( private var pitanja: List<Pitanje>,
                        private var idKviz: Int
) : Fragment()  {
    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var navigationPitanja : NavigationView

    private var takeKvizViewModel = TakeKvizViewModel()
    private var odgovorViewModel = OdgovorViewModel()

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private  var  imeKviza: String = ""
    private  var  imePredmeta: String = ""
    private var kvizUradjen: String = ""
    private var tekZapocet: Boolean = false
    private var listaOdgovoraKorisnika: List<Odgovor> = listOf()
    private var kvizVecOtvoren: KvizTaken? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        imeKviza = arguments?.getString("imeKviza").toString()
        imePredmeta = arguments?.getString("imePredmeta").toString()
        kvizUradjen = arguments?.getString("uradjen").toString()

        val activity = activity as MainActivity
        bottomNavigation = activity.getBottomNavigation()
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false

        navigationPitanja = view.findViewById(R.id.navigacijaPitanja)
        val meni = navigationPitanja.menu

        var i1: Int = 0
        var pom = 0

        //navigation meni sa strane postavka brojeva
        for(i in 1..pitanja.size){
            var temp = SpannableString(i.toString())
            temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.white)), 0, i.toString().length, 0)
            meni.add(0, i - 1, i - 1, temp)
            i1 = i
        }
        if(kvizUradjen == "1") meni.add(0, 250, i1 - 1, "Rezultat")

        //provjeriti da li je kviz vec poceo se radit prije
        scope.launch {
            kvizVecOtvoren = takeKvizViewModel.getPocetiKvizovi().find{ it.KvizId == idKviz }
            if(kvizVecOtvoren != null){
                tekZapocet = false
                listaOdgovoraKorisnika = odgovorViewModel.getOdgovoriKviz(idKviz)
                println("Listadddddd " + listaOdgovoraKorisnika.size)

                if(!listaOdgovoraKorisnika.isEmpty()){

                    for(odgovor in listaOdgovoraKorisnika){
                        val pitanje = pitanja.find{pitanje -> pitanje.id == odgovor.PitanjeId }
                        if(pitanje != null){
                            val indeks = pitanja.indexOf(pitanje) + 1
                            val temp = SpannableString(indeks.toString())

                            if(pitanje.tacan == odgovor.odgovoreno){ //ako je odgovor tacan
                                temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.tacno)), 0, indeks.toString().length, 0)
                            }
                            else if(odgovor.odgovoreno== 1000){ //kad nije odgovoreno pitanje a predano - odg.odgovoreno == 10000
                                temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.white)), 0, indeks.toString().length, 0)
                            }
                            else if(pitanje.tacan != odgovor.odgovoreno){
                                temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.pogresno)), 0, indeks.toString().length, 0)
                            }

                            meni.getItem(indeks-1).title = temp

                        }
                    }
                }
            }
            else{
                kvizVecOtvoren = takeKvizViewModel.zapocniKviz(idKviz)
                tekZapocet = true
            }
        }


        //otvaranje odg kviza klikom na navigation sa strane
        val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {item ->

            //ako se odabere opcija da se prikaze rezultat
            if(item.itemId == 250){
                val tag = "ZAVRSEN"+imeKviza+imePredmeta

                val tacnost = (4.toFloat() / pitanja.size.toFloat()) * 100
                val send = "Zavrsili ste kviz $imeKviza \n sa tacnoscu $tacnost"
                val bundle = Bundle()
                bundle.putString("poruka", send)

                val provjeraFragment = activity.supportFragmentManager.findFragmentByTag(tag)

                if(provjeraFragment == null) {

                    val newFragment = FragmentPoruka.newInstance()
                    newFragment.arguments = bundle
                    val transaction = activity.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.framePitanje, newFragment, tag)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                else{
                    provjeraFragment.arguments = bundle
                    val transaction = activity.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.framePitanje, provjeraFragment, tag)
                    transaction.commit()
                }

            }

            else {
                val tag: String = pitanja[item.order].naziv + imeKviza
                pom = item.order + 1 //treba za promjenu boje brojeva sa strane

                val bundle = Bundle()
                bundle.putString("uradjen", kvizUradjen) //KAD PRIMI PITANJE FRAGMENT DA ZNA DA NE MOZE BITI CLICKABLE ODGOVORI VISE

                val provjeraFragment = activity.supportFragmentManager.findFragmentByTag(tag)

                if (provjeraFragment == null) {
                    val newFragment = FragmentPitanje.newInstance(pitanja[item.order], odgovorZaPitanje(pitanja[item.order])) //nes mijenjat
                    newFragment.arguments = bundle
                    val transaction = activity.supportFragmentManager.beginTransaction()

                    transaction.replace(R.id.framePitanje, newFragment, tag)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else {
                    provjeraFragment.arguments = bundle
                    val transaction = activity.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.framePitanje, provjeraFragment, tag)
                    transaction.commit()
                }
            }

            return@OnNavigationItemSelectedListener true
        }
        navigationPitanja.setNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //promjena boje brojeva u nav view sa strane
        setFragmentResultListener("rezultat") { requestKey, bundle ->
            val rez = bundle.getInt("rezultatB")
            val odg = bundle.getInt("choosenAnswer")
            //Log.d("REZ je ", rez.toString())
            var temp = SpannableString(pom.toString())

            scope.launch{
                odgovorViewModel.postaviOdgovorKviz(kvizVecOtvoren!!.id, pitanja[pom-1].id, odg)
            }

            if (rez == 1) {
                temp.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            view.context,
                            R.color.tacno
                        )
                    ), 0, pom.toString().length, 0
                )

            } else {
                temp.setSpan(
                        ForegroundColorSpan(
                                ContextCompat.getColor(
                                        view.context,
                                        R.color.pogresno
                                )
                        ), 0, pom.toString().length, 0
                )

            }
            meni[pom-1].title = temp
        }

        //ako je uradjen ili ne dobija se preko bundle iz kvizListAdapter
        if(kvizUradjen == "0"){
            MainActivity.primiPodatke(bundleOf(
                    Pair("idKviza", idKviz)
            ))
        }

        return view
    }

    companion object {
        fun newInstance(pit: List<Pitanje>, id: Int): FragmentPokusaj = FragmentPokusaj(pit, id)
    }

    private fun odgovorZaPitanje(pitanje: Pitanje): Int{
       if(!tekZapocet) {
           val odg = listaOdgovoraKorisnika.find { odgovor -> odgovor.PitanjeId == pitanje.id }
           if (odg != null) return odg.odgovoreno
       }
        return -1
    }


    override fun onResume() {
        if(kvizUradjen == "0") {
            bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
            bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
            bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
            bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false
        }
        else{
            bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
            bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
            bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
            bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
        }
        super.onResume()
    }

    override fun onPause() {
        setFragmentResult("zavrseno", bundleOf(Pair("poruka", "Završili ste kviz")))
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
        super.onPause()
    }

}