package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getGrupe


class PredmetIGrupaViewModel {

    /* da bi dobila grupe za predmet based on predmet name - uzeti sve predmete
    * i filtrirati ih da nadjem zeljeni predmet i njegov id te tad pozvat getGrupeZaPredmet(idPredmeta)*/

    suspend fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
        val predmeti = PredmetIGrupaRepository.getPredmeti()
        for(p in predmeti){
            if(p.naziv == nazivPredmeta) return PredmetIGrupaRepository.getGrupeZaPredmet(p.id)
        }
        return emptyList()
    }

    suspend fun getGroupsZaKviz(idKviza: Int): List<Grupa> {
        return PredmetIGrupaRepository.getGrupeZaKviz(idKviza)
    }

    suspend fun getAllGrupe(): List<Grupa>{
        return getGrupe()
    }

    suspend fun upisiUGrupu(id: Int): Boolean{
        return PredmetIGrupaRepository.upisiUGrupu(id)
    }

    suspend fun getUpisaneGrupe(): List<Grupa>{
        return PredmetIGrupaRepository.getUpisaneGrupe()
    }


    suspend fun getAllPredmeti(): List<Predmet> {
        return PredmetIGrupaRepository.getPredmeti()
    }

    suspend fun getPredmetSaId(predmetId : Int): Predmet {
        return PredmetIGrupaRepository.getPredmetSaId(predmetId)
    }

    /* sve predmete uzet pa filtrirati na osnovu godine*/
    suspend fun getPredmetiGodine(god: Int): List<Predmet>{
        val predmeti = PredmetIGrupaRepository.getPredmeti()
        val vratiPredmete: MutableList<Predmet> = mutableListOf()

        for(p in predmeti){
            if(p.godina == god) vratiPredmete.add(p)
        }

        return vratiPredmete
    }

    /* uzet sve predmete sa trazene godine + sve upisane grupe
    * ukoliko se poklapa PredmetId grupe sa Id predmeta - removat
    * ono sto ostane je lista predmeta za zeljenu godinu na kojoj nije upisan  */

    suspend fun getPredmetiNaKojeNijeUpisan(g: Int): List<Predmet> {
        val upisaneGrupe = getUpisaneGrupe()
        val sviPredmeti = getPredmetiGodine(g)
        val predmeti: MutableList<Predmet> = getPredmetiGodine(g).toMutableList()

        for(u in upisaneGrupe){
            for(p in sviPredmeti){
                if(u.PredmetId == p.id) predmeti.remove(p)
            }
        }
        return predmeti
    }

//    fun upisi(p: String, g: Int){
//        PredmetRepository.upisiNaPredmet(p, g)
//    }  NA OSNOVU GRUPA GLEDATI GDJE JE KORISNIK UPISAN I KOJI PREDMETI SU DOSTUPNI ETC



}