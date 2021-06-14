 package ba.etf.rma21.projekat.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.viewmodel.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

 class KvizListAdapter (
         private var kvizovi: List<Kviz>,
         private var mSupportFragment: FragmentManager?,
         private var spinnerTekst: String,
         private var context: Context
 ) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

     private var pitanjeKvizViewModel = PitanjeKvizViewModel()
     private var takeKvizViewModel = TakeKvizViewModel()
     private var predmetIGrupaViewModel = PredmetIGrupaViewModel()
     private var odgovorViewModel = OdgovorViewModel()
     private var grupaKvizViewModel = GrupaKvizViewModel()

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

     @RequiresApi(Build.VERSION_CODES.O)
     @SuppressLint("SetTextI18n")
     override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
         var uradjen = 0
         var result: Deferred<Unit>

         if(kvizovi.isNotEmpty()) {

             var vrati1: List<KvizTaken> = listOf()
             scope.launch {
                 result = async {
                     vrati1 = takeKvizViewModel.getPokusajKviza(kvizovi[position].id)
                 }
                 result.await()
                 if (vrati1.isNotEmpty()) datumRada = Date.from(LocalDate.parse(vrati1[0].datumRada, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant())
                 else datumRada = null

                 if (vrati1.isNotEmpty()) bodoviKviz = vrati1[0].osvojeniBodovi
                 else bodoviKviz = null
             }


//             var vrati2: List<KvizTaken> = listOf()
//             scope.launch {
//                 result = async {
//                     vrati2 = takeKvizViewModel.getPokusajKviza(kvizovi[position].id)
//                 }
//                 result.await()
//                 if (vrati2.isNotEmpty()) bodoviKviz = vrati2[0].osvojeniBodovi
//                 else bodoviKviz = null
//                 println("Procitano je da kviz ima " + bodoviKviz + " bodova")
//             }

             var odgovori: List<Odgovor> = listOf()
             var pitanja: List<Pitanje> = listOf()
             var percent: Float = 0F
             var tacnoOdg: Float = 0F
             scope.launch {
                 result = async {
                     val pokusajKviza = takeKvizViewModel.getPocetiKvizovi()?.find { it.KvizId == kvizovi[position].id }

                     if (pokusajKviza != null) {
                         println("Ima pokusaj kviza")

                         if(spinnerTekst != "Svi kvizovi"){
                             odgovori = odgovorViewModel.getOdgovoriKviz(kvizovi[position].id)
                             pitanja = pitanjeKvizViewModel.getPitanjaIzBaze(kvizovi[position].id)
                         }
                         else {
                             odgovori = odgovorViewModel.getOdgovoriKviz(kvizovi[position].id)
                             pitanja = pitanjeKvizViewModel.getPitanja(kvizovi[position].id)
                         }
                         println("PUUJ " + "odgovora: " + odgovori.size + " pitanja: " + pitanja.size)

                         for (p in pitanja) {
                             val odg = odgovori.find { it.PitanjeId == p.id }
                             if (odg != null) {
                                 if (odg.odgovoreno == p.tacan) tacnoOdg += 1
                             }
                         }
                         percent = tacnoOdg / (pitanja.size)
                     }
                 }
                 result.await()
                 println("odgovora: " + odgovori.size + " pitanja: " + pitanja.size)

                 if (odgovori.isNotEmpty() && pitanja.isNotEmpty() && odgovori.size == pitanja.size) {
                     println("ZAVRSEN KVIZ")
                     println("odgovora: " + odgovori.size + " pitanja: " + pitanja.size)
                     if (datumRada != null) holder.textDatum.text = toSimpleString(datumRada)
                     else holder.textDatum.text = "inf"
                     holder.statusImage.setImageResource(R.drawable.plava)
                     holder.textBodovi.visibility = View.VISIBLE
                     holder.textBodovi.text = percent.toString()
                     uradjen = 1
                 }
             }

             //slucaj 1 - DATUM KRAJA JE PROSAO
             if (kvizovi[position].datumKraj != null && Date.from(LocalDate.parse(kvizovi[position].datumKraj, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()).before(GregorianCalendar.getInstance().time)) {
                 //1.1 - bodovi i datum rada su null == CRVENA
                 if (bodoviKviz == null && datumRada == null) {
                     if (kvizovi[position].datumKraj != null) holder.textDatum.text = kvizovi[position].datumKraj
                     else holder.textDatum.text = "inf"
                     holder.statusImage.setImageResource(R.drawable.crvena)
                     holder.textBodovi.visibility = View.INVISIBLE
                 }
             }

             //slucaj 2 - KVIZ JE JOS AKTIVAN
             else {
                 //2.2 aktivan je al nije uradjen == ZELENA
                 if (bodoviKviz == null && Date.from(LocalDate.parse(kvizovi[position].datumPocetka, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()).before(GregorianCalendar.getInstance().time)) {
                     println("Datum: " + kvizovi[position].datumKraj)
                     if (kvizovi[position].datumKraj != null) holder.textDatum.text = kvizovi[position].datumKraj
                     else holder.textDatum.text = kvizovi[position].datumPocetka.replace("2021", "2023")
                     holder.statusImage.setImageResource(R.drawable.zelena)
                     holder.textBodovi.visibility = View.INVISIBLE
                 }

                 //2.3 datum pocetka i datum kraja jos nisu dosli na red - TEK SE AKTIVIRA == ZUTA
                 else if (Date.from(LocalDate.parse(kvizovi[position].datumPocetka, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()).after(GregorianCalendar.getInstance().time)) {
                     holder.textDatum.text = kvizovi[position].datumPocetka
                     holder.statusImage.setImageResource(R.drawable.zuta)
                     holder.textBodovi.visibility = View.INVISIBLE
                 }
             }

             var grupe: MutableList<Grupa> = mutableListOf()
             var vrati: String = ""
             scope.launch {
                 result = async {

                     if(spinnerTekst != "Svi kvizovi"){
                         grupaKvizViewModel.setContext(context)
                         val idList = grupaKvizViewModel.getGrupeZaKvizBaza(kvizovi[position].id).map { grupaKviz ->grupaKviz.grupaId  }
                         predmetIGrupaViewModel.setContext(context)
                         for(id in idList){
                             val group = predmetIGrupaViewModel.getGroup(id)

                             if(group == null)
                                 grupe.add(predmetIGrupaViewModel.getGroupsZaKviz(kvizovi[position].id)!!.find {grupa1 -> grupa1.id == id && !grupe.contains(grupa1) }!!)
                             else grupe.add(group)
                         }
                     }
                     else{
                         grupe = predmetIGrupaViewModel.getGroupsZaKviz(kvizovi[position].id) as MutableList<Grupa>
                     }
                     var sviPredmeti: MutableList<String> = mutableListOf()

                     for (g in grupe) {

                         if(spinnerTekst != "Svi kvizovi") {
                             predmetIGrupaViewModel.setContext(context)
                             sviPredmeti.add(predmetIGrupaViewModel.getPredmetSaId(g.PredmetId).naziv)
                         }
                         else {
                             sviPredmeti.add(predmetIGrupaViewModel.getPredmetSaIdIzBaze(g.PredmetId).naziv)
                         }
                     }
                     val distinct = sviPredmeti.toSet().toList()

                     for (d in distinct) {
                         vrati += d
                         if (distinct.size > 1 && d != distinct[distinct.size - 1]) vrati += ","
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
                 var pitanja = listOf<Pitanje>()

                 if(spinnerTekst!="Svi kvizovi" ){
                     pitanjeKvizViewModel.setContext(context)
                     pitanja = pitanjeKvizViewModel.getPitanjaIzBaze(kvizovi[position].id)
                 }
                 else
                     pitanja = pitanjeKvizViewModel.getPitanja(kvizovi[position].id)

                 if (pitanja.isNotEmpty()) {
                     holder.itemView.setOnClickListener {
                         val tag: String = "pokusaj" + imePredmeta + kvizovi[position].naziv
                         val bundle = Bundle()
                         bundle.putString("imeKviza", kvizovi[position].naziv)
                         bundle.putString("imePredmeta", imePredmeta)
                         bundle.putString("uradjen", uradjen.toString()) //odredjuje se na osnovu boje loptice kviza
                         bundle.putString("tacnost", percent.toString())

                         val provjeraFragment = mSupportFragment?.findFragmentByTag(tag)

                         scope.launch {
                             if (provjeraFragment == null) {
                                 val pokusajFragment = FragmentPokusaj.newInstance(pitanja, kvizovi[position].id)
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

