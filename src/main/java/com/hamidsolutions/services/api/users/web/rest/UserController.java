package com.hamidsolutions.services.api.users.web.rest;

import com.hamidsolutions.services.api.users.commons.constants.Status;
import com.hamidsolutions.services.api.users.commons.controller.BaseController;
import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;
import com.hamidsolutions.services.api.users.commons.dto.ResponseDto;
import com.hamidsolutions.services.api.users.commons.exception.BusinessException;
import com.hamidsolutions.services.api.users.domain.User;
import com.hamidsolutions.services.api.users.dto.ResponseUserDTO;
import com.hamidsolutions.services.api.users.dto.UserDTO;
import com.hamidsolutions.services.api.users.repository.UserRepository;
import com.hamidsolutions.services.api.users.service.UserService;
import com.hamidsolutions.services.api.users.web.rest.vm.LoginVm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/account/register")
    public Mono<ResponseEntity<ResponseUserDTO>> createAccount(@Valid @RequestBody UserDTO userDTO) {
        log.info("USER REQUEST " + userDTO);
       return userService.isEmailExist(userDTO.getEmail())
               .flatMap(s->{

                   if(s){
                       throw new BusinessException(Collections.singletonList(
                    ErrorDto.builder()
                            .code("EMAIL")
                            .field("email")
                            .message("email already exist")
                            .build()));

                   }else{
                       if(userDTO.getPassword().length()< 8){
                           throw new BusinessException(Collections.singletonList(
                           ErrorDto.builder()
                                   .code("PASS")
                                   .field("password")
                                   .message("Password less than 8 digit")
                                   .build()));
                       }
                       return userService.createAccount(userDTO).map(createAccount ->
                               ResponseEntity.ok().body(createAccount));
                   }

               });
              // .switchIfEmpty(Mono.just()));

    }



    @GetMapping
    public Flux<UserDTO> getAllUsers() {
        return userService.findAllUser();

    }

    @GetMapping("/account/activateAccount")
    public Mono<ResponseEntity<String>> activateAccount(@RequestParam(name = "key") String key) {
        return userService.activateAccount(key).map(updatedUser ->
                ResponseEntity.ok().body(updatedUser.getUserId()))
                .switchIfEmpty(Mono.error(new BusinessException(Collections.singletonList(
                        ErrorDto.builder()
                                .code("PASS")
                                .field("password")
                                .message("activate key already  used")
                                .build()))));

    }

    @GetMapping("/account/resetPasswordRequest")
    public Mono<ResponseEntity<String>> resetPasswordRequest(@RequestParam(name = "key") String email) {
        return userService.resetPassworRequest(email).map(updatedUser ->
                ResponseEntity.ok().body(updatedUser.getUserId()))
                .switchIfEmpty(Mono.error(new BusinessException(Collections.singletonList(
                        ErrorDto.builder()
                                .code("PASS")
                                .field("password")
                                .message("email not exist")
                                .build()))));

    }

   @PostMapping("/account/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginVm lg) {
        return userService.login(lg).map(token->
            ResponseEntity.ok().body(token)
        )
        .switchIfEmpty(Mono.error(new BusinessException(Collections.singletonList(
                    ErrorDto.builder()
                            .code("PASS")
                            .field("password")
                            .message("error in token generating")
                            .build()))));

    }
}
//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")


//    @PutMapping("/{userId}")
//    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId, @RequestBody User user){
//        return userRepository.findById(userId)
//                .flatMap(dbUser -> {
//                    dbUser.setAge(user.getAge());
//                    dbUser.setSalary(user.getSalary());
//                    return userRepository.save(dbUser);
//                })
//                .map(updatedUser -> ResponseEntity.ok(updatedUser))
//                .defaultIfEmpty(ResponseEntity.badRequest().build());
//    }
//
//    @DeleteMapping("/{userId}")
//    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String userId){
//        return userRepository.findById(userId)
//                .flatMap(existingUser ->
//                        userRepository.delete(existingUser)
//                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
//                )
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }



//    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
//    public Flux<Currency> findAll(){
//        return currencyService.findAll();
//    }
//}
