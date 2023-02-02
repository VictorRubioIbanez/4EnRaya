package com.example.rayas.chat.infraestructure.controller;

import com.example.rayas.chat.application.ChatServiceInterface;
import com.example.rayas.chat.domain.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import java.util.Random;

@RestController
public class ChatController {
    @Autowired
    ChatServiceInterface chatServiceInter;
    @Autowired
    SimpMessagingTemplate websoccket;
    private String[] colores ={"red","green","blue","magenta","purple","orange"};

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals(("NUEVO_USUARIO"))){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");
        }else {chatServiceInter.saveMensaje(mensaje);}
        return mensaje;
    }


    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username){
        return username.concat("  esta escribiendo....");
    }

    @MessageMapping("/historial")
    public void historial(String clienteId){
        websoccket.convertAndSend("/chat/historial/" + clienteId, chatServiceInter.obtenerUltimos10Mensajes());

    }



}
