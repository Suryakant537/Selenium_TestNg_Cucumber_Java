package StepDefs.ApiSteps;

import Common.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class PutApiSteps {

    private RequestSpecification request;
    private Response response;

    Configuration APIconfig = new Configuration();
    JSONObject properties = APIconfig.readJSOnFile("APIconfig");
    String PutApiURL = (String)properties.get("Put_API_URL");
    public String payload = new String(Files.readAllBytes(Paths.get("src/test/java/Schema/PutData.json")));


    public PutApiSteps() throws IOException {
    }


    @Given("the user is on the Home page of Website")
    public void the_user_is_on_the_page() {
        RestAssured.baseURI = PutApiURL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(PutApiURL);
    }

    @When("the user Updates  new user with the following data")
    public void the_user_creates_a_new_user_with_the_following_data() throws IOException {
        response= given()
                .body(payload)
                .when()
                .put(PutApiURL);
    }

    @Then("the response should contain the following updated {string} in the list")
    public void theResponseShouldContainTheFollowingInTheList(String arg0) {
        String responseBody=response.getBody().asString();
        response.then().statusCode(200);
         responseBody.contains(arg0);
    }
}
