package com.example.demo.translate;

import lombok.Data;

import java.util.List;

@Data
public class TranslatePostObject {
    String src;
    String tgt;
    List<String> text;
    int mode;
    String origin;
    String username;
}
