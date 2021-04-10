package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa

fun dajGrupe(): List<Grupa>{
    return listOf(
            Grupa("Sri12", "UUP"),

            Grupa("Uto13", "MLTI"),

            Grupa("Sri12", "IM2"),
            Grupa("Sri14", "IM2"),

            Grupa("Cet16", "VIS"),

            Grupa("Sri9", "OS"),

            Grupa("Uto11", "TP"),
            Grupa("Pet13", "TP"),

            Grupa("Uto12", "DM"),
            Grupa("Pet9", "DM"),

            Grupa("Cet12", "LD"),
            Grupa("Cet14", "LD"),
            Grupa("Cet16", "LD"),

            Grupa("Pon16", "RPR"),
            Grupa("Cet16", "RPR"),

            Grupa("Uto12", "OBP"),

            Grupa("Uto14", "AIFJ"),
            Grupa("Pon16", "AIFJ"),

            Grupa("Pon12", "RMA"),
            Grupa("Pon14", "RMA"),

            Grupa("Pon11", "OOAD"),
            Grupa("Uto12", "OOAD"),
            Grupa("Pet12", "OOAD"),

            Grupa("Sri18", "US"),
            Grupa("Pet9", "US"),

            Grupa("Cet9", "RA")
    )
}

fun getGroupsByPredmet(naz: String): List<Grupa> {
    var lista : List<Grupa> = dajGrupe()
    //lista.filter{ lista.any{it.nazivPredmeta == naziv}} //NOT SURE IF THIS WORKS
    val vrati = lista.filter { it.nazivPredmeta == naz }
    return vrati
}

