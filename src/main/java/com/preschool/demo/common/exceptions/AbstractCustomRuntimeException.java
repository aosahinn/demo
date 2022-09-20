package com.preschool.demo.common.exceptions;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCustomRuntimeException extends RuntimeException {
    private String code;
    private List<String> parameterList;

    public AbstractCustomRuntimeException() {
    }

    public AbstractCustomRuntimeException(String code, String... parameter) {
        super(code);
        this.code = code;
        this.parameterList = new ArrayList();
        Collections.addAll(this.parameterList, parameter);
    }

    public AbstractCustomRuntimeException(Throwable cause) {
        super(cause);
    }

    public AbstractCustomRuntimeException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public AbstractCustomRuntimeException(Throwable cause, String code, String... parameter) {
        super(cause);
        this.code = code;
        this.parameterList = new ArrayList();
        Collections.addAll(this.parameterList, parameter);
    }

    public String getCode() {
        return this.code;
    }

    public List<String> getParameterList() {
        return this.parameterList;
    }
}
