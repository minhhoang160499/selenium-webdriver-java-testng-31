package JavaTest;

import java.util.Random;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "Automation";
        String lastName = "FC";

        String fullName = firstName + " " + lastName;
        System.out.println(fullName);

        fullName = firstName.concat("").concat(lastName);
        System.out.println(fullName);

        String hotelMsg = "Welcome " +fullName + " to InterContiental Hotel";
        System.out.println(hotelMsg);

//        String hotelMsg1 = "Welcome to InterContiental Hotel";
//        String hotelMsg2 = hotelMsg1[0:6] + fullName + hotelMsg1[6:];
//        System.out.println(hotelMsg);

        // Trả về true/ false
        hotelMsg.endsWith("Hotel");
    }
}
