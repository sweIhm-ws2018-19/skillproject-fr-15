package alexasescape.handlers;

import alexasescape.model.Highscore;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static alexasescape.handlers.RepeatIntentHandler.REPEAT_KEY;
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
    public void testHandleWithExistingScore() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(REPEAT_KEY, "Test");
        persistentAttributes.put(playerName, new Highscore(3, 2, 1));

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(playerName + " hat insgesamt 3 Spiele erfolgreich beendet. Fuer den besten Versuch hat er 2 Minuten und 1 Sekunden gebraucht!"));
    }

    @Test
    public void testHandleWithoutScore() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(REPEAT_KEY, "Test");

        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(String.format("Ich konnte keinen Highscore fuer %s finden.", playerName)));
    }

    @Test
    public void testHandleWithoutPlayerName() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(REPEAT_KEY, "Test");

        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains("Ich weiß nicht fuer wen ich den Highscore nachschlagen soll?"));
    }
}
