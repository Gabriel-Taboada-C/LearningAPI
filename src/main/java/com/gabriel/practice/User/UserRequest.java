package com.gabriel.practice.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "{user.name.notblank}")
    @Size(min = 3, message = "{user.name.size}")
    private String name;

    @NotBlank (message = "{user.password.notblank}")
    @Size (min = 4, message = "{user.passord.size}")
    private String password;
}
