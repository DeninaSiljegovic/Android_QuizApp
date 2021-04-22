package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

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