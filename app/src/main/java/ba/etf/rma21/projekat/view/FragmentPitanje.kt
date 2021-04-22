package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje


class FragmentPitanje ( private var pitanje: Pitanje): Fragment()  {

    private lateinit var pitanjeText: TextView
    private lateinit var listaOdgovora: ListView

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


        return view
    }

    companion object {
        fun newInstance(p: Pitanje): FragmentPitanje = FragmentPitanje(p)
    }

}