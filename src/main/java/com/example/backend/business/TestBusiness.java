package com.example.backend.business;

import com.example.backend.exception.BaseException;
import com.example.backend.exception.FileException;
import com.example.backend.exception.UserException;
import com.example.backend.model.MRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TestBusiness {
    public String register(MRegisterRequest request) throws BaseException {
        //validate request
        if (request == null) {
            throw UserException.requestNull();
        }

        //validate email
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }

        //validate ....

        return "";
    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }
        if (file.getSize() > 1048576 * 2) {
            //throw error

            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            throw FileException.unsupport();
        }

        List<String> supportType = Arrays.asList("image/jpeg", "image/png");
        if (!supportType.contains(contentType)) {
            //throw error(unsupport)
            throw FileException.unsupport();
        }

        //Get file
        //Todo : upload file File Storage (AWS S3, etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }
}
