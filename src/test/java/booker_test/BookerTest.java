package booker_test;

import booker.Guest;
import booker.RestfulBooker;
import booker.User;
import com.google.gson.Gson;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;


public class BookerTest {

    private User user;
    private int bookingId;
    private RestfulBooker restfulBooker;
    private Guest guest;


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
        Gson gson = new Gson();
        guest = gson.fromJson(guestBody, Guest.class);
    }

    @Дано("^пользователь проходит авторизацию и получает токен$")
    public void authorization() {
        user.setToken(restfulBooker.authorise(user));
    }

    @Когда("^пользователь подтверждает бронирование$")
    public void createBooking() {
        bookingId = restfulBooker.createBooking(guest);
    }

    @То("^пользователь получает подтверждение существования заказа$")
    public void chekoutBooking() {
        if (guest.equals(restfulBooker.getGuestById(bookingId))) {
            System.out.println("Это то самое бронирование!");
        } else {
            throw new RuntimeException("Это не то бронирование");
        }
    }

}
