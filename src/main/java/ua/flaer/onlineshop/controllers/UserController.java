package ua.flaer.onlineshop.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.model.dto.UserDto;
import ua.flaer.onlineshop.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
          UserDto savedUser = userService.createUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userService.getUsers();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
         if (!userService.isExist(id)){
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }

         UserDto foundUser = userService.findUserById(id).get();

         return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> fullUserUpdate(@PathVariable("id") Long id,
                                                  @RequestBody UserDto user){
        if (!userService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto updatedUser = userService.fullUpdateById(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUserUpdate(@PathVariable("id") Long id,
                                                     @RequestBody UserDto user){
        if (!userService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto updatedUser = userService.partialUpdate(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
