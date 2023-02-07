package com.accenture.accountservice.service;

import com.accenture.accountservice.exception.AccountServiceException;

public interface UserService {

    Boolean existUser(Long userId) throws AccountServiceException;

}
