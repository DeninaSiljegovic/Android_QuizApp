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
            var predmeti:  List<Predmet> = getAll()
            predmeti = predmeti.filter{ predmeti.any{it.godina == god+1}}
            return predmeti
        }

    }

}