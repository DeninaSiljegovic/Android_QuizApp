package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Odgovor

@Dao
interface OdgovorDao {

    @Query("SELECT * FROM odgovor")
    suspend fun getSveOdgovoreIzBaze(): List<Odgovor>

    @Query("SELECT MAX(id) FROM odgovor")
    suspend fun maxId(): Int?

    @Insert
    suspend fun insert(odgovor: Odgovor)

    @Query("DELETE FROM odgovor")
    suspend fun deleteAll()

    @Query("SELECT id FROM odgovor WHERE id==:id")
    suspend fun duplikat(id: Int): Int?

//    @Query("SELECT * FROM odgovor WHERE PitanjeId==:PitanjeId")
//    suspend fun checkDuplicate(PitanjeId: Int, idKviz: Int):Odgovor?

}