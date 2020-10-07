package booker_test;

import booker.BookingDates;
import booker.Guest;
import booker.User;
import com.google.gson.Gson;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


public class BookerTest {

    private User user;
    private String baseUri = "https://restful-booker.herokuapp.com/";
    private int bookingId;
    private String body;


    @Дано("^пользователь создаёт логин и пароль$")
    public void createUser() {
        user = new User("admin", "password123");
    }

    @Когда("^пользователь создаёт структуру бронирования$")
    public void createGuest(String body) {
        this.body = body;
    }

    @Дано("^пользователь проходит авторизацию и получает токен$")
    public void authorization() {
        String userBody = new Gson().toJson(user);
        String token = RestAssured.given()
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
        user.setToken(token);
    }

    @Когда("^пользователь подтверждает бронирование$")
    public void createBooking() {
        bookingId = RestAssured.given()
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .body(body)
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

    @То("^пользователь получает подтверждение существования заказа$")
    public void chekoutBooking() {
        String responseFirstName = RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("firstname");

        String responseLastName = RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("lastname");

        String responseAdditionalneeds = RestAssured.given()
                .baseUri(baseUri)
                .when()
                .get("booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("additionalneeds");


        if (body.contains(responseFirstName) && (body.contains(responseLastName))
        && (body.contains(responseAdditionalneeds))) {
            System.out.println("Это то самое бронирование!");
        } else {
            throw new NoSuchElementException();
        }

    }

}
