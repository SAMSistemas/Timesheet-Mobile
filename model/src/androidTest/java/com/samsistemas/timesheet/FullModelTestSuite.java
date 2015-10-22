package com.samsistemas.timesheet;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

public class FullModelTestSuite extends TestSuite {

    public static Test suite() {
        return new TestSuiteBuilder(FullModelTestSuite.class)
                .includeAllPackagesUnderHere()
                .build();
    }

    public FullModelTestSuite() { super(); }
}