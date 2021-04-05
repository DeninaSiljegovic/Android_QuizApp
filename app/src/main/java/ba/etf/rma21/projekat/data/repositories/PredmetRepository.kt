package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Predmet

class PredmetRepository {
    companion object {
        fun getUpisani(): List<Predmet> {
            // TODO: Implementirati return emptyList()
            return upisani();
        }

        fun getAll(): List<Predmet> {
            // TODO: Implementirati return emptyList()
            return predmeti();
        }
        // TODO: Implementirati i ostale potrebne metode
    }

}