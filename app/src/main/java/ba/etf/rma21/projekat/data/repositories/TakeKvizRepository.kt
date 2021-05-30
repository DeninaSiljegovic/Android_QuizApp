package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import java.util.*

class TakeKvizRepository {

    companion object{

        fun zapocniKviz(idKviza:Int): KvizTaken {
            return KvizTaken(12, "Denina", 12, GregorianCalendar(2021, 2, 10).getTime())
        }

        fun getPocetiKvizovi():List<KvizTaken>{
            return emptyList()
        }

    }

}