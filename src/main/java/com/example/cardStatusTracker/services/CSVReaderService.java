package com.example.cardStatusTracker.services;

import com.example.cardStatusTracker.models.CardStatus;
import com.example.cardStatusTracker.models.CardStatusTracker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;

/**
 * This service deals with the reading the data from csvs and it will return in form of entities
 */
@Service
public class CSVReaderService {
    private static final String CSV_FOLDER_PATH = "D:/Zywa(YC W22)Assigmenet/csvs";

    /**
     *
     * @param kewWord
     * @return list of all the csv data in the form of list of list of string array
     * in string array each value is the feild
     * in this data may comes from multiple csv file if file have provided key work
     */
    public List<List<String[]>> readAllTheCSVsWithKeyWord(String kewWord){
        File folder = new File(CSV_FOLDER_PATH);
        List<List<String[]>> allTheFileData=new ArrayList<>();
        if (folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".csv") && name.toLowerCase().contains(kewWord.toLowerCase()) );

            if (files != null) {
                for (File file : files) {
                    allTheFileData.add(readDataFromCsv(file));
                }
            }
        }
        return allTheFileData;
    }

    /**
     *
     * @param file
     * @return return the reads the all the feilds from the csv file and return in the form of list of string array
     */

    public List<String[]> readDataFromCsv(File file){
        try (CSVReader csvReader=new CSVReader(new FileReader(file))){
            List<String[]> allData = csvReader.readAll();
            return allData;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
    public List<CardStatusTracker> fromCSVToCardStatusTracker(CardStatus cardStatus,String keyWord){
        List<List<String[]>> redData=readAllTheCSVsWithKeyWord(keyWord);
        List<CardStatusTracker> cardStatusTrackerList=new ArrayList<>();
        for(var fileData:redData){
            for (var object:fileData) {
                CardStatusTracker cardStatusTracker=new CardStatusTracker();
                if(object[0].trim().equalsIgnoreCase("id"))continue;
                cardStatusTracker.setCardStatus(cardStatus);
                cardStatusTracker.setStatusId(object[0]);
                cardStatusTracker.setCardId(object[1]);
                cardStatusTracker.setUserMobileNumber(removeQuotes(object[2]));
                cardStatusTracker.setStatusTimeStamp(StringToLocalDateTime(object[3]));
                if(object.length>4)cardStatusTracker.setComment(object[4]);
                cardStatusTrackerList.add(cardStatusTracker);
            }

        }
        return cardStatusTrackerList;
    }

    /**
     *
     * @param timestamp in the String format from csv
     * @return standard format local date time
     */
    private LocalDateTime StringToLocalDateTime(String timestamp){
        DateTimeFormatter formatter;
        System.out.println(timestamp);
        // Check the format and choose the appropriate formatter
        if (timestamp.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")) {
            formatter = DateTimeFormatter.ISO_DATE_TIME;
        } else if (timestamp.matches("\\d{2}-\\d{2}-\\d{4} \\d{1,2}:[0-5][0-9] (AM|PM)")) {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a", Locale.US);
        }
        else if(timestamp.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}")) {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        } else if (timestamp.matches("\\d{2}-\\d{2}-\\d{4} \\d{1,2}:[0-5][0-9](AM|PM)")) {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma", Locale.US);
        }
        else {
            throw new IllegalArgumentException("Unsupported timestamp format: " + timestamp);
        }
        return LocalDateTime.parse(timestamp, formatter);
    }

    /**
     * This feild clean and standerdize the mobile numebr
     * @param input muobile number as string
     * @return the mobile number in the standard format
     */
    private static String removeQuotes(String input) {
        StringBuilder sb=new StringBuilder();
        boolean got=false;
        for(char i:input.toCharArray()){

            if(i =='5')got=true;
            if(i>='0'&&i<='9'&& got)sb.append(i);
        }
        return sb.toString();
    }
}
