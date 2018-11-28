package alexasescape.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
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
        final Response response = TestUtil.standardTestForHandle(handler);
        assertTrue(response.getOutputSpeech().toString().contains("Hallo. Moechtest Du das Spiel starten oder Deinen Highscore abfragen"));
    }
}
