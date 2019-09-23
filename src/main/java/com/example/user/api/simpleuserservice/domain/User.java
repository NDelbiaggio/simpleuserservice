package com.example.user.api.simpleuserservice.domain;

import com.example.user.api.simpleuserservice.domain.IUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements IUser {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    @NotEmpty
    String name;

    @NotEmpty
    String groupId;

    public User(@NotEmpty String name, @NotEmpty String groupId) {
        this.name = name;
        this.groupId = groupId;
    }
}
