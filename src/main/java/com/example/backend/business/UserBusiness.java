package com.example.backend.business;

import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.FileException;
import com.example.backend.exception.UserException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.MLoginRequest;
import com.example.backend.model.MRegisterRequest;
import com.example.backend.model.MRegisterResponse;
import com.example.backend.service.TokenService;
import com.example.backend.service.UserService;
import com.example.backend.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public String login(MLoginRequest request) throws BaseException {
        //ValidateRequest

        //Varify Database
        Optional<User> opt = userService.findByEmail(request.getEmail());

        if (opt.isEmpty()){
            // throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }
        User user = opt.get();

        if (!userService.matchPassword(request.getPassword(),user.getPassword())){
            // throw password fail, password incorect
            throw  UserException.loginFailPasswordIncorrect();
        }

        //TODO:generate JWT
        //geberate token to use backen
        return tokenService.tokenize(user);
    }

    public String refreshToken() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()){
            throw UserException.unauthorizeed();
        }

        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()){
            throw UserException.notFound();
        }
        User user = optUser.get();
        return tokenService.tokenize(user);
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
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
            throw FileException.unsupported();
        }

        List<String> supportType = Arrays.asList("image/jpeg", "image/png");
        if (!supportType.contains(contentType)) {
            //throw error(unsupport)
            throw FileException.unsupported();
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
