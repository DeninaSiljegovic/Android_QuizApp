package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj ( private var pitanja: List<Pitanje> ) : Fragment()  {
    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var navigationPitanja : NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        val activity = activity as MainActivity
        bottomNavigation = activity.getBottomNavigation()
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false

        navigationPitanja = view.findViewById(R.id.navigacijaPitanja)
        val meni = navigationPitanja.menu
        for(i in 1..pitanja.size){
            var temp = SpannableString(i.toString())
            meni.add(0, i - 1, i - 1, temp)
        }

        val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {item ->
            val newFragment = FragmentPitanje.newInstance(pitanja[item.order])
            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framePitanje, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            return@OnNavigationItemSelectedListener true
        }
        navigationPitanja.setNavigationItemSelectedListener(onNavigationItemSelectedListener)


        return view
    }

    companion object {
        fun newInstance(pit: List<Pitanje>): FragmentPokusaj = FragmentPokusaj(pit)
    }
}