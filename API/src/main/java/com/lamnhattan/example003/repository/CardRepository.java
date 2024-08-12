package com.lamnhattan.example003.repository;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamnhattan.example003.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByGooglecustomer(BigInteger google_customer);
}
