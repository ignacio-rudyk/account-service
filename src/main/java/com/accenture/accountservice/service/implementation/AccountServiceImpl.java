package com.accenture.accountservice.service.implementation;

import com.accenture.accountservice.dao.AccountDAO;
import com.accenture.accountservice.model.dto.AccountDTO;
import com.accenture.accountservice.model.entities.Account;
import com.accenture.accountservice.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public AccountDTO saveAccount(AccountDTO newAccount) {
        if(newAccount != null){
            Account account = mapper.map(newAccount, Account.class);
            account.setNumberAccount(createSequenceOfNumbers(10));
            account.setCbu(createSequenceOfNumbers(22));
            account = accountDAO.save(account);
            return mapper.map(account, AccountDTO.class);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if(id != null){
            accountDAO.deleteById(id);
        }
    }

    @Override
    public AccountDTO findById(Long id) {
        AccountDTO ret = null;
        Optional<Account> result = accountDAO.findById(id);
        if(!result.isEmpty()){
            ret = mapper.map(result.get(), AccountDTO.class);
        }
        return ret;
    }

    @Override
    public Boolean existsById(Long id) {
        if(id != null){
            return accountDAO.existsById(id);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<AccountDTO> list() {
        List<Account> list = (List<Account>) accountDAO.findAll();
        return list.stream()
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
}
