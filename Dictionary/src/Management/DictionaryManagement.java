package Management;

import java.io.*;
import java.util.*;

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
    public void insertFromFile(String pathFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(pathFile));

        while (scan.hasNext()) {
            String target = scan.nextLine();
            String explain = scan.nextLine();

            Dictionary.wordListHash.put(target, explain);
        }
    }

    /*
     * Xuat tu ra file.
     */
    public void dictionaryExportToFile() throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream("data_Now.txt");

        try (BufferedOutputStream bout = new BufferedOutputStream(fout)) {
            Set set = Dictionary.wordListHash.entrySet();
            Iterator iterator = set.iterator();

            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                String line = mentry.getKey() + "\n" + mentry.getValue();
                bout.write(line.getBytes());
                bout.write(System.lineSeparator().getBytes());
            }
        }
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
    public boolean dictionaryLookUp(String target) {

        return (Dictionary.wordListHash.get(target) != null);
    }

    /*
     * Them 1 tu vao trong danh sach.
     */
    public void addWord(String target, String explain) {

        Dictionary.wordListHash.put(target, explain);
    }

    /*
     * Thay doi nghia cua tu trong danh sach.
     */
    public void modifyWord(String target, String explain) {

        Dictionary.wordListHash.replace(target, explain);
    }

    /*
     * Xoa 1 tu khoi danh sach.
     */
    public void removeWord(String target) {

        Dictionary.wordListHash.remove(target);
    }

}
