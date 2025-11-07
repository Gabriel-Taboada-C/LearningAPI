package com.gabriel.practice.Authorization;

import com.gabriel.practice.User.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String name;
    String password;
    UserEntity.Rol rol;

}
