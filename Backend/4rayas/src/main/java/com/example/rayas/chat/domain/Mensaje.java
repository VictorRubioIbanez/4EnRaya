package com.example.rayas.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document(collection = "mensajes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    private String id;
    private String texto;
    private Long fecha;
    private String user;
    private String tipo;
    private  String color;




}
