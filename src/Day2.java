import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) {
        

        advnt2();


    }

    public static void advnt1() {

        String filePath = "src\\day2.txt";
        
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean[] games = new boolean[100];
            for(int i = 0; i < games.length; i++) {
                games[i] = true;
            }

            //12 red cubes, 13 green cubes, and 14 blue cubes
            int red = 12;
            int green = 13;
            int blue = 14;

            while ((line = bufferedReader.readLine()) != null) {

                String[] semiColSplit = line.split(";");
                
                int game = Integer.parseInt(semiColSplit[0].split(":")[0].split(" ")[1]);
                //System.out.println(game);

                for(int i = 0; i < semiColSplit.length; i++) {

                    if(i == 0) {

                        String[] cubesSplit = semiColSplit[i].substring(7).split(",");
                        for(String s : cubesSplit) {

                            if(s.contains("red")) {
                                if(Integer.parseInt(s.split(" ")[1]) > red) games[game-1] = false;
                            }

                            if(s.contains("blue")) {
                                if(Integer.parseInt(s.split(" ")[1]) > blue) games[game-1] = false;
                            }

                            if(s.contains("green")) {
                                if(Integer.parseInt(s.split(" ")[1]) > green) games[game-1] = false;
                            }

                        }

                    }

                    else {

                        String[] cubesSplit = semiColSplit[i].split(",");

                        for(String s : cubesSplit) {

                            if(s.contains("red")) {
                                if(Integer.parseInt(s.split(" ")[1]) > red) games[game-1] = false;
                            }

                            if(s.contains("blue")) {
                                if(Integer.parseInt(s.split(" ")[1]) > blue) games[game-1] = false;
                            }

                            if(s.contains("green")) {
                                if(Integer.parseInt(s.split(" ")[1]) > green) games[game-1] = false;
                            }

                        }
                    }


                }
       
            }

            int sum = 0;
            for(int i = 0; i < games.length; i++) {
                System.out.println("Game: "+(i+1)+" "+games[i]);
                if(games[i]) sum += (i+1);
            }

            System.out.println(sum);
            //System.out.println(total);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void advnt2() {

        String filePath = "src\\day2.txt";
        

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            
            int sumOfPowers = 0;
            //12 red cubes, 13 green cubes, and 14 blue cubes
            int red = 0;
            int green = 0;
            int blue = 0;

            while ((line = bufferedReader.readLine()) != null) {

                String[] semiColSplit = line.split(";");
                
                //System.out.println(game);
                red = 0;
                green = 0;
                blue = 0;

                for(int i = 0; i < semiColSplit.length; i++) {

                    if(i == 0) {

                        String[] cubesSplit = semiColSplit[i].substring(7).split(",");
                        for(String s : cubesSplit) {

                            if(s.contains("red")) {
                                if(Integer.parseInt(s.split(" ")[1]) > red) red = Integer.parseInt(s.split(" ")[1]);
                            }

                            if(s.contains("blue")) {
                                if(Integer.parseInt(s.split(" ")[1]) > blue) blue = Integer.parseInt(s.split(" ")[1]);
                            }

                            if(s.contains("green")) {
                                if(Integer.parseInt(s.split(" ")[1]) > green) green = Integer.parseInt(s.split(" ")[1]);
                            }

                        }

                    }

                    else {

                        String[] cubesSplit = semiColSplit[i].split(",");

                        for(String s : cubesSplit) {

                            if(s.contains("red")) {
                                if(Integer.parseInt(s.split(" ")[1]) > red) red = Integer.parseInt(s.split(" ")[1]);
                            }

                            if(s.contains("blue")) {
                                if(Integer.parseInt(s.split(" ")[1]) > blue) blue = Integer.parseInt(s.split(" ")[1]);
                            }

                            if(s.contains("green")) {
                                if(Integer.parseInt(s.split(" ")[1]) > green) green = Integer.parseInt(s.split(" ")[1]);
                            }

                        }
                    }


                }

                System.out.println(red * blue * green);
                sumOfPowers += red * blue * green;
       
            }

            System.out.println("TOTAL:" +sumOfPowers);
            
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
