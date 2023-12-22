package com.example.cardStatusTracker.mappers;

import com.example.cardStatusTracker.Dto.CardStatusTrackerDto;
import com.example.cardStatusTracker.models.CardStatusTracker;

public class CardStatusTrackerMapper {
    public static CardStatusTrackerDto CardStatusTrackerToCardStatusTrackerDto(CardStatusTracker cardStatusTracker,CardStatusTrackerDto cardStatusTrackerDto){
        cardStatusTrackerDto.setCardStatus(cardStatusTracker.getCardStatus());
        cardStatusTrackerDto.setCardId(cardStatusTracker.getCardId());
        cardStatusTrackerDto.setComment(cardStatusTracker.getComment());
        cardStatusTrackerDto.setStatusTimeStamp(cardStatusTracker.getStatusTimeStamp());
        cardStatusTrackerDto.setUserMobileNumber(cardStatusTracker.getUserMobileNumber());
        return cardStatusTrackerDto;
    }
}

