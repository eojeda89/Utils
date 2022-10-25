package com.example.demo.targets;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/targets")
public class TargetController {
    @Autowired
    SegmentService segmentService;

    @PostMapping(consumes = "multipart/form-data")
    public String addSegments(@RequestParam int jobId){
        try {
            File inputDir = new File("E:\\PECAT\\inputDir\\");
            int files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputDir.listFiles()[0]));
                BeanListProcessor<ImportJobRow> rowProcessor = new BeanListProcessor<>(ImportJobRow.class);
                TsvParserSettings parserSettings = new TsvParserSettings();
                parserSettings.setProcessor(rowProcessor);
                parserSettings.setHeaderExtractionEnabled(true);
                TsvParser parser = new TsvParser(parserSettings);
                parser.parse(bufferedReader);
                List<ImportJobRow> beans = rowProcessor.getBeans();
                List<Tu> tuList = segmentService.findTusInJob(jobId);
                int count = 0;
                for (ImportJobRow importJobRow : beans) {
                    Tu tu = tuList.stream().filter(tu1 -> tu1.getExtid().equals(importJobRow.getExtId())).collect(Collectors.toList()).get(0);
                    if (tu != null) {
                        segmentService.addSegments(tu.getId(), 1, importJobRow.getTranslation(), 1);
                        System.out.print("TU ID: " + tu.getId());
                    } else{
                        System.out.println("TU ID: Null");
                    }
                    count++;
                    System.out.println("    Done....(" + count + "/" + beans.size() + ")");
                }
                return "Successfully!!!";
            } else return "NO file on input directory...";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
