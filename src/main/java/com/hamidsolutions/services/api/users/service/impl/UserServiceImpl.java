package com.hamidsolutions.services.api.users.service.impl;

import com.hamidsolutions.services.api.users.commons.dto.ErrorDto;
import com.hamidsolutions.services.api.users.commons.exception.BusinessException;
import com.hamidsolutions.services.api.users.config.security.JWTUtil;
import com.hamidsolutions.services.api.users.domain.User;
import com.hamidsolutions.services.api.users.dto.ListUserDTO;
import com.hamidsolutions.services.api.users.dto.ResponseLoginUser;
import com.hamidsolutions.services.api.users.dto.ResponseUserDTO;
import com.hamidsolutions.services.api.users.dto.UserDTO;
import com.hamidsolutions.services.api.users.mappers.UserMapper;
import com.hamidsolutions.services.api.users.repository.UserRepository;
import com.hamidsolutions.services.api.users.service.UserService;
import com.hamidsolutions.services.api.users.web.rest.vm.LoginVm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JWTUtil jwtUtil;
    @Override
    public Mono<UserDTO> createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        Mono<UserDTO> monoUserDto = userRepository.save(user).
                flatMap(s -> {
                    return Mono.just(userMapper.userToUserDTO(s));
                });
        // .subscribe()       ;
        return monoUserDto;
    }

    // @PutMapping("/{userId}")
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


    @Override
    // @Transactional
    public Mono<UserDTO> activateAccount(String activateKey) {
        Mono<UserDTO> monoUserDto = userRepository.findOneByCreateAccountKey(activateKey)
                .flatMap(user -> {
                    user.setCreateAccountKey(null);
                    user.setActivate(true);
                    return userRepository.save(user)
                            .flatMap(s -> {
                                return Mono.just(userMapper.userToUserDTO(s));
                            });
                });
        return monoUserDto;
    }

    @Override
    public Mono<UserDTO> resetPassworRequest(String email) {
        Mono<UserDTO> monoUserDto = userRepository.findOneByEmailAndDisableIsFalse(email)
                .flatMap(user -> {
                    user.setResetPasswordKey(UUID.randomUUID().toString());
                    user.setResetPasswordDate(Instant.now());
                    return userRepository.save(user)
                            .flatMap(s -> {
                                return Mono.just(userMapper.userToUserDTO(s));
                            });
                });
        return monoUserDto;
    }

    @Override
    public Flux<ListUserDTO> findAllUser() {
        Flux<ListUserDTO> fluxUserDTO = userRepository.findAll().
                flatMap(s -> {
                    return Flux.just(userMapper.userToListUserDTO(s));
                });
        return fluxUserDTO;
    }

    @Override
    public Mono<ResponseUserDTO> createAccount(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateAccountKey(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user).flatMap(saveUser -> {

            return Mono.just(userMapper.userToResponseUserDTO(saveUser));

        });
    }

//      userRepository.findByEmail(userDTO.getEmail()).map(u -> {
//
//            throw new BusinessException(Collections.singletonList(
//                    ErrorDto.builder()
//                            .code("AEThyy")
//                            .field("email")
//                            .message("email already exist")
//                            .build()));
//
//        }).switchIfEmpty(createUserIn(userDTO));


    Mono<UserDTO> createUserIn(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateAccountKey(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user).
                flatMap(s -> {
                    return Mono.just(userMapper.userToUserDTO(s));
                });
    }

