package com.hamidsolutions.services.api.users.service;

import com.hamidsolutions.services.api.users.domain.User;
import com.hamidsolutions.services.api.users.dto.ListUserDTO;
import com.hamidsolutions.services.api.users.dto.ResponseLoginUser;
import com.hamidsolutions.services.api.users.dto.ResponseUserDTO;
import com.hamidsolutions.services.api.users.dto.UserDTO;
import com.hamidsolutions.services.api.users.web.rest.vm.LoginVm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDTO> createUser(UserDTO userDTO);
    Mono<UserDTO> activateAccount(String activateKey);
    Mono<UserDTO> resetPassworRequest(String email);
    Flux<ListUserDTO> findAllUser();
    Mono<ResponseUserDTO> createAccount(UserDTO userDTO);
    Mono<UserDTO> updateAUser(UserDTO userDTO);
    Mono<UserDTO> desableAccount(UserDTO userDTO);
    Mono<Boolean> isEmailExist(String email);
    Mono<String> login(LoginVm loginVm);
    Mono<User> fetchUserByUsername(String username);

}
