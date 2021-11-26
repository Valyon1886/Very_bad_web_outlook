package com.example.archem_prac4.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.archem_prac4.models.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
