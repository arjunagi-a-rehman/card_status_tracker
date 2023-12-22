package com.example.cardStatusTracker.services;

import com.example.cardStatusTracker.Dto.CardStatusTrackerDto;
import com.example.cardStatusTracker.mappers.CardStatusTrackerMapper;
import com.example.cardStatusTracker.models.CardStatus;
import com.example.cardStatusTracker.models.CardStatusTracker;
import com.example.cardStatusTracker.repository.ICardStatusTrackerRepo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardStatusTrackerServices {
    private ICardStatusTrackerRepo cardStatusTrackerRepo;
    private CSVReaderService csvReaderService;
    @Scheduled(cron = "0 0 10 * * ?")
    public void saveCardsFromCSV(){

        List<CardStatusTracker>  cardStatusTrackerList=csvReaderService.fromCSVToCardStatusTracker(CardStatus.PICKED,"pickup");
        for (CardStatusTracker cardStatusTracker:cardStatusTrackerList){
            if(cardStatusTrackerRepo.findByUserMobileNumberAndStatusTimeStampAndCardId(cardStatusTracker.getUserMobileNumber(), cardStatusTracker.getStatusTimeStamp(), cardStatusTracker.getCardId()).isEmpty()){
                cardStatusTrackerRepo.save(cardStatusTracker);
            }
        }
        cardStatusTrackerList=csvReaderService.fromCSVToCardStatusTracker(CardStatus.DELIVERED,"delivered");
        for (CardStatusTracker cardStatusTracker:cardStatusTrackerList){
            if(cardStatusTrackerRepo.findByUserMobileNumberAndStatusTimeStampAndCardId(cardStatusTracker.getUserMobileNumber(), cardStatusTracker.getStatusTimeStamp(), cardStatusTracker.getCardId()).isEmpty()){
                cardStatusTrackerRepo.save(cardStatusTracker);
            }
        }
        cardStatusTrackerList=csvReaderService.fromCSVToCardStatusTracker(CardStatus.DELIVERY_EXCEPTION,"exception");

        for (CardStatusTracker cardStatusTracker:cardStatusTrackerList){
            if(cardStatusTrackerRepo.findByUserMobileNumberAndStatusTimeStampAndCardId(cardStatusTracker.getUserMobileNumber(), cardStatusTracker.getStatusTimeStamp(), cardStatusTracker.getCardId()).isEmpty()){
                cardStatusTrackerRepo.save(cardStatusTracker);
            }
        }
        cardStatusTrackerList=csvReaderService.fromCSVToCardStatusTracker(CardStatus.RETURNED,"returned");
        for (CardStatusTracker cardStatusTracker:cardStatusTrackerList){
            if(cardStatusTrackerRepo.findByUserMobileNumberAndStatusTimeStampAndCardId(cardStatusTracker.getUserMobileNumber(), cardStatusTracker.getStatusTimeStamp(), cardStatusTracker.getCardId()).isEmpty()){
                cardStatusTrackerRepo.save(cardStatusTracker);
            }
        }

    }

    public CardStatusTrackerDto getCardData(String userMobileOrCardNumber) {
        if(userMobileOrCardNumber.matches("ZYW\\d+")){
            CardStatusTracker cardStatusTracker=cardStatusTrackerRepo.findMostRecentByCardNumber(userMobileOrCardNumber).orElseThrow();
            return CardStatusTrackerMapper.CardStatusTrackerToCardStatusTrackerDto(cardStatusTracker,new CardStatusTrackerDto());
        }else {
            CardStatusTracker cardStatusTracker=cardStatusTrackerRepo.findMostRecentByUserMobileNumber(userMobileOrCardNumber).orElseThrow();
            return CardStatusTrackerMapper.CardStatusTrackerToCardStatusTrackerDto(cardStatusTracker,new CardStatusTrackerDto());
        }
    }

}