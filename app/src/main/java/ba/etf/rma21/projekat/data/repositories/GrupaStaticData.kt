package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa

fun dajGrupe(): List<Grupa>{
    return listOf(
            Grupa("Pon9", "RMA"),
            Grupa("Pon12", "RMA"),
            Grupa("Pon14", "RMA"),

            Grupa("Cet12", "LD"),
            Grupa("Cet14", "LD"),
            Grupa("Cet16", "LD"),

            Grupa("Uto12", "DM"),
            Grupa("Uto14", "DM"),
            Grupa("Pet9", "DM"),
            Grupa("Pet12", "DM"),

            Grupa("Sri12", "IM2"),
            Grupa("Sri14", "IM2"),

            Grupa("Uto11", "TP"),
            Grupa("Uto13", "MLTI")
    )
}

fun getGroupsByPredmet(naz: String): List<Grupa> {
    var lista : List<Grupa> = dajGrupe()
    //lista.filter{ lista.any{it.nazivPredmeta == naziv}} //NOT SURE IF THIS WORKS
    val vrati = lista.filter { it.nazivPredmeta == naz }
    return vrati
}

