package com.example.demo.targets;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;

@Data
public class ImportJobRow {
    @Parsed(field = "id")
    private String extId;
    @Parsed(field = {"source", "filename", "inputText", "src", "srcText"})
    private String source;
    @Parsed(field = {"translation1", "transcription", "audio", "tags", "target", "tgt", "trans", "translation"})
    private String translation;
    @Parsed(field = {"translation2", "alternative", "alttranslation", "alttrans"})
    private String altTranslation;
    @Parsed(field = "mark")
    private int mark = 0;
    @Parsed
    private String transcode = "";
}
