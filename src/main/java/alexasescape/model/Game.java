package alexasescape.model;

import alexasescape.constants.Constant;
import alexasescape.constants.Items;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Game {

    private Date startTime;
    private int failedAttempts;
    private int maxFailedAttempts;

    @EqualsAndHashCode.Exclude
    private Queue<Room> rooms;
    private static Random random = new Random();

    private Player player;
    private GameStatus gameStatus;
    private static String solveDescription = "";


    public Game(int maxFailedAttempts, List<Room> rooms, Player player) {
        Objects.requireNonNull(rooms, "rooms must not be null");
        if(rooms.size() != 3)
            throw new IllegalArgumentException("A game has to contain 3 rooms");
        this.maxFailedAttempts = maxFailedAttempts;
        this.rooms = new ArrayDeque<>();
        this.rooms.addAll(rooms);
        this.player = player;
        startTime = new Date();
        gameStatus = GameStatus.DESCRIBE;
    }

    @JsonIgnore
    public Room getCurrentRoom(){
        return rooms.peek();
    }

    public boolean failed(){
        return failedAttempts++ < maxFailedAttempts;
    }

    public static List<Item> generateItems(){

        final int min = 1;
        final int max = 3; //one item with and three without a key
        final int itemsCount = random.nextInt(max)+min;

        final List<Item> itemsWithKey = new ArrayList<>();
        itemsWithKey.add(new Item("ein Schrank", "im Schrank liegt ein Schluessel", true));
        itemsWithKey.add(new Item("ein Regal", "Ich sehe einen Schluesselauf dem Regal", true));
        itemsWithKey.add(new Item("ein Blumentopf", "Unter den Blättern sehe ich etwas glänzen.. Es ist ein Schlueesel", true));

        List<Item> itemsWithoutKey = new ArrayList<>();
        itemsWithoutKey.add(new Item("eine Kiste", "in der Kiste liegt ein Bild", false));
        itemsWithoutKey.add(new Item("ein Tisch", "auf dem Tisch liegt nur Geschirr und Besteck", false));
        itemsWithoutKey.add(new Item("ein Bett", "Im Bett liegen Klamotten, aber kein Schluessel", false));
        itemsWithoutKey.add(new Item("ein Schreibtisch", "auf dem Schreibtisch liegt nur Geschirr und Besteck", false));
        itemsWithoutKey.add(new Item("ein Teppich", "auf dem Teppich liegt nur Geschirr und Besteck", false));
        itemsWithoutKey.add(new Item("ein Stuhl", "auf dem Stuhl liegt nur Geschirr und Besteck", false));
        itemsWithoutKey.add(new Item("ein Sofa", "auf dem Sofa liegt nur Geschirr und Besteck", false));


        final List<Item> itemsForGame = new ArrayList<>();
        final int keySize = itemsWithKey.size()-1;
        final int noKeySize = itemsWithoutKey.size()-1;

        final int selectKey = random.nextInt(keySize); //pick a random element as key between index 0 and
        final int selectNoKey = random.nextInt(noKeySize-itemsCount); //pick startElement of items without key

        itemsForGame.add(itemsWithKey.get(selectKey));

        for(int i = 0; i<itemsCount; i++)
            itemsForGame.add(itemsWithoutKey.get(selectNoKey+i));
        return itemsForGame;
    }

    public static Game setUp(Player player) {
        List<Room> rooms = new LinkedList<>();
        rooms.add(new Room("Raum eins", Items.getItemList()));
        rooms.add(new Room("Raum zwei", Items.getItemList()));
        rooms.add(new Room("Raum drei", Items.getItemList()));

        return new Game(Constant.MAXATTEMPTS, rooms, player);
    }

    public void finishRoom(){
        rooms.poll();
    }

    private Item itemExists(String input){
        Item retValue = null;
        List<Item> toTest = rooms.peek().getItems();
        Iterator<Item> iterator = toTest.iterator();
        while(iterator.hasNext() && retValue == null)
            retValue = iterator.next().matches(input);
        return retValue;
    }

    public String nextTurn(String input){
        Item item = itemExists(input);
        if(item == null)
            return "Wie bitte";
        if(item.isKey()){
            finishRoom();
            return item.getDescription().concat(item.getSolveDescription()).concat(rooms.peek().getDescription());
        }
        if (!item.isKey() && failed()){
            //itembeschreibung - ich konnte leider nix finden..
            return item.getDescription().concat(rooms.peek().getDescription());
        }
        return "Spiel zu Ende";
    }
}
