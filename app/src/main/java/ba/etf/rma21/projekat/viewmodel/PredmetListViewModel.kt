package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class PredmetListViewModel {

    fun getUpisani(): List<Predmet> {
        return PredmetRepository.getUpisani()
    }

    fun getAll(): List<Predmet> {
        return PredmetRepository.getAll()
    }

    fun getPredmetiGodine(god: Int): List<Predmet>{
        return PredmetRepository.getPredmetiGodine(god)
    }

    fun upisiNaPredmet(p: String, g: Int){
        PredmetRepository.upisiNaPredmet(p, g)
    }

    fun getPredmetiNaKojeNijeUpisan(g: Int): List<Predmet> {
        return PredmetRepository.getPredmetiNaKojeNijeUpisan(g)
    }



}