//    @Override
//    public Mono<UserDTO> createAccount(UserDTO userDTO) {
//        Mono<UserDTO> monoUserDto;
//        userRepository.findOneByEmail(userDTO.getEmail()).map(u -> {
//            if (u.isPresent()) {
//                throw new BusinessException(Collections.singletonList(
//                        ErrorDto.builder()
//                                .code("AEThyy")
//                                .field("email")
//                                .message("email already exist")
//                                .build()));
//            }else {
//            User user = userMapper.userDTOToUser(userDTO);
//            user.setUserId(UUID.randomUUID().toString());
//            user.setCreateAccountKey(UUID.randomUUID().toString());
//            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//
//                monoUserDto =  userRepository.save(user).
//                    flatMap(s -> {
//                        return Mono.just(userMapper.userToUserDTO(s));
//                    });
//            // .subscribe()       ;
//            return monoUserDto;
//        });
//
//    }

    @Override
    public Mono<UserDTO> updateAUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Mono<UserDTO> desableAccount(UserDTO userDTO) {
        return null;
    }

    @Override
    public Mono<Boolean> isEmailExist(String email) {
        return userRepository.findByEmail(email).flatMap(s -> {
            if (s != null) {
                return Mono.just(true);
            } else {
                return Mono.just(false);
            }

        }).switchIfEmpty(Mono.just(false));
    }

    @Override
    public Mono<String> login(LoginVm loginVm) {
        return userRepository.findByEmailAndActivateIsTrue(loginVm.getUsername())
                .flatMap(userDetails -> {
                    if (bCryptPasswordEncoder.matches(loginVm.getPassword(),userDetails.getPassword())) {
                        ResponseLoginUser r = userMapper.userToResponseLoginUser(userDetails);
                        r.setRememberMe(loginVm.isRememberMe());
                        return Mono.just(jwtUtil.generateToken(r));  //AuthResponse(jwtUtil.generateToken(userDetails));
                    } else {
                        return Mono.error(
                                new WebClientResponseException(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name(),null,null,null));
                    }
                    }).switchIfEmpty(
                        Mono.error(new BusinessException(Collections.singletonList(
                                ErrorDto.builder()
                                        .code("PASS")
                                        .field("password")
                                        .message("email not exist")
                                        .build()))))
                ;

    }

    @Override
    public Mono<User> fetchUserByUsername(String username) {
        return userRepository.findByEmail(username).flatMap(u->{
          return  Mono.just((u));
         }).switchIfEmpty(
                Mono.error(new UsernameNotFoundException(username +" Not Exist")));

    }


//        throw new BusinessException(Collections.singletonList(
//                ErrorDto.builder()
//                        .code("AEThyy")
//                        .field("email")
//                        .message("email already exist")
//                        .build()));
//
//    })
    //.switchIfEmpty(createUserIn(userDTO));

}


//    public Mono<UserWithComments> getUserWithComments(String userId) {
//        Mono<UserInfo> userInfo = Mono.fromCallable(() -> BlockingUserApi.getUserInfo(userId));
//        Mono<Comments> userComments = Mono.fromCallable(() -> BlockingCommentsApi.getCommentsForUser(userId));
//
//        Mono<UserWithComments> userWithComments = userInfo.
//                flatMap(info -> userComments.
//                        map(comments -> new UserWithComments(info, comments))
//                );
//
//        return userWithComments;
//    }

//    Flux<User> getAllAdmins () {
//        List<User> allUsers = Users.getAllUsers();
//        return Flux.fromIterable(allUsers).
//                filter(user -> user.getRoles().equals("ADMIN"));
//    }


//    Mono<User> findUser(String username) {
//        String queryUrl = "http://my-api-address/users/" + username;
//
//        return Mono.fromCallable(() -> httpClient.get(queryUrl)).
//                flatMap(response -> {
//                    if (response.statusCode == 404) return Mono.error(new NotFoundException("User " + username + " not found"));
//                    else if (response.statusCode == 500) return Mono.error(new InternalServerErrorException());
//                    else if (response.statusCode != 200) return Mono.error(new Exception("Unknown error calling my-api"));
//                    return Mono.just(response.data);
//                });
//    }

//return users.map(u -> new UserDto(u, roleRepo.findById(u.getRoleId())));
//        Or in the case where findById() returns a Mono:
//
//        return users.flatMap(u -> roleRepo.findById(u.getRoleId()).map(r -> new UserDto(u, r)));


//    Stream<T> stream = yourMono<T>.map(it -> it.parallelStream()).block()
//        Another way just process it in reactive approach (note, anyway someone have to subscribe to your publisher, it can't be done by itself):
//
//        yourMono<T>.flatMapMany(Flux::fromIterable)
//        .flatMap(it -> {
//        //there goes your <Map<String, Object>>
//        });

//    public Mono<User> findByUsername(String username) {
//        if (username.equals(userUsername)) {
//            return Mono.just(user);
//        } else if (username.equals(adminUsername)) {
//            return Mono.just(admin);
//        } else {
//            return Mono.empty();
//        }