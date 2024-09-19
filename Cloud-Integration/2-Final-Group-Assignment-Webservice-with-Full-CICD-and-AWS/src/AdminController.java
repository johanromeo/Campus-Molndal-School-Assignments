package com.jromeo.webservice.auth.controller;

import com.jromeo.webservice.auth.entity.UserEntity;
import com.jromeo.webservice.auth.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Johan Romeo and Sandra Jeppsson Kristiansson
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntity> findUserById(@PathVariable(value = "id") Integer userId) {
//        return new ResponseEntity<>(adminService.findUserById(userId), HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable(value = "id") Integer userId) {
        try {
            UserEntity author = adminService.findUserById(userId);
            return ResponseEntity.ok(author);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return new ResponseEntity<>(adminService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<UserEntity> updateUserRole(@PathVariable(value = "id") Integer userId) {
        return new ResponseEntity<>(adminService.updateUserRole(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Integer userId) {
        adminService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<UserEntity> removeRole(@PathVariable(value = "id") Integer userId) {
        return new ResponseEntity<>(adminService.removeRole(userId), HttpStatus.NO_CONTENT);
    }
}
