package com.example.demo.registration;

import com.example.demo.KorisniciRepository;
import com.example.demo.model.Korisnici;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(path = "")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @Autowired
    public KorisniciRepository kr;

    @GetMapping(path = "/test")
    public List<Korisnici> test(@RequestBody RegistrationRequest request) {
        System.out.println(kr.findAll());
        return kr.findAll();
    }

}
