package Management;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import GoogleTranslator.GoogleTranslator.LANGUAGE;

public class DictionaryCommandline extends DictionaryManagement {

    public static final Scanner scanner = new Scanner(System.in);

    public static String input = "src\\resources\\dictionaries.txt";
    public static String output = "src\\resources\\output.txt";
    public static String recent = "src\\resources\\recentWord.txt";
    public static String favorite = "src\\resources\\favoriteWord.txt";

    public DictionaryCommandline() {
    }

    /*
     * Xuat tat ca cac tu trong danh sach ra man hinh.
     */
    public void showAllWords(List<Word> list) {
        System.out.printf("%-5s|%-20s\t|%s", "No", "English", "Vietnamese");
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-5d%s", i + 1, list.get(i).toString());
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Basic.
     */
    public void dictionaryBasic() throws IOException {
        // insertFromCommandline();
        insertFromFile(input, wordList);
        showAllWords(wordList);
        insertFromFile(recent, recentWordList);
        showAllWords(recentWordList);


        dictionaryLookup();
        dictionarySearcher();

        dictionaryAddWord();
        dictionaryModifyWord();
        dictionaryDeleteWord();
    }

    /*
     * Advanced.
     */
    public void dictionaryAdvaned() throws IOException {
        sort(wordList);
        removeDuplicates(wordList);
        dictionaryExportToFile(output, wordList);

        removeDuplicates(recentWordList);
        dictionaryExportToFile(recent, recentWordList);
    }

    /*
     * Import.
     */
    public void dictionaryImport() throws IOException {
        insertFromFile(input, wordList);
        insertFromFile(recent, recentWordList);
    }

    /*
     * Export.
     */
    public void dictionaryExport() throws IOException {
        sort(wordList);
        removeDuplicates(wordList);
        dictionaryExportToFile(output, wordList);

        removeDuplicates(recentWordList);
        dictionaryExportToFile(recent, recentWordList);
    }

    /*
     * Tim kiem nhieu tu bat dau bang tu duoc nhap vao tu dong lenh.
     */
    public void dictionarySearcher() {
        System.out.println("Nh???p t??? b???n mu???n t??m ki???m: ");
        String word = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().length() < word.length()) {
                continue;
            }
            if (i.getWord_target().substring(0, word.length()).equalsIgnoreCase(word)) {
                System.out.println(i.getWord_target() + " ngh??a l??: " + i.getWord_explain());
                addRecent(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Kh??ng t??m th???y t??? n??y");
        }
    }

    /*
     * Tim kiem chinh xac 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryLookup() {
        System.out.println("Nh???p t??? b???n mu???n t??m ki???m: ");
        String wordTarget = scanner.nextLine();
        String wordExplain = dictionaryLookup(wordTarget);
        if (wordExplain.equalsIgnoreCase("S??? d???ng google translate API")) {
            System.out.println("Kh??ng t??m th???y t??? n??y");
        } else {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println(word);
        }
    }

    /*
     * Them 1 tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryAddWord() {
        System.out.println("Nh???p t??? b???n mu???n th??m v??o: ");
        String wordTarget = scanner.nextLine();
        System.out.println("Nh???p ngh??a c???a t??? ????: ");
        String wordExplain = scanner.nextLine();
        if (addWord(wordTarget, wordExplain)) {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println("???? th??m t???: " + word);
        }
    }

    /*
     * Thay doi nghia cua tu duoc nhap vao tu dong lenh.
     */
    public void dictionaryModifyWord() {
        System.out.println("Nh???p t??? b???n mu???n s???a: ");
        String wordTarget = scanner.nextLine();
        System.out.println("Nh???p ngh??a c???a t??? ????: ");
        String wordExplain = scanner.nextLine();
        if (modifyWord(wordTarget, wordExplain)) {
            Word word = new Word(wordTarget, wordExplain);
            System.out.println("???? s???a t???: " + word);
        }
    }

    /*
     * Xoa 1 tu duoc nhap vao tu dong lenh khoi danh sach.
     */
    public void dictionaryDeleteWord() {
        System.out.println("Nh???p t??? b???n mu???n x??a: ");
        String word = scanner.nextLine();
        if (deleteWord(word)) {
            System.out.println("???? x??a t??? " + word);
        }
    }

    /*
     * Dich tu nhap tu dong lenh bang google translator.
     */
    public void googleTranslator() throws IOException, ParseException {
        while (true) {
            System.out.println("Nh???p t??? b???n mu???n d???ch: ");
            String word = scanner.nextLine();
            System.out.println("B???n mu???n d???ch sang ti???ng n??o? Ch???n 1 s???: ");
            System.out.println("1 - Ti???ng Vi???t");
            System.out.println("2 - Ti???ng Anh");
            System.out.println("3 - Ti???ng Ph??p");
            System.out.println("4 - Ti???ng ?????c");
            System.out.println("5 - Ti???ng Nga");
            System.out.println("6 - Ti???ng H??n");
            System.out.println("7 - Ti???ng Nh???t");
            System.out.println("8 - Ti???ng Trung");
            String number = scanner.nextLine();
            LANGUAGE dest = null;
            switch (number) {
                case "1":
                    dest = LANGUAGE.VIETNAMESE;
                    System.out.println("D???ch sang ti???ng Vi???t l??: ");
                    break;
                case "2":
                    dest = LANGUAGE.ENGLISH;
                    System.out.println("D???ch sang ti???ng Anh l??: ");
                    break;
                case "3":
                    dest = LANGUAGE.FRENCH;
                    System.out.println("D???ch sang ti???ng Ph??p l??: ");
                    break;
                case "4":
                    dest = LANGUAGE.GERMAN;
                    System.out.println("D???ch sang ti???ng ?????c l??: ");
                    break;
                case "5":
                    dest = LANGUAGE.RUSSIAN;
                    System.out.println("D???ch sang ti???ng Nga l??: ");
                    break;
                case "6":
                    dest = LANGUAGE.KOREAN;
                    System.out.println("D???ch sang ti???ng H??n l??: ");
                    break;
                case "7":
                    dest = LANGUAGE.JAPANESE;
                    System.out.println("D???ch sang ti???ng Nh???t l??: ");
                    break;
                case "8":
                    dest = LANGUAGE.CHINESE;
                    System.out.println("D???ch sang ti???ng Trung l??: ");
                    break;
                default:
                    System.out.println("S??? b???n ch???n kh??ng ????ng.");
                    break;
            }
            if (dest != null) {
                System.out.println(googleTranslator(word, LANGUAGE.AUTO, dest));
            }
            System.out.println("B???n c?? mu???n d???ch ti???p kh??ng? Y or N");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("n") || exit.equalsIgnoreCase("no")) {
                break;
            }
        }
    }
}