package ba.etf.rma21.projekat

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){

    private lateinit var listaKvizova: RecyclerView
    private lateinit var filterKvizova: Spinner
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizListViewModel()
    private lateinit var upisDugme: FloatingActionButton
    //private val br: BroadcastReceiver = ConnectivityBroadcastReceiver()
    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaKvizova = findViewById(R.id.listaKvizova)
        filterKvizova = findViewById(R.id.filterKvizova)
        upisDugme = findViewById(R.id.upisDugme)

        // Spinner Drop down elements
        val categories: MutableList<String> = ArrayList()
        categories.add("Svi moji kvizovi")
        categories.add("Svi kvizovi")
        categories.add("Urađeni kvizovi")
        categories.add("Budući kvizovi")
        categories.add("Prošli kvizovi")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        filterKvizova.setAdapter(dataAdapter);

        //grid layout - da se prikazu fino kao mreza - linear je da idu ----
        listaKvizova.layoutManager = GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        )
        listaKvizovaAdapter = KvizListAdapter(listOf())
        listaKvizova.adapter = listaKvizovaAdapter

        filterKvizova.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                //first,  we have to retrieve the item position as a string
                // then, we can change string value into integer
                val item_position = position.toString()
                val positonInt = Integer.valueOf(item_position)
                if(positonInt  == 0)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
                else if(positonInt == 1)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll())
                else if(positonInt == 2) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getDone())
                else if(positonInt == 3) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getFuture())
                else if(positonInt == 4) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getNotTaken())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll()) }
        })


        //intent
        upisDugme.setOnClickListener{
            val intent = Intent(this, UpisPredmetActivity::class.java)
            startActivity(intent)
        }

    }



}

