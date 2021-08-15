package com.example.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }

    //user.register.request.null
    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    //user.register.email.null
    public static UserException emailNull() {
        return new UserException("register.email.null");
    }


    //CREATE

    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createEmailDuplocated() {
        return new UserException("register.email.duplocated");
    }

    public static UserException createPasswordNull() {
        return new UserException("register.Password.null");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }
}
