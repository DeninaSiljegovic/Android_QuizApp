package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.viewmodel.AccountViewModel
import ba.etf.rma21.projekat.viewmodel.DBViewModel
import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class FragmentKvizovi : Fragment() {

    private lateinit var listaKvizova: RecyclerView
    private lateinit var filterKvizova: Spinner
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizViewModel()
    private var DBViewmodel = DBViewModel()
    private var accountViewModel = AccountViewModel()

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    private val model: SharedViewModel by activityViewModels()


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
                    view.background = ColorDrawable(Color.parseColor("#F1E9EC"))
                    view.setTextColor(Color.parseColor("#2E2D88"))
                }
                return view
            }
        }

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        filterKvizova.adapter = dataAdapter;

        //grid layout - da se prikazu fino kao mreza
        listaKvizova.layoutManager = GridLayoutManager(
                view.context,
                2,
                GridLayoutManager.VERTICAL,
                false
        )
        listaKvizovaAdapter = KvizListAdapter(listOf(), activity?.supportFragmentManager, filterKvizova.selectedItem.toString(), requireActivity().applicationContext)
        listaKvizova.adapter = listaKvizovaAdapter
        kvizListViewModel.setContext(requireActivity().applicationContext)
        filterKvizova.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                //first,  we have to retrieve the item position as a string
                // then, we can change string value into integer
                val item_position = position.toString()
                val positonInt = Integer.valueOf(item_position)
                scope.launch {
                    kvizListViewModel.setContext(activity!!.applicationContext)
                    DBViewmodel.setContext(requireActivity().applicationContext)
                    accountViewModel.setContext(requireActivity().applicationContext)


                    if(DBViewmodel.updateNow()) {
                        accountViewModel.updatePodatke()
                    }

                    if (positonInt == 0) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
                    else if (positonInt == 1) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll())
                    else if (positonInt == 2) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyDone())
                    else if (positonInt == 3) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyFuture())
                    else if (positonInt == 4) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyNotTaken())
                }
                listaKvizovaAdapter.updateSpinner(filterKvizova.selectedItem.toString())
            }


            override fun onNothingSelected(parent: AdapterView<*>?) { scope.launch { listaKvizovaAdapter.updateKvizove( kvizListViewModel.getAll())} }
        }

        if(model.getIzmjena() == 1) {
            scope.launch { listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes()) }
            filterKvizova.setSelection(0)
            //da bi se restartovali spinneri na pocetni izgled - inace se prebaci na iduci neupisani predmet/godinu
            model.setIzmjena(0)
        }

        val uradjen = arguments?.getString("uradjen")

        if(uradjen.toString() == "1"){
            scope.launch { listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes()) }
            filterKvizova.setSelection(0)
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }


}