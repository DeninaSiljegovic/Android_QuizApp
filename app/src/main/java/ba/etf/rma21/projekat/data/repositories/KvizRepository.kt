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

        fun getMyKvizes(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        fun getAll(): List<Kviz> {
            return sviKvizovi()
        }

        fun getDone(): List<Kviz> {
            return uradjeniKvizovi()
        }

        fun getFuture(): List<Kviz> {
           return buduciKvizovi()
        }

        fun getNotTaken(): List<Kviz> {
            return prosliNeuradjeniKvizovi()
        }
        // TODO: Implementirati i ostale potrebne metode
    }
}