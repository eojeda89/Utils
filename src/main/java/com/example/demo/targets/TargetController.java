package com.example.demo.targets;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/targets")
public class TargetController {
    @Autowired
    SegmentService segmentService;

    @PostMapping(path = "/add", consumes = "multipart/form-data")
    public String addSegments(@RequestParam int jobId, @RequestParam MultipartFile file){
        try {
            String fileLocation = "/opt/tomcat/latest/" + file.getOriginalFilename();
            writeToFile(file.getInputStream(), fileLocation);
            //File inputDir = new File("E:\\PECAT\\inputDir\\");
            //int files = inputDir.listFiles().length;
            //if (inputDir.isDirectory() && files > 0) {
            File inputFile = new File(fileLocation);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
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
            inputFile.delete();
            return "Successfully!!!";
            //} else return "NO file on input directory...";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

        int l = 0;
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));

            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                l = l + read;
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (l);
    }

    @GetMapping(path = "/check")
    public String checkConnection(){
        return "Connection Success...";
    }
}
