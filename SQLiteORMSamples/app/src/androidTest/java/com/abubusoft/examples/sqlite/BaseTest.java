package com.abubusoft.examples.sqlite;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.abubusoft.kripton.android.KriptonLibrary;

import org.junit.Before;

/**
 * Created by xcesco on 30/11/2017.
 */

public class BaseTest {

    @Before
    public void setup() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        KriptonLibrary.init(appContext);

    }

}
