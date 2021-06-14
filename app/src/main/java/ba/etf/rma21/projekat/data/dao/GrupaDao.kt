package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface GrupaDao {

    @Insert
    suspend fun insertAll(vararg users: Grupa)

    @Query("SELECT * FROM grupa")
    suspend fun getAll(): List<Grupa> //ovo su grupe koje su korisnikove - on je upisan na njih

    @Query("DELETE FROM grupa")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(g: Grupa)

    @Query("SELECT MAX(id)+1 FROM grupa")
    suspend fun generateId(): Int

    @Query("SELECT id FROM grupa WHERE id==:id")
    suspend fun duplikat(id: Int): Int?

}