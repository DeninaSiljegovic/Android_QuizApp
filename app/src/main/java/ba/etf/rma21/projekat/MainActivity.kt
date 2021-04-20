package ba.etf.rma21.projekat


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.viewmodel.PredmetViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigation : BottomNavigationView
//    private lateinit var listaKvizova: RecyclerView
//    private lateinit var filterKvizova: Spinner
//    private lateinit var listaKvizovaAdapter: KvizListAdapter
//    private var kvizListViewModel = KvizViewModel()
    private var predmetListViewModel = PredmetViewModel()
    private lateinit var upisDugme: FloatingActionButton
    private var odabranaGod : Int = 0


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val predmetiFragments = FragmentPredmeti.newInstance()
                openFragment(predmetiFragments)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation= findViewById(R.id.bottomNav)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //Defaultni fragment
        bottomNavigation.selectedItemId= R.id.kvizovi
        val kvizFragment = FragmentKvizovi.newInstance()
        openFragment(kvizFragment)

//        // Spinner Drop down elements
//        val categories: MutableList<String> = ArrayList()
//        categories.add("Svi moji kvizovi")
//        categories.add("Svi kvizovi")
//        categories.add("Urađeni kvizovi")
//        categories.add("Budući kvizovi")
//        categories.add("Prošli kvizovi")
//
//        // Creating adapter for spinner
//        //val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
//        val dataAdapter:ArrayAdapter<String> = object: ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_spinner_dropdown_item,
//            categories
//        ){
//            override fun getDropDownView(
//                position: Int,
//                convertView: View?,
//                parent: ViewGroup
//            ): View {
//                val view: TextView = super.getDropDownView(
//                    position,
//                    convertView,
//                    parent
//                ) as TextView
//                // set item text size
//                view.setTextSize(TypedValue.COMPLEX_UNIT_SP,15F)
//
//                // set selected item style
//                if (position == filterKvizova.selectedItemPosition){
//                    view.background = ColorDrawable(Color.parseColor("#E695B1"))
//                    view.setTextColor(Color.parseColor("#2E2D88"))
//                }
//                return view
//            }
//        }
//
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // attaching data adapter to spinner
//        filterKvizova.setAdapter(dataAdapter);

//        //grid layout - da se prikazu fino kao mreza - linear je da idu ----
//        listaKvizova.layoutManager = GridLayoutManager(
//                this,
//                2,
//                GridLayoutManager.VERTICAL,
//                false
//        )
//        listaKvizovaAdapter = KvizListAdapter(listOf())
//        listaKvizova.adapter = listaKvizovaAdapter
//
//        filterKvizova.setOnItemSelectedListener(object : OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
//                //first,  we have to retrieve the item position as a string
//                // then, we can change string value into integer
//                val item_position = position.toString()
//                val positonInt = Integer.valueOf(item_position)
//                if(positonInt  == 0)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
//                else if(positonInt == 1)  listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll())
//                else if(positonInt == 2) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyDone())
//                else if(positonInt == 3) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyFuture())
//                else if(positonInt == 4) listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyNotTaken())
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) { listaKvizovaAdapter.updateKvizove(kvizListViewModel.getAll()) }
//        })

//        upisDugme.setOnClickListener{
//            upisDugmeClicked(it)
//        }
    }//onCreate end


//     //"Go to Second Activity" button click
//    fun upisDugmeClicked(view: View?) {
//        val intent = Intent(this, UpisPredmetActivity::class.java)
//         intent.putExtra("selectedYear", odabranaGod.toString())
//        startActivityForResult(intent, 0)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // check that it is the SecondActivity with an OK result
//            if (resultCode == RESULT_OK) {
//                odabranaGod = data?.getStringExtra("selectedYear").toString().toInt()
//                kvizListViewModel.upisiKviz(data?.getStringExtra("grupa").toString()) //RADI OK
//                predmetListViewModel.upisi(data?.getStringExtra("predmet").toString(), data?.getStringExtra("godina").toString().toInt()+1)
//                listaKvizovaAdapter.updateKvizove(kvizListViewModel.getMyKvizes())
//                filterKvizova.setSelection(0)
//            }
//    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}//main activiry closed

