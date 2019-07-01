import java.util.HashMap;
//import java.util.Set;

public class GetCardPath implements Cards {

    public static HashMap<String, String> createMap(String[][] allCards, String[][] cardImageLocations) {

        HashMap<String, String> cardsAndPaths = new HashMap<String, String>();
        for (int i = 0; i < allCards.length; i++) {
            for (int j = 0; j < allCards[0].length; j++) {
                cardsAndPaths.put(allCards[i][j], cardImageLocations[i][j]);
            }
        }


        return cardsAndPaths;
    }

    public static void main(String[] args) {
        HashMap<String, String> locations = createMap(allCards, cardImageLocations);
        System.out.println(locations.get("2-spades"));
        System.out.println(locations.get("3-spades"));
        System.out.println(locations.get("4-spades"));
        System.out.println(locations.get("5-spades"));
        System.out.println(locations.get("6-spades"));
        System.out.println(locations.get("7-spades"));
        System.out.println(locations.get("8-spades"));
        System.out.println(locations.get("9-spades"));
        System.out.println(locations.get("10-spades"));
        System.out.println(locations.get("J-spades"));
        System.out.println(locations.get("Q-spades"));
        System.out.println(locations.get("K-spades"));
        System.out.println(locations.get("A-spades"));
        System.out.println(locations.get("2-clubs"));
        System.out.println(locations.get("3-clubs"));
        System.out.println(locations.get("4-clubs"));
        System.out.println(locations.get("5-clubs"));
        System.out.println(locations.get("6-clubs"));
        System.out.println(locations.get("7-clubs"));
        System.out.println(locations.get("8-clubs"));
        System.out.println(locations.get("9-clubs"));
        System.out.println(locations.get("10-clubs"));
        System.out.println(locations.get("J-clubs"));
        System.out.println(locations.get("Q-clubs"));
        System.out.println(locations.get("K-clubs"));
        System.out.println(locations.get("A-clubs"));
        System.out.println(locations.get("2-hearts"));
        System.out.println(locations.get("3-hearts"));
    }


}
