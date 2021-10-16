package main;

import java.io.IOException;
import java.util.Scanner;

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
        System.out.println("No\t|English\t\t|Vietnamese");
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(i + 1 + "\t" + wordList.get(i).toString());
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
}
