package myServiceTests;


import com.fasterxml.jackson.core.JsonProcessingException;
import dataProviders.UsersTestData;
import models.CourseModel;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import resolvers.Endpoints;
import resolvers.EndpointsResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class UsersStubTests extends BaseStubTests {


@Test(dataProvider = "getUserStubData", dataProviderClass = UsersTestData.class)
public static void testGetUser(UserModel userModel) throws IOException {
	String userEndpoint = EndpointsResolver.resolveEndpoint(Endpoints.GET_USER);
	buildStub(urlMatching(userEndpoint + "[0-9]+"), userModel, 200);
	String response = sendRequest(userEndpoint + "1");
	UserModel user = null;
	try {
		user = objectMapper.readValue(response, UserModel.class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	Assert.assertTrue(user.equals(userModel));
}

@Test(dataProvider = "getCoursesData", dataProviderClass = UsersTestData.class)
public static void testGetCourses(List<CourseModel> courses) throws IOException {
	String endpoint = EndpointsResolver.resolveEndpoint(Endpoints.ALL_COURSES);
	buildStub(urlEqualTo(endpoint), courses, 200);
	String response = sendRequest(endpoint);
	CourseModel[] gotCourses = null;
	try {
		gotCourses = objectMapper.readValue(response, CourseModel[].class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	Assert.assertTrue(Arrays.asList(gotCourses).containsAll(courses) && Arrays.asList(gotCourses).size() == courses.size());
}

@Test(dataProvider = "getUsersStubData", dataProviderClass = UsersTestData.class)
public static void testUsersTestData(List<UserModel> users) throws IOException {
	String endpoint = EndpointsResolver.resolveEndpoint(Endpoints.ALL_USERS);
	buildStub(urlEqualTo("/users/get/all"), users, 200);
	String response = sendRequest(endpoint);
	UserModel[] gotUsers = null;
	try {
		gotUsers = objectMapper.readValue(response, UserModel[].class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	Assert.assertTrue(Arrays.asList(gotUsers).containsAll(users) && Arrays.asList(gotUsers).size() == users.size());
}


}
