package md.crudproject.fincontrol.controller;

import md.crudproject.fincontrol.dto.CreatedUserDto;
import md.crudproject.fincontrol.dto.ViewUserDto;
import md.crudproject.fincontrol.model.User;
import md.crudproject.fincontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new-user")
    public ResponseEntity<User> createUser(@RequestBody CreatedUserDto createdUserDto){
        userService.createUser(createdUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID idOfUser){
        ViewUserDto viewUserDto = userService.getUserByUserId(idOfUser);
        return ResponseEntity.ok(viewUserDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User newUser){
     userService.updateUser(id, newUser);
     return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable UUID id){ //FIXME: проверить логику
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
