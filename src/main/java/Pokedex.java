import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private final int MAX_POKEMON = 151;

    private List<PokemonEntry> pokemonEntryList = new ArrayList<>(MAX_POKEMON);

    public Pokedex(String pokemonFilePath) {
        generatePokedexWithFile(pokemonFilePath);
    }

    private void generatePokedexWithFile(String csvFile) {

        System.out.println("GENERATING THE POKEDEX.......");

        try {
            Thread.sleep(1000); // Pauses for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restores the interrupted status
        }

        System.out.println("CURRENT LIMIT IS " + MAX_POKEMON);

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;

            // readNext() reads the file line-by-line, parsing it into an array
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    pokemonEntryList.add(new PokemonEntry(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7]));
                }
            }
            // Remove the column names from the list
            pokemonEntryList.removeFirst();
            System.out.println("SUCCESSFULLY GENERATED " + pokemonEntryList.size() + " ENTRIES TO THE POKEDEX");

        } catch (Exception e) {
            System.out.println("An exception has occurred: " + e.getLocalizedMessage());
        }

    }

    public List<PokemonEntry> getPokemonEntryList() {
        return pokemonEntryList;
    }

    public void setPokemonEntryList(List<PokemonEntry> pokemonEntryList) {
        this.pokemonEntryList = pokemonEntryList;
    }

    public void printEntirePokedexEntries() {
        for (PokemonEntry pokemonEntry : pokemonEntryList) {
            System.out.println(pokemonEntry);
        }
    }

    public void printPartialPokedex(int total) {
        if (total <= 0 || total > MAX_POKEMON) {
            System.out.println("Please enter a valid number for printing");
            return;
        }

        for (int i = 0; i < total; i++) {
            System.out.println(pokemonEntryList.get(i).toString());
        }
    }

    public int getPokedexSize() {
        return pokemonEntryList.size();
    }

    public PokemonEntry getPokemonEntry(int entryNumber) {
        if (entryNumber < 0 || entryNumber > MAX_POKEMON) {
            System.out.println("Please enter a valid pokedex entry number");
            return null;
        }

        return pokemonEntryList.get(entryNumber);
    }
}
