package ch.lalumash.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Config implements Serializable {
    private String url;
    private String symbols;
    private String max;
    private String user;
    private String dtosPerRequest;
}
