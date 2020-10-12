package booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestfulBooker {
    private final String BASE_URI = "https://restful-booker.herokuapp.com/";

    public String authorise(User user) {
        return RestAssured.given()
                .baseUri(BASE_URI)
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

    public int createBooking(Guest guest) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(guest)
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

    public Guest getGuestById(int bookingId) {
        Guest guest = RestAssured.given()
                .baseUri(BASE_URI)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getObject("", Guest.class);
        return guest;
    }

}
