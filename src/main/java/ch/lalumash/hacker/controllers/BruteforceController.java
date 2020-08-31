package ch.lalumash.hacker.controllers;

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
        bruteforceManager.setUser(user);
        return user;
    }
    @GetMapping("/dtos/{dtosPerRequest}")
    public String setDtosPerRequests(@PathVariable String dtosPerRequest) {
        bruteforceManager.setDtosPerRequest(dtosPerRequest);
        return dtosPerRequest;
    }
    @GetMapping("/symbols/{symbols}")
    public String setSymbols(@PathVariable String symbols) {
        bruteforceManager.setSymbols(symbols);
        return symbols;
    }
    @GetMapping("/max/{max}")
    public String setMax(@PathVariable String max) {
        bruteforceManager.setMax(max);
        return max;
    }
}
