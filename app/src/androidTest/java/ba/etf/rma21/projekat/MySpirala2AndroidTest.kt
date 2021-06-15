//package ba.etf.rma21.projekat
//
//import androidx.recyclerview.widget.RecyclerView
//import androidx.test.espresso.Espresso
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.assertion.ViewAssertions
//import androidx.test.espresso.contrib.NavigationViewActions
//import androidx.test.espresso.contrib.RecyclerViewActions
//import androidx.test.espresso.intent.rule.IntentsTestRule
//import androidx.test.espresso.matcher.ViewMatchers
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import ba.etf.rma21.projekat.data.repositories.KvizRepository
//import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
//import org.hamcrest.CoreMatchers
//import org.hamcrest.Matchers
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@RunWith(AndroidJUnit4::class)
//class MySpirala2AndroidTest {
//
//    @get:Rule
//    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)
//
//    @Test
//    fun prviZadatakTest1(){
//        //provjera da li se sacuvaju odabrane info za upis kad se ponovo vrati
//        onView(withId(R.id.predmeti)).perform(click())
//
//        onView(withId(R.id.odabirGodina)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("2"))).perform(click())
//        onView(withId(R.id.odabirPredmet)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD"))).perform(click())
//        onView(withId(R.id.odabirGrupa)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD2-2"))).perform(click())
//
//        Espresso.pressBack()
//        onView(withId(R.id.predmeti)).perform(click())
//        onView(withSubstring("2"))
//        onView(withSubstring("OOAD"))
//        onView(withSubstring("OOAD2-2"))
//
//        onView(withId(R.id.dodajPredmetDugme)).perform(click())
//        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withSubstring("Uspješno ste upisani u grupu OOAD2-2 predmeta OOAD!"))
//        Espresso.pressBack()
//    }
//
//    @Test
//    fun prviZadatakTest2(){
//        //isto kao prvi test samo umjesto back idemo klick na kvizovi opciju
//        onView(withId(R.id.predmeti)).perform(click())
//
//        onView(withId(R.id.odabirGodina)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("2"))).perform(click())
//        onView(withId(R.id.odabirPredmet)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("LD"))).perform(click())
//        onView(withId(R.id.odabirGrupa)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("LD2-3"))).perform(click())
//
//        onView(withId(R.id.kvizovi)).perform(click())
//        onView(withId(R.id.predmeti)).perform(click())
//        onView(withSubstring("2"))
//        onView(withSubstring("LD"))
//        onView(withSubstring("LD2-3"))
//
//        onView(withId(R.id.dodajPredmetDugme)).perform(click())
//        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withSubstring("Uspješno ste upisani u grupu OOAD2-2 predmeta OOAD!"))
//        onView(withId(R.id.kvizovi)).perform(click())
//    }
//
//    @Test
//    fun drugiZadatakTest1(){
//        //bez zaustavljanja rad kviza
//        onView(withId(R.id.filterKvizova)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
//        val kvizovi = KvizRepository.getMyKvizes()
//        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
//                hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
//
//        val pitanja = PitanjeKvizRepository.getPitanja(kvizovi[0].naziv, kvizovi[0].nazivPredmeta)
//        var i = 0
//        for (pitanje in pitanja) {
//            onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(i))
//            Espresso.onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.odgovoriLista)).atPosition(1).perform(click())
//            i++
//        }
//
//        onView(withId(R.id.predajKviz)).perform(click())
//        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withSubstring("Završili ste kviz"))
//        onView(withId(R.id.kvizovi)).perform(click())
//    }
//
//    @Test
//    fun drugiZadatakTest2(){
//        //izadje pa nastavi radit
//        onView(withId(R.id.filterKvizova)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
//        val kvizovi = KvizRepository.getMyKvizes()
//        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
//                hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
//
//        val pitanja = PitanjeKvizRepository.getPitanja(kvizovi[0].naziv, kvizovi[0].nazivPredmeta)
//        var i = 0
//        for (pitanje in pitanja) {
//            onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(i))
//            Espresso.onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.odgovoriLista)).atPosition(1).perform(click())
//            if(i != pitanja.size - 2) i++
//        }
//
//        onView(withId(R.id.zaustaviKviz)).perform(click())
//        onView(withId(R.id.kvizovi)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withId(R.id.predmeti)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withId(R.id.predajKviz)).check(ViewAssertions.matches(CoreMatchers.not(isDisplayed())))
//        onView(withId(R.id.zaustaviKviz)).check(ViewAssertions.matches(CoreMatchers.not(isDisplayed())))
//
//        onView(withId(R.id.filterKvizova)).perform(click())
//        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Svi moji kvizovi"))).perform(click())
//        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[0].naziv)),
//                hasDescendant(withText(kvizovi[0].nazivPredmeta))), click()))
//
//        onView(withId(R.id.predajKviz)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withId(R.id.zaustaviKviz)).check(ViewAssertions.matches(isDisplayed()))
//
//        onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(i+1))
//        Espresso.onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.odgovoriLista)).atPosition(1).perform(click())
//
//        onView(withId(R.id.predajKviz)).perform(click())
//        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
//        onView(withSubstring("Završili ste kviz"))
//        onView(withId(R.id.kvizovi)).perform(click())
//
//    }
//
//
//}