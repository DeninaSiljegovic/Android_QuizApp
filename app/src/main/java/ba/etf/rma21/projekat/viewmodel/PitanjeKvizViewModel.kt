package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeKvizViewModel {

    fun getPitanja(naziv: String, nazivPredmeta: String): List<Pitanje> {
        return PitanjeKvizRepository.getPitanja(naziv, nazivPredmeta)
    }

//
//    fun setSelectedAnswer(s: String, poz: Int){
//        PitanjeKvizRepository.setSelectedAnswer(s, poz)
//    }
//
//    fun getSelectedAnswer(s: String): Int{
//        return PitanjeKvizRepository.getSelectedAnswer(s)
//    }

}