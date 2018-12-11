package alexasescape.handlers;

import alexasescape.constants.CardsText;
import alexasescape.constants.SpeechText;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import alexasescape.constants.GameStatus;
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
        final String speechText;
//        if(StorageKey.STATE.get(input,Storage.SESSION, GameStatus.class).orElse(null) == GameStatus.PLAY) {
             speechText = SpeechText.HANG_UP;
            StorageKey.REPEAT.put(input, Storage.SESSION, speechText);
            StorageKey.STATE.put(input,Storage.SESSION, GameStatus.MENU);
//        }
//        else
//            speechText = SpeechText.WRONG_HANDLER;

        return input.getResponseBuilder()
                .withSimpleCard(CardsText.MENU, speechText)
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
