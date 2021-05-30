package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Odgovor

class OdgovorRepository {

    companion object{

        fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
            return emptyList<Odgovor>()
        }

        fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int{
            return -1
        }

    }
}