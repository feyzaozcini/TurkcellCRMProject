package com.turkcell.authserver.services.mappers;
import com.turkcell.authserver.entities.User;
import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromRegisterRequest(RegisterRequest request);
    User userFromLoginRequest(LoginRequest request);

}
