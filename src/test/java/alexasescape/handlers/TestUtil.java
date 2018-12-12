package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.StorageKey;
import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestUtil {

    public static HandlerInput mockHandlerInput(Map<String, String> slots, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
        when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);

        final Intent.Builder intentBuilder = Intent.builder();
        slots.forEach((key, value) ->
                intentBuilder.putSlotsItem(key, Slot.builder().withName(key).withValue(value).build())
        );

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(intentBuilder.build())
                        .build())
                .build();


        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

        return input;
    }

    public static HandlerInput mockHandlerInput(String playerName, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
        return mockHandlerInput(Collections.singletonMap("PlayerName", playerName), sessionAttributes, persistentAttributes, requestAttributes);
    }

    public static Response standardTestForHandle(RequestHandler handler) {
        final Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(StorageKey.REPEAT.getKey(), "Test");
        sessionAttributes.put(StorageKey.STATE.getKey(), GameStatus.PLAY);
        final HandlerInput inputMock = TestUtil.mockHandlerInput(Collections.emptyMap(), sessionAttributes, Collections.emptyMap(), Collections.emptyMap());
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertFalse(response.getShouldEndSession());
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }
}
