package alexasescape.handlers;

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

public class HangUpIntentHandlerTest {

    private HangUpIntentHandler handler;

    @Before
    public void setup() {
        handler = new HangUpIntentHandler();
    }

    @Test
    public void canHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void handle() {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(REPEAT_KEY, "Test");
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains("Hallo. Moechtest Du das Spiel starten oder Deinen Highscore abfragen"));
    }
}
