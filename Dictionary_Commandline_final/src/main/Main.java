package main;

import java.io.IOException;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        DictionaryCommandline d = new DictionaryCommandline();
        // d.dictionaryBasic();
        d.dictionaryAdvaned();

        d.googleTranslator();
    }
}
