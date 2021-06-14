package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorBody
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        suspend fun postaviOdgovorKviz(idKvizTaken:Int, idPitanje:Int, odgovor:Int):Int{
            return (withContext(Dispatchers.IO){
                val pokusaj = TakeKvizRepository.getPocetiKvizovi()!!.find{it.id == idKvizTaken}
                var bod = 0F

                //pr da li je tacno odgovoreno pa dodati bodove
                val pit = PitanjeKvizRepository.getPitanja(pokusaj!!.KvizId).find{ it.id == idPitanje}
                if(pit?.tacan == odgovor){
                    pokusaj.osvojeniBodovi += 50F
                    bod = 1F }

                val response = ApiConfig.retrofit.postaviOdgovorKviz(getHash(), idKvizTaken, OdgovorBody(odgovor, idPitanje, bod))

                when(response.body()){
                    is Odgovor -> return@withContext pokusaj.osvojeniBodovi.toInt() //todo OVO VRACA UKUPNE BODOVE NA KVIZU
                    else -> return@withContext  -1
                }
            })
        }

    }
}