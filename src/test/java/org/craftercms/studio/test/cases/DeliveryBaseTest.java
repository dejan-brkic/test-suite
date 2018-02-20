package org.craftercms.studio.test.cases;

import org.craftercms.studio.test.pages.DeliveryHomePage;
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


/**
 * All Test Cases should extend this class
 */
public class DeliveryBaseTest {

    protected WebDriverManager driverManager;
    protected UIElementsPropertiesManager uiElementsPropertiesManager;
    protected ConstantsPropertiesManager constantsPropertiesManager;
    protected DeliveryHomePage deliveryHome;
    
    @BeforeClass
    public void setUp() {
        driverManager = new WebDriverManager();
        uiElementsPropertiesManager = new UIElementsPropertiesManager(FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
        constantsPropertiesManager = new ConstantsPropertiesManager(FilesLocations.CONSTANTSPROPERTIESFILEPATH);
        driverManager.setConstantsPropertiesManager(constantsPropertiesManager);
        deliveryHome = new DeliveryHomePage(driverManager, "testsitefordeliverytest");
    }

    @AfterClass
    public void close() {
        driverManager.closeConnection();
    }

}
