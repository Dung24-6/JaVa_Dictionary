package main;
import java.io.IOException;

public class DictionaryCommandline extends Dictionary {

    public DictionaryCommandline() {
    }

    public void showAllWords() {
        System.out.println("No\t|English\t\t|Vietnamese");
        for (int i = 0; i < wordList.size(); i++) {
            System.out.print(i + "\t|" + wordList.get(i).getWord_target());
            System.out.println("\t\t\t|" + wordList.get(i).getWord_explain());
        }
    }

    public void dictionaryBasic() {
        DictionaryManagement dictionary = new DictionaryManagement();
        dictionary.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvaned() throws IOException {
        DictionaryManagement dictionary = new DictionaryManagement();
        dictionary.insertFromFile();
        showAllWords();
        dictionary.dictionaryLookup();
        dictionarySearcher();
        dictionary.dictionaryExportToFile();
        dictionary.changeWord();
        showAllWords();
        dictionary.deleteWord();
        showAllWords();
    }

    public void dictionarySearcher() {
        System.out.println("Nhap tu muon tim kiem nao ");
        String s = scanner.nextLine();
        boolean found = false;
        for (Word i : wordList) {
            if (i.getWord_target().contains(s)) {
                System.out.println(i.getWord_target() + " nghia la: " + i.getWord_explain());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tu nay");
        }
    }
}