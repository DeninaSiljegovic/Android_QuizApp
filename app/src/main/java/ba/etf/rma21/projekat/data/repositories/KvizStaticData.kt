package ba.etf.rma21.projekat.data.repositories

import android.util.Log
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

var mojiKvizovi: MutableList<Kviz> = listOf(
        //Kviz("Kviz 1", "MLTI", GregorianCalendar(2021, 2, 1).getTime(), GregorianCalendar(2021, 2, 3).getTime(), GregorianCalendar(2021, 2, 1).getTime(), 5, "MLTI1-1", 2.5F),
        Kviz("Kviz 3", "IM2", GregorianCalendar(2021, 2, 10).getTime(), GregorianCalendar(2021, 4, 11).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "IM21-1", null),
        Kviz("Kviz 3", "OOAD", GregorianCalendar(2021, 5, 2).getTime(), GregorianCalendar(2021, 5, 5).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "OOAD2-3", null)
        //Kviz("Kviz 1", "IM2", GregorianCalendar(2021, 3, 2).getTime(), GregorianCalendar(2021, 3, 3).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "IM21-1", null),
        //Kviz("Kviz 2", "TP", GregorianCalendar(2021, 2, 12).getTime(), GregorianCalendar(2021, 2, 13).getTime(), GregorianCalendar(2021, 1, 12).getTime(), 3, "TP1-2", 1F),
        //Kviz("Kviz 4", "TP", GregorianCalendar(2021, 3, 8).getTime(), GregorianCalendar(2021, 3, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 3, "TP1-2", null)
    ).toMutableList()

