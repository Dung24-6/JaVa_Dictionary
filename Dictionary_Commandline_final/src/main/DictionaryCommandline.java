package Management;

import java.io.IOException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import GoogleTranslator.GoogleTranslator.LANGUAGE;

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
        super.dictionarySort();
        super.dictionaryRemoveDuplicates();
        showAllWords();
    }

    /*
     * Advanced.
     */
    public void dictionaryAdvaned() throws IOException {
        super.insertFromFile(input);
        // super.dictionarySort();
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
     * Tim kiem nhieu tu bat dau bang tu duoc nhap vao tu dong lenh.
     */
    public void dictionarySearcher() {
        System.out.println("Nhập từ bạn muốn tìm kiếm: ");
        String word = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().length() < word.length()) {
                continue;
            }
            if (i.getWord_target().substring(0, word.length()).equalsIgnoreCase(word)) {
                System.out.println(i.getWord_target() + " nghĩa là: " + i.getWord_explain());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy từ này");
        }
    }

    /*
     * Tim kiem chinh xac 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryLookup() {
        System.out.println("Nhập từ bạn muốn tìm kiếm: ");
        String wordTarget = scanner.nextLine();
        String wordExplain = super.dictionaryLookup(wordTarget);
        if (wordExplain.equalsIgnoreCase("Sử dụng google translate API")) {
            System.out.println("Không tìm thấy từ này");
        } else {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println(word);
        }
    }

    /*
     * Them 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryAddWord() {
        System.out.println("Nhập từ bạn muốn thêm vào: ");
        String wordTarget = scanner.nextLine();
        System.out.println("Nhập nghĩa của từ đó: ");
        String wordExplain = scanner.nextLine();
        if (super.addWord(wordTarget, wordExplain)) {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println("Đã thêm từ: " + word);
        }
    }

    /*
     * Thay doi nghia cua tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryModifyWord() {
        System.out.println("Nhập từ bạn muốn sửa: ");
        String wordTarget = scanner.nextLine();
        System.out.println("Nhập nghĩa của từ đó: ");
        String wordExplain = scanner.nextLine();
        if (super.modifyWord(wordTarget, wordExplain)) {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println("Đã sửa từ: " + word);
        }
    }

    /*
     * Xoa 1 tu duoc nhap vao tu dong lenh khoi danh sach.
     */
    public void dictionaryDeleteWord() {
        System.out.println("Nhập từ bạn muốn xóa: ");
        String word = scanner.nextLine();
        if (super.deleteWord(word)) {
            System.out.println("Đã xóa từ " + word);
        }
    }

    /*
     * Dich tu nhap tu dong lenh bang google translator.
     */
    public void googleTranslator() throws IOException, ParseException {
        while (true) {
            System.out.println("Nhập từ bạn muốn dịch: ");
            String word = scanner.nextLine();
            System.out.println("Bạn muốn dịch sang tiếng nào? Chọn 1 số: ");
            System.out.println("1 - Tiếng Việt");
            System.out.println("2 - Tiếng Anh");
            System.out.println("3 - Tiếng Pháp");
            System.out.println("4 - Tiếng Đức");
            System.out.println("5 - Tiếng Nga");
            System.out.println("6 - Tiếng Hàn");
            System.out.println("7 - Tiếng Nhật");
            System.out.println("8 - Tiếng Trung");
            String number = scanner.nextLine();
            LANGUAGE dest = null;
            switch (number) {
                case "1":
                    dest = LANGUAGE.VIETNAMESE;
                    System.out.println("Dịch sang tiếng Việt là: ");
                    break;
                case "2":
                    dest = LANGUAGE.ENGLISH;
                    System.out.println("Dịch sang tiếng Anh là: ");
                    break;
                case "3":
                    dest = LANGUAGE.FRENCH;
                    System.out.println("Dịch sang tiếng Pháp là: ");
                    break;
                case "4":
                    dest = LANGUAGE.GERMAN;
                    System.out.println("Dịch sang tiếng Đức là: ");
                    break;
                case "5":
                    dest = LANGUAGE.RUSSIAN;
                    System.out.println("Dịch sang tiếng Nga là: ");
                    break;
                case "6":
                    dest = LANGUAGE.KOREAN;
                    System.out.println("Dịch sang tiếng Hàn là: ");
                    break;
                case "7":
                    dest = LANGUAGE.JAPANESE;
                    System.out.println("Dịch sang tiếng Nhật là: ");
                    break;
                case "8":
                    dest = LANGUAGE.CHINESE;
                    System.out.println("Dịch sang tiếng Trung là: ");
                    break;
                default:
                    System.out.println("Số bạn chọn không đúng.");
                    break;
            }
            if (dest != null) {
                System.out.println(googleTranslator(word, dest));
            }
            System.out.println("Bạn có muốn dịch tiếp không? Y or N");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("n") || exit.equalsIgnoreCase("no")) {
                break;
            }
        }
    }
}
