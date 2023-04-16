package com.freshfood.API_FreshShop.Controller;

import com.freshfood.API_FreshShop.Entity.Account;
import com.freshfood.API_FreshShop.Entity.InfoUser;
import com.freshfood.API_FreshShop.Entity.ResponseObject;
import com.freshfood.API_FreshShop.Repository.AccountRepository;
import com.freshfood.API_FreshShop.Repository.InfoUserRepository;
import com.freshfood.API_FreshShop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/fresh_shop/user")
public class UserController {
    @Autowired
    AccountRepository repository;

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/{id}")
    Account getAccount(@PathVariable Long id){
        return repository.findOne(id);
    }
    @PostMapping(path="/new",consumes = {MediaType.ALL_VALUE})
    ResponseEntity<ResponseObject> createAccount(@RequestBody Account account,@RequestParam String name,
                                                 @RequestParam String email){
    Account accountt = repository.findByUsername(account.getUsername());
        if(accountt!=null)
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","username đã tồn tại","")
            );
        account.setRole(false); // Default user
        Account checkAccount = repository.save(account);
        if(checkAccount!=null) {
            InfoUser infoUser = new InfoUser();
            infoUser.setEmail(email);
            infoUser.setName(name);
            infoUser.setAccount(checkAccount);

            InfoUser result = infoUserRepository.save(infoUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tạo tài khoản thành công",result));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed","Tạo tài khoản không thành công","")
        );
    }

    @PostMapping(path="/login",consumes = {MediaType.ALL_VALUE})
    ResponseEntity<ResponseObject> login(@RequestParam("username") String username,@RequestParam("password") String password){
        Account a = repository.findByUsernamePass(username,password);
        if(a != null){
           return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","Đăng nhập thành công",infoUserRepository.findByAccount(a))
            );
        }
        return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed","Tài khoản hoặc mật khẩu của bạn không đúng","")
        );
    }

    @PutMapping(path = "/changePassword",consumes = {MediaType.ALL_VALUE})
    ResponseEntity<ResponseObject> changePass(@RequestBody Account account,@RequestParam String newPass){
        if(account.getPassword()==newPass)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed","Mật khẩu mới trùng mật khẩu cũ","")
            );
        account.setPassword(newPass);
        repository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success","Đổi mât khẩu thành công",infoUserRepository.findByAccount(account))
        );
    }

    @PutMapping(path = "/info",consumes = {MediaType.ALL_VALUE})
    InfoUser changeName(@RequestBody InfoUser user){
        return infoUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> delete(@PathVariable Long id){
        Account account = repository.findOne(id);
        if(account==null)
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Không tìm thấy tài khoản trên","")
            );
        try {
            infoUserRepository.delete(account);
            repository.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","Xóa tài khoản thành công","")
            );
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", ex.getMessage(), "")
            );
        }
    }




}
