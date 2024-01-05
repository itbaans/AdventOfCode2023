import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventDay1 {


    public static void main(String[] args) {
        
        System.out.println(advnt2());

    }

    public static int advnt1() {

        String filePath = "src\\day1.txt";
        int total = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                int first = 0;
                int scnd = 0;
                boolean ch = true;

                for(int i = 0; i < line.length(); i++) {

                    if((int)line.charAt(i) >= 48 && (int)line.charAt(i) <= 57) {

                        if(ch) first = Integer.parseInt(line.charAt(i)+"");
                        scnd = Integer.parseInt(line.charAt(i)+"");
                        ch = false;

                    }

                }

                //System.out.println(first * 10 + scnd);
                total += first * 10 + scnd;
                

            }

            //System.out.println(total);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



        return total;


    }

    public static int advnt2() {


        String filePath = "src\\advnt.txt"; // Replace with the actual file path
        int total = 0;
        String[] numbers = {"zero" ,"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                int first = 0;
                int scnd = 0;
                boolean ch = true;
                int count = 0;

                while(count != line.length()) {

                    for(int i = count + 1; i <= line.length(); i++) {

                        for(int ind = 0; ind < numbers.length; ind++) {

                            //kfxone67bzb2
                            if(line.substring(count, i).length() == 1 && (int)line.substring(count, i).charAt(0) >= 48 && (int)line.substring(count, i).charAt(0) <= 57) {
                                if(ch) first = Integer.parseInt(line.substring(count, i).charAt(0)+"");
                                scnd = Integer.parseInt(line.substring(count, i).charAt(0)+"");
                                ch = false;
                            }

                            if(numbers[ind].equals(line.substring(count, i))) {

                                if(ch) first = ind;
                                scnd = ind;
                                ch = false;

                            }

                        }

                    }
                    count++;
                }

                System.out.println(first * 10 + scnd);
                total += first * 10 + scnd;
                

            }

            //System.out.println(total);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



        return total;




    }
}
