package myServiceTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class BaseStubTests {

protected static final ObjectMapper objectMapper = new ObjectMapper();
protected static WireMockServer wireMockServer;
protected static String wireMockHostName = "http://localhost";
protected static CloseableHttpClient client = HttpClients.createDefault();

public static void buildStub(UrlPattern urlPattern, Object object, int statusCode) throws JsonProcessingException {
	stubFor(WireMock.get(urlPattern)
			        .willReturn(aResponse()
					                    .withBody(new ObjectMapper().writeValueAsString(object))
					                    .withStatus(statusCode)));
}

public static String sendRequest(String endpoint) throws IOException {
	HttpGet request = new HttpGet(String.format("%s:%s" + endpoint, wireMockHostName, wireMockServer.port()));
	HttpResponse httpResponse = client.execute(request);
	HttpEntity entity = httpResponse.getEntity();
	return EntityUtils.toString(entity, "UTF-8");
}


@BeforeSuite
public static void setUpServer() {
	wireMockServer = new WireMockServer(options().port(6060));
	wireMockServer.start();
	configureFor(wireMockServer.port());
}

@AfterSuite
public static void closeWiremock() {
	wireMockServer.stop();
}


}
