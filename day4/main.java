import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static String searchWord = "XMAS";
    public static String searchWord2 = "MAS";

    public static int GetX_MASWord(ArrayList<ArrayList<Character>> data) {
        int numberFind = 0;
        for (int i = 1; i < data.size() - 1; i++) {
            for (int k = 1; k < data.get(0).size() - 1; k++) {
                StringBuilder word1 = new StringBuilder();
                StringBuilder word2 = new StringBuilder();

                word1.append(data.get(i - 1).get(k - 1));
                word1.append(data.get(i).get(k));
                word1.append(data.get(i + 1).get(k + 1));

                word2.append(data.get(i + 1).get(k - 1));
                word2.append(data.get(i).get(k));
                word2.append(data.get(i - 1).get(k + 1));

                String forward1 = word1.toString();
                String backwar1 = new StringBuilder(forward1).reverse().toString();

                String forward2 = word2.toString();
                String backward2 = new StringBuilder(forward2).reverse().toString();

                if (((forward1.equals(searchWord2) || (backwar1.equals(searchWord2))) && ((forward2.equals(searchWord2))
                        || (backward2.equals(searchWord2))))) {
                    System.out.println(word1.toString());
                    System.out.println(word2.toString());
                    numberFind++;
                }
            }
        }
        return numberFind;
    }

    public static ArrayList<ArrayList<Character>> GetListDataFromFile(String filePath) {
        ArrayList<ArrayList<Character>> listData = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Character> characterList = new ArrayList<>();
                for (Character c : line.toCharArray()) {
                    if (c == ' ') {
                        continue;
                    }
                    characterList.add(c);
                }

                listData.add(characterList);
            }
            reader.close();

        } catch (IOException error) {
            System.err.println(error);
        }
        return listData;
    }

    public static int CheskDataHorizontal(ArrayList<ArrayList<Character>> data) {
        int numberFind = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int k = 0; k <= data.get(i).size() - searchWord.length(); k++) {
                StringBuilder word = new StringBuilder();
                for (int n = 0; n < searchWord.length(); n++) {
                    word.append(data.get(i).get(k + n));
                }
                String forward = word.toString();
                String backward = new StringBuilder(forward).reverse().toString();
                if (forward.equals(searchWord)
                        || backward.equals(searchWord)) {
                    numberFind++;
                }

            }
        }
        return numberFind;
    }

    public static int CheskDataVertical(ArrayList<ArrayList<Character>> data) {
        int numberFind = 0;
        for (int i = 0; i <= data.size() - searchWord.length(); i++) {
            for (int k = 0; k < data.get(0).size(); k++) {
                StringBuilder word = new StringBuilder();
                for (int n = 0; n < searchWord.length(); n++) {
                    word.append(data.get(i + n).get(k));
                }
                String forward = word.toString();
                String backward = new StringBuilder(forward).reverse().toString();

                if (forward.equals(searchWord)
                        || backward.equals(searchWord)) {
                    numberFind++;
                }
            }
        }
        return numberFind;
    }

    public static int CheskDataDiagonal(ArrayList<ArrayList<Character>> data) {
        int numberFind = 0;
        for (int i = 0; i <= data.size() - searchWord.length(); i++) {
            for (int j = 0; j <= data.get(i).size() - searchWord.length(); j++) {
                StringBuilder word = new StringBuilder();
                for (int k = 0; k < searchWord.length(); k++) {
                    word.append(data.get(i + k).get(j + k));
                }
                String forward = word.toString();
                String backward = new StringBuilder(forward).reverse().toString();
                if (forward.equals(searchWord)
                        || backward.equals(searchWord)) {
                    numberFind++;
                }

            }
            for (int p = searchWord.length() - 1; p < data.get(i).size(); p++) {
                StringBuilder word = new StringBuilder();
                for (int k = 0; k < searchWord.length(); k++) {
                    word.append(data.get(i + k).get(p - k));
                }
                String forward = word.toString();
                String backward = new StringBuilder(forward).reverse().toString();
                if (forward.equals(searchWord)
                        || backward.equals(searchWord)) {
                    numberFind++;
                }
            }
        }
        return numberFind;
    }

    public static int GetNumberOfWordMatching() {
        ArrayList<ArrayList<Character>> data = GetListDataFromFile("input.txt");
        // return CheskDataHorizontal(data) + CheskDataVertical(data) +
        // CheskDataDiagonal(data);
        return GetX_MASWord(data);
    }

    public static void main(String[] arge) {
        System.out.println(GetNumberOfWordMatching());
    }
}