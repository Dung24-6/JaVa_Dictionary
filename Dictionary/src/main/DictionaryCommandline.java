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
}
