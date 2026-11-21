import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.*;

public class TrainerData {
    public static String[] trainerNames;
    public static int trainerNamesSize;
    private static int trainerId = 100000;

    private static Map<String, List<Integer>> validTrainerPartyMap = new HashMap<>();

    public static int getNextAvailableTrainerId() {
        return trainerId++;
    }

    public TrainerData() {
    }

    public static void populateTrainerMap(String csvFile) {
        System.out.print("GENERATING THE ENEMY TRAINER DATA...");
        printSleep(1000);
        System.out.println("......\n");


        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withSkipLines(1)
                .build()) {
            String[] nextLine;
            List<Integer> entryNumbers = new ArrayList<>();

            // readNext() reads the file line-by-line, parsing it into an array
            while ((nextLine = reader.readNext()) != null) {
                //System.out.println(Arrays.toString(nextLine));
                if (nextLine.length > 0) {
                    for (int i = 1; i < nextLine.length; i++) {
                        entryNumbers.add(Integer.parseInt(nextLine[i]));
                    }
                    validTrainerPartyMap.put(nextLine[0], new ArrayList<>(entryNumbers));
                    entryNumbers.clear();
                }
            }

            trainerNames = validTrainerPartyMap.keySet().toArray(String[]::new);;
            trainerNamesSize = trainerNames.length;

        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e.getLocalizedMessage());
        }
    }

    public static Map<String, List<Integer>> getValidTrainerPartyMap() {
        return validTrainerPartyMap;
    }

    public static List<Integer> getValidTrainerPartyEntryArray(String trainerClass) {
        return validTrainerPartyMap.get(trainerClass);
    }

    public static void setValidTrainerPartyMap(Map<String, List<Integer>> validTrainerPartyMap) {
        TrainerData.validTrainerPartyMap = validTrainerPartyMap;
    }

    public static void printSleep(int sleepTimer) {
        try {
            Thread.sleep(sleepTimer);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
