package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("UPDATE account SET id=:id, student=:student, acHash=:acHash WHERE id == :stariId")
    suspend fun updateUser(id: Int, student: String, acHash: String, stariId: String)

    @Query("SELECT lastUpdate FROM account WHERE acHash==:acHash")
    suspend fun getLastUpdate(acHash: String): String?
}