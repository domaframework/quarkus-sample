package sample;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MessageResourceTest {

  @Test
  public void testHelloEndpoint() {
    given().when().get("/hello").then().statusCode(200).body(is("hello"));
  }

  @Test
  public void testHelloId1Endpoint() {
    given().when().get("/hello/1").then().statusCode(200).body(is("hello"));
  }

  @Test
  public void testHelloId2Endpoint() {
    given().when().get("/hello/2").then().statusCode(200).body(is("世界"));
  }
}
