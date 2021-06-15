package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.GrupaKviz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GrupaKvizRepository {
    companion object{
        private lateinit var context: Context

        fun setContext(_context: Context){
            context=_context
        }

        suspend fun getGrupeZaKvizBaza(kvizId: Int): List<GrupaKviz>{
            return withContext(Dispatchers.IO) {
                try {
                    val database = AppDatabase.getInstance(context)
                    val grupaKviz = database.grupaKvizDao().getGrupeZaKvizBaza(kvizId)
                    return@withContext grupaKviz
                } catch (error: Exception) {
                    println(error.printStackTrace())
                    return@withContext listOf<GrupaKviz>()
                }
            }
        }

    }
}