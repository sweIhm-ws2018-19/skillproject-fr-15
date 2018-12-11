package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.SpeechText;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class StartIntentHandlerTest {

    private StartIntentHandler handler;

    @Before
    public void setup() {
        handler = new StartIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {

        final Map<String, Object> sessionAttributes = new HashMap<>();
        final Map<String, Object> persistentAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.MENU);

        final HandlerInput inputMock = TestUtil.mockHandlerInput("", sessionAttributes, persistentAttributes, null);
        final Optional<Response> res = handler.handle(inputMock);
        assertTrue(res.isPresent());
        final Response response = res.get();
        assertTrue(response.getOutputSpeech().toString().contains(SpeechText.START));
    }
}
