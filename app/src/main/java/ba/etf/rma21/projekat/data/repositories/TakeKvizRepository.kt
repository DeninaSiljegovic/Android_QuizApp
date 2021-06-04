package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TakeKvizRepository {

    companion object{

        suspend fun zapocniKviz(idKviza:Int): KvizTaken? {
            return (withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.zapocniKviz(getHash(), idKviza)

                when(val responseBody = response.body()){
                    is KvizTaken -> {
                            return@withContext responseBody
                    }
                    else -> return@withContext null
                }


            })
        }

        suspend fun getPocetiKvizovi():List<KvizTaken>?{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getPocetniKvizovi(getHash())

                when(val responseBody = response.body()){
                    is List<KvizTaken> -> {
                        if(responseBody.isNotEmpty()) {
                            return@withContext responseBody }
                        return@withContext null
                    }
                    else -> return@withContext null
                }
            }
        }

    }

}