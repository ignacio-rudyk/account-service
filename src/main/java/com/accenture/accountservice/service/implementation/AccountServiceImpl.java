package com.accenture.accountservice.service.implementation;

import com.accenture.accountservice.dao.AccountDAO;
import com.accenture.accountservice.exception.*;
import com.accenture.accountservice.exception.validation.AccountInNegativeException;
import com.accenture.accountservice.exception.validation.AmountNegativeException;
import com.accenture.accountservice.exception.validation.AmountPositiveException;
import com.accenture.accountservice.exception.validation.FieldNullException;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.model.dto.MoneyOperationDTO;
import com.accenture.accountservice.model.entities.Account;
import com.accenture.accountservice.service.AccountService;
import com.accenture.accountservice.service.UserService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Override
    public MoneyOperationDTO addAmount(MoneyOperationDTO addingMoney) throws AccountServiceException, AccountDAOException {
        try{
            BigDecimal amount = new BigDecimal(addingMoney.getAmount());
            if(amount.compareTo(new BigDecimal(0)) <= 0) {
                throw new AmountNegativeException();
            }
            Optional<Account> result = accountDAO.findById(addingMoney.getAccountId());
            if(!result.isEmpty()){
                Account account = result.get();
                if(account.getEnabled()) {
                    account.addFunds(amount);
                    accountDAO.save(account);
                    return addingMoney;
                }
            }
            throw new AccountInexistentException();
        } catch (DataAccessException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw new AccountDAOException();
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw e;
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            throw t;
        }
    }

    @Override
    public MoneyOperationDTO subtractAmount(MoneyOperationDTO moneyTheft) throws AccountServiceException, AccountDAOException {
        try{
            BigDecimal amount = new BigDecimal(moneyTheft.getAmount());
            if(amount.compareTo(new BigDecimal(0)) >= 0) {
                throw new AmountPositiveException();
            }
            Optional<Account> result = accountDAO.findById(moneyTheft.getAccountId());
            if(!result.isEmpty()){
                Account account = result.get();
                if(account.getEnabled()) {
                    account.subtractFunds(amount.negate());
                    validateAccountData(account);
                    accountDAO.save(account);
                    return moneyTheft;
                }
            }
            throw new AccountInexistentException();
        } catch (DataAccessException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw new AccountDAOException();
        } catch (AccountServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw e;
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            throw t;
        }
    }

    @Override
    public AccountDTO createAccount(Long userId) throws AccountServiceException, AccountDAOException {
        if(userService.existUser(userId)) {
            try {
                Account newAccount = new Account(userId);
                newAccount.setNumberAccount("00"+createSequenceOfNumbers(8));
                newAccount.setCbu(createSequenceOfNumbers(22));
                newAccount.setEnabled(Boolean.TRUE);
                newAccount = accountDAO.save(newAccount);
                return mapper.map(newAccount, AccountDTO.class);
            } catch (DataAccessException e) {
                logger.error("[Error " + e.getClass() + "] " + e.getMessage());
                throw new AccountDAOException(e.getMessage());
            } catch (Throwable t) {
                logger.error("[Error " + t.getClass() + "] " + t.getMessage());
                throw t;
            }
        } else {
            throw new UserInexistentException();
        }
    }

    @Override
    public AccountDTO delete(Long id) throws AccountServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        AccountDTO account = findById(id);
        Account accountDisabled = mapper.map(account, Account.class);
        accountDisabled.setEnabled(Boolean.FALSE);
        accountDisabled = accountDAO.save(accountDisabled);
        return mapper.map(accountDisabled, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> deleteAccountsByUserId(Long userId) throws AccountServiceException {
        List<AccountDTO> deletedAccounts = new ArrayList<AccountDTO>();
        if(userId != null){
            List<AccountDTO> accountsToDelete = getListByUserId(userId);
            for (AccountDTO account : accountsToDelete) {
                deletedAccounts.add(delete(account.getId()));
            }
            return deletedAccounts;
        } else {
            throw new FieldNullException();
        }
    }

    @Override
    public AccountDTO findById(Long id) throws AccountServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        Optional<Account> result = accountDAO.findById(id);
        if(!result.isEmpty() && result.get().getEnabled()){
            return mapper.map(result.get(), AccountDTO.class);
        } else {
            throw new AccountInexistentException();
        }
    }

    @Override
    public Long findAccountIdByCbu(String cbu) throws AccountServiceException {
        if(cbu == null) {
            throw new FieldNullException();
        }
        Optional<Account> result = accountDAO.findByCbu(cbu);
        if(!result.isEmpty() && result.get().getEnabled()){
            return result.get().getId();
        } else {
            throw new AccountInexistentException();
        }
    }

    @Override
    public Long findAccountIdByNumberAccount(String numberAccount) throws AccountServiceException {
        if(numberAccount == null) {
            throw new FieldNullException();
        }
        Optional<Account> result = accountDAO.findByNumberAccount(numberAccount);
        if(!result.isEmpty() && result.get().getEnabled()){
            return result.get().getId();
        } else {
            throw new AccountInexistentException();
        }
    }

    @Override
    public Boolean existsById(Long id) throws AccountServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        if(accountDAO.existsById(id)) {
            AccountDTO accountFound = findById(id);
            return accountFound.getEnabled();
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean existAccountByNumberAccount(String numberAccount) throws AccountServiceException {
        if(numberAccount == null) {
            throw new FieldNullException();
        }
        if(accountDAO.existsByNumberAccount(numberAccount)) {
            Optional<Account> result = accountDAO.findByNumberAccount(numberAccount);
            if(!result.isEmpty()){
                Account accountFound = result.get();
                return accountFound.getEnabled();
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean existAccountByCbu(String cbu) throws AccountServiceException {
        if(cbu == null) {
            throw new FieldNullException();
        }
        if(accountDAO.existsByCbu(cbu)) {
            Optional<Account> result = accountDAO.findByCbu(cbu);
            if(!result.isEmpty()){
                Account accountFound = result.get();
                return accountFound.getEnabled();
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<AccountDTO> list() {
        List<Account> list = (List<Account>) accountDAO.findAll();
        return list.stream()
                   .filter(e -> e.getEnabled())
                   .map(e -> mapper.map(e, AccountDTO.class))
                   .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> getListByUserId(Long userId) {
        List<Account> list = (List<Account>) accountDAO.findAllByUserId(userId);
        return list.stream()
                .filter(e -> e.getEnabled())
                .map(e -> mapper.map(e, AccountDTO.class))
                .collect(Collectors.toList());
    }

    private String createSequenceOfNumbers(Integer quantity){
        String cbu = "";
        for(int i = 0 ; i < quantity ; i++) {
            cbu += (int) (Math.random() * 9) + 1;
        }
        return cbu;
    }

    private void validateAccountData(Account account) throws AccountInNegativeException {
        BigDecimal numberZero = new BigDecimal(0);
        if(account.getFunds().compareTo(numberZero) < 0) {
            throw new AccountInNegativeException();
        }
    }

}