var sviKvizovi: MutableList<Kviz> = listOf(
    Kviz("Kviz 1", "RMA", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 10).getTime(),2, "RMA2-1", 1.5F),
    Kviz("Kviz 1", "DM", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 11).getTime(), GregorianCalendar(2021, 2, 10).getTime(), 5, "DM2-2", 2.5F),
    Kviz("Kviz 3", "IM2", GregorianCalendar(2021, 4, 10).getTime(), GregorianCalendar(2021, 4, 11).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "IM21-1", null),
    Kviz("Kviz 2", "RMA", GregorianCalendar(2021, 3, 5).getTime(), GregorianCalendar(2021, 3, 15).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "RMA2-1", null),
    Kviz("Kviz 0", "RMA", GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 10).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 2, "RMA2-2", null),


    Kviz("Kviz 1", "UUP", GregorianCalendar(2021, 4, 19).getTime(), GregorianCalendar(2021, 4, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 15, "UUP1-1", null),

    Kviz("Kviz 1", "MLTI", GregorianCalendar(2021, 2, 1).getTime(), GregorianCalendar(2021, 2, 3).getTime(), GregorianCalendar(2021, 2, 1).getTime(), 5, "MLTI1-1", 2.5F),

    Kviz("Kviz 1", "IM2", GregorianCalendar(2021, 3, 2).getTime(), GregorianCalendar(2021, 3, 3).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "IM21-1", null),
    Kviz("Kviz 2", "IM2", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "IM21-2", null),

    Kviz("Kviz 1", "VIS", GregorianCalendar(2021, 4, 5).getTime(), GregorianCalendar(2021, 4, 6).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 15, "VIS1-1", null),

    Kviz("Kviz 1", "OS", GregorianCalendar(2021, 2, 8).getTime(), GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 8).getTime(), 30, "OS1-1", 25F),

    Kviz("Kviz 1", "TP", GregorianCalendar(2021, 1, 12).getTime(), GregorianCalendar(2021, 1, 13).getTime(), GregorianCalendar(2021, 1, 12).getTime(), 3, "TP1-1", 1.5F),
    Kviz("Kviz 2", "TP", GregorianCalendar(2021, 2, 12).getTime(), GregorianCalendar(2021, 2, 13).getTime(), GregorianCalendar(2021, 2, 12).getTime(), 3, "TP1-1", 2F),
    Kviz("Kviz 3", "TP", GregorianCalendar(2021, 2, 12).getTime(), GregorianCalendar(2021, 2, 13).getTime(), GregorianCalendar(2021, 1, 12).getTime(), 3, "TP1-2", 1F),
    Kviz("Kviz 4", "TP", GregorianCalendar(2021, 3, 8).getTime(), GregorianCalendar(2021, 3, 20).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 3, "TP1-2", null),

    Kviz("Kviz 1", "DM", GregorianCalendar(2021, 2, 15).getTime(), GregorianCalendar(2021, 2, 16).getTime(), GregorianCalendar(2021, 2, 15).getTime(), 5, "DM2-1", 3.5F),
    Kviz("Kviz 2", "DM", GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 25).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "DM2-1", null),
    Kviz("Kviz 3", "DM", GregorianCalendar(2021, 4, 12).getTime(), GregorianCalendar(2021, 4, 13).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 5, "DM2-1", null),
    Kviz("Kviz 4", "DM", GregorianCalendar(2021, 1, 12).getTime(), GregorianCalendar(2021, 1, 13).getTime(), GregorianCalendar(1970, 0, 1).getTime(),3, "DM2-2", null),

    Kviz("Kviz 1", "LD", GregorianCalendar(2021, 2, 5).getTime(), GregorianCalendar(2021, 2, 6).getTime(), GregorianCalendar(2021, 2, 5).getTime(),3, "LD2-1", 2F),
    Kviz("Kviz 2", "LD", GregorianCalendar(2021, 2, 20).getTime(), GregorianCalendar(2021, 2, 21).getTime(), GregorianCalendar(2021, 1, 20).getTime(),3, "LD2-1", 2.5F),
    Kviz("Kviz 3", "LD", GregorianCalendar(2021, 3, 8).getTime(), GregorianCalendar(2021, 3, 25).getTime(), GregorianCalendar(2021, 3, 10).getTime(),3, "LD2-1", 2F),
    Kviz("Kviz 4", "LD", GregorianCalendar(2021, 3, 2).getTime(), GregorianCalendar(2021, 3, 13).getTime(), GregorianCalendar(2021, 3, 7).getTime(),3, "LD2-2", 3F),
    Kviz("Kviz 5", "LD", GregorianCalendar(2021, 4, 19).getTime(), GregorianCalendar(2021, 5, 23).getTime(), GregorianCalendar(1970, 0, 1).getTime(),3, "LD2-3", null),

    Kviz("Kviz 0", "RPR", GregorianCalendar(2020, 10, 22).getTime(), GregorianCalendar(2020, 10, 23).getTime(), GregorianCalendar(2020, 10, 22).getTime(), 30, "RPR2-1", 2.5F),
    Kviz("Kviz 1", "RPR", GregorianCalendar(2020, 11, 2).getTime(), GregorianCalendar(2020, 11, 3).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 3, "RPR2-2", null),

    Kviz("Kviz 1", "OBP", GregorianCalendar(2020, 11, 28).getTime(), GregorianCalendar(2020, 11, 29).getTime(), GregorianCalendar(2020, 11, 28).getTime(), 35, "OBP2-1", 20F),

    Kviz("Kviz 1", "AIFJ", GregorianCalendar(2021, 2, 20).getTime(), GregorianCalendar(2021, 2, 23).getTime(), GregorianCalendar(2021, 2, 20).getTime(), 15, "AIFJ2-1", 10F),
    Kviz("Kviz 2", "AIFJ", GregorianCalendar(2021, 4, 28).getTime(), GregorianCalendar(2021, 4, 29).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 12, "AIFJ2-2", null),

    Kviz("Kviz 1", "OOAD", GregorianCalendar(2021, 1, 18).getTime(), GregorianCalendar(2021, 1, 19).getTime(), GregorianCalendar(2021, 1, 18).getTime(), 10, "OOAD2-1", 3F),
    Kviz("Kviz 2", "OOAD", GregorianCalendar(2021, 3, 11).getTime(), GregorianCalendar(2021, 3, 15).getTime(), GregorianCalendar(2021, 3, 11).getTime(), 10, "OOAD2-2", 4.5F),
    Kviz("Kviz 3", "OOAD", GregorianCalendar(2021, 5, 2).getTime(), GregorianCalendar(2021, 5, 5).getTime(), GregorianCalendar(1970, 0, 1).getTime(), 10, "OOAD2-3", null),

    Kviz("Kviz 0", "US", GregorianCalendar(2021, 2, 5).getTime(), GregorianCalendar(2021, 2, 9).getTime(), GregorianCalendar(2021, 2, 7).getTime(), 5, "US3-1", 2F),
    Kviz("Kviz 1", "US", GregorianCalendar(2021, 3, 8).getTime(), GregorianCalendar(2021, 3, 9).getTime(), GregorianCalendar(2021, 3, 8).getTime(), 5, "US3-2", 2.8F),

    Kviz("Kviz 1", "RA", GregorianCalendar(2021, 4, 20).getTime(), GregorianCalendar(2021, 4, 29).getTime(), GregorianCalendar(2021, 11, 28).getTime(), 15, "RA3-1", null)
).toMutableList()



