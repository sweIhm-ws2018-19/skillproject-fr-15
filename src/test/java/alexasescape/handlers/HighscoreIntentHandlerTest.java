package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.StorageKey;
import alexasescape.model.Highscore;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class HighscoreIntentHandlerTest {

    private HighscoreIntentHandler handler;
    private final String playerName = "Hans";

    @Before
    public void setup() {
        handler = new HighscoreIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithExistingScoreAndWin() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.MENU);
        persistentAttributes.put(playerName, new Highscore(3, 2, 1));

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(String.format("Hey %s! Von insgesamt %d versuchen liegt die beste Runde bei %d Minuten und %d Sekunden.", playerName, 3, 2, 1)));
    }

    @Test
    public void testHandleWithExistingScoreAndNoWin() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.MENU);
        persistentAttributes.put(playerName, new Highscore(3, 0, 0));

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(String.format("Hey %s! Von insgesamt %d versuchen hast du noch keinen erfolgreich beendet!", playerName, 3)));
    }

    @Test
    public void testHandleWithoutScore() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.MENU);
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(String.format("Hey %s! Du hast noch keine Runde gespielt. Also los gehts!", playerName)));
    }

    @Test
    public void testHandleWithoutPlayerName() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.MENU);
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");

        final HandlerInput inputMock = TestUtil.mockHandlerInput(Collections.emptyMap(), sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains("Ich weiss nicht fuer wen ich den Highscore nachschlagen soll?"));
    }
}
