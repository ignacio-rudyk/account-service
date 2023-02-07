package com.accenture.accountservice.dao;

import com.accenture.accountservice.model.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends CrudRepository<Account, Long> {

    List<Account> findAllByUserId(Long userId);

    Optional<Account> findByNumberAccount(String numberAccount);

    Optional<Account> findByCbu(String cbu);

    Boolean existsByNumberAccount(String numberAccount);

    Boolean existsByCbu(String cbu);

}
