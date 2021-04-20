package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.viewmodel.KvizViewModel


class FragmentKvizovi : Fragment() {

    private lateinit var listaKvizova: RecyclerView
    private lateinit var filterKvizova: Spinner
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizViewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.kvizovi_fragment, container, false)
        listaKvizova = view.findViewById(R.id.listaKvizova)
        filterKvizova = view.findViewById(R.id.filterKvizova)

        // Spinner Drop down elements
        val categories: MutableList<String> = ArrayList()
        categories.add("Svi moji kvizovi")
        categories.add("Svi kvizovi")
        categories.add("Urađeni kvizovi")
        categories.add("Budući kvizovi")
        categories.add("Prošli kvizovi")

        // Creating adapter for spinner
        //val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        val dataAdapter:ArrayAdapter<String> = object: ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        ){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView
                // set item text size
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP,15F)

                // set selected item style
                if (position == filterKvizova.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#E695B1"))
                    view.setTextColor(Color.parseColor("#2E2D88"))
                }
                return view
            }
        }

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        filterKvizova.setAdapter(dataAdapter);

        //grid layout - da se prikazu fino kao mreza
        listaKvizova.layoutManager = GridLayoutManager(
                view.context,
                2,
                GridLayoutManager.VERTICAL,
                false
        )
        listaKvizovaAdapter = KvizListAdapter(listOf())
        listaKvizova.adapter = listaKvizovaAdapter

        filterKvizova.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                //first,  we have to retrieve the item position as a string
                // then, we can change string value into integer
                val item_position = position.toString()
                val positonInt = Integer.valueOf(item_position)
                if(positonInt  == 0)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
                else if(positonInt == 1)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll())
                else if(positonInt == 2) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyDone())
                else if(positonInt == 3) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyFuture())
                else if(positonInt == 4) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyNotTaken())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll()) }
        })


        return view
    }

        companion object {
            fun newInstance(): FragmentKvizovi = FragmentKvizovi()
        }


}