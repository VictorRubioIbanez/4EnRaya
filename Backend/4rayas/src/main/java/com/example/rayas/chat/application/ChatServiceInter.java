package com.example.rayas.chat.application;

import com.example.rayas.chat.domain.Mensaje;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ChatServiceInter {

     List<Mensaje> obtenerUltimos10Mensajes();
     Mono saveMensaje(Mensaje mensaje);
}