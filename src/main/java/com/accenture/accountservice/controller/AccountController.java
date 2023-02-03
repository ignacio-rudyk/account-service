package com.accenture.accountservice.controller;

import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/account")
public interface AccountController {

    /*@PostMapping(value = "/saveAccount")
    ResponseEntity<ErrorResponse> saveAccount(@RequestBody AccountDTO account);*/

    @PostMapping(value = "/createAccount/{userId}")
    ResponseEntity<ErrorResponse> createAccount(@PathVariable(name = "userId") Long userId);

    @DeleteMapping("/removeAccount/{id}")
    ResponseEntity<ErrorResponse> removeAccount(@PathVariable(name = "id") Long id);

    @DeleteMapping("/removeAccountsByUserId/{userId}")
    ResponseEntity<ErrorResponse> removeAccountsByUserId(@PathVariable(name = "userId") Long userId);

    @GetMapping("/getAccount/{id}")
    ResponseEntity<ErrorResponse> getAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/existAccount/{id}")
    ResponseEntity<ErrorResponse> existAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/list")
    ResponseEntity<ErrorResponse> list();

    @GetMapping("/getListByUserId/{userId}")
    ResponseEntity<ErrorResponse> getListByUserId(@PathVariable(name = "userId") Long userId);
}
