package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.*

class KvizViewModel {

    fun upisiKviz(g:String): List<Kviz> {
        return KvizRepository.upisiKviz(g)
    }

    fun getMyKvizes(): List<Kviz> {
        return KvizRepository.getMyKvizes()
    }

    fun getAll(): List<Kviz>{
        return KvizRepository.getAll()
    }

    fun getDone(): List<Kviz> {
        return KvizRepository.getDone()
    }

    fun getFuture(): List<Kviz> {
        return KvizRepository.getFuture()
    }

    fun getNotTaken(): List<Kviz> {
        return KvizRepository.getNotTaken()
    }

    fun getMyDone(): List<Kviz> {
        return KvizRepository.getMyDone()
    }

    fun getMyFuture(): List<Kviz> {
        return KvizRepository.getMyFuture()
    }

    fun getMyNotTaken(): List<Kviz> {
        return KvizRepository.getMyNotTaken()
    }



}