package ba.etf.rma21.projekat


import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigation : BottomNavigationView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val predmetiFragments = FragmentPredmeti.newInstance()
                openFragment(predmetiFragments)
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
            super.onBackPressed()
            finishAffinity()
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
        if(fragment !is FragmentKvizovi || supportFragmentManager.backStackEntryCount > 0)
            transaction.addToBackStack(null)
        transaction.commit()
    }


}//main activiry closed

