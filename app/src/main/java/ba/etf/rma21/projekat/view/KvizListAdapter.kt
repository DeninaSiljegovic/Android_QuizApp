package ba.etf.rma21.projekat.view

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
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class KvizListAdapter (
        private var kvizovi: List<Kviz>,
        private var mSupportFragment: FragmentManager?
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

    private var pitanjeKvizViewModel = PitanjeKvizViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_kviz, parent, false)
        return KvizViewHolder(view)

    }

    override fun getItemCount(): Int = kvizovi.size

    override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
        var uradjen = 0

        //slucaj 1 - DATUM KRAJA JE PROSAO
        if(kvizovi[position].datumKraj.before(GregorianCalendar.getInstance().getTime())){

            //1.1 - bodovi i datum rada su null == CRVENA
            if(kvizovi[position].osvojeniBodovi == null && kvizovi[position].datumRada.equals(GregorianCalendar(1970, 0, 1).getTime())){
                holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
                holder.statusImage.setImageResource(R.drawable.crvena)
                holder.textBodovi.visibility = View.INVISIBLE
            }

            //1.2 - imaju bodovi i datum rada == PLAVA
            else{
                holder.textDatum.text = toSimpleString(kvizovi[position].datumRada)
                holder.statusImage.setImageResource(R.drawable.plava)
                holder.textBodovi.visibility = View.VISIBLE
                holder.textBodovi.text = kvizovi[position].osvojeniBodovi.toString()
                uradjen = 1
            }
        }

        //slucaj 2 - KVIZ JE JOS AKTIVAN
        else{
            //KVIZ JE AKTIVAN I URADJEN == PLAVA
            if(kvizovi[position].osvojeniBodovi != null && kvizovi[position].datumRada != GregorianCalendar(1970, 0, 1).getTime()){
                holder.textDatum.text = toSimpleString(kvizovi[position].datumRada)
                holder.statusImage.setImageResource(R.drawable.plava)
                holder.textBodovi.visibility = View.VISIBLE
                holder.textBodovi.text = kvizovi[position].osvojeniBodovi.toString()
                uradjen = 1
            }
            //aktivan je al nije uradjen == ZELENA
            else if(kvizovi[position].osvojeniBodovi == null && kvizovi[position].datumPocetka.before(GregorianCalendar.getInstance().getTime())){
                holder.textDatum.text = toSimpleString(kvizovi[position].datumKraj)
                holder.statusImage.setImageResource(R.drawable.zelena)
                holder.textBodovi.visibility = View.INVISIBLE
            }
            //datum pocetka i datum kraja jos nisu dosli na red - TEK SE AKTIVIRA == ZUTA
            else if(kvizovi[position].datumPocetka.after(GregorianCalendar.getInstance().getTime())) {
                holder.textDatum.text = toSimpleString(kvizovi[position].datumPocetka)
                holder.statusImage.setImageResource(R.drawable.zuta)
                holder.textBodovi.visibility = View.INVISIBLE
            }
        }

        holder.textPredmet.text = kvizovi[position].nazivPredmeta
        holder.textKvizBr.text = kvizovi[position].naziv
        holder.textTrajanje.text = kvizovi[position].trajanje.toString() + " min"

        holder.itemView.setOnClickListener {
            val tag: String = "pokusaj" +  kvizovi[position].nazivPredmeta + kvizovi[position].naziv
            val bundle = Bundle()
            bundle.putString("imeKviza", kvizovi[position].naziv)
            bundle.putString("imePredmeta", kvizovi[position].nazivPredmeta)
            bundle.putString("uradjen", uradjen.toString()) //odredjuje se na osnovu boje loptice kviza

            val provjeraFragment = mSupportFragment?.findFragmentByTag(tag)

            if(provjeraFragment == null) {
                val pokusajFragment = FragmentPokusaj.newInstance(pitanjeKvizViewModel.getPitanja(kvizovi[position].naziv, kvizovi[position].nazivPredmeta))
                pokusajFragment.arguments = bundle
                val transaction = mSupportFragment?.beginTransaction()
                transaction?.replace(R.id.container, pokusajFragment, tag)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
            else{
                val transaction = mSupportFragment?.beginTransaction()
                transaction?.replace(R.id.container,provjeraFragment, tag)
                transaction?.commit()
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

