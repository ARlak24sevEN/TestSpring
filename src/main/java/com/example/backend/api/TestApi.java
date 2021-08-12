package com.example.backend.api;

import com.example.backend.business.TestBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.MRegisterRequest;
import com.example.backend.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestApi {

    //  @Autowired //Method 1 => field Injection
    //  private TestBusiness business;

    //Method 2 Code long but more performance => Constructor Injection
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }

    @GetMapping
   /* return String
   public String test(){
        return "hello test";
    }*/

    //Return Json

    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("arlak");
        response.setFoot("tom yom");
        return response;
    }


    @GetMapping
    @RequestMapping("/2")
    public TestResponse test2() {
        TestResponse response = new TestResponse();
        response.setName("arlak 2");
        response.setFoot("tom yom 2");
        return response;
    }


    //normal
   /* @PostMapping
    @RequestMapping("/register")
    public String register(@RequestBody MRegisterRequest request){
        String response = business.register(request);
        return "Resived"+response;
    }*/

    //Control http another 200ok
    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody MRegisterRequest request) throws BaseException {
        String response = business.register(request);
        return ResponseEntity.ok(response);
    }

    //upload file
    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return  ResponseEntity.ok(response);
    }


}
