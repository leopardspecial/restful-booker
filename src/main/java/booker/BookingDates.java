package booker;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BookingDates {
    private String checkin;
    private String checkout;


}
