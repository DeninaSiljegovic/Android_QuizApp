package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.models.AppDatabase
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.*
import kotlinx.coroutines.*
import java.util.*

class KvizViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }

    suspend fun getAll(): List<Kviz>{
        return KvizRepository.getAll()
    }

    suspend fun getByIdKviz(id: Int): Kviz {
        return KvizRepository.getById(id)
    }

    suspend fun getMyKvizes(): List<Kviz>{
        return KvizRepository.getMyKvizes(context)
    }

    suspend fun getMyFuture(): List<Kviz> {
        val sviKvizovi: List<Kviz> = KvizRepository.getMyKvizes(context)
        return sviKvizovi.filter { it.datumPocetka > GregorianCalendar.getInstance().time }
    }

    /* iz liste svih korisnikovih kvizova izbaciti one koji su zapoceti
     + filtrirati tako da je kviz vec prosao = dakle ne moze ga ni pokusati uraditi */
    suspend fun getMyNotTaken(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes().toMutableList()

        if (zapocetiKvizovi != null) {
            for(z in zapocetiKvizovi){
                for(m in myKvizovi){
                    if(z.KvizId == m.id) myKvizovi.remove(m)
                }
            }
            myKvizovi.filter { it.datumKraj < GregorianCalendar.getInstance().time }
        }
        else{
            return emptyList()
        }
        return myKvizovi
    }

    /*kvizovi koje je korisnik uradio*/
    suspend fun getMyDone(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes()
        val vrati: MutableList<Kviz> = mutableListOf()

        //dobijem sve kvizove koji su uradjeni prije danas ili danas
        if (zapocetiKvizovi != null) {
            zapocetiKvizovi.filter { it.datumRada <= GregorianCalendar.getInstance().time }
            for(z in zapocetiKvizovi){
                for(m in myKvizovi){
                    if(z.KvizId == m.id) vrati.add(m)
                }
            }

        }
        return vrati
    }


}