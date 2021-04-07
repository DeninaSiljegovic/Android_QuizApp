package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

//MJESEC IDE OD 0 OVDJE
fun sviKvizovi() : List<Kviz> {
    return listOf(
        Kviz("Kviz 1", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RI", 1.5F),
        Kviz("Kviz 1", "DM", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 11).getTime(), GregorianCalendar(2021, 2, 10).getTime(), 5, "RI", 2.5F),
        Kviz("Kviz 1", "IM", GregorianCalendar(2021, 4, 10).getTime(), GregorianCalendar(2021, 4, 11).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "RI", null),
        Kviz("Kviz 2", "RMA", GregorianCalendar(2021, 3, 5).getTime(), GregorianCalendar(2021, 3, 15).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "RI", null),
        Kviz("Kviz 0", "RMA", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 10).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "RI", null)
    )
}



fun uradjeniKvizovi(): List<Kviz>{
    return listOf(
            Kviz("Uradjen", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RI", 1.5F)
    )
}


fun buduciKvizovi(): List<Kviz>{
    return listOf(
            Kviz("Buduci", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RI", 1.5F)

    )
}



fun prosliNeuradjeniKvizovi(): List<Kviz>{
    return listOf(
            Kviz("Prosao", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RI", 1.5F)
    )
}


fun mojiKvizovi(): List<Kviz>{
    return listOf(
            Kviz("MOJkviz", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RI", 1.5F)
    )
}


