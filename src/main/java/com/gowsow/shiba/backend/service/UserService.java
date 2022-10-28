package com.gowsow.shiba.backend.service;


import com.gowsow.shiba.backend.controller.request.user.UserLoginOrRegisterRequest;
import com.gowsow.shiba.backend.dao.UserDao;
import com.gowsow.shiba.backend.dto.UserLoginOrRegisterResponse;
import com.gowsow.shiba.backend.errorhandle.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    @Autowired
    private UserDao userDao;

    public UserLoginOrRegisterResponse userLoginOrRegister(UserLoginOrRegisterRequest req) throws CustomException {
        if (req.getEmail() != null && req.getMima() != null) {
            if (userDao.findUserByEmail(req.getEmail()) != null) {
                if (userDao.findUserByEmailAndPassword(req.getEmail(), req.getMima()) == null) {
                    throw new CustomException(HttpStatus.FORBIDDEN, "incorrect account or password login attempted");
                } else {
                    UserLoginOrRegisterResponse res = new UserLoginOrRegisterResponse();
                    res.setStatus(0);
                    res.setMsg("login success");
                    return res;
                }
            } else {
                //register a new user
                if (userDao.registerNewUser(req.getEmail(), req.getMima())) {
                    UserLoginOrRegisterResponse res = new UserLoginOrRegisterResponse();
                    res.setStatus(0);
                    res.setMsg("user setup success");
                    return res;
                } else {
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "register account error");
                }
            }
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "email or password can't be null");
        }
    }
}
