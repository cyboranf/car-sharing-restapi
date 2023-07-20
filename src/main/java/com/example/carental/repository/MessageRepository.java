package com.example.carental.repository;

import com.example.carental.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender.id = :senderId AND m.recipient.id = :recipientId) OR (m.sender.id = :recipientId AND m.recipient.id = :senderId)")
    List<Message> findMessagesBetweenUsers(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);
}
