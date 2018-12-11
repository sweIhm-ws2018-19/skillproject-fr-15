package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class TellStoryIntentHandlerTest {

    private TellStoryIntentHandler handler;
    private final String playerName = "Hans";

    @Before
    public void setup() {
        handler = new TellStoryIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithPlayerName() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY);
        final HandlerInput inputMock = TestUtil.mockHandlerInput(playerName, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(playerName));
    }

    @Test
    public void testHandleWithoutPlayerName() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY);
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains("Ich habe Deinen Namen nicht verstanden"));
    }
}
