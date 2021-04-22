package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import kotlin.properties.Delegates


class FragmentPitanje ( private var pitanje: Pitanje): Fragment()  {

    private lateinit var pitanjeText: TextView
    private lateinit var listaOdgovora: ListView
    private var selectedPosition: PitanjeKvizViewModel = PitanjeKvizViewModel()
    private var odg by Delegates.notNull<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pitanje_fragment, container, false)

        pitanjeText = view.findViewById(R.id.tekstPitanja)
        listaOdgovora = view.findViewById(R.id.odgovoriLista)

        pitanjeText.text = pitanje.tekst
        listaOdgovora.adapter = ArrayAdapter<String>(
                view.context,
                android.R.layout.simple_list_item_1,
                pitanje.opcije
        )

        odg = selectedPosition.getSelectedAnswer(pitanje.naziv)

        if(odg > 0){
            listaOdgovora.isEnabled = false
            val textView = listaOdgovora.getChildAt(odg) as TextView
            //obojiti odgovarajuce odgovore
            if(odg == pitanje.tacan)
                textView.setTextColor(ContextCompat.getColor(view.context, R.color.tacno))
            else textView.setTextColor(ContextCompat.getColor(view.context, R.color.pogresno))
        }

//        listaOdgovora.setOnItemClickListener {
//            listaOdgovora.isEnabled = false
//            odg = listaOdgovora.selectedItemPosition
//            val textView = listaOdgovora.getChildAt(odg) as TextView
//            //obojiti odgovarajuce odgovore
//            if(odg == pitanje.tacan)
//                textView.setTextColor(ContextCompat.getColor(view.context, R.color.tacno))
//            else textView.setTextColor(ContextCompat.getColor(view.context, R.color.pogresno))
//        }

        listaOdgovora.onItemClickListener = OnItemClickListener { list, v, pos, id ->
            odg = pos
            listaOdgovora.isEnabled = false
            val textView = listaOdgovora.getChildAt(odg) as TextView
            //obojiti odgovarajuce odgovore
            if(odg == pitanje.tacan)
                textView.setTextColor(ContextCompat.getColor(view.context, R.color.tacno))
            else textView.setTextColor(ContextCompat.getColor(view.context, R.color.pogresno))
            selectedPosition.setSelectedAnswer(pitanje.naziv, odg)
        }


        return view
    }

    companion object {
        fun newInstance(p: Pitanje): FragmentPitanje = FragmentPitanje(p)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}