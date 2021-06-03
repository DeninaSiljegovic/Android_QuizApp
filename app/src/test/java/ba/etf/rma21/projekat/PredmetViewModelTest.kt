package ba.etf.rma21.projekat

import ba.etf.rma21.projekat.viewmodel.PredmetIGrupaViewModel
import org.junit.Test

import org.junit.Assert.*

////test filtriranja predmeta po godinama
//class PredmetViewModelTest {
//    private var predmetViewModel = PredmetIGrupaViewModel()
//
//    @Test
//    fun godinaPrvaTest(){
//        assertEquals(predmetViewModel.getPredmetiGodine(0).size, 6)
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(0).size, 3)
//
//        predmetViewModel.upisi("VIS", 1)
//
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(0).size, 2)
//    }
//
//    @Test
//    fun godinaDrugaTest(){
//        assertEquals(predmetViewModel.getPredmetiGodine(1).size, 7)
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(1).size, 7)
//
//        predmetViewModel.upisi("RMA", 2)
//
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(1).size, 6)
//    }
//
//    @Test
//    fun godinaTrecaTest(){
//        assertEquals(predmetViewModel.getPredmetiGodine(2).size, 2)
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(2).size, 2)
//
//        predmetViewModel.upisi("RA", 3)
//
//        assertEquals(predmetViewModel.getPredmetiNaKojeNijeUpisan(2).size, 1)
//    }
//
//    @Test
//    fun godinaCetvrtaTest(){
//        assertEquals(predmetViewModel.getPredmetiGodine(3).size, 0)
//    }
//
//    @Test
//    fun godinaPetaTest(){
//        assertEquals(predmetViewModel.getPredmetiGodine(4).size, 0)
//    }
//}