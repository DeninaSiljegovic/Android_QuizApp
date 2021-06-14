package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.models.AppDatabase
import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PitanjeKvizRepository {

    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }

        //ovu obrisi later
//        fun getPitanja(navizKviza: String, nazivPredmeta: String): List<Pitanje> {
//            return dajPitanja(navizKviza, nazivPredmeta).toList()
//        }

        //OVA OSTAJE FUNKCIJA
        suspend fun getPitanja(idKviza:Int):List<Pitanje>{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getPitanja(idKviza)

                return@withContext response.body()
            }!!
        }

        suspend fun getPitanjaIzBaze(idKviza: Int): List<Pitanje>{
            return withContext(Dispatchers.IO){
                try {
                    val database = AppDatabase.getInstance(context)
                    val pitanja = database.pitanjeDao().getPitanjaIzBaze(idKviza)
                    return@withContext pitanja

                }
                catch(error: Exception){
                    return@withContext listOf<Pitanje>()
                }
            }
        }

    }

}
