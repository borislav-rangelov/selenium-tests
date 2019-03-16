package com.brangelov.seleniumtests.anotepad;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        ANotepadLandingPageTests.class,
        ANotepadLoginPageTests.class,
        ANotepadNoteTests.class
})
@RunWith(Suite.class)
public class ANotepadSuite {
}
