package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import ba.etf.rma21.projekat.data.models.AppDatabase
import ba.etf.rma21.projekat.data.models.Change
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DBRepository {

    companion object {

        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }

        //true/false u zavisnosti da li su trenutno ispravni podaci + UPDATEA OVO - implement it
        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun updateNow(): Boolean {
            return withContext(Dispatchers.IO) {
                try {
                    val database = AppDatabase.getInstance(context)//ovdje nece context

                    //last datum kad je sve updated
                    val date = database.accountDao().getLastUpdate(AccountRepository.getHash())
                    val dateTime: LocalDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
                    //if (date == null) return@withContext true

                    val response = ApiConfig.retrofit.updateNow(AccountRepository.getHash(), dateTime.withNano(0).toString())
                    val responseBdy = response.body()
                    when (responseBdy) {
                        is Change -> {
                            return@withContext responseBdy.changed.contains("true")
                        }
                        else -> return@withContext false
                    }
                } catch (e: Exception) {
                    return@withContext false // does this make sense??
                }
            }
        }

    }


}