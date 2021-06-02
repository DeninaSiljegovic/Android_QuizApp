package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeKvizViewModel {

//    fun getPitanja(naziv: String, nazivPredmeta: String): List<Pitanje> {
//        return PitanjeKvizRepository.getPitanja(naziv, nazivPredmeta)
//    }

    suspend fun getPitanja(idKviza: Int): List<Pitanje>{
        return PitanjeKvizRepository.getPitanja(idKviza)
    }

}