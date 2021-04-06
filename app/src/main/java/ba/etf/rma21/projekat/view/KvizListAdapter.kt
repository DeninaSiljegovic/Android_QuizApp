package ba.etf.rma21.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

class KvizListAdapter (
        private var kvizovi: List<Kviz>,
        private val onItemClicked: (movie:Kviz) -> Unit
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_kviz, parent, false)
        return KvizViewHolder(view)
    }

    override fun getItemCount(): Int = kvizovi.size


    override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {

        //checkiranje datuma da bi se odresio status i koji se prikazuje
        val context: Context = holder.statusImage.getContext()

        //slucaj 1 - DATUM KRAJA JE PROSAO
        if(kvizovi[position].datumKraj.before(GregorianCalendar.getInstance().getTime())){

            //1.1 - bodovi i datum rada su null == CRVENA
            if(kvizovi[position].osvojeniBodovi == null && kvizovi[position].datumRada == null){
                holder.textDatum.text = kvizovi[position].datumKraj.toString()
                var id: Int = context.getResources()
                        .getIdentifier("crvena", "drawable", context.getPackageName())
            }

            //1.2 - imaju bodovi i datum rada == PLAVA
            else{
                holder.textDatum.text = kvizovi[position].datumRada.toString()
                var id: Int = context.getResources()
                        .getIdentifier("plava", "drawable", context.getPackageName())
            }

        }

        //slucaj 2 - KVIZ JE JOS AKTIVAN
        else{

            //datum pocetka i datum kraja jos nisu dosli na red - TEK SE AKTIVIRA == ZUTA
            if(kvizovi[position].datumPocetka.after(GregorianCalendar.getInstance().getTime())) {
                holder.textDatum.text = kvizovi[position].datumPocetka.toString()
                var id: Int = context.getResources()
                        .getIdentifier("zuta", "drawable", context.getPackageName())
            }

            //last case - aktivan je al nije uradjen == ZELENA
            else{
                holder.textDatum.text = kvizovi[position].datumKraj.toString()
                var id: Int = context.getResources()
                        .getIdentifier("zelena", "drawable", context.getPackageName())
            }
        }



        holder.textPredmet.text = kvizovi[position].nazivPredmeta
        holder.textKvizBr.text = kvizovi[position].naziv
        holder.textTrajanje.text = kvizovi[position].trajanje.toString() + " min" //nesto wrong check it
        holder.textBodovi.text = kvizovi[position].osvojeniBodovi.toString()

        holder.itemView.setOnClickListener{ onItemClicked(kvizovi[position]) }
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