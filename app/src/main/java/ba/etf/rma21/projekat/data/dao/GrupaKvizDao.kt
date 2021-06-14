package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.GrupaKviz

@Dao
interface GrupaKvizDao {

    @Insert
    suspend fun insert(grupaKviz: GrupaKviz)

    @Query("SELECT MAX(id)+1 FROM grupakviz")
    suspend fun generateId(): Int?

    @Query("SELECT * FROM grupakviz WHERE kvizId==:kvizId")
    suspend fun getGrupeZaKvizBaza(kvizId: Int): List<GrupaKviz>

    @Query("DELETE FROM grupakviz")
    suspend fun deleteAll()

    @Query("SELECT id,grupaId,kvizId FROM grupakviz WHERE grupaId==:grupaId AND kvizId==:kvizId")
    suspend fun duplikat(grupaId: Int, kvizId: Int): GrupaKviz?
}