package md.crudproject.fincontrol.service;

import md.crudproject.fincontrol.dto.CreatedUserDto;
import md.crudproject.fincontrol.dto.ViewUserDto;
import md.crudproject.fincontrol.model.User;
import java.util.UUID;

public interface UserService {

    void createUser(CreatedUserDto createdUserDto);
    ViewUserDto getUserByUserId(UUID id);
    void  updateUser(UUID idUpdatedUser, User newUser);
    void deleteUser(UUID idDeletedUsr);
}
