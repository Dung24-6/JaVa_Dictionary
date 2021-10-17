package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

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
     * Tim kiem chinh xac 1 tu trong danh sach.
     */
    public void dictionaryLookup(String word) {
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(word)) {
                System.out.println(i.getWord_target() + " co nghia la : " + i.getWord_explain());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Chua dich duoc tu nay");
        }
    }

    /*
     * Them 1 tu vao trong danh sach.
     */
    public void addWord(Word word) {
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(word.getWord_target())
                && i.getWord_explain().equalsIgnoreCase(word.getWord_explain())) {
                System.out.println(i.getWord_target() + " " + i.getWord_explain() + " da co trong danh sach.");
                found = true;
                break;
            }
        }
        if (!found) {
            wordList.add(word);
        }
    }

    /*
     * Thay doi nghia cua tu trong danh sach.
     */
    public void modifyWord(Word word) {
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().equalsIgnoreCase(word.getWord_target())) {
                i.setWord_explain(word.getWord_explain());
                System.out.println(i.getWord_target() + " thay doi nghia thanh: " + i.getWord_explain());
                found = true;
                break;
            }
        }
        if (!found) {
            wordList.add(word);
        }
    }

    /*
     * Xoa 1 tu khoi danh sach.
     */
    public void deleteWord(Word word) {
        boolean found = false;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord_target().equalsIgnoreCase(word.getWord_target())) {
                System.out.println(wordList.get(i).getWord_target() + " dong " + i + " da bi xoa.");
                wordList.remove(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tu nay khong co trong danh sach");
        }
    }

    /*
     * Sap xep danh sach.
     */
    public void dictionarySort() {
        // TODO
    }

    /*
     * Loai bo tu trung trong danh sach.
     */
    public void dictionaryRemoveDuplicates() {
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (wordList.get(j).getWord_target().equalsIgnoreCase(wordList.get(i).getWord_target())
                    && wordList.get(j).getWord_explain().equalsIgnoreCase(wordList.get(i).getWord_explain())) {
                    deleteWord(wordList.get(j));
                }
            }
        }
    }
}
