package com.kryhowsky.user.mapper;

import com.kryhowsky.common.rest.UserDto;
import com.kryhowsky.user.model.Role;
import com.kryhowsky.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    UserDto toDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toDao(UserDto userDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
