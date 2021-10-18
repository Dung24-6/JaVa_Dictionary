package Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import GoogleTranslator.GoogleTranslator;
import GoogleTranslator.GoogleTranslator.LANGUAGE;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
    }

    /*
     * Nhap vao tu dong lenh.
     */
    public void insertFromCommandline() {
        System.out.print("Nhap so tu nhap vao tu dien: ");
        Scanner scanner = new Scanner(System.in);
        int wordNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < wordNumber; i++) {
            System.out.print("Nhap tu moi ");
            String eng = scanner.nextLine();
            System.out.print("Nhap giai nghia ");
            String viet = scanner.nextLine();
            Word wordtam = new Word(eng, viet);
            wordList.add(wordtam);
        }
        System.out.println();
    }

    /*
     * Nhap vao tu file.
     */
    public void insertFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter("\t");
        while (scanner.hasNext()) {
            Word word = new Word();
            word.setWord_target(scanner.next());
            word.setWord_explain((scanner.nextLine()).trim());
            wordList.add(word);
        }
        scanner.close();
    }

    /*
     * Xuat tu ra file.
     */
    public void dictionaryExportToFile(String filePath) throws IOException {
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (Word i : wordList) {
            outputStreamWriter.write(i.getWord_target());
            outputStreamWriter.write("\t\t");
            outputStreamWriter.write(i.getWord_explain());
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.flush();
    }

    /*
     * Dich tu nhap tu dong lenh bang google translator.
     */
    public String googleTranslator(String word, LANGUAGE dest) throws IOException, ParseException {
        GoogleTranslator translator = new GoogleTranslator();
        translator.setSrcLang(LANGUAGE.AUTO);
        translator.setDestLang(dest);
        return translator.translate(word);
    }

    /*
     * Tim kiem chinh xac 1 tu trong danh sach.
     */
    public String dictionaryLookup(String word) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(word)) {
                return i.getWord_explain();
            }
        }
        return "Sử dụng google translate API";
    }

    /*
     * Them 1 tu vao trong danh sach.
     */
    public boolean addWord(String wordTarget, String wordExplain) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(wordTarget)
                && i.getWord_explain().equalsIgnoreCase(wordExplain)) {
                return false;
            }
        }
        Word word = new Word(wordTarget, wordExplain);
        wordList.add(word);
        return true;
    }

    /*
     * Thay doi nghia cua tu trong danh sach.
     */
    public boolean modifyWord(String wordTarget, String wordExplain) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(wordTarget)) {
                i.setWord_explain(wordExplain);
                return true;
            }
        }
        return false;
    }

    /*
     * Xoa 1 tu khoi danh sach.
     */
    public boolean deleteWord(String word) {
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord_target().equalsIgnoreCase(word)) {
                wordList.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * Sap xep danh sach.
     */
    public void dictionarySort() {
        Collections.sort(wordList);
    }

    /*
     * Loai bo tu trung trong danh sach.
     */
    public void dictionaryRemoveDuplicates() {
        for (int i = 1; i < wordList.size(); i++) {
            if (wordList.get(i).getWord_target().equalsIgnoreCase(wordList.get(i - 1).getWord_target())
                && wordList.get(i).getWord_explain().equalsIgnoreCase(wordList.get(i - 1).getWord_explain())) {
                wordList.remove(i);
            }
        }
    }
}
