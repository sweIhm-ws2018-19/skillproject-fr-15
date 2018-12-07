package alexasescape.model;

import alexasescape.constants.Constant;
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

    public static Game setUp(Player player) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("ein Schrank", "im Schrank liegt ein Schluessel", true));
        items.add(new Item("eine Kiste", "in der Kiste liegt ein Bild", false));

        List<Room> rooms = new LinkedList<>();
        rooms.add(new Room("Raum eins", items));
        rooms.add(new Room("Raum zwei", items));
        rooms.add(new Room("Raum drei", items));

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
        String retValue;
        Item item = itemExists(input);
        if(item == null)
            retValue = "Wie bitte";
        else if(item.isKey()){
            finishRoom();
            //Übergang
            retValue = rooms.peek().getDescription();
        } else {
            //ich konnte leider nix finden..
            retValue = rooms.peek().getDescription();
        }
        return retValue;
    }
}
