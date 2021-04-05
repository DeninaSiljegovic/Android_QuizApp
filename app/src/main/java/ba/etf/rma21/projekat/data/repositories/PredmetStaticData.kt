package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Predmet

//lista svih predmeta
fun predmeti(): List<Predmet> {
    return listOf(
        Predmet("UUP", 1),
        Predmet("IF1", 1),
        Predmet("IM1", 1),
        Predmet("LAG", 1),
        Predmet("OE", 1),
        Predmet("MLTI", 1),
        Predmet("IM2", 1),
        Predmet("VIS", 1),
        Predmet ("OS", 1),
        Predmet("TP", 1),

        Predmet("NA", 2),
        Predmet("ASP", 2),
        Predmet("DM", 2),
        Predmet("LD", 2),
        Predmet("RPR", 2),
        Predmet("OBP", 2),
        Predmet("AIFJ", 2),
        Predmet("RMA", 2),
        Predmet("ORM", 2),
        Predmet("OOAD", 2),
        Predmet("US", 2),
        Predmet("RA", 2),

        Predmet("WT", 3),
        Predmet("RG", 3),
        Predmet("OIS", 3),
        Predmet("OOI", 3),
        Predmet("VVI", 3),
        Predmet("OSP", 3),
        Predmet("DASS", 3),
        Predmet("ARM", 3),
        Predmet("PIS", 3),
        Predmet("OA", 3),
        Predmet("SI", 3),
        Predmet("VI", 3)
    )
}


//lista predmeta na koje je korisnik upisan
fun upisani(): List<Predmet> {
    return listOf(
        Predmet("UUP", 1),
        Predmet("IM2", 1),
        Predmet("TP", 1),
        Predmet("MLTI", 1),
        Predmet("ASP", 2),
        Predmet("DM", 2),
        Predmet("LD", 2),
        Predmet("ORM", 2)
    )
}

