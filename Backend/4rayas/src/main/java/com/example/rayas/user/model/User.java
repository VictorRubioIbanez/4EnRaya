package com.example.rayas.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Players")
public class User {

    @Id
    private String id;

    private String userName;

    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
