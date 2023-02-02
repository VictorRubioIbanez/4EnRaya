package com.example.rayas.chat.application;

import com.example.rayas.chat.domain.Mensaje;
import com.example.rayas.chat.infraestructure.repository.ChatRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceInterface implements ChatServiceInter {

    @Autowired
    ChatRepository chatrepository;


    @Override
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatrepository.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mono saveMensaje(Mensaje mensaje) {
        return chatrepository.save(mensaje);
    }
}
