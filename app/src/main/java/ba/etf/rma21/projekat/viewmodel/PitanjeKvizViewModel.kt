package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository

class PitanjeKvizViewModel {

//    fun getPitanja(naziv: String, nazivPredmeta: String): List<Pitanje> {
//        return PitanjeKvizRepository.getPitanja(naziv, nazivPredmeta)
//    }

    suspend fun getPitanja(idKviza: Int): List<Pitanje>{
        return PitanjeKvizRepository.getPitanja(idKviza)
    }
    fun setContext(_context: Context) {
        PitanjeKvizRepository.setContext(_context)
    }

    suspend fun getPitanjaIzBaze(idKviza: Int): List<Pitanje>{
        return PitanjeKvizRepository.getPitanjaIzBaze(idKviza)
    }

}