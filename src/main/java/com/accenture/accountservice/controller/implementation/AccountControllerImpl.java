package com.accenture.accountservice.controller.implementation;

import com.accenture.accountservice.controller.AccountController;
import com.accenture.accountservice.exception.AccountInexistentException;
import com.accenture.accountservice.exception.AccountServiceException;
import com.accenture.accountservice.exception.ValidationException;
import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.model.dto.SendingOfMoneyDTO;
import com.accenture.accountservice.model.dto.WithdrawalOfMoneyDTO;
import com.accenture.accountservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Override
    public ResponseEntity<ErrorResponse> addAmount(SendingOfMoneyDTO sendingOfMoney) {
        logger.info("[Microservice:Account, Endpoint:addAmount] sending_of_money = " + sendingOfMoney);
        try{
            sendingOfMoney = accountService.addAmount(sendingOfMoney);
            ErrorResponse errorResponse = new ErrorResponse(sendingOfMoney);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> subtractAmount(WithdrawalOfMoneyDTO withdrawalOfMoney) {
        logger.info("[Microservice:Account, Endpoint:subtractAccount] withdrawal_of_money = " + withdrawalOfMoney);
        try{
            withdrawalOfMoney = accountService.subtractAmount(withdrawalOfMoney);
            ErrorResponse errorResponse = new ErrorResponse(withdrawalOfMoney);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> createAccount(Long userId) {
        logger.info("[Microservice:Account, Endpoint:createAccount] user_id = " + userId);
        try{
            AccountDTO accountCreated = accountService.createAccount(userId);
            ErrorResponse errorResponse = new ErrorResponse(accountCreated);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CREATED);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> removeAccount(Long id) {
        logger.info("[Microservice:Account, Endpoint:removeAccount] id = " + id);
        try{
            AccountDTO accountDeleted = accountService.delete(id);
            ErrorResponse errorResponse = new ErrorResponse(accountDeleted);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountInexistentException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.NO_CONTENT);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> removeAccountsByUserId(Long userId) {
        logger.info("[Microservice:Account, Endpoint:removeAccountByUserId] user_id = " + userId);
        try{
            List<AccountDTO> deletedAccounts = accountService.deleteAccountsByUserId(userId);
            ErrorResponse errorResponse = new ErrorResponse(deletedAccounts);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> getAccount(Long id) {
        logger.info("[Microservice:Account, Endpoint:getAccount] id = " + id);
        try{
            AccountDTO accountFound = accountService.findById(id);
            ErrorResponse errorResponse = new ErrorResponse(accountFound);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (AccountInexistentException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.NO_CONTENT);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> existAccount(Long id) {
        logger.info("[Microservice:Account, Endpoint:existAccount] id = " + id);
        try {
            Boolean exist = accountService.existsById(id);
            ErrorResponse errorResponse = new ErrorResponse(exist);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> existAccountByNumberAccount(String numberAccount) {
        logger.info("[Microservice:Account, Endpoint:existAccountByNumberAccount] numberAccount = " + numberAccount);
        try {
            Boolean existAccount = accountService.existAccountByNumberAccount(numberAccount);
            ErrorResponse errorResponse = new ErrorResponse(existAccount);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> existAccountByCbu(String cbu) {
        logger.info("[Microservice:Account, Endpoint:existAccountByCbu] cbu = " + cbu);
        try {
            Boolean existAccount = accountService.existAccountByCbu(cbu);
            ErrorResponse errorResponse = new ErrorResponse(existAccount);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> list() {
        logger.info("[Microservice:Account, Endpoint:list]");
        try{
            List<AccountDTO> accountList = accountService.list();
            ErrorResponse errorResponse = new ErrorResponse(accountList);
            ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
            return response;
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ErrorResponse> getListByUserId(Long userId) {
        logger.info("[Microservice:Account, Endpoint:getListByUserId] user_id = " + userId);
        try{
            List<AccountDTO> accountList = accountService.getListByUserId(userId);
            ErrorResponse errorResponse = new ErrorResponse(accountList);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
