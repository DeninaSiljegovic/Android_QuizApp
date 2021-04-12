package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.viewmodel.GrupaViewModel
import org.junit.Test

import org.junit.Assert.*

class GrupaViewModelTest {
    private var grupaViewModel = GrupaViewModel()


    @Test
    fun Test1(){
        assertEquals(grupaViewModel.getGroupsByPredmet("DM").size, 2)
        assertEquals(grupaViewModel.getGroupsByPredmet("LD").size, 3)
    }


    @Test
    fun Test2(){
        assertEquals(grupaViewModel.getGroupsByPredmet("RA").size, 1)
        assertEquals(grupaViewModel.getGroupsByPredmet("TP").size, 2)
    }


    @Test
    fun Test3(){
        assertEquals(grupaViewModel.getGroupsByPredmet("MLTI").size, 1)
        assertEquals(grupaViewModel.getGroupsByPredmet("OOAD").size, 3)
    }

}