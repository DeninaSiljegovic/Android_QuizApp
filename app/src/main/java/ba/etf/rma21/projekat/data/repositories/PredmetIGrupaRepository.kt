package ba.etf.rma21.projekat.data.repositories


import android.util.Log
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.AccountRepository.Companion.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PredmetIGrupaRepository {

    companion object {

        suspend fun getPredmeti(): List<Predmet> {
            return withContext(Dispatchers.IO){
                val response = ApiConfig.retrofit.getPredmeti()

                return@withContext response.body()
            }!!
        }

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

        suspend fun upisiUGrupu(idGrupa: Int): Boolean {
            return withContext(Dispatchers.IO) {
                val response = ApiConfig.retrofit.upisiUGrupu(idGrupa, getHash())
                val responseBody = response.body()

                //CHECK IF THIS IS OK
                Log.d("ODGOVOR UPISA U GRUPU ", responseBody.toString())

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

    }

}