package ba.etf.rma21.projekat


import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPoruka
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
            R.id.predajKviz -> {
                //napravit funkciju koja predaje kviz
                val bundle = Bundle()
                bundle.putString("imekviza", "KSK") //OVO NAPRAVI
                val newFragment = FragmentPoruka.newInstance()
                newFragment.arguments = bundle
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


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if(fragment !is FragmentKvizovi || supportFragmentManager.backStackEntryCount > 0) {
            transaction.add(R.id.container, fragment)
            transaction.addToBackStack(null)
        }
        else transaction.add(R.id.container, fragment, "pocetniKvizovi")
        transaction.commit()
    }


}//main activiry closed

