package booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestfulBooker {
    private final String baseUri = "https://restful-booker.herokuapp.com/";

    public String autorisation(User user) {
        return RestAssured.given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("auth")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("token");
    }

    public int createBooking(String guestBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .body(guestBody)
                .when()
                .post("booking")
                .then()
                .log().body(true)
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getInt("bookingid");
    }

    public String getFirstNameOfGuestById(int bookingId) {
        return RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("firstname");
    }

    public String getLastNameOfGuestById(int bookingId) {
        return RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("lastname");
    }

    public String getAditionalNeedsOfGuestById(int bookingId) {
        return RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("additionalneeds");
    }
}
