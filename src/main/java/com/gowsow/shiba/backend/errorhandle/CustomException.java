package com.gowsow.shiba.backend.errorhandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CustomException extends Exception {

    private HttpStatus status;
    private String message;

}
