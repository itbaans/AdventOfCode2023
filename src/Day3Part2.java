import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) {
        String filePath = "src\\day3.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            char[][] data = new char[140][140];
            int r = 0;
            ArrayList<Gear> gears = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {

                for(int i = 0; i < line.length(); i++){
                    data[r][i] = line.charAt(i);
                    if(data[r][i] == '*') gears.add(new Gear(r,i));
                }
                r++;
            }
            //int sum = 0;

            for(int i = 0; i < data.length; i++) {

                for (int j = 0; j < data.length; j++) {
                    String s = "";
                    if(data[i][j] >= 48 && data[i][j] <= 57) {
                        if(checkNeighbor2(i, j, gears) != null) {
                            s+=data[i][j];
                            int t = j+1;
                            for(; t <= 139 && data[i][t] >= 48 && data[i][t] <= 57; t++) {
                                s+=data[i][t];
                            }
                            checkNeighbor2(i, j, gears).nums.add(Integer.parseInt(s));
                            j = t;
                            //System.out.print(s+" ");
                            //sum += Integer.parseInt(s);
                        }
                        else {
                            s = "";
                            boolean check = false;
                            s+=data[i][j];
                            int t = j+1;
                            int temp = 0;
                            for(; t < 139 && data[i][t] >= 48 && data[i][t] <= 57; t++) {
                                s+=data[i][t];
                                if(checkNeighbor2(i, t, gears) != null) {
                                    check = true;
                                    temp = t;
                                }                    
                            }
                            if(check) {
                                //System.out.print(s+" ");
                                checkNeighbor2(i, temp, gears).nums.add(Integer.parseInt(s));
                                //sum += Integer.parseInt(s);
                            }
                            j=t;
                        }
                    }
                    
                }
                //System.out.println();
                
            }
            //System.out.println(sum);
            int gearRatioSum = 0;
            for(Gear g : gears) {
                if(g.nums.size() == 2) {
                    gearRatioSum += (g.nums.get(0) * g.nums.get(1));
                }
            }

            System.out.println(gearRatioSum);


            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSymboled(char c) {
        return c != '.' && ((int)c < 48 || (int)c > 57);
    }

    public static Gear checkNeighbor2(int r, int c, ArrayList<Gear> gears) {

        for (Gear g : gears) {
            if((Math.abs(g.x - r) == 1 && Math.abs(g.y - c) == 1)) return g;
            if((Math.abs(g.x - r) == 1 && Math.abs(g.y - c) == 0)) return g;
            if((Math.abs(g.x - r) == 0 && Math.abs(g.y - c) == 1)) return g;
        }

        return null;

    }

    public static boolean checkNeigbor(int i, int j, char[][] d) {

        if(i < d.length && j < d.length) {
            if(i == 0 && j == 0) return isSymboled(d[i][j+1]) || isSymboled(d[i+1][j+1]) || isSymboled(d[i+1][j]);
            if(i == d.length - 1 && j == d.length-1) return isSymboled(d[i][j-1]) || isSymboled(d[i-1][j-1]) || isSymboled(d[i-1][j]);
            if(i == 0 && j == d.length-1) return isSymboled(d[i+1][j]) || isSymboled(d[i][j-1]) || isSymboled(d[i+1][j-1]);
            if(i == d.length-1 && j == 0) return isSymboled(d[i-1][j]) || isSymboled(d[i][j+1]) || isSymboled(d[i-1][j+1]);
            if(i == 0 && j > 0) return isSymboled(d[i+1][j]) || isSymboled(d[i][j-1]) || isSymboled(d[i+1][j-1]) || isSymboled(d[i][j+1]) || isSymboled(d[i+1][j+1]);
            if(i > 0 && j == 0) return isSymboled(d[i+1][j]) || isSymboled(d[i-1][j]) || isSymboled(d[i+1][j+1]) || isSymboled(d[i][j+1]) || isSymboled(d[i-1][j+1]);
            if(i == d.length-1 && j > 0) return isSymboled(d[i-1][j]) || isSymboled(d[i][j-1]) || isSymboled(d[i-1][j-1]) || isSymboled(d[i][j+1]) || isSymboled(d[i-1][j+1]);
            if(i > 0 && j == d.length-1) return isSymboled(d[i+1][j]) || isSymboled(d[i-1][j]) || isSymboled(d[i+1][j-1]) || isSymboled(d[i][j-1]) || isSymboled(d[i-1][j-1]);

            return isSymboled(d[i][j+1]) || isSymboled(d[i][j-1]) || isSymboled(d[i-1][j-1]) || isSymboled(d[i+1][j+1]) || isSymboled(d[i-1][j+1]) || isSymboled(d[i+1][j-1]) || isSymboled(d[i-1][j]) || isSymboled(d[i+1][j]);
        }

        return false;
    }

    
}

class Gear {

    int x;
    int y;
    List<Integer> nums = new LinkedList<>();

    Gear(int X, int Y) {
        x = X;
        y = Y;
    }

}

