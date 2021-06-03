package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorBody
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OdgovorRepository {

    companion object{

        suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
            return withContext(Dispatchers.IO){
                val pokusaj = TakeKvizRepository.getPocetiKvizovi().find{it.KvizId == idKviza}
                val response = ApiConfig.retrofit.getOdgovoriKviz(getHash(), pokusaj!!.id)

                when(val responseBody = response.body()){
                    is List<Odgovor> -> return@withContext responseBody
                    else -> return@withContext listOf<Odgovor>()
                }

            }!!
        }

        //u funkciju se salje kviz_id iz fragmenta pokusaj - da bi nasli odgovarajuci KvizTaken mora KvizId == poslanom id Kviz-a
        suspend fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int{
            return withContext(Dispatchers.IO){
                val pokusaj = TakeKvizRepository.getPocetiKvizovi().find{it.id == idKvizTaken}
                var bod = pokusaj?.osvojeniBodovi
                if(bod == null) bod = 0F

                //pr da li je tacno odgovoreno pa dodati bodove
                val pit = PitanjeKvizRepository.getPitanja(pokusaj!!.KvizId).find{ it.id == idPitanje}
                if(pit?.tacan == odgovor) bod += 1


                val response = ApiConfig.retrofit.postaviOdgovorKviz(getHash(), pokusaj!!.id, OdgovorBody(odgovor, idPitanje, bod))

                when(response.body()){
                    is Odgovor -> return@withContext 5 //todo OVO VRACA UKUPNE BODOVE NA KVIZU
                    else -> return@withContext  -1
                }
            }!!
        }

    }
}