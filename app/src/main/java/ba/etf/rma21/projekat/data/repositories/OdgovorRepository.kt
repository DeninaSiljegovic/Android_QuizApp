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
                val response = ApiConfig.retrofit.getOdgovoriKviz(getHash(), idKviza)

                when(val responseBody = response.body()){
                    is List<Odgovor> -> return@withContext responseBody
                    else -> return@withContext listOf<Odgovor>()
                }

            }!!
        }

        //?? KAKO OVO
        suspend fun postaviOdgovorKviz(idKvizTaken:Int,idPitanje:Int,odgovor:Int):Int{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.postaviOdgovorKviz(AccountRepository.getHash(), idKvizTaken, OdgovorBody(odgovor, idPitanje, 1))

                when(response.body()){
                    is Odgovor -> return@withContext 5 //todo OVO VRACA UKUPNE BODOVE NA KVIZU
                    else -> return@withContext  -1
                }
            }!!
        }

    }
}