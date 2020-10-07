package booker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
public class Guest {
    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final BookingDates bookingdates;
    private final String additionalneeds;
}
