package newagency.picfav.netwotk.token;

public interface TokenManager {
    String getToken();

    String refreshToken();

    boolean isTokenValid();

    void clearToken();
}
