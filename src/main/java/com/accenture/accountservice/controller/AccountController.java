package com.accenture.accountservice.controller;

import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.MoneyOperationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/account")
public interface AccountController {

    @PutMapping(value = "/addAmount")
    ResponseEntity<ErrorResponse> addAmount(@RequestBody MoneyOperationDTO moneyOperation);

    @PutMapping(value = "/subtractAmount")
    ResponseEntity<ErrorResponse> subtractAmount(@RequestBody MoneyOperationDTO moneyOperation);

    @PostMapping(value = "/createAccount/{userId}")
    ResponseEntity<ErrorResponse> createAccount(@PathVariable(name = "userId") Long userId);

    @DeleteMapping("/removeAccount/{id}")
    ResponseEntity<ErrorResponse> removeAccount(@PathVariable(name = "id") Long id);

    @DeleteMapping("/removeAccountsByUserId/{userId}")
    ResponseEntity<ErrorResponse> removeAccountsByUserId(@PathVariable(name = "userId") Long userId);

    @GetMapping("/getAccount/{id}")
    ResponseEntity<ErrorResponse> getAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/getAccountIdByCbu/{cbu}")
    ResponseEntity<ErrorResponse> getAccountIdByCbu(@PathVariable(name = "cbu") String cbu);

    @GetMapping("/getAccountIdByNumberAccount/{numberAccount}")
    ResponseEntity<ErrorResponse> getAccountIdByNumberAccount(@PathVariable(name = "numberAccount") String numberAccount);

    @GetMapping("/existAccount/{id}")
    ResponseEntity<ErrorResponse> existAccount(@PathVariable(name = "id") Long id);

    @GetMapping("/existAccountByNumberAccount/{numberAccount}")
    ResponseEntity<ErrorResponse> existAccountByNumberAccount(@PathVariable(name = "numberAccount") String numberAccount);

    @GetMapping("/existAccountByCbu/{cbu}")
    ResponseEntity<ErrorResponse> existAccountByCbu(@PathVariable(name = "cbu") String cbu);

    @GetMapping("/list")
    ResponseEntity<ErrorResponse> list();

    @GetMapping("/getListByUserId/{userId}")
    ResponseEntity<ErrorResponse> getListByUserId(@PathVariable(name = "userId") Long userId);

}
