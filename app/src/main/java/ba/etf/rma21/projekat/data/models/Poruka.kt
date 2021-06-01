package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

class Poruka(
        @SerializedName("message") val message: String
)