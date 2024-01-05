import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {

    public static void main(String[] args) {
        advnt1();
    }

    public static void advnt1() {

        String filePath = "src\\day3.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            char[][] data = new char[140][140];
            int r = 0;

            while ((line = bufferedReader.readLine()) != null) {

                for(int i = 0; i < line.length(); i++){
                    data[r][i] = line.charAt(i);
                }
                r++;
            }

            int sum = 0;

            for(int i = 0; i < data.length; i++) {

                for (int j = 0; j < data.length; j++) {
                    String s = "";
                    if(data[i][j] >= 48 && data[i][j] <= 57) {
                        if(checkNeigbor(i, j, data)) {
                            s+=data[i][j];
                            int t = j+1;
                            for(; t <= 139 && data[i][t] >= 48 && data[i][t] <= 57; t++) {
                                s+=data[i][t];
                            }
                            j = t;
                            System.out.print(s+" ");
                            sum += Integer.parseInt(s);
                        }
                        else {
                            s = "";
                            boolean check = false;
                            s+=data[i][j];
                            int t = j+1;
                            for(; t < 139 && data[i][t] >= 48 && data[i][t] <= 57; t++) {
                                s+=data[i][t];
                                if(checkNeigbor(i, t, data)) check = true;                    
                            }
                            j=t;
                            if(check) {
                                System.out.print(s+" ");
                                sum += Integer.parseInt(s);
                            }
                        }
                    }
                    
                }
                System.out.println();
                
            }
            System.out.println(sum);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isSymboled(char c) {
        return c != '.' && ((int)c < 48 || (int)c > 57);
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
