import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex("src/main/java/pokemon_first_151.csv");
        Trainer firstTrainer = new Trainer("Red", pokedex, 6);
        List<Trainer> randomPokemonTrainers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            randomPokemonTrainers.add(Trainer.generateRandomTrainer(pokedex));
        }

        printTrainerInformation(firstTrainer, randomPokemonTrainers);

    }

    public static void printTrainerInformation(Trainer trainer, List<Trainer> otherTrainers) {
        System.out.println(trainer);

        otherTrainers.forEach(System.out::println);
    }
}
