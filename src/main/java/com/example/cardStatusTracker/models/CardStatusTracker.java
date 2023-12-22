package com.example.cardStatusTracker.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class CardStatusTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statusId;
    private String cardId;
    private String userMobileNumber;
    private LocalDateTime statusTimeStamp;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private String comment;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;

}