package com.accenture.accountservice.controller.implementation;

import com.accenture.accountservice.controller.AccountController;
import com.accenture.accountservice.exception.AccountInexistentException;
import com.accenture.accountservice.exception.AccountServiceException;
import com.accenture.accountservice.exception.ValidationException;
import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    /*@Override
    public ResponseEntity<ErrorResponse> saveAccount(AccountDTO accountModify) {
        try{
            AccountDTO accountSaved = accountService.saveAccount(accountModify);
            ErrorResponse errorResponse = new ErrorResponse(accountSaved);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    public ResponseEntity<ErrorResponse> createAccount(Long userId) {
        try{
            AccountDTO accountCreated = accountService.createAccount(userId);
            ErrorResponse errorResponse = new ErrorResponse(accountCreated);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ErrorResponse> removeAccount(Long id) {
        try{
            AccountDTO accountDeleted = accountService.delete(id);
            ErrorResponse errorResponse = new ErrorResponse(accountDeleted);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountInexistentException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.NO_CONTENT);
        } catch (ValidationException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> removeAccountsByUserId(Long userId) {
        try{
            List<AccountDTO> deletedAccounts = accountService.deleteAccountsByUserId(userId);
            ErrorResponse errorResponse = new ErrorResponse(deletedAccounts);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> getAccount(Long id) {
        try{
            AccountDTO accountFound = accountService.findById(id);
            ErrorResponse errorResponse = new ErrorResponse(accountFound);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountInexistentException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.NO_CONTENT);
        } catch (ValidationException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ErrorResponse> existAccount(Long id) {
        try {
            Boolean exist = accountService.existsById(id);
            ErrorResponse errorResponse = new ErrorResponse(exist);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ErrorResponse> list() {
        try{
            List<AccountDTO> accountList = accountService.list();
            ErrorResponse errorResponse = new ErrorResponse(accountList);
            ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
            return response;
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ErrorResponse> getListByUserId(Long userId) {
        try{
            List<AccountDTO> accountList = accountService.getListByUserId(userId);
            ErrorResponse errorResponse = new ErrorResponse(accountList);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(-1, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
