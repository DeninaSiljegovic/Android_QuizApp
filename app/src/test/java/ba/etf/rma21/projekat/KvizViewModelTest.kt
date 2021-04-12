package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.viewmodel.KvizViewModel
import org.junit.Test
import org.junit.Assert.*
import java.util.*

//moraju se run jedan po jedan
class KvizViewModelTest {
    private var kvizViewModel = KvizViewModel()

    @Test
    fun sviKvizoviTest(){
        assertEquals(kvizViewModel.getAll().size, 35)
    }

    @Test
    fun pocetnoStanjeApp(){
        assertEquals(kvizViewModel.getMyKvizes().size, 5)
        assertEquals(kvizViewModel.getMyDone().size, 2)
        assertEquals(kvizViewModel.getMyFuture().size, 1)
        assertEquals(kvizViewModel.getMyNotTaken().size, 1)
    }

    @Test
    fun uradjeniKvizTest(){
        kvizViewModel.upisiKviz("LD2-2")
        val nadji = kvizViewModel.getMyDone().find{ it.nazivGrupe.equals("LD2-2") && it.nazivPredmeta.equals("LD")}
        assertNotEquals(nadji, null)

        assertEquals(kvizViewModel.getMyDone().size, 3)

        kvizViewModel.upisiKviz("OBP2-1")
        assertEquals(kvizViewModel.getMyDone().size, 4)

    }

    @Test
    fun buduciKvizTest(){
        kvizViewModel.upisiKviz("RA3-1")
        val nadji = kvizViewModel.getMyFuture().find{ it.nazivGrupe.equals("RA3-1") && it.nazivPredmeta.equals("RA")}
        assertNotEquals(nadji, null)

        assertEquals(kvizViewModel.getMyFuture().size, 2)
    }

    @Test
    fun buduciKvizTest1(){
        kvizViewModel.upisiKviz("UUP1-1")
        val nadji = kvizViewModel.getMyKvizes().find{ it.nazivGrupe.equals("UUP1-1") && it.datumRada.equals(GregorianCalendar(1970, 0, 1).getTime()) }
        assertNotEquals(nadji, null)

        assertEquals(kvizViewModel.getMyKvizes().size, 6)
    }

    @Test
    fun prosliKvizTest(){
        kvizViewModel.upisiKviz("RPR2-2")
        val nadji = kvizViewModel.getMyNotTaken().find{ it.nazivGrupe.equals("RPR2-2") && it.nazivPredmeta.equals("RPR")}
        assertNotEquals(nadji, null)

        assertEquals(kvizViewModel.getMyNotTaken().size, 2)

        kvizViewModel.upisiKviz("DM2-2")
        assertEquals(kvizViewModel.getMyNotTaken().size, 3)
    }

    @Test
    fun sviMojiKvizoviTest(){
        kvizViewModel.upisiKviz("RPR2-2")
        kvizViewModel.upisiKviz("UUP1-1")
        assertEquals(kvizViewModel.getMyKvizes().size, 7)
    }


}