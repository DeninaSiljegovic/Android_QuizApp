package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetIGrupaViewModel
import ba.etf.rma21.projekat.viewmodel.SharedViewModel
import java.util.stream.Collectors

class FragmentPredmeti : Fragment() {

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirPredmet: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var dodajPredmetDugme: Button
    private var predmetListViewModel = PredmetIGrupaViewModel()
    private var grupaListViewModel = GrupaViewModel()
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizViewModel()
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.predmeti_fragment, container, false)

        odabirGodina = view.findViewById(R.id.odabirGodina)
        odabirPredmet = view.findViewById(R.id.odabirPredmet)
        odabirGrupa = view.findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = view.findViewById(R.id.dodajPredmetDugme)


        //odabGod = intent.getStringExtra("selectedYear")?.toInt() ?: 0
        val lastSelectedYear = model.getlastSelectedGodina()
        val lastSelectedPredmet = model.getlastSelectedPredmet()
        val lastSelectedGrupa = model.getlastSelectedGrupaa()

        // Spinner Drop down elements
        val categories: MutableList<String> = ArrayList()
        categories.add("1")
        categories.add("2")
        categories.add("3")
        categories.add("4")
        categories.add("5")

        //DA SE OZNACI KOJI SE ELEMENT BIRA
        val dataAdapter: ArrayAdapter<String> = object: ArrayAdapter<String>(
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
                if (position == odabirGodina.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#F1E9EC"))
                    view.setTextColor(Color.parseColor("#2E2D88"))
                }
                return view
            }
        }
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        odabirGodina.adapter = dataAdapter
        if(lastSelectedYear != "")  odabirGodina.setSelection(lastSelectedYear.toInt())

        odabirGodina.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                var positonInt = Integer.valueOf(item_position)
                model.setlastSelectedGodina(odabirGodina.selectedItemPosition.toString())
                //val predmeti = getPredmetiGodine(positonInt).map { it.naziv }.stream().collect(Collectors.toList())
                val predmeti = predmetListViewModel.getPredmetiNaKojeNijeUpisan(positonInt).map { it.naziv }.stream().collect(
                    Collectors.toList())

                //DA SE OZNACI KOJI SE ELEMENT BIRA
                val dataAdapter1: ArrayAdapter<String> = object: ArrayAdapter<String>(
                    view.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    predmeti
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
                        if (position == odabirGodina.selectedItemPosition){
                            view.background = ColorDrawable(Color.parseColor("#F1E9EC"))
                            view.setTextColor(Color.parseColor("#2E2D88"))
                        }
                        return view
                    }
                }

                odabirPredmet.adapter = dataAdapter1
                if(lastSelectedPredmet != "") odabirPredmet.setSelection(predmeti.indexOf(lastSelectedPredmet));
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR GODINA


        odabirPredmet.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                model.setlastSelectedPredmet(odabirPredmet.selectedItem.toString())
                val grupe = grupaListViewModel.getGroupsByPredmet(odabirPredmet.selectedItem.toString()).map { it.naziv }.stream().collect(
                    Collectors.toList())

                //DA SE OZNACI KOJI SE ELEMENT BIRA
                val dataAdapter2: ArrayAdapter<String> = object: ArrayAdapter<String>(
                    view.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    grupe
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
                        if (position == odabirGodina.selectedItemPosition){
                            view.background = ColorDrawable(Color.parseColor("#F1E9EC"))
                            view.setTextColor(Color.parseColor("#2E2D88"))
                        }
                        return view
                    }
                }

                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirGrupa.adapter = dataAdapter2
                if(lastSelectedGrupa != "")  odabirGrupa.setSelection(grupe.indexOf(lastSelectedGrupa));
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR PREDMETA


        odabirGrupa.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                model.setlastSelectedGrupa(odabirGrupa.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR GRUPE

        dodajPredmetDugme.setOnClickListener{
            dodajPredmetDugmeClicked(it)
        }

        return view
    }

    fun dodajPredmetDugmeClicked(view: View?) {
        if(odabirGodina.selectedItem != null && odabirPredmet.selectedItem != null && odabirGrupa.selectedItem != null){
            val bundle = Bundle()
            bundle.putString("predmet", odabirPredmet.selectedItem.toString())
            bundle.putString("grupa", odabirGrupa.selectedItem.toString())

            kvizListViewModel.upisiKviz(model.getlastSelectedGrupaa())
            model.setIzmjena(1)

            predmetListViewModel.upisi(odabirPredmet.selectedItem.toString(), odabirGodina.selectedItemPosition.toString().toInt()+1)

            model.setlastSelectedGodina("")
            model.setlastSelectedPredmet("")
            model.setlastSelectedGrupa("")

            val newFragment = FragmentPoruka.newInstance()
            newFragment.arguments = bundle

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, newFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }

    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }

}