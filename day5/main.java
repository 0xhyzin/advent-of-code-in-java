
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class main {

    public static int GetDataFromFile(String filePath) {
        ArrayList<String> roles = new ArrayList<>();
        String lines = new String();
        String data = new String();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            data = reader.lines().collect(Collectors.joining("\n"));
            roles = new ArrayList<>(Arrays.asList(data.split("#")[0].split("\n")));
            lines = data.split("#")[1].replaceFirst("\n", lines);
            reader.close();

        } catch (Exception error) {
            System.err.println(error);
        }
        HashMap<Integer, ArrayList<Integer>> listRoles = new HashMap<>();
        for (String role : roles) {
            String[] line = role.split("\\|");
            Integer num1 = Integer.parseInt(line[0]);
            if (!listRoles.containsKey(num1)) {

                ArrayList<Integer> listNumber = new ArrayList<>();
                Pattern pattern = Pattern.compile(num1 + "\\|(.*)");
                Matcher matcher = pattern.matcher(data.split("#")[0]);
                while (matcher.find()) {
                    int roleNumber = Integer.parseInt(matcher.group(1));
                    listNumber.add(roleNumber);
                }
                listRoles.put(num1, listNumber);
            }
        }
        return GetNumberOfTrurRoles(listRoles, lines);
    }

    public static int GetNumberOfTrurRoles(HashMap<Integer, ArrayList<Integer>> listRoles, String lines) {
        int totalCurrectpage = 0;
        boolean isRoleApply = true;
        for (String line : lines.split("\n")) {
            List<Integer> listNumber = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<Integer> listNumberRev = listNumber.reversed();
            for (int i = 0; i < listNumberRev.size(); i++) {
                ArrayList<Integer> currentListRole = listRoles.get(listNumberRev.get(i));
                for (int j = 1 + i; j < listNumberRev.size() - i; j++) {
                    if (currentListRole.contains(listNumberRev.get(j))) {
                        isRoleApply = false;
                        break;
                    } else {
                        isRoleApply = true;
                    }
                }
                if (!isRoleApply) {
                    break;
                }
            }
            if (isRoleApply) {
                totalCurrectpage += listNumber.get(listNumber.size() / 2);
            }else{
                isRoleApply=true;
            }
        }
        return totalCurrectpage;
    }

    public static void main(String[] args) {
        System.err.println(GetDataFromFile("input.txt"));
    }
}
