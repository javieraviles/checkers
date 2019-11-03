package es.urjccode.mastercloudapps.adcs.draughts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.AllControllerTests;
import es.urjccode.mastercloudapps.adcs.draughts.models.AllModelTests;
import es.urjccode.mastercloudapps.adcs.draughts.views.AllViewTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AllModelTests.class, AllControllerTests.class, AllViewTests.class })
public final class AllTests {
}