fun sviKvizovi() : List<Kviz> {
    return sviKvizovi.toList()
}


fun zavrseniKvizovi(): List<Kviz>{
    var vrati =  sviKvizovi().filter { it.osvojeniBodovi != null && it.datumRada <= GregorianCalendar.getInstance().getTime()}
    return vrati
}


fun buduciKvizovi(): List<Kviz>{
    var vrati =  sviKvizovi().filter { it.datumPocetka > GregorianCalendar.getInstance().getTime() }
    return vrati
}


fun neuradjeniKvizovi(): List<Kviz>{
    var vrati =  sviKvizovi().filter { it.datumKraj < GregorianCalendar.getInstance().getTime() && it.osvojeniBodovi == null }
    return vrati
}

//KORISNIKOVI KVIZOVI
fun sviMojiKvizovi(): List<Kviz>{
    return mojiKvizovi.toList()
}

fun upisiKvizz(g: String): List<Kviz>{
    val tmp = sviKvizovi().filter { k -> k.nazivGrupe == g }
    for(k : Kviz in tmp) mojiKvizovi.add(k)
    return mojiKvizovi
}

fun mojiZavrseni(): List<Kviz>{
    val vrati =  sviMojiKvizovi().filter { it.osvojeniBodovi != null && it.datumRada <= GregorianCalendar.getInstance().getTime()}
    return vrati
}


fun mojiBuduci(): List<Kviz>{
    var vrati =  sviMojiKvizovi().filter { it.datumPocetka > GregorianCalendar.getInstance().getTime() }
    return vrati
}

fun mojiNeuradjeni(): List<Kviz>{
    var vrati =  sviMojiKvizovi().filter { it.datumKraj < GregorianCalendar.getInstance().getTime() && it.osvojeniBodovi == null }
    return vrati
}

fun dodajUradjen(k: String, p: String){
    val kviz = sviKvizovi.find { kviz -> kviz.naziv == k && kviz.nazivPredmeta == p }
    kviz?.datumRada = Date()
    kviz?.osvojeniBodovi = 1F
    sviKvizovi.removeIf { kviz -> kviz.naziv == k && kviz.nazivPredmeta == p  }
    sviKvizovi.add(kviz!!)


    Log.d("Prije izmjene",  sviKvizovi.find { kviz -> kviz.naziv == k && kviz.nazivPredmeta == p }?.datumRada.toString())

    val kviz1 = mojiKvizovi.find { kviz -> kviz.naziv == k && kviz.nazivPredmeta == p }
    kviz1?.datumRada = Date()
    kviz1?.osvojeniBodovi = 1F
    mojiKvizovi.removeIf { kviz -> kviz.naziv == k && kviz.nazivPredmeta == p  }
    mojiKvizovi.add(kviz)

    Log.d("Nakon izmjene", mojiKvizovi.size.toString())
}





