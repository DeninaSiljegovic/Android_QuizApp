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
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj ( private var pitanja: List<Pitanje> ) : Fragment()  {
    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var navigationPitanja : NavigationView
    private  var  imeKviza: String = ""
    private  var  imePredmeta: String = ""
    private var kvizUradjen: String = ""
    private var nizOdg = IntArray(pitanja.size){0}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        imeKviza = arguments?.getString("imeKviza").toString()
        imePredmeta = arguments?.getString("imePredmeta").toString()
        kvizUradjen = arguments?.getString("uradjen").toString()

//        Log.d("TEST123", arguments?.getString("test").toString())

        val activity = activity as MainActivity
        bottomNavigation = activity.getBottomNavigation()
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false

        navigationPitanja = view.findViewById(R.id.navigacijaPitanja)
        val meni = navigationPitanja.menu

        //navigation mei sa strane postavka brojeva
        for(i in 1..pitanja.size){
            var temp = SpannableString(i.toString())
            if(nizOdg[i-1] == 0) temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.white)), 0, i.toString().length, 0)
            else if(nizOdg[i-1] == 1) temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.tacno)), 0, i.toString().length, 0)
            else  temp.setSpan(ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.pogresno)), 0, i.toString().length, 0)
            meni.add(0, i - 1, i - 1, temp)
        }

        var pom = 0

        //otvaranje odg kviza klikom na navigation sa strane
        val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {item ->
            val tag: String = pitanja[item.order].naziv + imeKviza
            pom = item.order + 1 //treba za promjenu boje brojeva sa strane

            val bundle = Bundle()
            bundle.putString("uradjen", kvizUradjen) //KAD PRIMI PITANJE FRAGMENT DA ZNA DA NE MOZE BITI CLICKABLE ODGOVORI VISE

            val provjeraFragment = activity.supportFragmentManager.findFragmentByTag(tag)

            if(provjeraFragment == null) {
                val newFragment = FragmentPitanje.newInstance(pitanja[item.order])
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

            return@OnNavigationItemSelectedListener true
        }
        navigationPitanja.setNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //promjena boje brojeva u nav view sa strane
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
                nizOdg[pom-1] = 1
            } else {
                temp.setSpan(
                        ForegroundColorSpan(
                                ContextCompat.getColor(
                                        view.context,
                                        R.color.pogresno
                                )
                        ), 0, pom.toString().length, 0
                )
                nizOdg[pom - 1] = -1
            }
            meni[pom-1].title = temp
        }

        setFragmentResult("zavrseno", bundleOf(Pair("kvizIme", imeKviza)))

        setFragmentResultListener("OZNACIuradjen") { requestKey, bundle ->
            kvizUradjen = bundle.getString("OZNACIuradjenKviz")!!
        }

        if(kvizUradjen == "0"){
            Log.d("PokusajFragment", imeKviza)
            MainActivity.primiPodatke(bundleOf(
                    Pair("imeKviza", imeKviza),
                    Pair("imePredmeta", imePredmeta)
            ))
        }

        return view
    }

    companion object {
        fun newInstance(pit: List<Pitanje>): FragmentPokusaj = FragmentPokusaj(pit)
    }


    override fun onResume() {
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = true
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = false
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = false
        super.onResume()
    }

    override fun onPause() {
        bottomNavigation.menu.findItem(R.id.predajKviz).isVisible = false
        bottomNavigation.menu.findItem(R.id.zaustaviKviz).isVisible = false
        bottomNavigation.menu.findItem(R.id.kvizovi).isVisible = true
        bottomNavigation.menu.findItem(R.id.predmeti).isVisible = true
        super.onPause()
    }

}