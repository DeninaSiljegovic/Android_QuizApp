package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository.Companion.getPitanjaIzBaze
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository


class OdgovorViewModel {

    suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
        return OdgovorRepository.getOdgovoriKviz(idKviza)
    }

    suspend fun postaviOdgovor(idKvizTaken:Int, idPitanje:Int, odgovor:Int):Int{
        return OdgovorRepository.postaviOdgovor(idKvizTaken, idPitanje, odgovor)
    }

    fun setContext(_context: Context) {
        OdgovorRepository.setContext(_context)
    }

    suspend fun getOdgovoreZaKvizIzBaze(idKviz: Int): List<Odgovor> {
        return OdgovorRepository.getOdgovoreZaKvizIzBaze(idKviz)
    }

    suspend fun predajOdgovore(idKviza: Int){
        return OdgovorRepository.predajOdgovore(idKviza)
    }


}