package com.springforum.app.Modules.User.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserProfileDetailsDTO(
        @NotNull
        @NotEmpty
        String userName,

        @NotNull
        @NotEmpty
        String userImageURL,

        @NotNull
        @NotEmpty
        long userId,

        @NotNull
        @NotEmpty
        String userCreationDate
) {
}
