package newagency.picfav.util;

public class AppConstants {

    public enum GrantType {
        PASSWORD("password");

        public String value;

        GrantType(String value) {
            this.value = value;
        }
    }

    public enum GridState {
        BIG(1f),
        SMALL(0.5f);

        public float coeff;

        GridState(float coeff) {
            this.coeff = coeff;
        }
    }
}
