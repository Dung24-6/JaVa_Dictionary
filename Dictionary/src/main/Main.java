package main;

import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        DictionaryCommandline d = new DictionaryCommandline();
        // d.dictionaryBasic();
        d.dictionaryAdvaned();

        d.googleTranslator(GoogleTranslator.LANGUAGE.AUTO, GoogleTranslator.LANGUAGE.ENGLISH);
    }
}
