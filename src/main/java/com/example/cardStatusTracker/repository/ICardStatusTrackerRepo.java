package com.example.cardStatusTracker.repository;

import com.example.cardStatusTracker.models.CardStatusTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ICardStatusTrackerRepo extends JpaRepository<CardStatusTracker,Long> {
    List<CardStatusTracker> findByUserMobileNumberAndStatusTimeStampAndCardId(String userMobileNumber, LocalDateTime statusTimeStamp, String cardId);
    @Query("SELECT c FROM CardStatusTracker c WHERE c.cardId = :cardNumber ORDER BY c.statusTimeStamp DESC LIMIT 1")
    Optional<CardStatusTracker> findMostRecentByCardNumber(@Param("cardNumber") String cardNumber);

    @Query("SELECT c FROM CardStatusTracker c WHERE c.userMobileNumber = :userMobileNumber ORDER BY c.statusTimeStamp DESC LIMIT 1")
    Optional<CardStatusTracker> findMostRecentByUserMobileNumber(@Param("userMobileNumber") String userMobileNumber);

}
