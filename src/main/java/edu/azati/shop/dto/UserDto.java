package edu.azati.shop.dto;

import com.sun.istack.NotNull;
import edu.azati.shop.validation.PasswordMatches;
import edu.azati.shop.validation.ValidEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    @ValidEmail
    private String email;

    public UserDto() {
    }
}
