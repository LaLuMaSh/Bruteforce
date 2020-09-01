package ch.lalumash.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class Config implements Serializable {
    private String url;
    private String symbols;
    private String max;
    private String user;
    private String dtosPerRequest;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDtosPerRequest() {
        return dtosPerRequest;
    }

    public void setDtosPerRequest(String dtosPerRequest) {
        this.dtosPerRequest = dtosPerRequest;
    }

    public Config() {
    }

    public Config(String url, String symbols, String max, String user, String dtosPerRequest) {
        this.url = url;
        this.symbols = symbols;
        this.max = max;
        this.user = user;
        this.dtosPerRequest = dtosPerRequest;
    }
}
