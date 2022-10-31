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
public class UserLogoutRequest {

    @ApiModelProperty(example = "xxx@gmail.com", required = true)
    @Email(message = "email should be valid")
    @NotBlank(message = "email cannot be null")
    private String email;
}
