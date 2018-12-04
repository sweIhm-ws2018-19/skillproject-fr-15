package alexasescape.handlers;

import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RepeatIntentHandler implements RequestHandler{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RepeatIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        final String speechText = StorageKey.REPEAT.get(input, Storage.SESSION, String.class)
                .orElse("Ich habe nichts, dass ich fuer dich wiederholen koennte!");

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }

}
