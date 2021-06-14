package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {
    @Query("DELETE FROM pitanje")
    suspend fun deleteAll()

    @Query("SELECT id FROM pitanje WHERE id==:id")
    suspend fun duplikat(id: Int): Int?

    @Insert
    suspend fun insert(p: Pitanje)

    @Query("SELECT MAX(id)+1 FROM pitanje")
    suspend fun generateId(): Int
}