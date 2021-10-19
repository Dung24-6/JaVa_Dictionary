package Management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
    public static List<Word> wordList = new ArrayList<>();

    public static List<Word> recentWordList = new ArrayList<>();

    public static List<Word> favoriteWordList = new ArrayList<>();

    public Dictionary() {
    }

    public static void addRecent(Word word) {
        recentWordList.add(0, word);
        removeDuplicates(recentWordList);
    }

    /*
     * Sap xep danh sach.
     */
    public static void sort(List<Word> list) {
        Collections.sort(list);
    }

    /*
     * Loai bo tu trung trong danh sach.
     */
    public static void removeDuplicates(List<Word> list) {
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
