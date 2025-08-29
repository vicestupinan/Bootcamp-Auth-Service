package co.com.vmestupinan.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.vmestupinan.api.dto.CreateUserDto;
import co.com.vmestupinan.model.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toModel(CreateUserDto dto);

}
