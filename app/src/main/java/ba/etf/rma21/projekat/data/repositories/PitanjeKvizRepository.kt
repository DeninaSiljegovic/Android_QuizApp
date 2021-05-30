package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.obrisati.dajPitanja

class PitanjeKvizRepository {

    companion object {
        //ovu obrisi later
        fun getPitanja(navizKviza: String, nazivPredmeta: String): List<Pitanje> {
            return dajPitanja(navizKviza, nazivPredmeta).toList()
        }

        //OVA OSTAJE FUNKCIJA
        fun getPitanja(idKviza:Int):List<Pitanje>{
            return emptyList<Pitanje>()
        }

    }

}
