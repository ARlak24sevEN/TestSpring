package com.example.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }

    //user.register.request.null
    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    //unauthorizeed
    public static UserException unauthorizeed() {
        return new UserException("unauthorizeed");
    }

    //user.register.email.null
    public static UserException emailNull() {
        return new UserException("register.email.null");
    }


    //CREATE

    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("register.email.duplocated");
    }

    public static UserException createPasswordNull() {
        return new UserException("register.Password.null");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }


    //Login
    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail.email");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }

    //update
    public static UserException notFound() {
        return new UserException("user.not.found");
    }

}
