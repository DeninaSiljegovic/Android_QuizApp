package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz

class KvizRepository {
//Every class can implement a companion object, which is an object that is common to all
// instances of that class. It’d come to be similar to static fields in Java.
    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }

        fun getMyKvizes(): List<Kviz>{
            return sviMojiKvizovi()
        }

        fun getMyKvizes(g: String = ""): List<Kviz> {
            return sviMojiKvizovi(g)
        }

        fun getAll(): List<Kviz> {
            return sviKvizovi()
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

    }
}