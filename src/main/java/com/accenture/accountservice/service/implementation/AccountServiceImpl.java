package com.accenture.accountservice.service.implementation;

import com.accenture.accountservice.dao.AccountDAO;
import com.accenture.accountservice.exception.*;
import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.model.entities.Account;
import com.accenture.accountservice.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
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
    private RestTemplate restTemplate;

    @Value("${user.service.server.port}")
    private String userServicePort;

    /*@Override
    public AccountDTO saveAccount(AccountDTO newAccount) throws AccountServiceException, AccountDAOException {
        if(newAccount != null){
            try{
                Account account = mapper.map(newAccount, Account.class);
                account.setEnabled(accountIsEnabled(newAccount));
                account = accountDAO.save(account);
                return mapper.map(account, AccountDTO.class);
            } catch (DataAccessException e) {
                throw new AccountDAOException();
            } catch (AccountServiceException e) {
                throw e;
            } catch (Throwable t) {
                throw t;
            }
        } else {
            throw new FieldNullException();
        }
    }*/

    @Override
    public AccountDTO createAccount(Long userId) throws AccountServiceException, AccountDAOException {
        if(existUser(userId)) {
            try {
                Account newAccount = new Account(userId);
                newAccount.setNumberAccount("00"+createSequenceOfNumbers(8));
                newAccount.setCbu(createSequenceOfNumbers(22));
                newAccount.setEnabled(Boolean.TRUE);
                newAccount = accountDAO.save(newAccount);
                return mapper.map(newAccount, AccountDTO.class);
            } catch (DataAccessException e) {
                throw new AccountDAOException(e.getMessage());
            } catch (Throwable t) {
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
        if(!result.isEmpty()){
            return mapper.map(result.get(), AccountDTO.class);
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

    private Boolean accountIsEnabled(AccountDTO account) throws AccountServiceException {
        try {
            if(existsById(account.getId())){
                AccountDTO accountFound = findById(account.getId());
                return accountFound.getEnabled();
            } else {
                return Boolean.TRUE;
            }
        } catch (FieldNullException e){
            return Boolean.TRUE;
        }
    }

    private Boolean existUser(Long userId) throws AccountServiceException {
        try{
            String url = "http://localhost:" + userServicePort + "/user/existUser/" + userId;
            restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ErrorResponse.class);
            ErrorResponse errorResponse = responseEntity.getBody();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                if(errorResponse.getCode() == 0){
                    return (Boolean) errorResponse.getData();
                } else {
                    throw new AccountServiceException(errorResponse.getDesc(), errorResponse.getCode());
                }
            } else {
                throw new AccountServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } catch (Throwable t) {
            throw t;
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

}
