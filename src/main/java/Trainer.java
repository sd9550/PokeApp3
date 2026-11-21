import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trainer {

    public static final int MAX_PARTY_SIZE = 6;
    public static final String[] TRAINER_GENDERS = new String[] {"MALE", "FEMALE"};

    private final int trainerIdNumber;
    private final String trainerName;
    private final List<PokemonEntry> pokemonParty = new ArrayList<>(MAX_PARTY_SIZE);
    private int battleVictories = 0;

    public Trainer(String trainerName) {
        this.trainerName = trainerName;
        this.trainerIdNumber = TrainerData.getNextAvailableTrainerId();
    }

    public Trainer(String trainerName, Pokedex pokedex, int partySize) {
        this.trainerName = trainerName;
        this.trainerIdNumber = TrainerData.getNextAvailableTrainerId();
        generateRandomParty(pokedex, partySize);
    }

    public static Trainer generateRandomTrainer(Pokedex pokedex, boolean randomParty) {
        Random random = new Random();
        String randomName = TrainerData.trainerNames[random.nextInt(TrainerData.trainerNamesSize)];
        int partySize;

        if (randomParty) {
            partySize = random.nextInt(5) + 1;
        } else {
            partySize = 6;
        }

        Trainer randomTrainer = new Trainer(randomName);

        randomTrainer.generatePartyByTrainerClass(pokedex, partySize);

        return randomTrainer;
    }

    public void generateRandomParty(Pokedex pokedex, int partyLimit) {
        if (partyLimit < 0 || partyLimit > MAX_PARTY_SIZE) {
            System.out.println("Please enter a valid party size");
            return;
        }

        Random random = new Random();
        pokemonParty.clear();

        for (int i = 0; i < partyLimit; i++) {
            int entry = random.nextInt(pokedex.getPokedexSize());
            PokemonEntry pokemonEntry = pokedex.getPokemonEntry(entry);
            pokemonParty.add(pokemonEntry);
        }
    }

    public void generatePartyByTrainerClass(Pokedex pokedex, int partyLimit) {
        if (partyLimit < 0 || partyLimit > MAX_PARTY_SIZE) {
            System.out.println("Please enter a valid party size");
            return;
        }

        Random random = new Random();
        pokemonParty.clear();
        List<Integer> validEntries = TrainerData.getValidTrainerPartyEntryArray(trainerName);

        for (int i = 0; i < partyLimit; i++) {
            int entry = random.nextInt(validEntries.size());
            PokemonEntry pokemonEntry = pokedex.getPokemonEntry(validEntries.get(entry));
            pokemonParty.add(pokemonEntry);
        }
    }

    public void generatePresetParty(Pokedex pokedex, int[] entries) {
        if (entries.length < 1 || entries.length > 6) {
            System.out.println("Please submit 1 to 6 entries");
            return;
        }

        pokemonParty.clear();

        for (int i = 0; i < entries.length; i++) {
            PokemonEntry pokemonEntry = pokedex.getPokemonEntry(entries[i]);
            pokemonParty.add(pokemonEntry);
        }

        System.out.println("Added " + entries.length + " party members");
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Trainer Name: " + trainerName).append("\nTrainer Party: ");

        for (PokemonEntry pokemonEntry : pokemonParty) {
            stringBuilder.append(pokemonEntry.getPokemonName()).append(" ");
        }

        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public int getBattleVictories() {
        return battleVictories;
    }

    public void setBattleVictories(int battleVictories) {
        this.battleVictories = battleVictories;
    }

    public List<PokemonEntry> getPokemonParty() {
        return pokemonParty;
    }

}
