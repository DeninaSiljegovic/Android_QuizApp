package ba.etf.rma21.projekat.viewmodel

import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private var lastSelectedGodina: String = ""
    private var lastSelectedPredmet: String = ""
    private var lastSelectedGrupa: String = ""

    fun setlastSelectedGodina(msg: String?) {
        lastSelectedGodina = msg.toString()
    }

    fun getlastSelectedGodina(): String {
        return lastSelectedGodina
    }

    fun setlastSelectedPredmet(msg: String?) {
        lastSelectedPredmet = msg.toString()
    }

    fun getlastSelectedPredmet(): String {
        return lastSelectedPredmet
    }

    fun setlastSelectedGrupa(msg: String?) {
        lastSelectedGrupa = msg.toString()
    }

    fun getlastSelectedGrupaa(): String {
        return lastSelectedGrupa
    }
}

