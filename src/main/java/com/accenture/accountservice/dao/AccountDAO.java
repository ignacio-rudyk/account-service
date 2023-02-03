package com.accenture.accountservice.dao;

import com.accenture.accountservice.model.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountDAO extends CrudRepository<Account, Long> {

    List<Account> findAllByUserId(Long userId);

}
