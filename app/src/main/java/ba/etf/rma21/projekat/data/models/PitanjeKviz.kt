package ba.etf.rma21.projekat.data.models

data class PitanjeKviz(
        var naziv: String,
        val kviz: String,
        val predmetNaziv: String,
        val bodovi: Float,
        var selectedOdgovor: Int
) {
}