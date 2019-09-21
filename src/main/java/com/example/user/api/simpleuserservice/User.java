package com.example.user.api.simpleuserservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User implements IUser{

    @Id
    String id;

    String name;

    String groupId;

}
