import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DictionaryManagement extends Dictionary {

    private static final Scanner scanner = new Scanner(System.in);

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

        scanner.close();
    }

    public void insertFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\resources\\dictionaries.txt"));
        scanner.useDelimiter("s*\ts*");
        while (scanner.hasNext()) {
            //String wordscan = scanner.nextLine();
            Word word = new Word();
            word.setWord_target(scanner.next());
            word.setWord_explain((scanner.nextLine()).trim());
            wordList.add(word);

        }
        scanner.close();
    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu muon tra nao ");
            Scanner scanner = new Scanner(System.in);
        String s;
        s = scanner.nextLine();
        for (Word i:wordList ) {
                if (i.getWord_target().equals(s)) {
                    System.out.println(s + "  nghia la : " + i.getWord_explain());
                }
                else System.out.println("Chua dich duoc tu nay");
            }
    }

}
