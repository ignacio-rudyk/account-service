package com.accenture.accountservice.controller;

import com.accenture.accountservice.model.dto.AccountDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface AccountController {

    @PostMapping(value = "/createAccount")
    ResponseEntity<AccountDTO> createAccount();

    @PostMapping(value = "/createAccount/{id}")
    ResponseEntity<AccountDTO> createAccountByIdUser(@PathVariable(name = "id") Long idUser);

    @DeleteMapping("/removeAccount/{id}")
    ResponseEntity<String> removeAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/getAccount/{id}")
    ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/existAccount/{id}")
    ResponseEntity<Boolean> existAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/list")
    ResponseEntity<List<AccountDTO>> list();
}
