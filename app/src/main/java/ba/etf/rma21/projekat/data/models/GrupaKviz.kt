package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GrupaKviz(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="grupaId") val grupaId: Int,
    @ColumnInfo(name="kvizId") val kvizId: Int
)    {

}