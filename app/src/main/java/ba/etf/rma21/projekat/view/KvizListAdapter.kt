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
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import ba.etf.rma21.projekat.viewmodel.PredmetIGrupaViewModel
import ba.etf.rma21.projekat.viewmodel.TakeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class KvizListAdapter (
        private var kvizovi: List<Kviz>,
        private var mSupportFragment: FragmentManager?
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

    private var pitanjeKvizViewModel = PitanjeKvizViewModel()
    private var takeKvizViewModel = TakeKvizViewModel()
    private var predmetIGrupaViewModel = PredmetIGrupaViewModel()
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

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

        //slucaj 1 - DATUM KRAJA JE PROSAO
        if(kvizovi[position].datumKraj.before(GregorianCalendar.getInstance().time)){

            //1.1 - bodovi i datum rada su null == CRVENA
            if(getBodovi(kvizovi[position]) == null  && getDatumRada(kvizovi[position])!! == GregorianCalendar(1970, 0, 1).time){
                holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
                holder.statusImage.setImageResource(R.drawable.crvena)
                holder.textBodovi.visibility = View.INVISIBLE
            }

            //1.2 - imaju bodovi i datum rada == PLAVA
            else{
                holder.textDatum.text = toSimpleString(getDatumRada(kvizovi[position]))
                holder.statusImage.setImageResource(R.drawable.plava)
                holder.textBodovi.visibility = View.VISIBLE
                holder.textBodovi.text = getBodovi(kvizovi[position]).toString()
                uradjen = 1
            }
        }

        //slucaj 2 - KVIZ JE JOS AKTIVAN
        else{
            //2.1 KVIZ JE AKTIVAN I URADJEN == PLAVA
            if(getBodovi(kvizovi[position]) != null &&  getDatumRada(kvizovi[position])!! != GregorianCalendar(1970, 0, 1).time){
                holder.textDatum.text = toSimpleString( getDatumRada(kvizovi[position])!! )
                holder.statusImage.setImageResource(R.drawable.plava)
                holder.textBodovi.visibility = View.VISIBLE
                holder.textBodovi.text = getBodovi(kvizovi[position]).toString()
                uradjen = 1
            }

            //2.2 aktivan je al nije uradjen == ZELENA
            else if(getBodovi(kvizovi[position]) == null && kvizovi[position].datumPocetka.before(GregorianCalendar.getInstance().time)){
                holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
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

        holder.textPredmet.text = imenaPredmeta(kvizovi[position])
        holder.textKvizBr.text = kvizovi[position].naziv
        holder.textTrajanje.text = kvizovi[position].trajanje.toString() + " min"

        //OTVARANJE FRAGMENTA POKUSAJ
        scope.launch {
            if (pitanjeKvizViewModel.getPitanja(kvizovi[position].id).isNotEmpty()) {

                holder.itemView.setOnClickListener {
                    val tag: String = "pokusaj" + imenaPredmeta(kvizovi[position]) + kvizovi[position].naziv
                    val bundle = Bundle()
                    bundle.putString("imeKviza", kvizovi[position].naziv)
                    bundle.putString("imePredmeta", imenaPredmeta(kvizovi[position]))
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

    private fun getDatumRada(kviz: Kviz): Date? {
        var vrati: Date? = null
        scope.launch { vrati = takeKvizViewModel.getPokusajKviza(kviz.id)[0].datumRada }
        return vrati
    }

    private fun getBodovi(kviz: Kviz): Float? {
        var vrati: Float? = null
        scope.launch { vrati = takeKvizViewModel.getPokusajKviza(kviz.id)[0].osvojeniBodovi }
        return vrati
    }

    private fun imenaPredmeta(kviz: Kviz): String{
        var grupe : List<Grupa> = listOf()
        scope.launch { grupe = predmetIGrupaViewModel.getGroupsZaKviz(kviz.id) }

        var sviPredmeti: MutableList<String> = mutableListOf()
        for(g in grupe){
            scope.launch { sviPredmeti.add(predmetIGrupaViewModel.getPredmetSaId(g.PredmetId).naziv) }
        }

        val distinct = sviPredmeti.toSet().toList()
        var vrati: String = ""

        for(d in distinct){
            vrati += d
        }

        return vrati
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

