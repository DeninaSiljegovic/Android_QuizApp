package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

class PitanjeKvizRepository {

    companion object {
        fun getPitanja(navizKviza: String, nazivPredmeta: String): List<Pitanje> {
            return dajPitanja(navizKviza, nazivPredmeta)
        }
    }

}
