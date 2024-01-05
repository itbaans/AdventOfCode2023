import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) {

        advntPart2();

    }

    public static void advntPart1() {

        String filePath = "src\\day4.txt";

        //part1
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int pointSum = 0;
            while ((line = bufferedReader.readLine()) != null) {
                
                String[] split = line.split(":")[1].split("\\|");
                String[] splitWinnigNums = split[0].split(" ");
                String[] splitNumsGot = split[1].split(" ");

                int pow = 0;
                int linePoins = 0;
                for(String s : splitNumsGot) {
                    if(!s.equals("")) {
                        for(String sw : splitWinnigNums) {
                            if(!sw.equals("") && Integer.parseInt(s) == Integer.parseInt(sw)) {
                                System.out.print(sw+" ");
                                linePoins = (int)(Math.pow(2, pow));
                                pow++;
                            }
                        }
                    }
                }
                System.out.print("Points: "+linePoins);
                pointSum += linePoins;
                System.out.println();

            }

            System.out.println("TOTAL POINTS: "+pointSum);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void advntPart2() {

        String filePath = "src\\day4.txt";

        int[][] cardData = new int[187][2];

        //part2
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int ind = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                
                String[] split = line.split(":")[1].split("\\|");
                String[] splitWinnigNums = split[0].split(" ");
                String[] splitNumsGot = split[1].split(" ");

                int matches = 0;
                for(String s : splitNumsGot) {
                    if(!s.equals("")) {
                        for(String sw : splitWinnigNums) {
                            if(!sw.equals("") && Integer.parseInt(s) == Integer.parseInt(sw)) {
                                //System.out.print(sw+" ");
                                matches++;
                            }
                        }
                    }
                }
                cardData[ind][0] = matches;
                cardData[ind][1] = 1;
                ind++;

            }
            
            int totalInstances = 0;
            for(int i = 0; i < cardData.length; i++) {
                totalInstances += cardData[i][1];
                for(int inst = 0; inst < cardData[i][1]; inst++) {

                    if(cardData[i][0] != 0) {
                        for(int t = i+1; t <= cardData[i][0] + i; t++) {
                            cardData[t][1]++;
                        }
                    }

                }


            }
            System.out.println("TOTAL INSTANCES: "+totalInstances);
            bufferedReader.close();
            

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}
