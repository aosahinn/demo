package com.preschool.demo.type;

public enum UserType {
    ALL(0L),
    CUSTOMER(1L),
    OWNER(2L);

    private Long val;

    UserType(Long val) {
        this.val = val;
    }

    public Long getVal() {
        return val;
    }
}
