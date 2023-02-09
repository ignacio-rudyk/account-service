package com.accenture.accountservice.service.implementation;

import com.accenture.accountservice.exception.AccountServiceException;
import com.accenture.accountservice.model.ErrorResponse;
import com.accenture.accountservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.server.port}")
    private String userServicePort;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private static String DOMAIN = "http://localhost:";

    private static String EXIST_USER_SERVICE = "/user/existUser/";

    @Override
    public Boolean existUser(Long userId) throws AccountServiceException {
        try{
            String url = DOMAIN + userServicePort + EXIST_USER_SERVICE + userId;
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
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            throw t;
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

}
