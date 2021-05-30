package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.obrisati.*
import java.util.*

class KvizRepository {
//Every class can implement a companion object, which is an object that is common to all
// instances of that class. It’d come to be similar to static fields in Java.
    companion object {

        fun getAll():List<Kviz>{
            return emptyList<Kviz>()
        }

        fun getById(id:Int):Kviz{
            return Kviz(1, "pokusaj", GregorianCalendar(2021, 2, 10).getTime(), GregorianCalendar(2021, 2, 10).getTime(), 2 )
        }


        fun getUpisani():List<Kviz>{
            return emptyList()
        }





        //ovo sve ispod obrisat later
        fun getMyKvizes(): List<Kviz>{
            return sviMojiKvizovi()
        }

        fun upisiKviz(g: String = ""): List<Kviz> {
            return upisiKvizz(g)
        }

        fun getDone(): List<Kviz> {
            return zavrseniKvizovi()
        }

        fun getFuture(): List<Kviz> {
           return buduciKvizovi()
        }

        fun getNotTaken(): List<Kviz> {
            return neuradjeniKvizovi()
        }

        fun getMyDone(): List<Kviz> {
            return mojiZavrseni()
        }

        fun getMyFuture(): List<Kviz> {
            return mojiBuduci()
        }

        fun getMyNotTaken(): List<Kviz> {
            return mojiNeuradjeni()
        }

        fun dodajUradjenKviz(k: String, p: String){
            dodajUradjen(k, p)
        }

    }
}