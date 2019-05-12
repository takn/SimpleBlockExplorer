package com.studio1r.simpleblockexplorer

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainIntegrationTest {

    class SimpleIdlingResource : IdlingResource {
        /** Returns the name of the resources (used for logging and idempotency of registration).  */
        override fun getName(): String {
            return "hi"
        }

        override fun isIdleNow(): Boolean {
            return true;
        }


        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        }

    }

//    @BeforeClass

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(SimpleIdlingResource())
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.studio1r.simpleblockexplorer", appContext.packageName)

    }

    @Test
    fun itemsCanExpand() {
        onView(ViewMatchers.withText("timestamp: ts0"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
