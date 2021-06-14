package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Account

@Dao
interface AccountDao {
    @Insert
    suspend fun insertAccount(account: Account)

    @Query("UPDATE account SET id=:id, student=:student, acHash=:acHash WHERE id == :stariId")
    suspend fun updateUser(id: Int, student: String, acHash: String, stariId: String)

    @Query("SELECT lastUpdate FROM account WHERE acHash==:acHash")
    suspend fun getLastUpdate(acHash: String): String?

    @Query("SELECT * FROM account")
    suspend fun getAll(): List<Account>

    @Query("UPDATE account SET lastUpdate=:lastUpdate WHERE acHash==:acHash")
    suspend fun setLastUpdate(acHash: String, lastUpdate: String?)

}