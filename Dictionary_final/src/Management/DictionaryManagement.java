package Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
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
        System.out.print("Số từ bạn nhập vào từ điển: ");
        Scanner scanner = new Scanner(System.in);
        int wordNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < wordNumber; i++) {
            System.out.print("Nhập từ mới: ");
            String eng = scanner.nextLine();
            System.out.print("Nhập giải nghĩa: ");
            String viet = scanner.nextLine();
            Word wordtam = new Word(eng, viet);
            wordList.add(wordtam);
        }
        System.out.println();
    }

    /*
     * Nhap vao tu file.
     */
    public void insertFromFile(String filePath, List<Word> list) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter("\t");
        while (scanner.hasNext()) {
            Word word = new Word();
            word.setWord_target(scanner.next());
            word.setWord_explain((scanner.nextLine()).trim());
            list.add(word);
        }
        scanner.close();
    }

    /*
     * Xuat tu ra file.
     */
    public void dictionaryExportToFile(String filePath, List<Word> list) throws IOException {
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (Word i : list) {
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
    public static String googleTranslator(String word, LANGUAGE src, LANGUAGE dest) throws IOException, ParseException {
        GoogleTranslator translator = new GoogleTranslator();
        translator.setSrcLang(LANGUAGE.AUTO);
        translator.setDestLang(dest);
        addRecent(new Word(word, translator.translate(word)));
        return translator.translate(word);
    }

    /*
     * Tim kiem nhieu tu.
     */
    public static String dictionarySearcher(String word) {
        String result = "";
        for (Word i : wordList) {
            if (i.getWord_target().length() < word.length()) {
                continue;
            }
            if (i.getWord_target().substring(0, word.length()).equalsIgnoreCase(word)) {
                result = result + i.getWord_target() + "\n";
                addRecent(i);
            }
        }
        return result;
    }

    /*
     * Tim kiem chinh xac 1 tu trong danh sach.
     */
    public static String dictionaryLookup(String word) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(word)) {
                addRecent(i);
                return i.getWord_explain();
            }
        }
        return "Sử dụng google translate API";
    }
    public static String dictionaryBinaryLookup(String wordToSearch) {
        int left = 0;
        int right = wordList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Word word = wordList.get(mid);
            if (word.getWord_target().equalsIgnoreCase(wordToSearch)) {
                addRecent(word);
                return word.getWord_explain();
            }
            if (word.getWord_target().compareTo(wordToSearch) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return "Sử dụng google translate API";
    }

    /*
     * Them 1 tu vao trong danh sach.
     */
    public static boolean addWord(String wordTarget, String wordExplain) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(wordTarget)
                    && i.getWord_explain().equalsIgnoreCase(wordExplain)) {
                return false;
            }
        }
        Word word = new Word(wordTarget, wordExplain);
        wordList.add(word);
        addRecent(word);
        return true;
    }

    /*
     * Thay doi nghia cua tu trong danh sach.
     */
    public static boolean modifyWord(String wordTarget, String wordExplain) {
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(wordTarget)) {
                addRecent(i);
                i.setWord_explain(wordExplain);
                addRecent(i);
                return true;
            }
        }
        return false;
    }

    /*
     * Xoa 1 tu khoi danh sach.
     */
    public static boolean deleteWord(String word) {
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord_target().equalsIgnoreCase(word)) {
                addRecent(wordList.get(i));
                wordList.remove(i);
                return true;
            }
        }
        return false;
    }

}