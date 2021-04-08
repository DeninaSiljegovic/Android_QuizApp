package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa

class GrupaRepository {
    companion object {
        init {
            // TODO: Implementirati
        }

        fun getGroupsByPredmet(naz: String): List<Grupa> {
            var lista : List<Grupa> = dajGrupe()
            //lista.filter{ lista.any{it.nazivPredmeta == naziv}} //NOT SURE IF THIS WORKS
            val vrati = lista.filter { it.nazivPredmeta == naz }
            return vrati
        }
    }
}