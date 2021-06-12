package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Account(
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo(name = "student") @SerializedName("student") val student: String, //email studenta
    @ColumnInfo(name = "acHash") @SerializedName("acHash")val acHash: String, //hash accounta
    @ColumnInfo(name = "lastUpdate") val lastUpdate: String //pr dal ovo treba biti string
) {
}