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
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.viewmodel.GrupaViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import java.util.stream.Collectors

class FragmentPredmeti : Fragment() {

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirPredmet: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var dodajPredmetDugme: Button
    private var predmetListViewModel = PredmetViewModel()
    private var grupaListViewModel = GrupaViewModel()
    private var odabGod: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.predmeti_fragment, container, false)

        odabirGodina = view.findViewById(R.id.odabirGodina)
        odabirPredmet = view.findViewById(R.id.odabirPredmet)
        odabirGrupa = view.findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = view.findViewById(R.id.dodajPredmetDugme)

        //odabGod = intent.getStringExtra("selectedYear")?.toInt() ?: 0

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
                    view.background = ColorDrawable(Color.parseColor("#E695B1"))
                    view.setTextColor(Color.parseColor("#2E2D88"))
                }
                return view
            }
        }
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        odabirGodina.setAdapter(dataAdapter)
        odabirGodina.setSelection(odabGod)

        odabirGodina.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                var positonInt = Integer.valueOf(item_position)
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
                            view.background = ColorDrawable(Color.parseColor("#E695B1"))
                            view.setTextColor(Color.parseColor("#2E2D88"))
                        }
                        return view
                    }
                }

                odabirPredmet.setAdapter(dataAdapter1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR GODINA


        odabirPredmet.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
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
                            view.background = ColorDrawable(Color.parseColor("#E695B1"))
                            view.setTextColor(Color.parseColor("#2E2D88"))
                        }
                        return view
                    }
                }

                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirGrupa.setAdapter(dataAdapter2)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR PREDMETA


        odabirGrupa.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR GRUPE


        return view
    }

    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }

}