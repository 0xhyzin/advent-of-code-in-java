import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class main {

    // Part 2
    public static int GetSimilarity(int number, ArrayList<Integer> rightArry) {
        int totalSimilarity = 0;
        for (int item : rightArry) {
            if (item == number) {
                totalSimilarity++;
            }
        }
        return totalSimilarity;
    }

    public static int GetFinalResultBySimilarity(ArrayList<Integer> leftArry, ArrayList<Integer> rightArry) {
        int finalResult = 0;
        for (int item : leftArry) {
            finalResult += item * GetSimilarity(item, rightArry);
        }
        return finalResult;
    }

    // Part 1
    public static ArrayList<Integer> GetDistanceList(ArrayList<Integer> leftArry, ArrayList<Integer> rightArry) {
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < leftArry.size(); i++) {
            int distance = (leftArry.get(i) > rightArry.get(i)) ? (leftArry.get(i) - rightArry.get(i))
                    : -1 * (leftArry.get(i) - rightArry.get(i));

            finalList.add(distance);
        }
        return finalList;
    }

    public static int GetDistancSum(ArrayList<Integer> distanceArray) {
        int finalResult = 0;
        for (int item : distanceArray) {
            finalResult += item;
        }
        return finalResult;
    }

    public static Integer GetFinalResult(String fileBath) {
        ArrayList<Integer> leftArray = new ArrayList<>();
        ArrayList<Integer> rightArray = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileBath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] spaceList = line.split("   ");
                leftArray.add(Integer.parseInt(spaceList[0]));
                rightArray.add(Integer.parseInt(spaceList[1]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 2
        int finalResult = GetFinalResultBySimilarity(leftArray, rightArray);


        // Part 1
        // Collections.sort(leftArray);
        // Collections.sort(rightArray);

        // ArrayList<Integer> finalArray = GetDistanceList(leftArray, rightArray);

        // int finalResult = GetDistancSum(finalArray);

        return finalResult;
    }

    public static void main(String[] args) {
        int finalResult = GetFinalResult("input.txt");
        // System.out.println("Part 1 :" + finalResult);
        System.out.println("Part 2 :" + finalResult);
    }
}