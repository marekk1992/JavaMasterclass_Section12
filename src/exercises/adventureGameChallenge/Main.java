package exercises.adventureGameChallenge;

import java.util.*;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, String> exitCommands = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of the hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        exitCommands.put("QUIT", "Q");
        exitCommands.put("WEST", "W");
        exitCommands.put("EAST", "E");
        exitCommands.put("NORTH", "N");
        exitCommands.put("SOUTH", "S");

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = getDirection(scanner.nextLine().toUpperCase());

            if (direction != null && exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }

    public static String getDirection(String userInput) {
        if (userInput.length() == 1) {
            return userInput.toUpperCase();
        } else {
            String[] words = userInput.split(" ");
            for (String exit : exitCommands.keySet()) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equalsIgnoreCase(exit)) {
                        return exitCommands.get(exit);
                    }
                }
            }
        }
        return null;
    }
}