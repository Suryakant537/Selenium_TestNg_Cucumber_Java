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


public class PatchApiSteps {

    private RequestSpecification request;
    private Response response;

    Configuration APIconfig = new Configuration();
    JSONObject properties = APIconfig.readJSOnFile("APIconfig");

    String PatchApiURL = (String)properties.get("Patch_API_URL");
    public String payload = new String(Files.readAllBytes(Paths.get("src/test/java/Schema/PatchData.json")));
    public PatchApiSteps() throws IOException {
    }


    @Given("the user is on the Home page of Website for Patch API")
    public void the_user_is_on_the_page() {

        RestAssured.baseURI = PatchApiURL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(PatchApiURL);
    }

    @When("the user Updates user with the following job data")
    public void the_user_creates_a_new_user_with_the_following_data() throws IOException {
        response =  given()
                .body(payload)
                .when()
                .patch(PatchApiURL);

    }

    @Then("the response should contain the following updated job {string} in the list")
    public void theResponseShouldContainTheFollowingInTheList(String arg0) {
        String responseBody=response.getBody().asString();
        response.then().statusCode(200);
         responseBody.contains(arg0);

    }
}
