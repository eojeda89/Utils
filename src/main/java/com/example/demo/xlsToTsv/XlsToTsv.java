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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
                                key = row.getCell(3).getRichStringCellValue().getString() + "_" + y;
                            String language = sheet.getSheetName();
                            if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING)
                                source = row.getCell(1).getRichStringCellValue().getString();
                            if (row.getCell(2) != null)
                                target = row.getCell(2).getRichStringCellValue().getString();
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

}
