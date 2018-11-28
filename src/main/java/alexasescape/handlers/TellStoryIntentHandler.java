package alexasescape.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.util.Optional;

import static alexasescape.handlers.RepeatIntentHandler.REPEAT_KEY;
import static com.amazon.ask.request.Predicates.intentName;

public class TellStoryIntentHandler implements RequestHandler {
    private static final String PLAYER_NAME_SLOT = "PlayerName";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MyNameIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final Request request = input.getRequestEnvelope().getRequest();
        final IntentRequest intentRequest = (IntentRequest) request;
        final Intent intent = intentRequest.getIntent();
        final Slot playerNameSlot = intent.getSlots().get(PLAYER_NAME_SLOT);
        final String speechText;

        if (playerNameSlot != null && playerNameSlot.getValue() != null) {
            final String playerName = playerNameSlot.getValue();
            speechText = "Gott sei Dank " + playerName + "! Du musst mir helfen! Ich wurde entfuehrt und in irgendein Hauses gesperrt! Ich bin in einem dunklen Raum. Ich sehe eine Kiste, einen Schrank und eine Tuer. Was soll ich tuen?";
        }
        else{
            speechText = "Ich habe Deinen Namen nicht verstanden";
        }
        input.getAttributesManager().getSessionAttributes().put(REPEAT_KEY, speechText);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
