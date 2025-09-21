import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

    // two rules -increasing or all decreasing from 1 to 3

    public static enum typeCompart {
        start,
        GraterThan,
        SmallerThan,
        similar
    }

    public static boolean TryDeleteOneLevel(ArrayList<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> repostTest = new ArrayList<>(report);
            repostTest.remove(i);
            if (!CheckIncreasingOrDecreasing(repostTest)) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean CheckIncreasingOrDecreasing(ArrayList<Integer> report) {

        typeCompart type = typeCompart.start;
        for (int i = 0; i < (report.size() - 1); i++) {
            if (i == 0) {

                type = typeCompart.start;
            }
            if (report.get(i) > report.get(i + 1)) {
                if (type != typeCompart.GraterThan && type != typeCompart.start) {
                    return false;
                } else if (!checkGapsBetweenLevels(report.get(i), report.get(i + 1))) {
                    return false;
                }
                type = typeCompart.GraterThan;

            } else if (report.get(i) < report.get(i + 1)) {
                if (type != typeCompart.SmallerThan && type != typeCompart.start) {
                    return false;
                } else if (!checkGapsBetweenLevels(report.get(i), report.get(i + 1))) {
                    return false;
                }
                type = typeCompart.SmallerThan;

            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkGapsBetweenLevels(int curr, int next) {

        if (Math.abs(curr - next) > 3) {
            return false;
        }
        return true;
    }

    public static int GetNumberOfSafeReport(ArrayList<Integer[]> reportList) {
        int numberOfSafeReport = 0;
        for (Integer[] report : reportList) {
            if (CheckIncreasingOrDecreasing(new ArrayList<>(Arrays.asList(report)))) {
                System.out.println("reposrt : " + Arrays.toString(report) + " safe");
                numberOfSafeReport++;
            } else if (TryDeleteOneLevel(new ArrayList<>(Arrays.asList(report)))) {
                System.out.println("reposrt : " + Arrays.toString(report) + " safe");
                numberOfSafeReport++;
            } else {
                System.out.println("reposrt : " + Arrays.toString(report) + " not safe");
            }
        }
        return numberOfSafeReport;
    }

    public static int GetSafeReports(String filePath) {
        ArrayList<Integer[]> reportList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String report;
            while ((report = reader.readLine()) != null) {
                String[] levelList = report.split(" ");
                ArrayList<Integer> levelListIntger = new ArrayList<>();
                for (String item : levelList) {
                    levelListIntger.add(Integer.parseInt(item));
                }
                Integer[] list = new Integer[levelListIntger.size()];
                list = levelListIntger.toArray(list);
                reportList.add(list);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        int safeReport = GetNumberOfSafeReport(reportList);

        return safeReport;
    }

    public static void main(String[] args) {

        System.out.println("number of safe report : " + GetSafeReports("input.txt"));

    }
}