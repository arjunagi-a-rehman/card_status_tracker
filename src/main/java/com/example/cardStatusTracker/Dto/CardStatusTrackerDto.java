package com.example.cardStatusTracker.Dto;

import com.example.cardStatusTracker.models.CardStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(
        name = "CardTrackingStatus schema",
        description = "This schema consists of the details regarding card, user and it's status"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardStatusTrackerDto {
    @Schema(
            description = "The card number",
            example = "ZYW8991"
    )
    private String cardId;
    @Schema(
            description = "The Customer Mobile Number",
            example = "545576587"
    )
    private String userMobileNumber;
    @Schema(
            description = "The time stamp when status updated",
            example = "2023-11-13 09:34:56.000000"
    )
    private LocalDateTime statusTimeStamp;
    @Schema(
            description = "The status of card"
            ,example = "DELIVERED"
    )
    private CardStatus cardStatus;
    @Schema(
            description = "The comments on status update",
            example = "User not available"
    )
    private String comment;
}
