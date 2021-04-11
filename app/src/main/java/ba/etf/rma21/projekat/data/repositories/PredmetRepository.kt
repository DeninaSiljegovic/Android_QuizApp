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

        fun getPredmetiGodine(god: Int): List<Predmet> {
            return predmetiGodine(god)
        }

        fun getPredmetiNaKojeNijeUpisan(g: Int): List<Predmet> {
            return predmetiNaKojeNijeUpisan(g)
        }

        fun upisiNaPredmet(p: String, g: Int){
            upisinaPredmet(p, g)
        }

    }

}