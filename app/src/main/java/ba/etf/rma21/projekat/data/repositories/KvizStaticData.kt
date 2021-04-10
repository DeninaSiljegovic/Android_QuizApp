package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

//MJESEC IDE OD 0 OVDJE
fun sviKvizovi() : List<Kviz> {
    return listOf(
        Kviz("Kviz 1", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "Pon12", 1.5F),
        Kviz("Kviz 1", "DM", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 11).getTime(), GregorianCalendar(2021, 2, 10).getTime(), 5, "Pet9", 2.5F),
        Kviz("Kviz 1", "IM2", GregorianCalendar(2021, 4, 10).getTime(), GregorianCalendar(2021, 4, 11).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "Sri12", null),
        Kviz("Kviz 2", "RMA", GregorianCalendar(2021, 3, 5).getTime(), GregorianCalendar(2021, 3, 15).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "Pon12", null),
        Kviz("Kviz 0", "RMA", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 10).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "Pon14", null),


        Kviz("Kviz 1", "UUP", GregorianCalendar(2021, 4, 19).getTime(), GregorianCalendar(2021, 4, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 15, "Sri12", null),

        Kviz("Kviz 1", "MLTI", GregorianCalendar(2021, 2, 1).getTime(), GregorianCalendar(2021, 2, 3).getTime(), GregorianCalendar(2021, 2, 1).getTime(), 5, "Uto13", 2.5F),

        Kviz("Kviz 1", "IM2", GregorianCalendar(2021, 3, 2).getTime(), GregorianCalendar(2021, 3, 3).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "Sri12", null),
        Kviz("Kviz 2", "IM2", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "Sri14", null),

        Kviz("Kviz 1", "VIS", GregorianCalendar(2021, 4, 5).getTime(), GregorianCalendar(2021, 4, 6).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 15, "Cet16", null),

        Kviz("Kviz 1", "OS", GregorianCalendar(2021, 2, 8).getTime(), GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 8).getTime(), 30, "Sri9", 25F),

        Kviz("Kviz 1", "TP", GregorianCalendar(2021, 1, 12).getTime(), GregorianCalendar(2021, 1, 13).getTime(), GregorianCalendar(2021, 1, 12).getTime(), 3, "Uto11", 1.5F),
        Kviz("Kviz 2", "TP", GregorianCalendar(2021, 2, 12).getTime(), GregorianCalendar(2021, 2, 13).getTime(), GregorianCalendar(2021, 2, 12).getTime(), 3, "Uto11", 2F),
        Kviz("Kviz 1", "TP", GregorianCalendar(2021, 2, 12).getTime(), GregorianCalendar(2021, 2, 13).getTime(), GregorianCalendar(2021, 1, 12).getTime(), 3, "Pet13", 1F),

        Kviz("Kviz 1", "DM", GregorianCalendar(2021, 2, 15).getTime(), GregorianCalendar(2021, 2, 16).getTime(), GregorianCalendar(2021, 2, 15).getTime(), 5, "Uto12", 3.5F),
        Kviz("Kviz 2", "DM", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 25).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "Uto12", null),
        Kviz("Kviz 3", "DM", GregorianCalendar(2021, 4, 12).getTime(), GregorianCalendar(2021, 4, 13).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "Uto12", null),
        Kviz("Kviz 1", "DM", GregorianCalendar(2021, 1, 12).getTime(), GregorianCalendar(2021, 1, 13).getTime(), GregorianCalendar(1970, 0, 1).getTime(),3, "Pet9", null),

        Kviz("Kviz 1", "LD", GregorianCalendar(2021, 2, 5).getTime(), GregorianCalendar(2021, 2, 6).getTime(), GregorianCalendar(2021, 2, 5).getTime(),3, "Cet12", 2F),
        Kviz("Kviz 2", "LD", GregorianCalendar(2021, 2, 20).getTime(), GregorianCalendar(2021, 2, 21).getTime(), GregorianCalendar(2021, 1, 20).getTime(),3, "Cet12", 2.5F),
        Kviz("Kviz 3", "LD", GregorianCalendar(2021, 3, 8).getTime(), GregorianCalendar(2021, 3, 25).getTime(), GregorianCalendar(2021, 3, 10).getTime(),3, "Cet12", 2F),
        Kviz("Kviz 4", "LD", GregorianCalendar(2021, 3, 2).getTime(), GregorianCalendar(2021, 3, 13).getTime(), GregorianCalendar(2021, 3, 7).getTime(),3, "Cet14", 3F),
        Kviz("Kviz 5", "LD", GregorianCalendar(2021, 4, 19).getTime(), GregorianCalendar(2021, 5, 23).getTime(), GregorianCalendar(1970, 0, 1).getTime(),3, "Cet16", null),

         Kviz("Kviz 0", "RPR", GregorianCalendar(2020, 10, 22).getTime(), GregorianCalendar(2020, 10, 23).getTime(), GregorianCalendar(2020, 10, 22).getTime(), 30, "Pon16", 2.5F),
         Kviz("Kviz 1", "RPR", GregorianCalendar(2020, 11, 2).getTime(), GregorianCalendar(2020, 11, 3).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 3, "Uto11", null),

         Kviz("Kviz 1", "OBP", GregorianCalendar(2020, 11, 28).getTime(), GregorianCalendar(2020, 11, 29).getTime(), GregorianCalendar(2020, 11, 28).getTime(), 35, "Uto12", 20F),

//ovo zavrsiti
         Kviz("Kviz 1", "AIFJ", GregorianCalendar(2021, 2, 20).getTime(), GregorianCalendar(2021, 2, 23).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto14", 10F),
         Kviz("Kviz 2", "AIFJ", GregorianCalendar(2021, 3, 28).getTime(), GregorianCalendar(2021, 3, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Pon16", 8.5F),

         Kviz("Kviz 1", "OOAD", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F),
         Kviz("Kviz 2", "OOAD", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F),
         Kviz("Kviz 3", "OOAD", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F),

         Kviz("Kviz 1", "US", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F),
         Kviz("Kviz 1", "US", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F),

         Kviz("Kviz 1", "RA", GregorianCalendar(2021, 11, 28).getTime(), GregorianCalendar(2021, 11, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 35, "Uto12", 20F)





            )
}



fun uradjeniKvizovi(): List<Kviz>{
    var svi = sviKvizovi()
    var vrati = svi.filter { it.osvojeniBodovi != null && it.datumRada <= GregorianCalendar.getInstance().getTime()}
    return vrati
}


fun buduciKvizovi(): List<Kviz>{
    var svi = sviKvizovi()
    var vrati = svi.filter { it.datumPocetka > GregorianCalendar.getInstance().getTime() }
    return vrati
}



fun prosliNeuradjeniKvizovi(): List<Kviz>{
    var svi = sviKvizovi()
    var vrati = svi.filter { it.datumKraj < GregorianCalendar.getInstance().getTime() && it.osvojeniBodovi == null }
    return vrati
}


fun mojiKvizovi(): List<Kviz>{
    var svi = sviKvizovi()
    var upisani_predmeti = upisani()
    var vrati = upisani_predmeti.flatMap { api -> svi.filter { api.naziv == it.nazivPredmeta }}
    return vrati
}


