package ch.lalumash.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
