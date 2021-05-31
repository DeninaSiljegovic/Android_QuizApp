package ba.etf.rma21.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AccountRepository {

    companion object {
        var acHash: String = "29465788-eff2-4984-93b4-f205077b0b09"

        suspend fun postaviHash(ACHash: String): Boolean {
            return withContext(Dispatchers.IO){
                val pom = acHash
                acHash = ACHash
                return@withContext acHash != pom
            }
        }

        fun getHash(): String {
            return acHash
        }
    }

}