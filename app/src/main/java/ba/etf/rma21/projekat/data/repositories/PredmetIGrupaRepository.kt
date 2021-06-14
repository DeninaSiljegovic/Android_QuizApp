package ba.etf.rma21.projekat.data.repositories


import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.models.AppDatabase
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Path

class PredmetIGrupaRepository {

    companion object {
        private lateinit var context: Context

        //funkcija za upis u bazu - jedne grupe
        suspend fun writeGrupa(grupa: Grupa) : String?{
            return withContext(Dispatchers.IO) {
                try{
                    val db = AppDatabase.getInstance(context)
                    db.grupaDao().insertAll(grupa)
                    return@withContext "success"
                }
                catch(error:Exception){
                    return@withContext null
                }
            }
        }

        //funkcija za upis u bazu - jednof predmeta
        suspend fun writePredmet(predmet: Predmet) : String?{
            return withContext(Dispatchers.IO) {
                try{
                    val db = AppDatabase.getInstance(context)
                    db.predmetDao().insertAll(predmet)
                    return@withContext "success"
                }
                catch(error:Exception){
                    return@withContext null
                }
            }
        }




        //svi predmeti - dobavlja se sa apija
        suspend fun getPredmeti(): List<Predmet> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getPredmeti()

                return@withContext response.body()
            }!!
        }

        suspend fun getPredmetSaId(predmetId : Int): Predmet{
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getPredmetSaId(predmetId)

                return@withContext response.body()
            }!!
        }

        //lista svih grupa - sa apija
        suspend fun getGrupe(): List<Grupa> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getGrupe()

                return@withContext response.body()
            }!!
        }

        suspend fun getGrupeZaPredmet(idPredmeta: Int): List<Grupa> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getGrupeZaPredmet(idPredmeta)

                return@withContext response.body()
            }!!
        }

        suspend fun getGrupeZaKviz(idKviza: Int): List<Grupa> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit. getGrupeZaKviz(idKviza)

                return@withContext response.body()
            }!!
        }

        suspend fun upisiUGrupu(idGrupa: Int): Boolean {
            return withContext(Dispatchers.IO) {
                val response = ApiConfig.retrofit.upisiUGrupu(idGrupa, getHash())
                val responseBody = response.body()

                if (responseBody.toString().contains("je dodan u grupu")) return@withContext true
                return@withContext false
            }
        }

        suspend fun getUpisaneGrupe(): List<Grupa> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getUpisaneGrupe(getHash())

                return@withContext response.body()
            }!!
        }





        suspend fun upisiKorisnikoveGrupeUBazu(){
            val grupe = getUpisaneGrupe()
            for(g in grupe) writeGrupa(g)
        }


        suspend fun upisiKorisnikovePredmeteUBazu(){
            val grupe = getUpisaneGrupe()
            val predmeti = getPredmeti()

            for(g in grupe){
                for(p in predmeti)
                    if(g.PredmetId == p.id) writePredmet(p)
            }

        }


    }

}