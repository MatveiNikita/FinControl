package md.crudproject.fincontrol.service.impl;

import jakarta.transaction.Transactional;
import md.crudproject.fincontrol.dto.CreatedUserDto;
import md.crudproject.fincontrol.dto.ViewUserDto;
import md.crudproject.fincontrol.model.User;
import md.crudproject.fincontrol.repository.UserRepository;
import md.crudproject.fincontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void createUser(CreatedUserDto createdUserDto) {
        Optional.of(createdUserDto).
                filter(dto -> !userRepository.existsByEmailIgnoreCase(dto.email()))
                .map(dto -> User.builder()
                        .username(dto.username())
                        .email(dto.email())
                        .password(dto.password())
                        .dateOfBirth(dto.dateOfBirth())
                        .build())
                .ifPresentOrElse(userRepository::save,
                        () -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email: " + createdUserDto.email() + " is exist");
                        });
    }


    @Override
    public ViewUserDto getUserByUserId(UUID id) {
        return userRepository.findById(id).map(user -> new ViewUserDto(user.getUsername(), user.getEmail(), user.getDateOfBirth()))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id - " + id + " is not founded"));
    }

    @Transactional
    @Override
    public void updateUser(UUID idUpdatedUser, User newUser) {

        User updatedUser = userRepository.findById(idUpdatedUser).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id - " + idUpdatedUser + " is not founded"));
        updatedUser.setUsername(newUser.getUsername());
        updatedUser.setDateOfBirth(newUser.getDateOfBirth());
        userRepository.save(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(UUID idDeletedUsr) {
        User deletesUser = userRepository.findById(idDeletedUsr).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id - " + idDeletedUsr + " is not founded"
        ));
        userRepository.delete(deletesUser);
    }

    public User getUserByEmail(String email){
        return userRepository.getUsersByEmailIgnoreCase(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email - " + email + " is not founded")
        );
    }
}
