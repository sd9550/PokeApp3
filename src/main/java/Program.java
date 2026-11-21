import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex("src/main/java/pokemon_first_151.csv");
        TrainerData.populateTrainerMap("src/main/java//TrainerPartyData_Populated.csv");
        Trainer firstTrainer = new Trainer("Red", pokedex, 6);
        List<Trainer> randomPokemonTrainers = new ArrayList<>();
        BattleArena battleArena = null;

        for (int i = 0; i < 3; i++) {
            randomPokemonTrainers.add(Trainer.generateRandomTrainer(pokedex, false));
        }

        battleArena = new BattleArena(firstTrainer, randomPokemonTrainers);
        battleArena.startBattleArena();

        //printTrainerInformation(firstTrainer, randomPokemonTrainers);

    }

    public static void printTrainerInformation(Trainer trainer, List<Trainer> otherTrainers) {
        System.out.println(trainer);

        otherTrainers.forEach(System.out::println);
    }
}
