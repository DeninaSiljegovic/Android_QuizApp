package ba.etf.rma21.projekat

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun prviZadatakTest1(){
        //provjera da li se sacuvaju odabrane info za upis kad se ponovo vrati
        onView(withId(R.id.predmeti)).perform(click())

        onView(withId(R.id.odabirGodina)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("2"))).perform(click())
        onView(withId(R.id.odabirPredmet)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD2-2"))).perform(click())

        Espresso.pressBack()
        onView(withId(R.id.predmeti)).perform(click())
        onView(withSubstring("2"))
        onView(withSubstring("OOAD"))
        onView(withSubstring("OOAD2-2"))

        onView(withId(R.id.dodajPredmetDugme)).perform(click())
        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
        onView(withSubstring("Uspješno ste upisani u grupu OOAD2-2 predmeta OOAD!"))
        Espresso.pressBack()
    }

    @Test
    fun prviZadatakTest2(){
        //isto kao prvi test samo umjesto back idemo klick na kvizovi opciju
        onView(withId(R.id.predmeti)).perform(click())

        onView(withId(R.id.odabirGodina)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("2"))).perform(click())
        onView(withId(R.id.odabirPredmet)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("OOAD2-2"))).perform(click())

        onView(withId(R.id.kvizovi)).perform(click())
        onView(withId(R.id.predmeti)).perform(click())
        onView(withSubstring("2"))
        onView(withSubstring("OOAD"))
        onView(withSubstring("OOAD2-2"))

        onView(withId(R.id.dodajPredmetDugme)).perform(click())
        onView(withId(R.id.tvPoruka)).check(ViewAssertions.matches(isDisplayed()))
        onView(withSubstring("Uspješno ste upisani u grupu OOAD2-2 predmeta OOAD!"))
        onView(withId(R.id.kvizovi)).perform(click())
    }

    @Test
    fun drugiZadatakTest1(){


    }

    @Test
    fun drugiZadatakTest2(){

    }


}