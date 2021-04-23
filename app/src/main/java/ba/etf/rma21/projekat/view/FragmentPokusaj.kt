package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj ( private var pitanja: List<Pitanje> ) : Fragment()  {
    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var navigationPitanja : NavigationView
    private var bojeNav = IntArray(pitanja.size){0}
    private var pom = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        val imeKviza = arguments?.getString("imeKviza")

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
            temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.white)), 0, i.toString().length, 0)
            meni.add(0, i - 1, i - 1, temp)
        }

        val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {item ->
            val tag: String = pitanja[item.order].naziv + imeKviza
            pom = item.order + 1

            val provjeraFragment = activity.supportFragmentManager.findFragmentByTag(tag)

            if(provjeraFragment == null) {
                val newFragment = FragmentPitanje.newInstance(pitanja[item.order])
                val transaction = activity.supportFragmentManager.beginTransaction()

                transaction.replace(R.id.framePitanje, newFragment, tag)
                transaction.addToBackStack(null)

//                activity.supportFragmentManager.setFragmentResultListener(
//                        "rezultat",
//                        this,
//                        FragmentResultListener { requestKey: String, result: Bundle ->
//                            val rez = arguments?.getString("rezultatB")?.toInt()
//
//                            if(rez != null) {
//                                var temp = SpannableString(item.order.toString())
//
//                                if (rez!! >= 0 && rez == pitanja[item.order].tacan) {
//                                    temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.tacno)), 0, item.order.toString().length, 0)
//                                } else  temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.pogresno)), 0, item.order.toString().length, 0)
//                                meni.add(0, item.order - 1, item.order - 1, temp)
//                            }
//
//                        })

                transaction.commit()
            }

            else{
                val transaction = activity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framePitanje, provjeraFragment, tag)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            return@OnNavigationItemSelectedListener true
        }
        navigationPitanja.setNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setFragmentResultListener("rezultat") { requestKey, bundle ->

            val rez = bundle.getInt("rezultatB")
            //Log.d("REZ je ", rez.toString())
            var temp = SpannableString(pom.toString())

            if (rez == 1) {
                temp.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            view.context,
                            R.color.tacno
                        )
                    ), 0, pom.toString().length, 0
                )
            } else temp.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        view.context,
                        R.color.pogresno
                    )
                ), 0, pom.toString().length, 0
            )
            meni[pom-1].title = temp
        }


        return view
    }

    companion object {
        fun newInstance(pit: List<Pitanje>): FragmentPokusaj = FragmentPokusaj(pit)
    }
}