package alexasescape.model;

import alexasescape.constants.Constant;
import alexasescape.constants.Items;
import alexasescape.constants.SpeechText;
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


    public Game(int maxFailedAttempts, List<Room> rooms, Player player) {
        Objects.requireNonNull(rooms, "rooms must not be null");
        if (rooms.size() != 3)
            throw new IllegalArgumentException("A game has to contain 3 rooms");
        this.maxFailedAttempts = maxFailedAttempts;
        this.rooms = new ArrayDeque<>();
        this.rooms.addAll(rooms);
        this.player = player;
        startTime = new Date();
    }

    @JsonIgnore
    public Room getCurrentRoom() {
        return rooms.peek();
    }

    @JsonIgnore
    public String getCurrentRoomDescription() {
        if (rooms.peek() == null)
            return SpeechText.GAME_WON;
        return rooms.peek().getDescription();
    }

    /**
     * Use the passed item and increment failedAttempts if item is not a key.
     *
     * @return True if item is a key, else false
     */
    public boolean useItem(Item item) {
        if (item.isKey())
            finishRoom();
        else
            failedAttempts++;
        return item.isKey();
    }

    public boolean isLost() {
        return !rooms.isEmpty() && failedAttempts >= maxFailedAttempts;
    }

    public boolean isWon() {
        return rooms.isEmpty() && !isLost();
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
        final Room curRoom = getCurrentRoom();
        if (curRoom != null)
            return curRoom.getItems()
                    .stream()
                    .filter(item -> item.matches(input) != null)
                    .findFirst()
                    .orElse(null);
        return null;
    }

    public String nextTurn(String input) {
        final Item item = itemExists(input);
        if (item == null)
            return SpeechText.WHAT_WAS_THAT;

        final boolean itemIsKey = useItem(item);
        final String response = itemIsKey && !isLost()
                ? item.getDescription().concat(item.getSolveDescription()).concat(getCurrentRoomDescription())
                : item.getDescription().concat(rooms.peek().getDescription());

        return isLost() ? SpeechText.GAME_OVER : response;
    }
}
