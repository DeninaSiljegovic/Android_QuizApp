package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository


class TakeKvizViewModel {

    suspend fun zapocniKviz(idKviza:Int): KvizTaken? {
        return TakeKvizRepository.zapocniKviz(idKviza)
    }

    //null ako student nije pokusao rjesavat niti jedan kviz
    suspend fun getPocetiKvizovi():List<KvizTaken>{
        return TakeKvizRepository.getPocetiKvizovi()!!
    }

    //lista ce sadrzati uvijek 1 element jer ce samo jedan KvizTaken imati KvizId koji se poklapa sa id Kviza koji smo send
    suspend fun getPokusajKviza(id: Int): List<KvizTaken> {
        val sviPokrenuti = getPocetiKvizovi()
        return sviPokrenuti.filter { it.KvizId == id }
    }



}