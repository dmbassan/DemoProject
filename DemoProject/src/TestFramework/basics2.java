package TestFramework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class basics2 {
  @Test
  public void postData() {
	  RestAssured.baseURI="https://216.10.245.166";
	  given().
	  queryParam("key", "qaclick123")
	  .body("{"+
		    "\"location\": {"+
	        "\"lat\": -38.383494,"+
	        "\"lng\": 33.427362"+
	    "},"+
	    "\"accuracy\": 50,"+
	    "\"name\": \"Frontline House\","+
	    "\"phone number\": \"(+91)9838933937\""+
	"}").
	  when().post("/maps/api/place/add/json").
	  then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	  body("status", equalTo("OK"));
	 
  //create place = response (place id)
  }
}
