public class LeapYear {

    boolean isLeap(int year) {
        if (((year % 4) == 0) && !((year % 100) == 0)){
            return true;
        } else return (year % 400) == 0;
    }

//    public static void main(String[] args) {
//        LeapYear leapYear = new LeapYear();
//        System.out.println(leapYear.isLeap(2023)); // false
//        System.out.println(leapYear.isLeap(2024)); // true
//        System.out.println(leapYear.isLeap(2012)); // true
//    }
}
