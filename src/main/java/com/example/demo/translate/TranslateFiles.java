package com.example.demo.translate;

import com.example.demo.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.MurmurHash3;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/translate")
public class TranslateFiles {

    private final List<String> targetIdioms = Arrays.asList("es");//"zh","de","fr","ja","pt","ko","ru","it","pl","nl",,"tr","cs","ar","hi"

    @PostMapping(value = "")
    public ResponseEntity<String> translateFile(){
        //Procesando el fichero y creando el body del request
        int files = 0;
        try {
            File inputDir = new File("D:/4-Trabajo/translate/input");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                for (int i = 0; i < files; i++) {
                    File inputFile = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(inputFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
                    System.out.println("FILE: " + inputFile.getName());
                    List<String> texts = new ArrayList<>();
                    String line = br.readLine();
                    while (line != null){
                        if (30<line.length()&&500>line.length()) {
                            texts.add(line.trim().replace("\t", " "));
                        }
                        line = br.readLine();
                    }
                    br.close();
                    for (String targetIdiom : targetIdioms) {
                        int id = 1;
                        //Creando el body del request de 20 en 20
                        File fileoutput = new File("D:/4-Trabajo/translate/output/" + inputFile.getName() + "_en_" + targetIdiom + ".tsv");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
                        bw.write("id\tlocale\tinputText\ttranslation1\tmark\tnot_sure\tnote\n");
                        for (int index = 0; index-1 < texts.size() / 20; index ++) {
                            TranslatePostObject translateModel = new TranslatePostObject();
                            translateModel.setSrc("en");
                            translateModel.setTgt(targetIdiom);
                            List<String> segments = (index < (texts.size() / 20))?texts.subList(index*20, index*20+20):texts.subList(index*20, texts.size());
                            translateModel.setText(segments);
                            translateModel.setOrigin("PangeaMT");
                            translateModel.setUsername("admin@pangeanic.mt");
                            translateModel.setMode(targetIdiom.equals("pt") ? 10 : 2);
                            //Creando el archivo de salida

                            //Creando la llamada al servicio
                            try {
                                RestTemplate restTemplate = new RestTemplate();
                                HttpHeaders headers = new HttpHeaders();
                                headers.setContentType(MediaType.APPLICATION_JSON);
                                String transUri = "http://prod.pangeamt.com:8080/NexRelay/v1/translate";
                                String result = "";

                                HttpEntity<TranslatePostObject> request = new HttpEntity<>(translateModel, headers);
                                ResponseEntity<String> responseEntity = restTemplate.exchange(transUri, HttpMethod.POST, request, String.class);
                                result = responseEntity.getBody();
                                //System.out.println(result);
                                List<List<LinkedHashMap<String, String>>> response = new ArrayList<>();
                                response = new ObjectMapper().readValue(result, List.class);
                                //System.out.println("response: " + response);
                                for (int x = 0; x < response.size(); x++) {
                                    List<LinkedHashMap<String, String>> trs = response.get(x);
                                    LinkedHashMap<String, String> ss = trs.get(0);
                                    //System.out.println("Line " + (id) + "\t" + ss.get("src") + "\t" + ss.get("tgt"));
                                    bw.write((id) + "\t" + translateModel.getSrc() + "\t" + ss.get("src") + "\t" + ss.get("tgt") + "\n");
                                    id++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                //return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        }
                        System.out.println("**********************************END OF FILE " + fileoutput.getName() +"********************************************************");
                        bw.close();
                    }
                }
            } else{
                return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFiles(@RequestParam MultipartFile...files){
        if (files.length>0){
            for (MultipartFile file: files){
                try {
                    writeToFile(file.getInputStream(), "/opt/tomcat/translate/input/" + file.getName());
                }catch (Exception e){
                    e.printStackTrace();
                    return new ResponseEntity<>("UFF!!!", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>("OK!!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("OH!!!", HttpStatus.BAD_REQUEST);
        }
    }

    // save uploaded file to new location
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

    @PostMapping(value = "/normalize")
    public ResponseEntity<String> normalizeFile(){
        //Procesando el fichero y creando el body del request
        int files = 0;
        try {
            File inputDir = new File("D:/4-Trabajo/translate/normalizing/input");
            files = inputDir.listFiles().length;
            if (inputDir.isDirectory() && files > 0) {
                for (int i = 0; i < files; i++) {
                    File inputFile = inputDir.listFiles()[i];
                    FileInputStream file = new FileInputStream(inputFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
                    System.out.println("FILE: " + inputFile.getName());
                    List<String> texts = new ArrayList<>();
                    String line = br.readLine();
                    while (line != null){
                        //if (30<line.length()&&500>line.length()) {
                        if (!line.equals("\r\n"))
                            texts.add(line
                                    .trim()
                                    .replace("\t", " ")
                                    .replace("\t\n", " ")
                                    .replace("。", ".\n")
                                    .replace("。。。\n", "。。。 ")
                                    .replace("; ", ";\n")
                                    .replace("。[", "。\n[")
                                    .replaceAll("op\\.\n", "op。 ")
                                    .replaceAll("v\\.\n", "v。 ")
                                    .replaceAll("\\b(https?|ftp|file)://[\\w+&@#/%?=~_|!:,.;]*[\\w+&@#/%=~_|]", "")
                                    .replaceAll("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+", "")
                                    .replaceAll("(A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)\\.\n", "")
                            );
                        //}
                        line = br.readLine();
                    }
                    br.close();
                    File fileoutput = new File("D:/4-Trabajo/translate/normalizing/output/" + inputFile.getName());
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutput));
                    for (String text : texts) {
                        bw.write(text);
                    }
                    System.out.println("**********************************END OF FILE " + fileoutput.getName() +"********************************************************");
                    bw.close();

                }
            } else{
                return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
