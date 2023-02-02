package com.example.rayas.chat.infraestructure.repository;

import com.example.rayas.chat.domain.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ChatRepository extends ReactiveMongoRepository<Mensaje, String> {
    public List<Mensaje> findFirst10ByOrderByFechaDesc();
}