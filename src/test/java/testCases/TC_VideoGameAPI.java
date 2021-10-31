

package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class TC_VideoGameAPI {

	@Test
	public void test_getAllVideoGames()
	{
		given()
		
		.when()
		.get("http://localhost:8080/app/videogames")
		.then()
		
		.statusCode(200);
		
	}
	
	
//@Test(priority = 2)
	public void test_addNewVideoGame() {
		HashMap data=new HashMap();
		data.put("id", "121");
		data.put("name", "Spider-Man");
		data.put("releaseDate", "2021-10-30T19:15:24");
		data.put("reviewScore", "3");
		data.put("category", "3");
		data.put("rating", "3");
		
	Response res=
		given()
		.contentType("application/json")
		.when()
		.post("http://localhost:8080/app/videogames")
		.then()
		.statusCode(201)
		
	     .log().body()
		.extract().response();
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
		
	}
	
	
		@Test(priority = 3)
		public void test_getVideoGame() {

	

			given()
			.contentType("application/json")
			.when()
			.get("http://localhost:8080/app/videogames/2")
			.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("2"))
			.body("videoGame.name", equalTo("Gran Turismo 3"));
	
		}
	@Test(priority = 4)
		public void test_UpdateVideoGame() {
			HashMap data=new HashMap();
			data.put("id", "2");
			data.put("name", "Spider-Man");
			data.put("releaseDate", "2021-10-30T19:15:24");
			data.put("reviewScore", "3");
			given()
			.contentType("application/json")
			.body(data)
			.when()
			.put("http://localhost:8080/app/videogames/2")
			.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("2"))
			.body("videoGame.name", equalTo("Spider-Man"));
		}
	
}
