package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorBody
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class OdgovorRepository {

    companion object{
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }


        suspend fun getOdgovoriKviz(idKviza:Int):List<Odgovor>{
            return withContext(Dispatchers.IO){
                val pokusaj = TakeKvizRepository.getPocetiKvizovi()!!.find{it.KvizId == idKviza}
                val response = ApiConfig.retrofit.getOdgovoriKviz(getHash(), pokusaj!!.id)

                when(val responseBody = response.body()){
                    is List<Odgovor> -> return@withContext responseBody
                    else -> return@withContext listOf<Odgovor>()
                }

            }!!
        }

        //u funkciju se salje kviz_id iz fragmenta pokusaj - da bi nasli odgovarajuci KvizTaken mora KvizId == poslanom id Kviz-a
        //SPIRALA 4 - ODGOVOR SE SADA POSTAVLJA U BAZU
        suspend fun postaviOdgovorKviz(idKvizTaken:Int, idPitanje:Int, odgovor:Int):Int{
            return (withContext(Dispatchers.IO) {
                try {

                    val pokusaj = TakeKvizRepository.getPocetiKvizovi()!!.find { it.id == idKvizTaken }
                    var bod = 0F
                    var bodovi = 0F

                    //pr da li je tacno odgovoreno pa dodati bodove
                    var pitanja = PitanjeKvizRepository.getPitanjaIzBaze(pokusaj!!.KvizId)
                    var pit = pitanja.find { it.id == idPitanje }


                    if(pit == null){
                        pit = PitanjeKvizRepository.getPitanja(pokusaj!!.KvizId).find { pitanje1 -> pitanje1.id == idPitanje  }
                    }

                    val database = AppDatabase.getInstance(context)

                    println("Pls work " + pit)

                    if(database.odgovorDao().duplikat(idPitanje, pokusaj.KvizId) == null) {
                        if (pit?.tacan == odgovor) {
                            bodovi += 50F
                            bod = 1F
                        }
                        database.odgovorDao().insert(
                            Odgovor(
                                (if (database.odgovorDao().maxId() == null) 0
                                else database.odgovorDao().maxId())!! + 1, odgovor, idPitanje, pokusaj.KvizId
                            )
                        )
                    }

                    return@withContext 50
                }
                catch (err: Exception){
                    println(err.printStackTrace())
                    return@withContext -1
                }
            })
        }



        suspend fun predajOdgovore(idKviza: Int) {

            return withContext(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getInstance(context)
                    val odgovori = getOdgovoreZaKvizIzBaze(idKviza)
                    val pokusajKviza = TakeKvizRepository.getPocetiKvizovi()!!.find { it.KvizId == idKviza }
                    var brojac = 0
                    for(odg in odgovori){
                        val response = ApiConfig.retrofit.postaviOdgovorKviz(getHash(), pokusajKviza!!.id, OdgovorBody(odg.odgovoreno, odg.PitanjeId, pokusajKviza.osvojeniBodovi.toFloat())
                        )
                        val responseBody = response.body()
                        when (responseBody) {
                            is Odgovor -> brojac++
                        }
                    }

                    if(odgovori.size == brojac){
                        db.kvizDao().updatePredan(true, idKviza)
                    }
                }
                catch(error: Exception){
                    println(error.printStackTrace())
                }
            }

        }


        suspend fun getSveOdgovoreIzBaze(): List<Odgovor>{
            return withContext(Dispatchers.IO){
                try {
                    val database = AppDatabase.getInstance(context)
                    val odg = database.odgovorDao().getSveOdgovoreIzBaze()
                    return@withContext odg

                }
                catch(error: Exception){
                    println(error.printStackTrace())
                    return@withContext listOf<Odgovor>()
                }
            }
        }


        suspend fun getOdgovoreZaKvizIzBaze(idKviz: Int): List<Odgovor> {
            var vratiOdgovore: MutableList<Odgovor> = mutableListOf()
            var listaPitanja = PitanjeKvizRepository.getPitanjaIzBaze(idKviz)
            var listaOdgovora = OdgovorRepository.getSveOdgovoreIzBaze()

            for(p in listaPitanja){
                for(o in listaOdgovora){
                    if(p.id == o.PitanjeId) vratiOdgovore.add(o)
                }
            }

            return vratiOdgovore
        }


    }
}