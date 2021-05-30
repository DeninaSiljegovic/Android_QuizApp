package ba.etf.rma21.projekat.data.obrisati

import ba.etf.rma21.projekat.data.models.Grupa

class GrupaRepository {
    companion object {
        init {
            // TODO: Implementirati
        }

        fun getGroupsByPredmet(naz: String): List<Grupa> {
            return groupsByPredmet(naz)
        }


    }
}