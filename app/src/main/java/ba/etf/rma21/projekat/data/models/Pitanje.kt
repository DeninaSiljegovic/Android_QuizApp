package ba.etf.rma21.projekat.data.models

data class Pitanje (
    val id: Int,
    val naziv:String,
    val tekstPitanja:String,
    val opcije: List<String>,
    val tacan:Int
){}