package newagency.picfav.util;

public class AppConstants {

    public enum GrantType {
        PASSWORD("password");

        public String value;

        GrantType(String value) {
            this.value = value;
        }
    }
}
