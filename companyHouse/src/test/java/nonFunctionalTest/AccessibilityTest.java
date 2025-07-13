package nonFunctionalTest;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import uiTests.BaseTestUi;

import java.net.URL;

public class AccessibilityTest extends BaseTestUi{

        private static final URL AXE_SCRIPT_URL = nonFunctionalTest.AccessibilityTest.class.getResource("/axe.min.js");

        @Test(groups={"Regression"},priority=0,testName="TC-001 - Verify Home Page follows WCAG 2.2 AA Standards",description="TVerify Home Page follows WCAG 2.2 AA Standards")
        public void testAccessibility() {
            JSONObject responseJSON = new AXE.Builder(driver, AXE_SCRIPT_URL).analyze();
            JSONArray violations = responseJSON.getJSONArray("violations");

            if (violations.length() == 0) {
                System.out.println("No accessibility violations found.");
            } else {
                AXE.writeResults("accessibilityReport", responseJSON);
                System.out.println(AXE.report(violations));
            }

            Assert.assertTrue(violations.length() == 0, "Accessibility violations found!");
        }
}
