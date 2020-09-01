package ch.lalumash.core;

public class LoginResponseDto {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token) {
        this.token = token;
    }
}
