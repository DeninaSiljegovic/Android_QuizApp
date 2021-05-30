package ba.etf.rma21.projekat.data.obrisati

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.obrisati.pitanjePoSifri
import ba.etf.rma21.projekat.data.obrisati.svaPitanja

class PitanjeRepository {
    companion object{

        fun getAllPitanja(): List<Pitanje> {
            return svaPitanja()
        }

        fun dajPitanjeSaSifrom(s: String): Pitanje {
            return pitanjePoSifri(s)
        }

    }

}