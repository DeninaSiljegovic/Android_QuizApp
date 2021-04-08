package ba.etf.rma21.projekat

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository.Companion.getGroupsByPredmet
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getPredmetiGodine

class UpisPredmetActivity : AppCompatActivity(){

    private lateinit var odabirGodina: Spinner
    private lateinit var odabirPredmet: Spinner
    private lateinit var odabirGrupa: Spinner
    private lateinit var dodajPredmetDugme: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_predmet)

        odabirGodina = findViewById(R.id.odabirGodina)
        odabirPredmet = findViewById(R.id.odabirPredmet)
        odabirGrupa = findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = findViewById(R.id.dodajPredmetDugme)

        // Spinner Drop down elements
        val categories: MutableList<String> = ArrayList()
        categories.add("Prva godina")
        categories.add("Druga godina")
        categories.add("Treca godina")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this@UpisPredmetActivity, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        odabirGodina.setAdapter(dataAdapter)

        odabirGodina.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                var positonInt = Integer.valueOf(item_position)
                val predmeti = getPredmetiGodine(positonInt).map { it.naziv }.stream().toArray()

                val dataAdapter1 = ArrayAdapter(this@UpisPredmetActivity, android.R.layout.simple_spinner_item, predmeti)
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                odabirPredmet.setAdapter(dataAdapter1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }) //ODABIR GODINA


        odabirPredmet.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val item_position = position.toString()
                val grupe = getGroupsByPredmet(odabirPredmet.selectedItem.toString()).map { it.naziv }.stream().toArray()

                val dataAdapter2 = ArrayAdapter(this@UpisPredmetActivity, android.R.layout.simple_spinner_item, grupe)
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



        dodajPredmetDugme.setOnClickListener{
            if(odabirGodina.selectedItem != null && odabirPredmet.selectedItem != null && odabirGrupa.selectedItem != null){
                //DODATI PREDMET

            }
        }
    }
}