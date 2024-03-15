package StepDefs.ApiSteps;


import Common.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.net.URI;


public class GetApiSteps {

    private Scenario scenario;
    private Response response;
    private final String BASE_URL = "https://reqres.in/api/users?page=2";

    Configuration APIconfig = new Configuration();
    JSONObject properties = APIconfig.readJSOnFile("APIconfig");

    String GetApiURL = (String)properties.get("Get_API_URL");

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = GetApiURL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
    }

    @Then("Response Code {string} is validated")
    public void response_is_validated(String responseMessage) throws InterruptedException {
        int responseCode = response.then().extract().statusCode();
        Thread.sleep(2000);
        Assert.assertEquals(responseMessage, responseCode+"");
    }

    @Then("Response Data Should Contain  {string}")
    public void responseIsArrayWith(String arg0) {
        response.then().statusCode(200);
        response = response.then().extract().response();
        scenario.log("Response Received == " + response.asString());
        String responseData = response.asString();
        responseData.contains(arg0);


    }

}
