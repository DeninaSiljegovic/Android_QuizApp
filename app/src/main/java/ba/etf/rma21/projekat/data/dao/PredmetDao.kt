package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PredmetDao {

    @Insert
    suspend fun insertAll(vararg users: Predmet)

    @Query("SELECT * FROM predmet")
    suspend fun getAll(): List<Predmet> //ovo su grupe koje su korisnikove - on je upisan na njih

    @Query("DELETE FROM predmet")
    suspend fun deleteAll()

    @Query("SELECT id FROM predmet WHERE id==:id")
    suspend fun duplikat(id: Int): Int?

    @Insert
    suspend fun insert(p: Predmet)

    @Query("SELECT * FROM predmet WHERE id==:id")
    suspend fun getPredmetByIdIzBaze(id: Int): Predmet

}