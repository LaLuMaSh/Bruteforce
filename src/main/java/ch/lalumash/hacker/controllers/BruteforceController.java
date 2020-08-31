package ch.lalumash.hacker.controllers;

import ch.lalumash.core.Config;
import ch.lalumash.hacker.manager.BruteforceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BruteforceController {
    private final BruteforceManager bruteforceManager;

    @Autowired
    public BruteforceController(BruteforceManager bruteforceManager) {
        this.bruteforceManager = bruteforceManager;
    }

    @GetMapping("/force")
    public String force() {
        return bruteforceManager.bruteforce();
    }
    @GetMapping("/user/{user}")
    public String setUser(@PathVariable String user) {
        bruteforceManager.getConfig().setUser(user);
        return user;
    }
    @GetMapping("/dtos/{dtosPerRequest}")
    public String setDtosPerRequests(@PathVariable String dtosPerRequest) {
        bruteforceManager.getConfig().setDtosPerRequest(dtosPerRequest);
        return dtosPerRequest;
    }
    @GetMapping("/symbols/{symbols}")
    public String setSymbols(@PathVariable String symbols) {
        bruteforceManager.getConfig().setSymbols(symbols);
        return symbols;
    }
    @GetMapping("/max/{max}")
    public String setMax(@PathVariable String max) {
        bruteforceManager.getConfig().setMax(max);
        return max;
    }
    @GetMapping("/url/{url}")
    public String setUrl(@PathVariable String url) {
        bruteforceManager.getConfig().setUrl("http://" + url + ":8080/login/m");
        return url;
    }

    @GetMapping("/info/")
    public Config getInfo() {
        return bruteforceManager.getConfig();
    }
}
