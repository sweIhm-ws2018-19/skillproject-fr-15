package alexasescape.handlers;

import alexasescape.constants.Slots;
import alexasescape.constants.SpeechText;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class TellStoryIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MyNameIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        final String speechText;
        final Optional<String> optionalPlayerName = Slots.PLAYER_NAME.value(input);

        if (optionalPlayerName.isPresent()) {
            final String playerName = optionalPlayerName.get();
            speechText = String.format(SpeechText.STORY, playerName);
        }
        else{
            speechText = "Ich habe Deinen Namen nicht verstanden";
        }

        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
