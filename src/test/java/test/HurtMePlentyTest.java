package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudMainPage;
import page.GoogleCloudPricingCalculatorPage;
import spec.TestSpec;

public class HurtMePlentyTest extends TestSpec {

    String SEARCH_TERM_MAIN_PAGE = "Google Cloud Platform Pricing Calculator";
    String PROVISIONING_MODEL = "Provisioning model: Regular";
    String INSTANCE_TYPE = "Instance type: n1-standard-8";
    String REGION_NAME = "Region: Frankfurt";
    String LOCAL_SSD = "Local SSD: 2x375 GiB";
    String COMMITMENT_TERM = "Commitment term: 1 Year";
    String MANUALLY_CALCULATED_VALUE = "Total Estimated Cost: USD 1,081.20 per 1 month";
    GoogleCloudMainPage cloudMainPage;

    GoogleCloudPricingCalculatorPage cloudCalculatorPage;

    @Test(description = "Fill in estimation form")
    public void fillInEstimationForm() {
        cloudMainPage = new GoogleCloudMainPage(driver)
                .openPage()
                .searchTerm(SEARCH_TERM_MAIN_PAGE)
                .clickCalculatorPage();
        cloudCalculatorPage = new GoogleCloudPricingCalculatorPage(driver)
                .switchToCalculatorIFrame()
                .fillInEstimationForm()
                .clickAddToEstimateButton();
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check selected Provisioning Model")
    public void checkProvisioningModel() {
        String provisioningModel = cloudCalculatorPage.getProvisioningModel();

        Assert.assertEquals(provisioningModel, PROVISIONING_MODEL, "Selected provisioning model isn't 'Regular'");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check selected Instance Type")
    public void checkInstanceType() {
        String instanceType = cloudCalculatorPage.getInstanceType();

        Assert.assertTrue(instanceType.contains(INSTANCE_TYPE), "Selected Instance type isn't 'n1-standard-8'");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check selected Region name")
    public void checkRegionName() {
        String regionName = cloudCalculatorPage.getRegionName();

        Assert.assertEquals(regionName, REGION_NAME, "Selected Region isn't 'Frankfurt'");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check selected Local SSD")
    public void checkLocalSSDValue() {
        String localSSD = cloudCalculatorPage.getLocalSSD();

        Assert.assertTrue(localSSD.contains(LOCAL_SSD), "Selected Local SSD isn't '2x375 GiB'");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check Commitment Term value")
    public void checkCommitmentTermValue() {
        String commitmentTerm = cloudCalculatorPage.getCommitmentTerm();

        Assert.assertEquals(commitmentTerm, COMMITMENT_TERM, "Selected Commitment Term isn't '1 Year'");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Compare Total Cost between autotest and manual")
    public void checkCostOnCalcPage() {
        String costOnCalcPage = cloudCalculatorPage.getTotalCost();

        Assert.assertEquals(costOnCalcPage, MANUALLY_CALCULATED_VALUE, "Total Cost from autotest doesn't match the one from the manual on Calculator page");
    }
}
