package com.accenture.accountservice.service;

import com.accenture.accountservice.exception.AccountDAOException;
import com.accenture.accountservice.exception.AccountServiceException;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.model.dto.SendingOfMoney;
import com.accenture.accountservice.model.dto.WithdrawalOfMoney;

import java.util.List;

public interface AccountService {

    SendingOfMoney addAmount( SendingOfMoney sendingOfMoney) throws AccountServiceException, AccountDAOException;

    WithdrawalOfMoney subtractAmount( WithdrawalOfMoney withdrawalOfMoney) throws AccountServiceException, AccountDAOException;

    AccountDTO createAccount(Long userId) throws AccountServiceException, AccountDAOException;

    AccountDTO delete(Long id) throws AccountServiceException;

    List<AccountDTO> deleteAccountsByUserId(Long userId) throws AccountServiceException;

    AccountDTO findById(Long id) throws AccountServiceException;

    Boolean existsById(Long id) throws AccountServiceException;

    Boolean existAccountByNumberAccount(String numberAccount) throws AccountServiceException;

    Boolean existAccountByCbu(String cbu) throws AccountServiceException;

    List<AccountDTO> list();

    List<AccountDTO> getListByUserId(Long userId);
}
