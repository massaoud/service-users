package com.hamidsolutions.services.api.users.mappers;


import com.hamidsolutions.services.api.users.domain.User;
import com.hamidsolutions.services.api.users.dto.ListUserDTO;
import com.hamidsolutions.services.api.users.dto.ResponseLoginUser;
import com.hamidsolutions.services.api.users.dto.ResponseUserDTO;
import com.hamidsolutions.services.api.users.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 *
 * @author massaoud
 */
@Mapper(
//        uses = {
//            CurrencyMapper.class//
//        }
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //@Mapping(target = "password", ignore = true)
    UserDTO userToUserDTO(User user);
    ListUserDTO userToListUserDTO(User user);
    ResponseUserDTO userToResponseUserDTO(User user);
    ResponseLoginUser userToResponseLoginUser(User user);
    User userDTOToUser(UserDTO userDTO);


}
