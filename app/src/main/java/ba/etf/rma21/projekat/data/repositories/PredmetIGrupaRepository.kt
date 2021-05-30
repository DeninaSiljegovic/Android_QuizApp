package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

class PredmetIGrupaRepository {

    fun getPredmeti():List<Predmet>{
        return emptyList()
    }

    fun getGrupe():List<Grupa>{
        return emptyList()
    }

    fun getGrupeZaPredmet(idPredmeta:Int):List<Grupa>{
        return emptyList()
    }

    fun upisiUGrupu(idGrupa:Int):Boolean{
        return false
    }

    fun getUpisaneGrupe():List<Grupa>{
        return emptyList()
    }

}