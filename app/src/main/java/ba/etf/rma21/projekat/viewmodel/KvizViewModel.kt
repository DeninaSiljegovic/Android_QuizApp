package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.*
import java.util.*

class KvizViewModel {

    suspend fun getAll(): List<Kviz>{
        return KvizRepository.getAll()
    }

    suspend fun getByIdKviz(id: Int): Kviz {
        return KvizRepository.getById(id)
    }

    suspend fun getMyKvizes(): List<Kviz>{
        return KvizRepository.getUpisani()
    }

    suspend fun getMyFuture(): List<Kviz> {
        val sviKvizovi: List<Kviz> =  KvizRepository.getUpisani()
        return sviKvizovi.filter { it.datumPocetka > GregorianCalendar.getInstance().time }
    }

    /* iz liste svih korisnikovih kvizova izbaciti one koji su zapoceti
     + filtrirati tako da je kviz vec prosao = dakle ne moze ga ni pokusati uraditi */
    suspend fun getMyNotTaken(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes().toMutableList()

        for(z in zapocetiKvizovi){
            for(m in myKvizovi){
                if(z.KvizId == m.id) myKvizovi.remove(m)
            }
        }

        myKvizovi.filter { it.datumKraj < GregorianCalendar.getInstance().time }
        return myKvizovi
    }

    /*kvizovi koje je korisnik uradio*/
    suspend fun getMyDone(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes()
        val vrati: MutableList<Kviz> = mutableListOf()

        //dobijem sve kvizove koji su uradjeni prije danas ili danas
        zapocetiKvizovi.filter { it.datumRada <= GregorianCalendar.getInstance().time }

        for(z in zapocetiKvizovi){
            for(m in myKvizovi){
                if(z.KvizId == m.id) vrati.add(m)
            }
        }

        return vrati
    }


//    fun upisiKviz(g:String): List<Kviz> {
//        return KvizRepository.upisiKviz(g)
//    }
//
//
//    fun dodajUradjenKviz(k: String, p: String){
//        KvizRepository.dodajUradjenKviz(k, p)
//    }

}