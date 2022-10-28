package com.gowsow.shiba.backend.controller;

import com.gowsow.shiba.backend.controller.request.user.UserLoginOrRegisterRequest;
import com.gowsow.shiba.backend.dto.UserLoginOrRegisterResponse;
import com.gowsow.shiba.backend.errorhandle.CustomException;
import com.gowsow.shiba.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "validate user login or register a new one", response = UserLoginOrRegisterResponse.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserLoginOrRegisterResponse.class))})})
    @RequestMapping(value = "/loginOrRegister", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> loginOrRegister(
            @Valid @ApiParam(required = true) @RequestBody UserLoginOrRegisterRequest req,
            BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
        }
        UserLoginOrRegisterResponse res = userService.userLoginOrRegister(req);
        return ResponseEntity.ok().body(res);
    }


}
