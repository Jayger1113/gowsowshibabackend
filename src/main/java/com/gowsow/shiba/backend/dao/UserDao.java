package com.gowsow.shiba.backend.dao;

import com.gowsow.shiba.backend.entity.UserEntity;
import com.gowsow.shiba.backend.errorhandle.CustomException;
import com.gowsow.shiba.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private Logger logger = LoggerFactory.getLogger(UserDao.class.getSimpleName());

    @Autowired
    private UserRepository userRepository;

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public boolean registerNewUser(String email, String password) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setCreateMillis(System.currentTimeMillis());
            userEntity.setUpdateMillis(System.currentTimeMillis());
            userRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            logger.error("registerNewUser error: ", e);
            return false;
        }
    }

}
