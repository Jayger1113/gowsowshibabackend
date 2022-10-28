package com.gowsow.shiba.backend.controller.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginOrRegisterRequest {

    @ApiModelProperty(example = "xxx@gmail.com", required = true)
    @Email(message = "email should be valid")
    @NotBlank(message = "email cannot be null")
    private String email;

    @ApiModelProperty(example = "you never know", required = true)
    @NotBlank(message = "mima cannot be null")
    private String mima;
}
