import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day5 {

    static long[] seeds = {858905075, 56936593, 947763189, 267019426, 206349064, 252409474, 660226451, 92561087, 752930744, 24162055, 75704321, 63600948, 3866217991L,
                        323477533, 3356941271L, 54368890, 1755537789, 475537300, 1327269841, 427659734};

    static long[][] seedsToSoilMap = new long[44][3];
    static long[][] soilToFertMap = new long[22][3];
    static long[][] fertToWatertMap = new long[40][3];
    static long[][] waterToLighttMap = new long[43][3];
    static long[][] lightToTempMap = new long[34][3];
    static long[][] tempToHumidityMap = new long[45][3];
    static long[][] humidityToLocation = new long[34][3];
    static ArrayList<long[]> possibleSeedsRanges = new ArrayList<>();
    static ArrayList<Long> possibleLocations = new ArrayList<>();
    public static void main(String[] args) {
        
        advntPart1();

    }

    public static void advntPart1() {

        initializeData("src\\day5texts\\seedToSoil.txt", seedsToSoilMap);
        initializeData("src\\day5texts\\soilToFert.txt", soilToFertMap);
        initializeData("src\\day5texts\\fertToWater.txt", fertToWatertMap);
        initializeData("src\\day5texts\\waterToLight.txt", waterToLighttMap);
        initializeData("src\\day5texts\\lightToTemp.txt", lightToTempMap);
        initializeData("src\\day5texts\\tempToHumidity.txt", tempToHumidityMap);
        initializeData("src\\day5texts\\humidityToLocation.txt", humidityToLocation);

        ArrayList<Long> locations = new ArrayList<>();

        //Part 1
        for(long seed : seeds) {

            // System.out.println(seed+"--->"+findDestination(findDestination(findDestination(findDestination(findDestination(findDestination(seed, soilToFertMap), fertToWatertMap), waterToLighttMap), lightToTempMap),
            // tempToHumidityMap), humidityToLocation));

            locations.add(finalDestination(seed));

        }

        long min = Long.MAX_VALUE;
        for(long loc : locations) {
            if(loc < min) min = loc;
        }
        System.out.println(min);

        //Part2
        long lo = humidityToLocation[0][1];
        long hi = humidityToLocation[0][1] + humidityToLocation[0][2] - 1;

        rangeMaker(lo, hi, tempToHumidityMap, 1);

        for(int i = 0; i < seeds.length - 1; i+=2) {

            for(long[] s : possibleSeedsRanges) {

                if(s[0] >= seeds[i] && s[0] <= seeds[i] + seeds[i + 1] - 1) {
                    possibleLocations.add(finalDestination(s[0]));
                }

                if(seeds[i] >= s[0] && seeds[i] <= s[1]) {
                    possibleLocations.add(finalDestination(seeds[i]));
                }

            }

        }

        for(long loc : possibleLocations) {
            System.out.println(loc);
        }


    }

    public static long finalDestination(long seed) {
        return sourceToDestination(sourceToDestination(sourceToDestination(sourceToDestination(sourceToDestination(sourceToDestination(sourceToDestination(seed, seedsToSoilMap), soilToFertMap), fertToWatertMap), waterToLighttMap), lightToTempMap),
        tempToHumidityMap), humidityToLocation);
    }

    public static void rangeMaker(long lo, long hi, long[][] sourceMap, int lvl) {

        //System.out.println(lo+" "+hi);
        for(long[] r : sourceMap) {

            if(sourceMap[0][0] != 0) {

                if(sourceMap[0][0] > lo && sourceMap[0][0] > hi) {

                    if(lvl == 1) rangeMaker(lo, hi, lightToTempMap, lvl+1);
                    if(lvl == 2) rangeMaker(lo, hi, waterToLighttMap, lvl+1);
                    if(lvl == 3) rangeMaker(lo, hi, fertToWatertMap, lvl+1);
                    if(lvl == 4) rangeMaker(lo, hi, soilToFertMap, lvl+1);
                    if(lvl == 5) rangeMaker(lo, hi, seedsToSoilMap, lvl+1);

                    if(lvl == 6){
                        System.out.println(lo+"-->"+hi);
                        long[] t = {lo, hi};
                        possibleSeedsRanges.add(t);
                        return;
                    }
                }

                else if(sourceMap[0][0] > lo && sourceMap[0][0] < hi) {

                    if(lvl == 1) rangeMaker(lo, sourceMap[0][0] - 1, lightToTempMap, lvl+1);
                    if(lvl == 2) rangeMaker(lo, sourceMap[0][0] - 1, waterToLighttMap, lvl+1);
                    if(lvl == 3) rangeMaker(lo, sourceMap[0][0] - 1, fertToWatertMap, lvl+1);
                    if(lvl == 4) rangeMaker(lo, sourceMap[0][0] - 1, soilToFertMap, lvl+1);
                    if(lvl == 5) rangeMaker(lo, sourceMap[0][0] - 1, seedsToSoilMap, lvl+1);

                    if(lvl == 6){
                        System.out.println(lo+"-->"+(sourceMap[0][0] - 1));
                        long[] t = {lo, (sourceMap[0][0] - 1)};
                        possibleSeedsRanges.add(t);
                        return;
                    }

                    lo = sourceMap[0][0];
                }
                
            }

            if(r[0] <= lo && r[0] + r[2] - 1 >= hi ) {

                if(lvl == 1) rangeMaker(r[1] + (lo - r[0]), r[1] + (hi - r[0]), lightToTempMap, lvl+1);
                if(lvl == 2) rangeMaker(r[1] + (lo - r[0]), r[1] + (hi - r[0]), waterToLighttMap, lvl+1);
                if(lvl == 3) rangeMaker(r[1] + (lo - r[0]), r[1] + (hi - r[0]), fertToWatertMap, lvl+1);
                if(lvl == 4) rangeMaker(r[1] + (lo - r[0]), r[1] + (hi - r[0]), soilToFertMap, lvl+1);
                if(lvl == 5) rangeMaker(r[1] + (lo - r[0]), r[1] + (hi - r[0]), seedsToSoilMap, lvl+1);

                if(lvl == 6) {
                    System.out.println(r[1] + (lo - r[0])+"-->"+(r[1] + (hi - r[0])));
                    long[] t = {r[1] + (lo - r[0]), (r[1] + (hi - r[0]))};
                    possibleSeedsRanges.add(t);
                    return;
                }

            }
            if(r[0] <= lo && r[0] + r[2] - 1 >= lo && r[0] + r[2] - 1 < hi) {  // r[0] + r[2] - 1 --> s, lo = r[0] + r[2] //

                if(lvl == 1) rangeMaker(r[1] + (lo - r[0]), r[1] + ((r[0] + r[2] - 1) - r[0]), lightToTempMap, lvl+1);
                if(lvl == 2) rangeMaker(r[1] + (lo - r[0]), r[1] + ((r[0] + r[2] - 1) - r[0]), waterToLighttMap, lvl+1);
                if(lvl == 3) rangeMaker(r[1] + (lo - r[0]), r[1] + ((r[0] + r[2] - 1) - r[0]), fertToWatertMap, lvl+1);
                if(lvl == 4) rangeMaker(r[1] + (lo - r[0]), r[1] + ((r[0] + r[2] - 1) - r[0]), soilToFertMap, lvl+1);
                if(lvl == 5) rangeMaker(r[1] + (lo - r[0]), r[1] + ((r[0] + r[2] - 1) - r[0]), seedsToSoilMap, lvl+1);
                if(lvl == 6){
                    System.out.println(r[1] + (lo - r[0])+"-->"+(r[1] + ((r[0] + r[2] - 1) - r[0])));
                    long[] t = {r[1] + (lo - r[0]), (r[1] + ((r[0] + r[2] - 1) - r[0]))};
                    possibleSeedsRanges.add(t);
                    return;
                }
                lo = r[0] + r[2];
            }

        }

        if(lo > sourceMap[sourceMap.length-1][0] + sourceMap[sourceMap.length-1][2] - 1) {

            if(lvl == 1) rangeMaker(lo, hi, lightToTempMap, lvl+1);
            if(lvl == 2) rangeMaker(lo, hi, waterToLighttMap, lvl+1);
            if(lvl == 3) rangeMaker(lo, hi, fertToWatertMap, lvl+1);
            if(lvl == 4) rangeMaker(lo, hi, soilToFertMap, lvl+1);
            if(lvl == 5) rangeMaker(lo, hi, seedsToSoilMap, lvl+1);

            if(lvl == 6) {
                System.out.println(lo+"-->"+hi);
                long[] t = {lo, hi};
                possibleSeedsRanges.add(t);
                return;
            }

        }

    }

    public static long sourceToDestination(long val, long[][] map) {

        for(int i = 0; i < map.length; i++) {

            if(val >= map[i][1] && val < map[i][1]+map[i][2]) {

                return map[i][0] + (val - map[i][1]);

            }
        }

        return val;

    }

    public static void initializeData(String filePath, long[][] map) {

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            int r = 0;
            while ((line = bufferedReader.readLine()) != null) {

                map[r][0] = Long.parseLong(line.split(" ")[0]);
                map[r][1] = Long.parseLong(line.split(" ")[1]);
                map[r][2] = Long.parseLong(line.split(" ")[2]);
                r++;

            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        sortMapByDestination(map);

    }

    public static void sortMapByDestination(long[][] map) {

        int n = map.length; 
  
        for (int i = 0; i < n - 1; i++) { 

            int min_idx = i; 
            for (int j = i + 1; j < n; j++) { 
                if (map[j][0] < map[min_idx][0]) 
                    min_idx = j; 
            } 
  
            long temp = map[min_idx][0];
            long temp1 = map[min_idx][1];
            long temp2 = map[min_idx][2];

            map[min_idx][0] = map[i][0];
            map[min_idx][1] = map[i][1];
            map[min_idx][2] = map[i][2];

            map[i][0] = temp;
            map[i][1] = temp1; 
            map[i][2] = temp2; 
        } 
    }


}
