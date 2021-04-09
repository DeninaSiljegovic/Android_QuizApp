package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Predmet

class PredmetRepository {
    companion object {
        fun getUpisani(): List<Predmet> {
            return upisani();
        }

        fun getAll(): List<Predmet> {
            return predmeti();
        }

        fun getPredmetiGodine(god: Int): List<Predmet>{
            return getPredmetiGodine(god)
        }

    }

}