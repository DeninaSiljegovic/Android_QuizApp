package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.models.AppDatabase
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository.Companion.getUpisaneGrupe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KvizRepository {
    //Every class can implement a companion object, which is an object that is common to all
// instances of that class. It’d come to be similar to static fields in Java.
    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }

        //funkcija za upis u bazu - jednog kviza
        suspend fun writeKviz(kviz:Kviz) : String?{
            return withContext(Dispatchers.IO) {
                try{
                    val db = AppDatabase.getInstance(context)
                    db.kvizDao().insertAll(kviz)
                    return@withContext "success"
                }
                catch(error:Exception){
                    return@withContext null
                }
            }
        }

        //vraca sve KORISNIKOVE KVIZOVE
        suspend fun getMyKvizes(context: Context) : List<Kviz> {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                var kvizovi = db!!.kvizDao().getAll()
                return@withContext kvizovi
            }
        }

        //ovo dobavlja SVE kvizove - pa zato ide preko API-ja
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

        //      koristeno u spirali 3 da se dobiju korisnikovi kvizovi na osnovu njegovih grupa
        suspend fun getUpisane(): List<Kviz>{
            return withContext(Dispatchers.IO){//
                val listaGrupa = getUpisaneGrupe()
                val vratiKvizove: MutableList<Kviz> = mutableListOf()//
                for(g in listaGrupa){
                    val response = ApiConfig.retrofit.getUpisani(g.id)
                    vratiKvizove.addAll(response.body()!!)
                }

                return@withContext vratiKvizove
            }
        }


    }
}