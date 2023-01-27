package com.accenture.accountservice.service;

import com.accenture.accountservice.model.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    AccountDTO saveAccount(AccountDTO newAccount);

    void delete(Long id);

    AccountDTO findById(Long id);

    Boolean existsById(Long id);

    List<AccountDTO> list();
}
