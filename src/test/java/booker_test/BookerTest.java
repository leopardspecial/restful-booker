package booker_test;

import booker.RestfulBooker;
import booker.User;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;

import java.util.NoSuchElementException;


public class BookerTest {

    private User user;
    private int bookingId;
    private String guestBody;
    private RestfulBooker restfulBooker;


    @Дано("^Инициализация Restful boker$")
    public void createRestfulBooker() {
        restfulBooker = new RestfulBooker();
    }

    @Дано("^пользователь создаёт логин и пароль$")
    public void createUser() {
        user = new User("admin", "password123");
    }

    @Когда("^пользователь создаёт структуру бронирования$")
    public void createGuestBody(String guestBody) {
        this.guestBody = guestBody;
    }

    @Дано("^пользователь проходит авторизацию и получает токен$")
    public void authorization() {
        user.setToken(restfulBooker.autorisation(user));
    }

    @Когда("^пользователь подтверждает бронирование$")
    public void createBooking() {
        bookingId = restfulBooker.createBooking(guestBody);
    }

    @То("^пользователь получает подтверждение существования заказа$")
    public void chekoutBooking() {
        String responseFirstName = restfulBooker.getFirstNameOfGuestById(bookingId);

        String responseLastName = restfulBooker.getLastNameOfGuestById(bookingId);

        String responseAditionalneeds = restfulBooker.getAditionalNeedsOfGuestById(bookingId);

        if (guestBody.contains(responseFirstName) && (guestBody.contains(responseLastName))
                && (guestBody.contains(responseAditionalneeds))) {
            System.out.println("Это то самое бронирование!");
        } else {
            throw new NoSuchElementException();
        }

    }

}
