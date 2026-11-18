import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trainer {

    private static final int MAX_PARTY_SIZE = 6;
    public static final String[] TRAINER_GENDERS = new String[] {"MALE", "FEMALE"};

    private final String trainerName;
    private final List<PokemonEntry> pokemonParty = new ArrayList<>(MAX_PARTY_SIZE);

    public Trainer(String trainerName) {
        this.trainerName = trainerName;
    }

    public Trainer(String trainerName, Pokedex pokedex, int partySize) {
        this.trainerName = trainerName;
        generateRandomParty(pokedex, partySize);
    }

    public static Trainer generateRandomTrainer(Pokedex pokedex) {
        Random random = new Random();
        String randomName = TrainerData.trainerNames[random.nextInt(TrainerData.trainerNamesSize)];
        int randomPartySize = random.nextInt(5) + 1;
        Trainer randomTrainer = new Trainer(randomName);

        randomTrainer.generateRandomParty(pokedex, randomPartySize);

        return randomTrainer;
    }

    public void generateRandomParty(Pokedex pokedex, int partyLimit) {
        if (partyLimit < 0 || partyLimit > MAX_PARTY_SIZE) {
            System.out.println("Please enter a valid party size");
            return;
        }

        Random random = new Random();

        for (int i = 0; i < partyLimit; i++) {
            int entry = random.nextInt(pokedex.getPokedexSize());
            PokemonEntry pokemonEntry = pokedex.getPokemonEntry(entry);
            pokemonParty.add(pokemonEntry);
        }
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
}
