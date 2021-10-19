package Management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

public class Dictionary {
    protected static List<Word> wordList = new ArrayList<>();

    protected static List<Word> recentWordList = new ArrayList<>();

    protected static List<Word> favoriteWordList = new ArrayList<>();

    public static HashMap<String, String> wordListHash = new HashMap<>();

    public static HashMap<String, String> recentWordListHash = new HashMap<>();

    public static HashMap<String, String> favoriteWorldList = new HashMap<>();

    public Dictionary() {
    }

    public void addRecent(Word word) {

        Dictionary.recentWordListHash.put(word.getWord_target(), word.getWord_explain());
        removeDuplicates(recentWordList);
    }

    /*
     * Sap xep danh sach.
     */
    public void sort(List<Word> list) {
        Collections.sort(list);
    }
    /*public void sortHash(HashMap<String,String> hashMap) {
        Collections.sort(hashMap);
    }*/

    /*
     * Loai bo tu trung trong danh sach.
     */
    public void removeDuplicates(List<Word> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getWord_target().equalsIgnoreCase(list.get(i).getWord_target())
                        && list.get(j).getWord_explain().equalsIgnoreCase(list.get(i).getWord_explain())) {
                    list.remove(j);
                }
            }
        }
    }

}
