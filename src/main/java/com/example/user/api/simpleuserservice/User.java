package com.example.user.api.simpleuserservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements IUser{

    @Id
    @NotEmpty
    String id;

    @NotEmpty
    String name;

    @NotEmpty
    String groupId;

}
