package main;

import java.io.IOException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import main.GoogleTranslator.LANGUAGE;

public class DictionaryCommandline extends DictionaryManagement {

    public static final Scanner scanner = new Scanner(System.in);

    public static String input = "src\\resources\\dictionaries.txt";
    public static String output = "src\\resources\\output.txt";

    public DictionaryCommandline() {
    }

    /*
     * Xuat tat ca cac tu trong danh sach ra man hinh.
     */
    public void showAllWords() {
        System.out.printf("%-5s|%-20s\t|%s", "No", "English", "Vietnamese");
        System.out.println();
        for (int i = 0; i < wordList.size(); i++) {
            System.out.printf("%-5d%s", i + 1, wordList.get(i).toString());
            System.out.println();
        }
    }

    /*
     * Basic.
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    /*
     * Advanced.
     */
    public void dictionaryAdvaned() throws IOException {
        super.insertFromFile(input);
        // super.dictionaryRemoveDuplicates();
        showAllWords();
        super.dictionaryExportToFile(output);

        dictionaryLookup();
        dictionarySearcher();

        dictionaryAddWord();
        dictionaryModifyWord();
        dictionaryDeleteWord();
    }

    /*
     * Tim kiem chinh xac 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryLookup() {
        System.out.println("Nhap tu ban muon tim kiem");
        String word = scanner.nextLine();
        super.dictionaryLookup(word);
    }

    /*
     * Tim kiem nhieu tu bat dau bang tu duoc nhap vao tu dong lenh.
     */
    public void dictionarySearcher() {
        System.out.println("Nhap tu ban muon tim kiem");
        String word = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().length() < word.length()) {
                continue;
            }
            if (i.getWord_target().substring(0, word.length()).equalsIgnoreCase(word)) {
                System.out.println(i.getWord_target() + " nghia la: " + i.getWord_explain());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tu nay");
        }
    }

    /*
     * Them 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryAddWord() {
        System.out.println("Nhap tu ban muon them vao");
        Word word = new Word();
        word.setWord_target(scanner.nextLine());
        System.out.println("Nhap nghia cua tu do");
        word.setWord_explain(scanner.nextLine());
        super.addWord(word);
    }

    /*
     * Thay doi nghia cua tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryModifyWord() {
        System.out.println("Nhap tu ban muon thay doi");
        Word word = new Word();
        word.setWord_target(scanner.nextLine());
        System.out.println("Nhap nghia cua tu do");
        word.setWord_explain(scanner.nextLine());
        super.modifyWord(word);
    }

    /*
     * Xoa 1 tu duoc nhap vao tu dong lenh khoi danh sach.
     */
    public void dictionaryDeleteWord() {
        System.out.println("Nhap tu ban muon xoa");
        Word word = new Word();
        word.setWord_target(scanner.nextLine());
        super.deleteWord(word);
    }

    /*
     * Dich tu nhap tu dong lenh bang google translator.
     */
    public void googleTranslator() throws IOException, ParseException {
        while (true) {
            System.out.println("Nhap 'EXIT' neu ban muon thoat.");
            System.out.println("Nhap tu ban muon dich");
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Ban muon dich sang tieng nao? hay chon 1 so: ");
            System.out.println("1 - Tieng Viet");
            System.out.println("2 - Tieng Anh");
            System.out.println("3 - Tieng Phap");
            System.out.println("4 - Tieng Duc");
            System.out.println("5 - Tieng Nga");
            System.out.println("6 - Tieng Han");
            System.out.println("7 - Tieng Nhat");
            System.out.println("8 - Tieng Trung");
            String number = scanner.nextLine();
            if (number.equalsIgnoreCase("exit")) {
                break;
            }
            LANGUAGE dest = null;
            switch (number) {
                case "1":
                    dest = LANGUAGE.VIETNAMESE;
                    System.out.println("Dich sang tieng Viet la: ");
                    break;
                case "2":
                    dest = LANGUAGE.ENGLISH;
                    System.out.println("Dich sang tieng Anh la: ");
                    break;
                case "3":
                    dest = LANGUAGE.FRENCH;
                    System.out.println("Dich sang tieng Phap la: ");
                    break;
                case "4":
                    dest = LANGUAGE.GERMAN;
                    System.out.println("Dich sang tieng Duc la: ");
                    break;
                case "5":
                    dest = LANGUAGE.RUSSIAN;
                    System.out.println("Dich sang tieng Nga la: ");
                    break;
                case "6":
                    dest = LANGUAGE.KOREAN;
                    System.out.println("Dich sang tieng Han la: ");
                    break;
                case "7":
                    dest = LANGUAGE.JAPANESE;
                    System.out.println("Dich sang tieng Nhat la: ");
                    break;
                case "8":
                    dest = LANGUAGE.CHINESE;
                    System.out.println("Dich sang tieng Trung la: ");
                    break;
                default:
                    System.out.println("So ban chon khong dung.");
                    break;
            }
            if (dest != null) {
                GoogleTranslator translator = new GoogleTranslator();
                translator.setSrcLang(LANGUAGE.AUTO);
                translator.setDestLang(dest);
                String data = translator.translate(word);
                System.out.println(data);
            }
        }
    }
}
