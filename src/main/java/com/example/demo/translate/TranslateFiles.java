package com.example.demo.translate;

import com.example.demo.UserModel;
import org.apache.commons.codec.digest.MurmurHash3;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TranslateFiles {

    @PostMapping(value = "translate", consumes = "multipart/form-data")
    public ResponseEntity<String> translateFile(@RequestParam(value = "file") MultipartFile file){
        //Procesando el fichero y creando el body del request
        //TODO ver el formato del archivo para mapearlo al objeto
        TranslatePostObject translateModel = new TranslatePostObject();
        translateModel.setSrc("en");
        translateModel.setTgt("es");
        List<String> texts = new ArrayList<>();
        texts.add("My dog is green");
        translateModel.setText(texts);
        translateModel.setOrigin("PangeaMT");
        translateModel.setUsername("admin@pangeanic.mt");
        translateModel.setMode(2);
        //Creando la llamaa al servicio
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject req = new JSONObject();
            String transUri = "http://prod.pangeamt.com:8080/NexRelay/v1/translate";
            String result = "";

            HttpEntity<TranslatePostObject> request = new HttpEntity<>(translateModel, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(transUri, HttpMethod.POST, request, String.class);
            result = responseEntity.getBody();
            System.out.println(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
