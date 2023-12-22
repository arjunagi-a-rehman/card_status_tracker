package com.example.cardStatusTracker.controller;

import com.example.cardStatusTracker.Dto.CardStatusTrackerDto;
import com.example.cardStatusTracker.Dto.ErrorResponseDto;
import com.example.cardStatusTracker.Dto.ResponseDto;
import com.example.cardStatusTracker.models.CardStatusTracker;
import com.example.cardStatusTracker.services.CardStatusTrackerServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(
        name = " API's for Card delivery related operations",
        description = "REST API's to do card status related operations like get the status read the status from csv"
)
@RestController
@Validated
public class CardStatusTrackerController {
    @Autowired
    private CardStatusTrackerServices cardStatusTrackerServices;
    @Operation(
            summary = "Store the data from CSV to db",
            description = "Though the system is schedule to read the data every day at 10am but if still user want to read the data in between or some new data arrive then this can be used"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/read_CSVs")
    public ResponseEntity<ResponseDto> readAllCSVS(){
        cardStatusTrackerServices.saveCardsFromCSV();
        return new ResponseEntity<>(new ResponseDto("201","added all the new records successfully"), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get card status and other related details REST API",
            description = "API to get  card status and other related details like user Mobile number card number and comments for the provided customer mobile number or card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/get_card_status")
    public ResponseEntity<CardStatusTrackerDto> getCardData(@RequestParam @Pattern(regexp = "^(?:\\d{9}|ZYW\\d{4})$",message = "if entering mobile number it should be 9 digit number , if Card number it should start with ZYW") String userMobileOrCardNumber){
        return ResponseEntity.ok(cardStatusTrackerServices.getCardData(userMobileOrCardNumber));
    }
}
