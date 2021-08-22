package com.example.backend;

import com.example.backend.entity.Address;
import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.service.AddressService;
import com.example.backend.service.SocialService;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    /* can't use
    private final UserService userService;

    TestUserService(UserService userService) {
        this.userService = userService;
    }*/

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        //check equals

        Assertions.assertEquals(TestCreateData.email, user.getEmail());
        boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
        Assertions.assertTrue(isMatched);
        Assertions.assertEquals(TestCreateData.name, user.getName());
    }

    @Order(2)
    @Test
    void testUpdate() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();


        //update data in database => user.setName(TestUpdateData.name);

        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
        //
    }


    @Order(3)
    @Test
    void testCreateSocial() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(user, SocialTestData.facebook, SocialTestData.line, SocialTestData.instagram, SocialTestData.tiktok);

        //check social has in database is not null
        Assertions.assertNotNull(social);
        //check data have in database example data of column facebook
        Assertions.assertEquals(SocialTestData.facebook, social.getFaceook());
    }

    @Order(3)
    @Test
    void testCreateAddress() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user, AddressTestData.line1, AddressTestData.line2, AddressTestData.zipcode);
        createAddress(user, AddressTestData2.line1, AddressTestData2.line2, AddressTestData2.zipcode);

    }

    private void createAddress(User user, String line1, String line2, String zipcode) {
        Address address = addressService.create(user, line1, line2, zipcode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());
    }

    @Order(9)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();

        //check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestData.facebook,social.getFaceook());

        //check address
        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2,addresses.size());

        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestCreateData {
        String email = "arlak@gmail.com";
        String password = "1234";
        String name = "Arlak";

    }

    interface AddressTestData {
        String line1 = "123/456";
        String line2 = "Muang";
        String zipcode = "96170";
    }

    interface AddressTestData2 {
        String line1 = "753/69";
        String line2 = "Muang";
        String zipcode = "96171";
    }

    interface SocialTestData {
        String facebook = "arlak abdulloh";
        String line = "";
        String instagram = "";
        String tiktok = "";
    }

    interface TestUpdateData {
        String name = "Nadia";
    }
}
