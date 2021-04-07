package ba.etf.rma21.projekat

import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var listaKvizova: RecyclerView
    private lateinit var filterKvizova: Spinner
    private lateinit var listaKvizovaAdapter: KvizListAdapter
    private var kvizListViewModel = KvizListViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaKvizova = findViewById(R.id.listaKvizova)
        //spinner may God help me to set up he he he

        listaKvizova.layoutManager = GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        )
        listaKvizovaAdapter = KvizListAdapter(listOf())
        listaKvizova.adapter = listaKvizovaAdapter
        listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll())


    }
}

