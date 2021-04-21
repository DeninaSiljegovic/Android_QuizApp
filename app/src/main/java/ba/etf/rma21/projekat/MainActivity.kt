package ba.etf.rma21.projekat


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizViewModel()
    private var predmetListViewModel = PredmetViewModel()
    private var odabranaGod : Int = 0
    private var odabraniPred : Int = 0
    private var odabranaGrupa : Int = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container, kvizoviFragment)
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val bundle = Bundle()
                bundle.putString(odabranaGod.toString(), "From Activity")
                val predmetiFragments = FragmentPredmeti.newInstance()
                predmetiFragments.arguments = bundle
                openFragment(predmetiFragments)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation= findViewById(R.id.bottomNav)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //Defaultni fragment
        bottomNavigation.selectedItemId = R.id.kvizovi
        val kvizFragment = FragmentKvizovi.newInstance()
        openFragment(kvizFragment)
    }//onCreate end


    fun saveData(id: Int, data: Bundle?) {
//        // based on the id you'll know which fragment is trying to save data(see below)
//        // the Bundle will hold the data
//        if(id == 1){
//            odabranaGod = data?.getString("selectedYear").toString().toInt()
//            kvizListViewModel.upisiKviz(data?.getString("grupa").toString()) //RADI OK
//            predmetListViewModel.upisi(data?.getString("predmet").toString(), data?.getString("godina").toString().toInt()+1)
//            listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
//        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}//main activiry closed

