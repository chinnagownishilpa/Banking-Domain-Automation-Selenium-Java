package Runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "classpath:Features/customer_flow.feature",
	    glue = {
	        "stepdefinitions.flow",
	        "stepdefinitions.hooks",
	        "stepdefinitions.common"
	    },
	    plugin = {
	        "pretty",
	        "html:target/cucumber.html"
	    },
	    //tags= "@regression",
	    dryRun = false
	)

public class TestRunner extends AbstractTestNGCucumberTests{
	
//@Override
//@DataProvider(parallel=true)
//public Object[][] scenarios(){
//	return super.scenarios();
//}

}
