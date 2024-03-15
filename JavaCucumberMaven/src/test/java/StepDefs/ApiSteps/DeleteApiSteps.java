package StepDefs.ApiSteps;

import Common.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class DeleteApiSteps {

    private RequestSpecification request;
    private Response response;

    Configuration APIconfig = new Configuration();
    JSONObject properties = APIconfig.readJSOnFile("APIconfig");

    String DeleteApiURL = (String)properties.get("Delete_API_URL");

    public String payload = new String(Files.readAllBytes(Paths.get("src/test/java/Schema/DeleteData.json")));
    public DeleteApiSteps() throws IOException {
    }


    @Given("the user is on the Home page of Website for Delete API")
    public void the_user_is_on_the_page() {

        RestAssured.baseURI = DeleteApiURL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(DeleteApiURL);
    }

    @When("the user Deletes new user with the following data")
    public void the_user_creates_a_new_user_with_the_following_data() throws IOException {
        response =  given()
                .body(payload)
                .when().delete(DeleteApiURL);
    }


    @Then("the response should not contain the following updated {string} in the list")
    public void theResponseShouldNotContainTheFollowingUpdatedInTheList(String arg0) {
        String responseBody=response.getBody().asString();
        response.then().statusCode(204);
        Assert.assertFalse(responseBody.contains(arg0));
    }
}
