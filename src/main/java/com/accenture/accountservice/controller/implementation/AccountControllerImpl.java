package com.accenture.accountservice.controller.implementation;

import com.accenture.accountservice.controller.AccountController;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> createAccount() {
        AccountDTO accountSaved = accountService.saveAccount(new AccountDTO());
        return new ResponseEntity<AccountDTO>(accountSaved, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDTO> createAccountByIdUser(Long idUser) {
        AccountDTO accountSaved = accountService.saveAccount(new AccountDTO(idUser));
        return new ResponseEntity<AccountDTO>(accountSaved, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> removeAccount(Long id) {
        accountService.delete(id);
        ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<AccountDTO> getAccount(Long id) {
        AccountDTO result = accountService.findById(id);
        ResponseEntity<AccountDTO> response = new ResponseEntity<AccountDTO>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<Boolean> existAccount(Long id) {
        Boolean result = accountService.existsById(id);
        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> list() {
        List<AccountDTO> result = accountService.list();
        ResponseEntity<List<AccountDTO>> response = new ResponseEntity<List<AccountDTO>>(result, HttpStatus.OK);
        return response;
    }
}
