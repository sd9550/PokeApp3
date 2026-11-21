import java.util.*;

public class BattleArena {

    private final List<Trainer> allTrainers = new ArrayList<>();
    private boolean oneTrainerLeft = false;
    private int numberOfRounds = 0;

    public BattleArena(Trainer playerTrainer, List<Trainer> otherTrainers) {
        allTrainers.add(playerTrainer);
        allTrainers.addAll(otherTrainers);
    }

    public void startBattleArena() {
        while (!oneTrainerLeft) {
            System.out.println("START OF WHILE LOOP");
            oneTrainerLeft = roundOfBattle();
        }
        printBattleArenaSummary();
    }

    private Trainer roundOfBattleRecursion(List<Trainer> arenaTrainers) {
        if (arenaTrainers.size() == 1) {
            return arenaTrainers.getFirst();
        }

        roundOfBattleRecursion(arenaTrainers);

        return null;
    }

    private boolean roundOfBattle() {
        Random random = new Random();
        numberOfRounds++;
        Collections.shuffle(allTrainers);

        List<Trainer> winners = new ArrayList<>();

        for (int i = 0; i < allTrainers.size(); i += 2) {
            if (i + 1 < allTrainers.size()) {
                // There is an opponent: battle
                Trainer a = allTrainers.get(i);
                Trainer b = allTrainers.get(i + 1);
                Trainer winner = battle(a, b, random); // extract the battle logic
                winners.add(winner);
            } else {
                // Odd one out: bye
                winners.add(allTrainers.get(i));
            }
        }

        allTrainers.clear();
        allTrainers.addAll(winners);

        return allTrainers.size() == 1;
    }

    private Trainer battle(Trainer a, Trainer b, Random random) {
        List<PokemonEntry> partyA = a.getPokemonParty();
        List<PokemonEntry> partyB = b.getPokemonParty();

        while (!partyA.isEmpty() && !partyB.isEmpty()) {
            // ... your existing battle logic ...
            // but do NOT remove from allTrainers here
            String firstTrainerPokemonName = partyA.getFirst().getPokemonName();
            String secondTrainerPokemonName = partyB.getFirst().getPokemonName();
            String firstTrainerName = a.getTrainerName();
            String secondTrainerName = b.getTrainerName();

            System.out.println("\n" + firstTrainerName + "'s " + firstTrainerPokemonName + " VS " + secondTrainerName + "'s " + secondTrainerPokemonName);

            int battleWinner = random.nextInt(9);

            if (battleWinner < 5) {
                System.out.println(firstTrainerName + "'s " + firstTrainerPokemonName + " wins!");
                partyB.removeFirst();
                //System.out.println("SIZE OF TRAINER 2 PARTY: " + partyB.size());
                // allTrainers.remove(i + 1);
            } else {
                System.out.println(secondTrainerName + "'s " + secondTrainerPokemonName + " wins!");
                partyA.removeFirst();
                //System.out.println("SIZE OF TRAINER 1 PARTY: " + partyA.size());
                //allTrainers.remove(i);
            }

            try {
                Thread.sleep(500); // Pauses for 1000 milliseconds (1 second)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restores the interrupted status
            }

            if (partyA.isEmpty()) {
                System.out.println(firstTrainerName + "'s party has been defeated!");
            } else if (partyB.isEmpty()) {
                System.out.println(secondTrainerName + "'s party has been defeated!");
            }
        }

        return partyA.isEmpty() ? b : a;
    }

    private boolean roundOfBattle2() {
        Random random = new Random();
        boolean oneTrainerLeft = false;
        int currentSize = allTrainers.size();

        numberOfRounds++;
        Collections.shuffle(allTrainers);

        for (int i = 0; i < currentSize; i++) {
            if (i + 1 < currentSize) {
                List<PokemonEntry> firstTrainerParty = allTrainers.get(i).getPokemonParty();
                List<PokemonEntry> secondTrainerParty = allTrainers.get(i + 1).getPokemonParty();
                String firstTrainerName = allTrainers.get(i).getTrainerName();
                String secondTrainerName = allTrainers.get(i + 1).getTrainerName();
                boolean trainerHasEmptyParty = false;

                //System.out.println("I IS " + i + " AND i + 1 is " + (i + 1));
                System.out.println("SIZE OF THE ARRAY AT THE START IS " + allTrainers.size());
                System.out.println("\n" + firstTrainerName + " VS " + secondTrainerName);

                while (!trainerHasEmptyParty) {
                    String firstTrainerPokemonName = firstTrainerParty.getFirst().getPokemonName();
                    String secondTrainerPokemonName = secondTrainerParty.getFirst().getPokemonName();

                    System.out.println("\n" + firstTrainerName + "'s " + firstTrainerPokemonName + " VS " + secondTrainerName + "'s " + secondTrainerPokemonName);

                    int battleWinner = random.nextInt(9);

                    if (battleWinner < 5) {
                        System.out.println(firstTrainerName + "'s " + firstTrainerPokemonName + " wins!");
                        secondTrainerParty.removeFirst();
                        System.out.println("SIZE OF TRAINER 2 PARTY: " +secondTrainerParty.size());
                        // allTrainers.remove(i + 1);
                    } else {
                        System.out.println(secondTrainerName + "'s " + secondTrainerPokemonName + " wins!");
                        firstTrainerParty.removeFirst();
                        System.out.println("SIZE OF TRAINER 1 PARTY: " +firstTrainerParty.size());
                        //allTrainers.remove(i);
                    }

                    try {
                        Thread.sleep(500); // Pauses for 1000 milliseconds (1 second)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restores the interrupted status
                    }

                    if (firstTrainerParty.isEmpty()) {
                        System.out.println(firstTrainerName + "'s party has been defeated!");
                        trainerHasEmptyParty = true;
                        allTrainers.remove(i);
                        currentSize = allTrainers.size();
                    } else if (secondTrainerParty.isEmpty()) {
                        System.out.println(secondTrainerName + "'s party has been defeated!");
                        trainerHasEmptyParty = true;
                        allTrainers.remove(i + 1);
                        currentSize = allTrainers.size();
                    }
                }

            }

            if (allTrainers.size() == 1) {
                System.out.println("THE SIZE OF ALL TRAINERS IS 1? " + allTrainers.size());
                oneTrainerLeft = true;
            }

        }

        return oneTrainerLeft;
    }

    private void printBattleArenaSummary() {
        System.out.println("\nThe Battle Arena is over after " + numberOfRounds + " rounds!");
        System.out.println("Size of the trainer list is " + allTrainers.size());
        System.out.println("The champion is " + allTrainers.getFirst().getTrainerName() + "!");
    }
}
