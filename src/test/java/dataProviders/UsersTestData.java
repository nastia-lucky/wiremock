package dataProviders;


import models.CourseModel;
import models.UserModel;

import java.util.Arrays;


public class UsersTestData {

@org.testng.annotations.DataProvider(name = "getUserStubData")
public static Object[][] getUserStubData() {
	UserModel userModel = new UserModel("Test user", "QA", "test@test.test", 23);
	return new Object[][]{{userModel}};
}


@org.testng.annotations.DataProvider(name = "getUsersStubData")
public static Object[][] getUsersStubData() {
	UserModel userModel = new UserModel("Test user", "QA", "test@test.test", 23);
	UserModel userModel2 = new UserModel("Test user 2", "QA 2", "test2@test.test", 22);
	return new Object[][]{{Arrays.asList(userModel, userModel2)}};
}

@org.testng.annotations.DataProvider(name = "getCoursesData")
public static Object[][] getCoursesData() {
	return new Object[][]{{Arrays.asList(new CourseModel("QA Java", 15000), new CourseModel("Java", 12000))}};
}

@org.testng.annotations.DataProvider(name = "getRealUserValidData")
public static Object[][] getRealUserValidData() {
	UserModel userModel = new UserModel("Test user 1", "QA 1", "test23@test.test", 23);
	UserModel userModel2 = new UserModel("Test user 3", "", "", 13);
	return new Object[][]{{userModel}, {userModel2}};
}

@org.testng.annotations.DataProvider(name = "getRealUserNotValidData")
public static Object[][] getRealUserNotValidData() {
	UserModel userModel = new UserModel(null, "QA 1", "test23@test.test", 23);
	UserModel userModel2 = new UserModel(null, "QA 1", "test23@test.test", 23);
	return new Object[][]{{userModel}, {userModel2}};
}

@org.testng.annotations.DataProvider(name = "getRealUserUpdateTestData")
public static Object[][] getRealUserUpdateTestData() {
	UserModel userModel = new UserModel("name", "QA 1", "test23@test.test", 23);
	UserModel updatedUserModel = new UserModel("updated name ", " updated QA 1", "updatedtest23@test.test", 26);
	return new Object[][]{{userModel, updatedUserModel}};
}


}
