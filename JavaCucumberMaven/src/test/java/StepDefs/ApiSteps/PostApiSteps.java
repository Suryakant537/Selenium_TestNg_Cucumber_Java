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


public class PostApiSteps {

    private RequestSpecification request;
    private Response response;
    Configuration APIconfig = new Configuration();
    JSONObject properties = APIconfig.readJSOnFile("APIconfig");

    String PostApiURL = (String)properties.get("Post_API_URL");
    public String payload = new String(Files.readAllBytes(Paths.get("src/test/java/Schema/PostData.json")));
    public PostApiSteps() throws IOException {
    }


    @Given("the user is on the Home page")
    public void the_user_is_on_the_page() {
        RestAssured.baseURI = PostApiURL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(PostApiURL);
    }

    @When("the user creates a new user with the following data:")
    public void the_user_creates_a_new_user_with_the_following_data() throws IOException {
        response =   given()
                .body(payload)
                .when()
                .post(PostApiURL);

    }

    @Then("the response should contain the following {string} in the list")
    public void theResponseShouldContainTheFollowingInTheList(String arg0) {
        String responseBody=response.getBody().asString();
        response.then().statusCode(201);
         responseBody.contains(arg0);
    }
}
