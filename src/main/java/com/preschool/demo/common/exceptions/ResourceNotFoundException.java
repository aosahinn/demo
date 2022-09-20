package com.preschool.demo.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends AbstractCustomRuntimeException {
    private static final long serialVersionUID = -2973552413886896615L;

    public ResourceNotFoundException(String code) {
        super(code, new String[0]);
    }

    public ResourceNotFoundException(String code, String parameter) {
        super(code, new String[]{parameter});
    }

    public ResourceNotFoundException(String code, String... parameter) {
        super(code, parameter);
    }
}
