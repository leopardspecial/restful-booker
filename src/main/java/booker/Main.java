package booker;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Main {
    public static void main(String[] args) {

//        User user = new User("admin", "password123");
//        String userBody = new Gson().toJson(user);
//        String token = RestAssured.given()
//                .baseUri("https://restful-booker.herokuapp.com/auth")
//                .contentType(ContentType.JSON)
//                .body(user)
//                .when()
//                .post()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .jsonPath()
//                .getString("token");
//        user.setToken(token);
//        System.out.println(token);
//
//        Guest guest = new Guest("Jim", "Hoffman", 2000,
//                true, "2019-01-20", "2019-01-22",  "Beer");
//        String guestBody = new Gson().toJson(guest);
//        String abs = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .baseUri("https://restful-booker.herokuapp.com/booking")
//                .body(guestBody)
//                .when()
//                .post()
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .prettyPeek()
//                .jsonPath()
//                .get("booking.bookingdates.checkin")
//                .getClass()
//                .toString();
//        System.out.println(abs);
String a = "Hi i am man";
if (a.contains("man")) {
    System.out.println("ASfasfsd!");
}



    }
}
