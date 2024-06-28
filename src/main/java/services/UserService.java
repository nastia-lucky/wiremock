package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.Helper;
import models.UserModel;
import resolvers.Endpoints;
import resolvers.EndpointsResolver;

import java.util.Arrays;
import java.util.List;

public class UserService {

protected static final ObjectMapper objectMapper = new ObjectMapper();


public Long createUser(UserModel user) {
	return Long.parseLong(Helper.post(user, EndpointsResolver.resolveEndpoint(Endpoints.POST_USER), 200));
}

public int createUserGetStatusCode(UserModel user) {
	return Helper.postAndGetStatusCode(user, EndpointsResolver.resolveEndpoint(Endpoints.POST_USER));
}

public List<Long> getAllUsersIds() {
	String response = Helper.get(EndpointsResolver.resolveEndpoint(Endpoints.ALL_USERS), 200);
	Long[] usersIds = null;
	try {
		usersIds = objectMapper.readValue(response, Long[].class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	return Arrays.asList(usersIds);
}

public void deleteUser(Long userId) {
	Helper.delete("/users/" + userId, 200);
}

public UserModel getUser(Long id) {
	String response = Helper.get(EndpointsResolver.resolveEndpoint(Endpoints.GET_USER) + id, 200);
	UserModel userModel = null;
	try {
		userModel = objectMapper.readValue(response, UserModel.class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	return userModel;
}

public UserModel getUserWithValidation(Long id) {
	String response = Helper.getWithJSONValidation(EndpointsResolver.resolveEndpoint(Endpoints.GET_USER) + id, "jsons/UserSchema.json", 200);
	UserModel userModel = null;
	try {
		userModel = objectMapper.readValue(response, UserModel.class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	return userModel;
}

public int getStatusCode(Long id) {
	return Helper.getStatusCode(EndpointsResolver.resolveEndpoint(Endpoints.GET_USER) + id);
}

public void updateUser(UserModel updatedRequest, Long id) {
	Helper.put(updatedRequest, "/users/" + id, 200);
}


}
