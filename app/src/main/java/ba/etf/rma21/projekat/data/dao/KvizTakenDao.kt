package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.KvizTaken

@Dao
interface KvizTakenDao {

    @Insert
    suspend fun insertAll(noviPokusaji: List<KvizTaken>)

    @Query("DELETE FROM kviztaken")
    suspend fun deleteAll()
}