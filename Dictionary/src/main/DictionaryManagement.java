import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    private static final Scanner scanner = new Scanner(System.in);

    public DictionaryManagement() {
    }

    public void insertFromCommandline() {
        System.out.print("Nhap so tu nhap vao tu dien: ");
        int wordNumber = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < wordNumber; i++) {
            System.out.print("Nhap tu moi ");
            String eng = scanner.nextLine();
            System.out.print("Nhap giai nghia ");
            String viet = scanner.nextLine();
            Word word = new Word(eng, viet);
            wordList.add(word);
        }
        System.out.println();

        scanner.close();
    }
}
