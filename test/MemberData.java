package src;


public class MemberData {
    public static void main(String[] args) {
        var firstName = "Ebuka";
        System.out.println("Hello " + firstName);
        final var LAST_NAME = "Amadi-Obi";
        enum membershipStatus {ACTIVE, PAST, ON_HOLD}
        membershipStatus EbukasStatus = membershipStatus.ACTIVE;
        System.out.println(EbukasStatus);
    }

}