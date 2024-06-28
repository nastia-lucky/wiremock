package resolvers;

public class EndpointsResolver {

public static String resolveEndpoint(Endpoints endpoint) {
	String stringEndpoint = null;
	switch (endpoint) {
		case ALL_USERS -> {
			stringEndpoint = "/users/get/all";
			break;
		}
		case ALL_COURSES -> {
			stringEndpoint = "/courses/get/all";
			break;
		}
		case GET_USER -> {
			stringEndpoint = "/users/get/";
			break;
		}
		case POST_USER -> {
			stringEndpoint = "/users/";
			break;
		}
	}
	return stringEndpoint;
}
}
