package ba.etf.rma21.projekat.data.obrisati

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.obrisati.PitanjeRepository.Companion.dajPitanjeSaSifrom

fun dajPitanjeKvizoviVezu(): MutableList<PitanjeKviz>{
    return listOf(
            PitanjeKviz("pit1", "Kviz 3", "IM2", 2F, -1),
            PitanjeKviz("pit2", "Kviz 3", "IM2", 2F, -1),
            PitanjeKviz("pit3", "Kviz 3", "IM2", 2F, -1),

            PitanjeKviz("pit4", "Kviz 3", "OOAD", 2F, -1),
            PitanjeKviz("pit5", "Kviz 3", "OOAD", 2F, -1),
            PitanjeKviz("pit6", "Kviz 3", "OOAD", 2F, -1),
            PitanjeKviz("pit7", "Kviz 3", "OOAD", 2F, -1)
    ).toMutableList()
}

fun dajPitanja(navizKviza: String, nazivPredmeta: String): List<Pitanje> {
    val list = dajPitanjeKvizoviVezu().filter { it.kviz == navizKviza && it.predmetNaziv == nazivPredmeta }
    val vrati: MutableList<Pitanje> = mutableListOf()
    for(k : PitanjeKviz in list){
        vrati.add(dajPitanjeSaSifrom(k.naziv))
    }
    return vrati
}

//fun dajOdabraniOdg(n: String): Int{
//    val trazeni = dajPitanjeKvizoviVezu().filter{ it.naziv == n}[0]
//    return trazeni.selectedOdgovor
//}
//
//fun setOdabraniOdg(s: String,kaviz: String, vr: Int){
//    val trazeni = dajPitanjeKvizoviVezu().filter{ it.naziv == s && it.kviz == kaviz}[0]
//    trazeni.selectedOdgovor = vr
//    dajPitanjeKvizoviVezu().removeIf { it.naziv == s && it.kviz == kaviz}
//    dajPitanjeKvizoviVezu().add(trazeni)
//}