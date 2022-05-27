package it.aitho.fabrickonboarding.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FabrickOnboardingController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok fabrick!");
    }
}
