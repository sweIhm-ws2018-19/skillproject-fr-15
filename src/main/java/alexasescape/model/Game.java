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
    private Player player;
    private GameStatus gameStatus;


    public Game(int maxFailedAttempts, List<Room> rooms, Player player) {
        Objects.requireNonNull(rooms, "rooms must not be null");
        if (rooms.size() != 3)
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

    @JsonIgnore
    public String getCurrentRoomDescription() {
        if(rooms.peek() == null)
            return "Danke! Ich bin frei! Und jetzt schalte dein Eco aus!";
        return rooms.peek().getDescription();
    }

    public boolean failed() {
        return failedAttempts++ < maxFailedAttempts;
    }

    public static Game setUp(Player player) {
        List<Room> rooms = new LinkedList<>();
        rooms.add(new Room("Raum eins", Items.getItemList()));
        rooms.add(new Room("Raum zwei", Items.getItemList()));
        rooms.add(new Room("Raum drei", Items.getItemList()));

        return new Game(Constant.MAXATTEMPTS, rooms, player);
    }

    public Room finishRoom() {
        return rooms.poll();
    }

    private Item itemExists(String input) {
        Item retValue = null;
        List<Item> toTest = rooms.peek().getItems();
        Iterator<Item> iterator = toTest.iterator();
        while (iterator.hasNext() && retValue == null)
            retValue = iterator.next().matches(input);
        return retValue;
    }

    public String nextTurn(String input) {
        Item item = itemExists(input);
        if (item == null)
            return "Wie bitte?";

        if (item.isKey()) {
            finishRoom();
            return item.getDescription().concat(item.getSolveDescription()).concat(getCurrentRoomDescription());
        }
        if (!item.isKey() && failed()) {
            //itembeschreibung - ich konnte leider nix finden..
            return item.getDescription().concat(rooms.peek().getDescription());
        }
        return "Spiel zu Ende";
    }
}
