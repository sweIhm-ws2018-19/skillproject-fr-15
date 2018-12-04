package alexasescape.handlers;

import alexasescape.constants.SpeachText;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HangUpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HangUpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        final String speechText = SpeachText.HANG_UP;
        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);

        return input.getResponseBuilder()
                .withSimpleCard("Alexas Escape Menu", speechText)
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
