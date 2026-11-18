import java.util.Arrays;

public class PokemonEntry {
    private final String pokemonEntryNumber;
    private final String pokemonName;
    private final String pokemonPrimaryType;
    private final String pokemonSecondaryType;
    private final String pokemonTypesLine;
    private final String pokemonWeaknessesLine;
    private final String pokemonEvolutionLevel;
    private final String pokemonFlavorText;

    private String[] pokemonTypesArray;
    private String[] pokemonWeaknessesArray;

    public PokemonEntry(String pokemonEntryNumber, String pokemonName, String pokemonPrimaryType, String pokemonSecondaryType, String pokemonTypesLine, String pokemonWeaknessesLine, String pokemonEvolutionLevel, String pokemonFlavorText) {
        this.pokemonEntryNumber = pokemonEntryNumber;
        this.pokemonName = pokemonName;
        this.pokemonPrimaryType = pokemonPrimaryType;
        this.pokemonSecondaryType = pokemonSecondaryType;
        this.pokemonTypesLine = pokemonTypesLine;
        this.pokemonWeaknessesLine = pokemonWeaknessesLine;
        this.pokemonEvolutionLevel = pokemonEvolutionLevel;
        this.pokemonFlavorText = pokemonFlavorText;

        parseTypesAndWeaknesses();
    }

    private void parseTypesAndWeaknesses() {
        pokemonTypesArray = pokemonTypesLine.split(",");
        pokemonWeaknessesArray = pokemonWeaknessesLine.split(",");
    }

    public String getPokemonName() {
        return pokemonName;
    }

    @Override
    public String toString() {
        return "PokemonEntry{" +
                "pokemonEntryNumber='" + pokemonEntryNumber + '\'' +
                ", pokemonName='" + pokemonName + '\'' +
                ", pokemonPrimaryType='" + pokemonPrimaryType + '\'' +
                ", pokemonSecondaryType='" + pokemonSecondaryType + '\'' +
                ", pokemonTypes='" + Arrays.toString(pokemonTypesArray) + '\'' +
                ", pokemonWeaknesses='" + Arrays.toString(pokemonWeaknessesArray) + '\'' +
                ", pokemonEvolutionLevel='" + pokemonEvolutionLevel + '\'' +
                ", pokemonFlavorText='" + pokemonFlavorText + '\'' +
                '}';
    }
}
