package main;
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
    }

    public void insertFromCommandline() {
        System.out.print("Nhap so tu nhap vao tu dien: ");
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

    public void insertFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\resources\\dictionaries.txt"));
        scanner.useDelimiter("s*\ts*");
        while (scanner.hasNext()) {
            Word word = new Word();
            word.setWord_target(scanner.next());
            word.setWord_explain((scanner.nextLine()).trim());
            wordList.add(word);
        }
        scanner.close();
    }

    public void dictionaryExportToFile() throws IOException {
        File file = new File("src\\resources\\output.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (Word i : wordList) {
            outputStreamWriter.write(i.getWord_target());
            outputStreamWriter.write("\t");
            outputStreamWriter.write(i.getWord_explain());
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.flush();
    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu muon tra nao ");
        String s = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(s)) {
                System.out.println(s + " nghia la : " + i.getWord_explain());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Chua dich duoc tu nay");
        }
    }

    public void changeWord() {
        System.out.println("Nhap tu muon thay doi nao ");
        String target = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(target)) {
                System.out.println("Nhap nghia cua tu do nao ");
                String explain = scanner.nextLine();
                i.setWord_explain(explain);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tu nay");
        }
    }

    public void deleteWord() {
        System.out.println("Nhap tu muon xoa nao ");
        String target = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(target)) {
                wordList.remove(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tu nay");
        }
    }
}
