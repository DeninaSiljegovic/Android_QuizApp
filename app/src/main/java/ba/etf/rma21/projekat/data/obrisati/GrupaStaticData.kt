package ba.etf.rma21.projekat.data.obrisati

import ba.etf.rma21.projekat.data.models.Grupa

fun dajGrupe(): List<Grupa>{
    return listOf(
            Grupa("UUP1-1", "UUP"),

            Grupa("MLTI1-1", "MLTI"),

            Grupa("IM21-1", "IM2"),
            Grupa("IM21-2", "IM2"),

            Grupa("VIS1-1", "VIS"),

            Grupa("OS1-1", "OS"),

            Grupa("TP1-1", "TP"),
            Grupa("TP1-2", "TP"),

            Grupa("DM2-1", "DM"),
            Grupa("DM2-2", "DM"),

            Grupa("LD2-1", "LD"),
            Grupa("LD2-2", "LD"),
            Grupa("LD2-3", "LD"),

            Grupa("RPR2-1", "RPR"),
            Grupa("RPR2-2", "RPR"),

            Grupa("OBP2-1", "OBP"),

            Grupa("AIFJ2-1", "AIFJ"),
            Grupa("AIFJ2-2", "AIFJ"),

            Grupa("RMA2-1", "RMA"),
            Grupa("RMA2-2", "RMA"),

            Grupa("OOAD2-1", "OOAD"),
            Grupa("OOAD2-2", "OOAD"),
            Grupa("OOAD2-3", "OOAD"),

            Grupa("US3-1", "US"),
            Grupa("US3-2", "US"),

            Grupa("RA3-1", "RA")
    )
}

fun groupsByPredmet(naz: String): List<Grupa> {
    var lista : List<Grupa> = dajGrupe()
    //lista.filter{ lista.any{it.nazivPredmeta == naziv}} //NOT SURE IF THIS WORKS
    val vrati = lista.filter { it.nazivPredmeta == naz }
    return vrati
}

