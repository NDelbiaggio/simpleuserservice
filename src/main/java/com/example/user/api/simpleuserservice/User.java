package com.example.user.api.simpleuserservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User implements IUser{

    @Id
    @NotEmpty
    String id;

    @NotEmpty
    String name;

    @NotEmpty
    String groupId;

}
