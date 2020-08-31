package ch.lalumash.hacker.controllers;

import ch.lalumash.hacker.manager.BruteforceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("force");
        return bruteforceManager.bruteforce();
    }
}
