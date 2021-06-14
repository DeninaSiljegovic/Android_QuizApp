package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class AccountRepository {

    companion object {
        private lateinit var context: Context

        fun setContext(_context: Context){
            context=_context
        }
        var acHash: String = "29465788-eff2-4984-93b4-f205077b0b09"

        suspend fun postaviHash(ACHash: String): Boolean {
            return withContext(Dispatchers.IO){
                try {
                    val lastUser = acHash
                    val pom = acHash
                    acHash = ACHash

                    var dataBase = AppDatabase.getInstance(context)
                    val response = ApiConfig.retrofit.dajAccount(acHash)

                    if (response.body() is Account) {
                        dataBase.accountDao().updateUser(response.body()!!.id, response.body()!!.student, response.body()!!.acHash, lastUser)
                        updatePodatke()
                        return@withContext true
                    }
                    return@withContext false
                }
                catch(error: Exception){
                    return@withContext false
                }
            }
        }

        fun getHash(): String {
            return acHash;
        }

        suspend fun updatePodatke(){
            return withContext(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getInstance(context)
                    deleteFromDatabase()
                    val noviKvizovi = KvizRepository.getUpisane()
                    val noveGrupe = PredmetIGrupaRepository.getUpisaneGrupe()
                    val noviPredmeti = PredmetIGrupaRepository.getPredmeti().filter { predmet -> noveGrupe!!.map { novaGrupa -> novaGrupa.id }.contains(predmet.id)}
                    val noviPokusaji = TakeKvizRepository.getPocetiKvizovi()

                    if(noviPokusaji !=null)
                        db.kvizTakenDao().insertAll(noviPokusaji)

                    for(noviKviz in noviKvizovi){
                        if(db.kvizDao().duplikat(noviKviz.id) == null)
                            db.kvizDao().insert(noviKviz)

                        val pitanja = PitanjeKvizRepository.getPitanja(noviKviz.id)

                        for(p in pitanja){
                            p.KvizId = noviKviz.id

                            if(db.pitanjeDao().duplikat(p.id) == null)
                                db.pitanjeDao().insert(p)

                            else{
                                p.id = db.pitanjeDao().generateId()
                                db.pitanjeDao().insert(p)
                            }
                        }

                        val grupe = PredmetIGrupaRepository.getGrupeZaKviz(noviKviz.id)

                        for(g in grupe){
                            if(db.grupaDao().duplikat(g.id) == null)
                                db.grupaDao().insert(g)
                        }

                        for(pr in noviPredmeti){
                            if(db.predmetDao().duplikat(pr.id) == null)
                                db.predmetDao().insert(pr)
                        }

                    }

                } catch (error: Exception) {
                    println(error.printStackTrace())
                }
            }
        }

        suspend fun deleteFromDatabase(){
            return withContext(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getInstance(context)
                    db.grupaDao().deleteAll()
                    db.predmetDao().deleteAll()
                    db.pitanjeDao().deleteAll()
                    db.kvizDao().deleteAll()
                    db.kvizTakenDao().deleteAll()
                } catch (error: Exception) {
                    println(error.printStackTrace())
                }
            }
        }
    }

}