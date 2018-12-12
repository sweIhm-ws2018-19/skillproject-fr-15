package alexasescape.handlers;

import alexasescape.constants.SpeechText;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CancelOrStopIntentHandlerTest {

    private CancelOrStopIntentHandler handler;

    @Before
    public void setup() {
        handler = new CancelOrStopIntentHandler();
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
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        final HandlerInput inputMock = TestUtil.mockHandlerInput(Collections.emptyMap(), sessionAttributes, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(SpeechText.CANCEL_OR_STOP));
    }
}
