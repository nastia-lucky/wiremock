package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class Helper {


private static final String URI = "http://localhost:7070";


public static String post(Object object, String endpoint, int statusCode) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .accept(ContentType.JSON)
			                    .contentType(ContentType.JSON)
			                    .body(object)
			                    .when()
			                    .log()
			                    .all()
			                    .post(endpoint);
	response.then()
			.log()
			.all()
			.assertThat()
			.statusCode(statusCode);
	return response.asString();
}

public static int postAndGetStatusCode(Object object, String endpoint) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .accept(ContentType.JSON)
			                    .contentType(ContentType.JSON)
			                    .body(object)
			                    .when()
			                    .log()
			                    .all()
			                    .post(endpoint);
	response.then()
			.log()
			.all();
	return response.statusCode();
}


public static String put(Object object, String endpoint, int statusCode) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .accept(ContentType.JSON)
			                    .contentType(ContentType.JSON)
			                    .body(object)
			                    .when()
			                    .log()
			                    .all()
			                    .put(endpoint);
	response.then()
			.log()
			.all()
			.assertThat()
			.statusCode(statusCode);
	
	return response.asString();
	
}

public static String get(String endpoint, int statusCode) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given()
			                                            .log()
			                                            .all();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .log()
			                    .all()
			                    .when()
			                    .log()
			                    .all()
			                    .get(endpoint);
	response.then()
			.statusCode(statusCode).log().all();
	return response.asString();
}

public static String getWithJSONValidation(String endpoint, String jsonSchemaPath, int statusCode) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given()
			                                            .log()
			                                            .all();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .log()
			                    .all()
			                    .when()
			                    .log()
			                    .all()
			                    .get(endpoint);
	ValidatableResponse validated = response.then().log().all().assertThat()
			                                .statusCode(200);
	validated.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)).log().all();
	return response.asString();
}


public static String delete(String endpoint, int statusCode) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .when()
			                    .log()
			                    .all()
			                    .delete(endpoint);
	
	response.then()
			.log()
			.all()
			.assertThat()
			.statusCode(statusCode);
	return response.asString();
}


public static int getStatusCode(String endpoint) {
	RequestSpecification requestSpecification = RestAssured
			                                            .given()
			                                            .log()
			                                            .all();
	Response response = requestSpecification
			                    .baseUri(URI)
			                    .log()
			                    .all()
			                    .when()
			                    .log()
			                    .all()
			                    .get(endpoint);
	response.then().log().all();
	return response.getStatusCode();
}


}
