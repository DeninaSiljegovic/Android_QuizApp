package ba.etf.rma21.projekat.data.models

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("SELECT lastUpdate FROM account WHERE acHash==:acHash")
    suspend fun getLastUpdate(acHash: String): String?
}