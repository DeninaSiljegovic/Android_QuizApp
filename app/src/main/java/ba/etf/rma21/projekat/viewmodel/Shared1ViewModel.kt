package ba.etf.rma21.projekat.viewmodel

class Shared1ViewModel {
    companion object {
        private var listaInfo: ArrayList<Pair<String, String>> = arrayListOf()
        private var kviz = ""
        private var predmet = ""
    }

        fun setKviz(k: String) {
            kviz = k
        }

        fun getKviz(): String {
            return kviz
        }

        fun setPredmet(k: String) {
            predmet = k
        }

        fun getPredmet(): String {
            return predmet
        }

        fun dodajZavrseni(k: String, p: String) {
            val pair = Pair(k, p)
            listaInfo.add(pair)
        }

        fun daLiJeUradjen(k: String, p: String): Boolean {
            if (listaInfo.size > 0) if (listaInfo.contains(Pair(k, p))) return true
            return false
        }

}