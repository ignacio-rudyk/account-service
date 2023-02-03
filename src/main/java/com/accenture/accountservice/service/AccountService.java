package com.accenture.accountservice.service;

import com.accenture.accountservice.exception.AccountDAOException;
import com.accenture.accountservice.exception.AccountServiceException;
import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {

    /*AccountDTO saveAccount(AccountDTO newAccount) throws AccountServiceException, AccountDAOException;*/

    AccountDTO createAccount(Long userId) throws AccountServiceException, AccountDAOException;

    AccountDTO delete(Long id) throws AccountServiceException;

    List<AccountDTO> deleteAccountsByUserId(Long userId) throws AccountServiceException;

    AccountDTO findById(Long id) throws AccountServiceException;

    Boolean existsById(Long id) throws AccountServiceException;

    List<AccountDTO> list();

    List<AccountDTO> getListByUserId(Long userId);
}
