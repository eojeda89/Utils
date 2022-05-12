package com.example.demo.xlsToTsv;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class XlsToTsv {

    @PostMapping("xlsToTsv/generate")
    public ResponseEntity<String> generateTSVFile(){
        LocalDateTime now = LocalDateTime.now();
        int files = 0;
        try {
            File inputDir = new File("D:\\PECAT\\inputDir\\");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                for (int i = 0; i < files; i++) {
                    File xlsx = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(xlsx);
                    System.out.println("FILE: " + xlsx.getName());
                    File dirOutput = new File("D:\\PECAT\\outputDir\\" + xlsx.getName());
                    dirOutput.mkdir();
                    Workbook workbook = new XSSFWorkbook(file);
                    int sheetIndex = 0;
                    for (Sheet sheet : workbook) {
                        sheet = workbook.getSheetAt(sheetIndex);
                        if (sheet.getSheetName().equals("Sheet1")) {
                            sheetIndex++;
                            continue;
                        }
                        System.out.println("Sheet: " + sheet.getSheetName());
                        File fileoutput = new File("D:\\PECAT\\outputDir\\" + xlsx.getName() + "\\toReview" + sheet.getSheetName() + ".tsv");
                        File fileoutput2 = new File("D:\\PECAT\\outputDir\\" + xlsx.getName() + "\\toTranslate" + sheet.getSheetName() + ".tsv");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(fileoutput2));
                        bw.write("id\tlocale\tinputText\ttranslation1\tnot_sure\tnote\n");
                        bw2.write("id\tlocale\tinputText\ttranslation1\tnot_sure\tnote\n");

                        int y = 0;
                        for (Row row : sheet) {
                            if (y == 0) {y++; continue;}
                            String key = "";
                            String source = "";
                            String target = "";
                            if (row.getCell(3) != null)
                                key = row.getCell(3).getRichStringCellValue().getString().replace("\n","") + "_" + y;
                            String language = sheet.getSheetName();
                            if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING)
                                source = row.getCell(1).getRichStringCellValue().getString().replace("\n","");
                            if (row.getCell(2) != null)
                                target = row.getCell(2).getRichStringCellValue().getString().replace("\n","");
                            if (!target.equals("") && !key.equals(""))
                                bw.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                            else if (!source.equals("") && !key.equals(""))
                                bw2.write(key + "\t" + language + "\t" + source + "\t\t\t\n");
                            else System.out.println("ROW NOT PROCESSED: " + (y + 1));
                            y++;
                        }
                        bw.close();
                        bw2.close();
                        sheetIndex++;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FUUUUCK!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(files + " files processed in " + (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)) + " seconds");
        return new ResponseEntity<>("GENERATED!!!", HttpStatus.CREATED);
    }

    @PostMapping("xlsToTsv/generate2")
    public ResponseEntity<String> generateONETSVFile(){
        LocalDateTime now = LocalDateTime.now();
        int files = 0;
        try {
            File inputDir = new File("D:\\PECAT\\inputDir\\");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                File fileoutputToReviewDE = new File("D:\\PECAT\\outputDir\\toReviewDE.tsv");
                File fileoutputToReviewES = new File("D:\\PECAT\\outputDir\\toReviewES.tsv");
                File fileoutputToReviewFR = new File("D:\\PECAT\\outputDir\\toReviewFR.tsv");
                File fileoutputToReviewRU = new File("D:\\PECAT\\outputDir\\toReviewRU.tsv");
                File fileoutputToReviewJP = new File("D:\\PECAT\\outputDir\\toReviewJP.tsv");
                File fileoutputTotranslateDE = new File("D:\\PECAT\\outputDir\\toTranslateDE.tsv");
                File fileoutputTotranslateES = new File("D:\\PECAT\\outputDir\\toTranslateES.tsv");
                File fileoutputTotranslateFR = new File("D:\\PECAT\\outputDir\\toTranslateFR.tsv");
                File fileoutputTotranslateRU = new File("D:\\PECAT\\outputDir\\toTranslateRU.tsv");
                File fileoutputTotranslateJP = new File("D:\\PECAT\\outputDir\\toTranslateJP.tsv");
                BufferedWriter bwTRDE = new BufferedWriter(new FileWriter(fileoutputToReviewDE));
                BufferedWriter bwTRES = new BufferedWriter(new FileWriter(fileoutputToReviewES));
                BufferedWriter bwTRFR= new BufferedWriter(new FileWriter(fileoutputToReviewFR));
                BufferedWriter bwTRRU = new BufferedWriter(new FileWriter(fileoutputToReviewRU));
                BufferedWriter bwTRJP = new BufferedWriter(new FileWriter(fileoutputToReviewJP));
                BufferedWriter bwTTDE = new BufferedWriter(new FileWriter(fileoutputTotranslateDE));
                BufferedWriter bwTTES = new BufferedWriter(new FileWriter(fileoutputTotranslateES));
                BufferedWriter bwTTFR = new BufferedWriter(new FileWriter(fileoutputTotranslateFR));
                BufferedWriter bwTTRU = new BufferedWriter(new FileWriter(fileoutputTotranslateRU));
                BufferedWriter bwTTJP = new BufferedWriter(new FileWriter(fileoutputTotranslateJP));
                String head = "id\tlocale\tinputText\ttranslation1\tnot_sure\tnote\n";
                bwTRDE.write(head);
                bwTRES.write(head);
                bwTRFR.write(head);
                bwTRRU.write(head);
                bwTRJP.write(head);
                bwTTDE.write(head);
                bwTTES.write(head);
                bwTTFR.write(head);
                bwTTRU.write(head);
                bwTTJP.write(head);
                for (int i = 0; i < files; i++) {
                    File xlsx = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(xlsx);
                    System.out.println("FILE: " + xlsx.getName());
                    /*bwTRDE.write(xlsx.getName() + "\n");
                    bwTRES.write(xlsx.getName() + "\n");
                    bwTRFR.write(xlsx.getName() + "\n");
                    bwTRRU.write(xlsx.getName() + "\n");
                    bwTRJP.write(xlsx.getName() + "\n");
                    bwTTDE.write(xlsx.getName() + "\n");
                    bwTTES.write(xlsx.getName() + "\n");
                    bwTTFR.write(xlsx.getName() + "\n");
                    bwTTRU.write(xlsx.getName() + "\n");
                    bwTTJP.write(xlsx.getName() + "\n");*/
                    File dirOutput = new File("D:\\PECAT\\outputDir\\" );
                    dirOutput.mkdir();
                    Workbook workbook = new XSSFWorkbook(file);
                    int sheetIndex = 0;
                    for (Sheet sheet : workbook) {
                        sheet = workbook.getSheetAt(sheetIndex);
                        if (sheet.getSheetName().equals("Sheet1")) {
                            sheetIndex++;
                            continue;
                        }
                        System.out.println("Sheet: " + sheet.getSheetName());
                        int y = 0;
                        for (Row row : sheet) {
                            if (y == 0) {y++; continue;}
                            String key = "";
                            String source = "";
                            String target = "";
                            if (row.getCell(3) != null)
                                key = row.getCell(3).getRichStringCellValue().getString().replace("\n","") + "_" + y;
                            String language = sheet.getSheetName();
                            if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING)
                                source = row.getCell(1).getRichStringCellValue().getString().replace("\n","");
                            if (row.getCell(2) != null)
                                target = row.getCell(2).getRichStringCellValue().getString().replace("\n","");

                            if (!target.equals("") && !key.equals("")) {
                                switch (sheet.getSheetName()){
                                    case "DE":{
                                        bwTRDE.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "ES":{
                                        bwTRES.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "FR":{
                                        bwTRFR.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "RU":{
                                        bwTRRU.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "JP":{
                                        bwTRJP.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                }
                            }
                            else if (!source.equals("") && !key.equals("")) {
                                switch (sheet.getSheetName()){
                                    case "DE":{
                                        bwTTDE.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "ES":{
                                        bwTTES.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "FR":{
                                        bwTTFR.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "RU":{
                                        bwTTRU.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                    case "JP":{
                                        bwTTJP.write(key + "\t" + language + "\t" + source + "\t" + target + "\t\t\n");
                                        break;
                                    }
                                }
                            }
                            else System.out.println("ROW NOT PROCESSED: " + (y + 1));
                            y++;
                        }
                        sheetIndex++;
                    }
                }
                bwTRDE.close();
                bwTRES.close();
                bwTRFR.close();
                bwTRRU.close();
                bwTRJP.close();
                bwTTDE.close();
                bwTTES.close();
                bwTTFR.close();
                bwTTRU.close();
                bwTTJP.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FUUUUCK!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(files + " files processed in " + (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)) + " seconds");
        return new ResponseEntity<>("GENERATED!!!", HttpStatus.CREATED);
    }

    @PostMapping("xlsToTsv/modifyTSV")
    public ResponseEntity<String> modifyTSVFile() {
        int files = 0;
        try {
            File inputDir = new File("D:\\PECAT\\inputDir\\");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                for (int i = 0; i < files; i++) {
                    File xlsx = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(xlsx);
                    BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
                    System.out.println("FILE: " + xlsx.getName());

                    File fileoutput = new File("D:\\PECAT\\outputDir\\" + xlsx.getName());
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
                    String line = br.readLine();
                    int ommitedLines = 0;
                    int changedLines = 0;
                    while (line != null) {
                        if (line.startsWith("A bunch of fives")
                                || line.startsWith("Brummagem screwdriver")
                                || line.startsWith("Dog's breakfast")
                                || line.startsWith("Good in parts")
                                || line.startsWith("Wotcher")
                                || line.startsWith("Damp squib")
                                || line.startsWith("Work one's arse off")
                                || line.startsWith("Fall off the back of a lorry")
                                || line.startsWith("The smallest room in the house")
                                || line.startsWith("Blind-man's buff")
                                || line.startsWith("Is the Pope Polish?")
                                || line.startsWith("Hooray Henry")
                                || line.startsWith("bang on")
                                || line.startsWith("Sleeveless errand")
                                || line.startsWith("Money for old rope")
                                || line.startsWith("Far from the madding crowd")
                                || line.startsWith("Rag-and-bone man")
                                || line.startsWith("Pick 'n' mix")
                                || line.startsWith("Bricks and clicks")
                                || line.startsWith("head the ball")
                                || line.startsWith("Local derby")
                                || line.startsWith("Panic stations")
                                || line.startsWith("Tissue of lies")
                                || line.startsWith("Storm in a teacup")
                                || line.startsWith("Copper-bottomed")
                                || line.startsWith("The year dot")
                                || line.startsWith("Donkey's years")
                        ) {
                            line = br.readLine();
                            ommitedLines++;
                            continue;
                        }
                        if (line.startsWith("Do a favour")) {
                            line = line.replaceAll("favour", "favor");
                            changedLines++;
                        }
                        bw.write(line+"\n");
                        line = br.readLine();
                    }

                    System.out.println("OMITTED LINES: " + ommitedLines);
                    System.out.println("CHANGED LINES: " + changedLines);
                    br.close();
                    bw.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("xlsToTsv/modifyTSV2")
    public ResponseEntity<String> modifyTSVFile2() {
        int files = 0;
        try {
            File inputDir = new File("D:\\PECAT\\inputDir\\");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                for (int i = 0; i < files; i++) {
                    File xlsx = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(xlsx);
                    BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
                    System.out.println("FILE: " + xlsx.getName());

                    File fileoutput = new File("D:\\PECAT\\outputDir\\" + xlsx.getName());
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
                    String line = br.readLine();
                    int counter = 1;
                    while (line != null){
                        if (!line.trim().replace("\n", "").replace("\t", "").isEmpty()){
                            bw.write(line.replace("\n", "") + "\n");
                        } else {
                            System.out.println("LINE OMITTED: " + counter);
                        }
                        line = br.readLine(); counter++;
                    }
                    br.close();
                    bw.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
