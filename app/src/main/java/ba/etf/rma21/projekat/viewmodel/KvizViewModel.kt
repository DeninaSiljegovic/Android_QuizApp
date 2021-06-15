package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.*
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class KvizViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun setContext(_context: Context) {
        KvizRepository.setContext(_context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    suspend fun getAll(): List<Kviz>{
        return KvizRepository.getAll()
    }

    suspend fun getByIdKviz(id: Int): Kviz {
        return KvizRepository.getById(id)
    }

    suspend fun getMyKvizes(): List<Kviz>{
        return KvizRepository.getMyKvizes()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMyFuture(): List<Kviz> {
        val sviKvizovi: List<Kviz> = KvizRepository.getMyKvizes()
        return sviKvizovi.filter { Date.from(LocalDate.parse(it.datumPocetka, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()) > GregorianCalendar.getInstance().time }
    }

    /* iz liste svih korisnikovih kvizova izbaciti one koji su zapoceti
     + filtrirati tako da je kviz vec prosao = dakle ne moze ga ni pokusati uraditi */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMyNotTaken(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes().toMutableList()

        if (zapocetiKvizovi != null) {
            for(z in zapocetiKvizovi){
                for(m in myKvizovi){
                    if(z.KvizId == m.id) myKvizovi.remove(m)
                }
            }
            myKvizovi.filter { Date.from(LocalDate.parse(it.datumKraj, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()) < GregorianCalendar.getInstance().time }
        }
        else{
            return emptyList()
        }
        return myKvizovi
    }

    /*kvizovi koje je korisnik uradio*/
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMyDone(): List<Kviz> {
        val zapocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
        val myKvizovi = getMyKvizes()
        val vrati: MutableList<Kviz> = mutableListOf()

        //dobijem sve kvizove koji su uradjeni prije danas ili danas
        if (zapocetiKvizovi != null) {
            zapocetiKvizovi.filter { LocalDate.parse(it.datumRada, dateFormatter) <= LocalDate.now()}
            for(z in zapocetiKvizovi){
                for(m in myKvizovi){
                    if(z.KvizId == m.id) vrati.add(m)
                }
            }

        }
        return vrati
    }

    suspend fun getUpisane(): List<Kviz>{
        return KvizRepository.getUpisane()
    }

}