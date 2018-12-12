package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.Slots;
import alexasescape.constants.StorageKey;
import alexasescape.model.Game;
import alexasescape.model.Player;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class DescribeAndDecideIntentHandlerTest {

    private DescribeAndDecideIntentHandler handler;
    private final String playerName = "Hans";

    @Before
    public void setup() {
        handler = new DescribeAndDecideIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithGameAsObject() throws IOException {
        Player player = new Player(playerName);
        Game game = Game.setUp(player);

        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.GAME.getKey(), game);
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY.toString());

        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
    }

    @Test
    public void testHandleWithGameAsObject_AndITEM() throws IOException {
        Player player = new Player(playerName);
        Game game = Game.setUp(player);

        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.GAME.getKey(), game);
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY.toString());
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");

        final Map<String, String> slotValues = new HashMap<>();
        slotValues.put(Slots.PLAYER_NAME.getSlotName(), playerName);
        slotValues.put(Slots.ITEM_NAME.getSlotName(), game.getCurrentRoom().getItems().get(0).getName());

        final HandlerInput inputMock = TestUtil.mockHandlerInput(slotValues, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
    }

    @Test
    public void testHandleWithGameAsJSONString() throws IOException {
        Player player = new Player(playerName);
        Game game = Game.setUp(player);

        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.GAME.getKey(), new ObjectMapper().writeValueAsString(game));
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY.toString());
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
    }

}
