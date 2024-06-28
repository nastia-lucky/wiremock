package myServiceTests;

import dataProviders.UsersTestData;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.UserService;

import java.util.List;

public class RealServiceTests {


@Test(dataProvider = "getRealUserValidData", dataProviderClass = UsersTestData.class)
public void createUserTest(UserModel user) {
	UserService userService = new UserService();
	Long userId = userService.createUser(user);
	List<Long> usersIds = userService.getAllUsersIds();
	UserModel createdUser = userService.getUserWithValidation(userId);
	SoftAssert softAssert = new SoftAssert();
	softAssert.assertTrue(usersIds.contains(userId),
			String.format("All users Ids doesn't contain created user [%s]", userId));
	softAssert.assertTrue(user.equals(createdUser),
			String.format("Added user [%s] is not equal to got user [%s]", user, createdUser));
	userService.deleteUser(userId);
	softAssert.assertAll();
	
}

@Test(dataProvider = "getRealUserValidData", dataProviderClass = UsersTestData.class, dependsOnMethods = "createUserTest")
public void deleteUserTest(UserModel user) {
	UserService userService = new UserService();
	Long userId = userService.createUser(user);
	userService.deleteUser(userId);
	List<Long> usersIds = userService.getAllUsersIds();
	SoftAssert softAssert = new SoftAssert();
	softAssert.assertFalse(usersIds.contains(userId),
			String.format("List of users [%s] contains id [%s] of deleted user", usersIds, userId));
	softAssert.assertEquals(userService.getStatusCode(userId), 404,
			String.format("Status code is not 404 upon getting deleted user [%s]", userId));
	softAssert.assertAll();
}

@Test(dataProvider = "getRealUserNotValidData", dataProviderClass = UsersTestData.class)
public void createNotValidUserTest(UserModel user) {
	UserService userService = new UserService();
	Assert.assertEquals(userService.createUserGetStatusCode(user), 400,
			String.format("It is not thrown 400 upon trying to create user with not valid data [%s]", user));
}

@Test(dataProvider = "getRealUserUpdateTestData", dataProviderClass = UsersTestData.class)
public void updateUserTest(UserModel user, UserModel updatedUserModelRequest) {
	UserService userService = new UserService();
	Long userId = userService.createUser(user);
	userService.updateUser(updatedUserModelRequest, userId);
	List<Long> usersIds = userService.getAllUsersIds();
	UserModel updatedUser = userService.getUser(userId);
	SoftAssert softAssert = new SoftAssert();
	softAssert.assertTrue(usersIds.contains(userId),
			String.format("All users Ids doesn't contain updated user [%s]", userId));
	softAssert.assertTrue(updatedUser.equals(updatedUserModelRequest),
			String.format("Updated  user model [%s] is not equal to got user [%s]", updatedUserModelRequest, updatedUser));
	userService.deleteUser(userId);
	softAssert.assertAll();
	
}


}
