import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static int mul(int num1, int num2) {
        return num1 * num2;
    }

    public static String FilterDataFromFile(String data) {

        String fileData = data;

        String result = new String();

        String targetDont = "don't()";
        String targetDo = "do()";

        int start = 0;
        int end = 0;

        while(start!=-1){
            start=(end==0)?0:fileData.indexOf(targetDo);
            end=fileData.indexOf(targetDont,start);

            if(start==-1 || end==-1){
                break;
            }

            String RemoveDate=fileData.substring(start, end);;

            result+=fileData.substring(start, end);

            System.out.println(result + "\n\n");
            fileData=fileData.replace(RemoveDate, "");
        }
        
        return result;

    }

    public static int GetMulResult(String filePath) {
        int result = 0;
        String data = new String();
        ArrayList<Integer[]> listMul = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                data = data + line;
            }
            reader.close();
        } catch (IOException error) {
            System.err.println(error);
        }
        data=FilterDataFromFile(data);
        Pattern p = Pattern.compile("mul\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)");
        Matcher m = p.matcher(data);
        while (m.find()) {
            int FirstIndexToFirstNumber = m.group().indexOf("(");
            int finalIndexToFirstNumber = m.group().indexOf(",");

            int firstNumber = Integer
                    .parseInt(m.group().substring(FirstIndexToFirstNumber + 1, finalIndexToFirstNumber));

            int firstIndexToSecoundNumber = m.group().indexOf(",");
            int finalIndexToSecoundNumber = m.group().indexOf(")");

            int secoundNumber = Integer
                    .parseInt(m.group().substring(firstIndexToSecoundNumber + 1, finalIndexToSecoundNumber));

            listMul.add(new Integer[] { firstNumber, secoundNumber });
        }

        return GetMul(listMul);
    }

    public static int GetMul(ArrayList<Integer[]> mulList) {
        int totalMul = 0;
        for (Integer[] numbers : mulList) {
            totalMul += mul(numbers[0], numbers[1]);
        }
        return totalMul;
    }

    public static void main(String[] args) {
        System.out.println(GetMulResult("input.txt"));
    }
}