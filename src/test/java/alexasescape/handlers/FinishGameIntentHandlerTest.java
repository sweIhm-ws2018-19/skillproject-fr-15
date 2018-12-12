package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.StorageKey;
import alexasescape.model.Game;
import alexasescape.model.Player;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class FinishGameIntentHandlerTest {

    private FinishGameIntentHandler handler;
    private final String playerName = "Hans";

    @Before
    public void setup() {
        handler = new FinishGameIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.FINISHED);
        final Map<String, Object> persistentAttributes = new HashMap<>();

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);

        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithPlayerName() {
        Player player = new Player(playerName);
        Game game = Game.setUp(player);

        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.GAME.getKey(), game);
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        final Map<String, Object> persistentAttributes = new HashMap<>();

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
    }

}
