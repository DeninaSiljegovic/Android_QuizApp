package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Kviz

@Dao
interface KvizDao {

    @Insert
    suspend fun insertAll(vararg users: Kviz)

    @Insert
    suspend fun insert(k: Kviz)

    @Query("SELECT * FROM kviz")
    suspend fun getAll(): List<Kviz> //ovo su kvizovi koji su korisnikovi - on ih je zapoceo??

    @Query("DELETE FROM kviz")
    suspend fun deleteAll()

    @Query("SELECT id FROM kviz WHERE id==:id")
    suspend fun duplikat(id: Int): Int?

    @Query("UPDATE kviz SET predan=:predan WHERE id==:id")
    suspend fun updatePredan(predan: Boolean, id: Int)
}