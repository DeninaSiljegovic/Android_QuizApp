package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository


class TakeKvizViewModel {

    suspend fun zapocniKviz(idKviza:Int): KvizTaken {
        return TakeKvizRepository.zapocniKviz(idKviza)
    }

    //null ako student nije pokusao rjesavat niti jedan kviz
    suspend fun getPocetiKvizovi():List<KvizTaken>{
        return TakeKvizRepository.getPocetiKvizovi()
    }



}