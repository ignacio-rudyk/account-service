package com.accenture.accountservice.dao;

import com.accenture.accountservice.model.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountDAO extends CrudRepository<Account, Long> {
}
