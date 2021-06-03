 package ba.etf.rma21.projekat.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.viewmodel.OdgovorViewModel
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetIGrupaViewModel
import ba.etf.rma21.projekat.viewmodel.TakeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class KvizListAdapter (
        private var kvizovi: List<Kviz>,
        private var mSupportFragment: FragmentManager?
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

    private var pitanjeKvizViewModel = PitanjeKvizViewModel()
    private var takeKvizViewModel = TakeKvizViewModel()
    private var predmetIGrupaViewModel = PredmetIGrupaViewModel()
    private var odgovorViewModel = OdgovorViewModel()

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private var imePredmeta: String =""
    private var datumRada: Date? = null
    private var bodoviKviz: Float? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_kviz, parent, false)
        return KvizViewHolder(view)

    }

    override fun getItemCount(): Int = kvizovi.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
        var uradjen = 0
        var result: Deferred<Unit>

        var vrati1: List<KvizTaken> = listOf()
        scope.launch {
            result = async {
                vrati1 = takeKvizViewModel.getPokusajKviza(kvizovi[position].id)
            }
            result.await()
            if (vrati1.size > 0) datumRada = vrati1[0].datumRada
            else datumRada = null
        }


        var vrati2: List<KvizTaken> = listOf()
        scope.launch {
            result = async {
                vrati2 =  takeKvizViewModel.getPokusajKviza(kvizovi[position].id)
            }
            result.await()
            if (vrati2.size > 0) bodoviKviz = vrati2[0].osvojeniBodovi
            else bodoviKviz = null
        }

        var odgovori: List<Odgovor> = listOf()
        var pitanja: List<Pitanje> = listOf()
        scope.launch {
            result = async {
                val pokusajKviza = takeKvizViewModel.getPocetiKvizovi().find { it.KvizId == kvizovi[position].id }

                if (pokusajKviza != null) {
                    println("Ima pokusaj kviza")
                    odgovori = odgovorViewModel.getOdgovoriKviz(kvizovi[position].id)
                    pitanja = pitanjeKvizViewModel.getPitanja(kvizovi[position].id)
                    println("PUUJ " + "odgovora: " + odgovori.size + " pitanja: " + pitanja.size)
                }
            }
            result.await()


            println("odgovora: " + odgovori.size + " pitanja: " + pitanja.size)

            if (odgovori.isNotEmpty() && pitanja.isNotEmpty() && odgovori.size == pitanja.size) {
                println("ZAVRSEN KVIZ")
                println("odgovora: " + odgovori.size + " pitanja: " + pitanja.size)
                holder.textDatum.text = toSimpleString(datumRada)
                holder.statusImage.setImageResource(R.drawable.plava)
                holder.textBodovi.visibility = View.VISIBLE
                holder.textBodovi.text = bodoviKviz.toString()
                uradjen = 1
            }
        }

        //slucaj 1 - DATUM KRAJA JE PROSAO
        if(kvizovi[position].datumKraj != null && kvizovi[position].datumKraj.before(GregorianCalendar.getInstance().time)){
            //1.1 - bodovi i datum rada su null == CRVENA
            if(bodoviKviz == null  && datumRada == null){
                if(kvizovi[position].datumKraj != null) holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
                else holder.textDatum.text = "inf"
                holder.statusImage.setImageResource(R.drawable.crvena)
                holder.textBodovi.visibility = View.INVISIBLE
            }
        }

        //slucaj 2 - KVIZ JE JOS AKTIVAN
        else{
            //2.2 aktivan je al nije uradjen == ZELENA
            if(bodoviKviz == null && kvizovi[position].datumPocetka.before(GregorianCalendar.getInstance().time)){
                if(kvizovi[position].datumKraj != null) holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
                else holder.textDatum.text = "inf"
                holder.statusImage.setImageResource(R.drawable.zelena)
                holder.textBodovi.visibility = View.INVISIBLE
            }

            //2.3 datum pocetka i datum kraja jos nisu dosli na red - TEK SE AKTIVIRA == ZUTA
            else if(kvizovi[position].datumPocetka.after(GregorianCalendar.getInstance().time)) {
                holder.textDatum.text = toSimpleString(kvizovi[position].datumPocetka)
                holder.statusImage.setImageResource(R.drawable.zuta)
                holder.textBodovi.visibility = View.INVISIBLE
            }
        }

        var grupe : List<Grupa> = listOf()
        var vrati: String = ""
        scope.launch {
            result = async {
                grupe = predmetIGrupaViewModel.getGroupsZaKviz(kvizovi[position].id)
                var sviPredmeti: MutableList<String> = mutableListOf()

                for (g in grupe) {
                    sviPredmeti.add(predmetIGrupaViewModel.getPredmetSaId(g.PredmetId).naziv)
                }
                val distinct = sviPredmeti.toSet().toList()

                for (d in distinct) {
                    vrati += d
                    if(distinct.size > 1 && d != distinct[distinct.size -1]) vrati += ","
                }
            }
            result.await()
            imePredmeta = vrati
            holder.textPredmet.text = vrati
        }
        holder.textKvizBr.text = kvizovi[position].naziv
        holder.textTrajanje.text = kvizovi[position].trajanje.toString() + " min"

        //OTVARANJE FRAGMENTA POKUSAJ
        scope.launch {
            if (pitanjeKvizViewModel.getPitanja(kvizovi[position].id).isNotEmpty()) {

                holder.itemView.setOnClickListener {
                    val tag: String = "pokusaj" + imePredmeta + kvizovi[position].naziv
                    val bundle = Bundle()
                    bundle.putString("imeKviza", kvizovi[position].naziv)
                    bundle.putString("imePredmeta", imePredmeta)
                    bundle.putString("uradjen", uradjen.toString()) //odredjuje se na osnovu boje loptice kviza

                    val provjeraFragment = mSupportFragment?.findFragmentByTag(tag)

                    scope.launch {
                        if (provjeraFragment == null) {
                            val pokusajFragment = FragmentPokusaj.newInstance(pitanjeKvizViewModel.getPitanja(kvizovi[position].id), kvizovi[position].id)
                            pokusajFragment.arguments = bundle
                            val transaction = mSupportFragment?.beginTransaction()
                            transaction?.replace(R.id.container, pokusajFragment, tag)
                            transaction?.addToBackStack(null)
                            transaction?.commit()
                        } else {
                            provjeraFragment.arguments = bundle
                            val transaction = mSupportFragment?.beginTransaction()
                            transaction?.replace(R.id.container, provjeraFragment, tag)
                            transaction?.commit()
                        }
                    }
                }
            }
        }
    }


    fun updateKvizove(kvizovi: List<Kviz>){
        //sortirati po datumima
        //this.kvizovi = kvizovi
        this.kvizovi = kvizovi.sortedByDescending { it.datumPocetka }
        notifyDataSetChanged()
    }

    fun toSimpleString(date: Date?) : String {
        val format = SimpleDateFormat("dd.MM.yyy")
        return format.format(date)
    }

    inner class KvizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPredmet: TextView = itemView.findViewById(R.id.textPredmet)
        val textKvizBr: TextView = itemView.findViewById(R.id.textKvizBr)
        val textDatum: TextView = itemView.findViewById(R.id.textDatum)
        val textBodovi: TextView = itemView.findViewById(R.id.textBodovi)
        val textTrajanje: TextView = itemView.findViewById(R.id.textTrajanje)
        val statusImage: ImageView = itemView.findViewById(R.id.statusImage)
    }

}

