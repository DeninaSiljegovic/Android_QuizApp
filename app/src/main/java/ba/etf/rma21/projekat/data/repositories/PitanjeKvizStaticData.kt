package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.models.PitanjeKviz
import ba.etf.rma21.projekat.data.repositories.PitanjeRepository.Companion.dajPitanjeSaSifrom

fun dajPitanjeKvizoviVezu(): List<PitanjeKviz>{
    return listOf(
            PitanjeKviz("pit1", "Kviz 3", "IM2", 2F),
            PitanjeKviz("pit2", "Kviz 3", "IM2", 2F),
            PitanjeKviz("pit3", "Kviz 3", "IM2", 2F),

            PitanjeKviz("pit4", "Kviz 3", "OOAD", 2F),
            PitanjeKviz("pit5", "Kviz 3", "OOAD", 2F),
            PitanjeKviz("pit6", "Kviz 3", "OOAD", 2F),
            PitanjeKviz("pit7", "Kviz 3", "OOAD", 2F)
    )
}

fun dajPitanja(navizKviza: String, nazivPredmeta: String): List<Pitanje> {
    val list = dajPitanjeKvizoviVezu().filter { it.kviz == navizKviza && it.predmetNaziv == nazivPredmeta }
    val vrati: MutableList<Pitanje> = mutableListOf()
    for(k : PitanjeKviz in list){
        vrati.add(dajPitanjeSaSifrom(k.naziv))
    }
    return vrati
}