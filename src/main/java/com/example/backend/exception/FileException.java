package com.example.backend.exception;

public class FileException extends BaseException {

    public FileException(String code) {
        super("File." + code);
    }

    //file null
    public static FileException fileNull() {
        return new FileException("null");
    }

    //file max size
    public static FileException fileMaxSize() {
        return new FileException("max.size");
    }

    //file unsupport
    public static FileException unsupport() {
        return new FileException("unsupprt.file.type");
    }

}
