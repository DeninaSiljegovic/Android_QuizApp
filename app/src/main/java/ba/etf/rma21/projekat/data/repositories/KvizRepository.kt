package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisaneGrupe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KvizRepository {
//Every class can implement a companion object, which is an object that is common to all
// instances of that class. It’d come to be similar to static fields in Java.
    companion object {

        suspend fun getAll():List<Kviz>{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getAll()

                return@withContext response.body()
            }!!
        }

        suspend fun getById(id:Int):Kviz{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getById(id)

                when(val responseBody = response.body()){
                    is Kviz -> return@withContext responseBody
                    else -> return@withContext null
                }

            }!!
        }


        suspend fun getUpisani():List<Kviz>{
            return withContext(Dispatchers.IO){

                val listaGrupa = getUpisaneGrupe()
                val vratiKvizove: MutableList<Kviz> = mutableListOf()

                for(g in listaGrupa){
                    val response = ApiConfig.retrofit.getUpisani(g.id)
                    vratiKvizove.addAll(response.body()!!)
                }

                return@withContext vratiKvizove
            }
        }


    }
}