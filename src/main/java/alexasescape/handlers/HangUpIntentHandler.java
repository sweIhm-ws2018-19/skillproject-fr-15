

package alexasescape.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Collections;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HangUpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HangUpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Hallo. Moechtest Du das Spiel starten oder Deinen Highscore abfragen";
        String reprompt = "Starte das Spiel oder frage Deinen Highscore ab.";
        input.getAttributesManager().getSessionAttributes().put(REPROMPT_KEY, speechText);
        return input.getResponseBuilder()
                .withSimpleCard("Alexas Escape Menu", speechText)
                .withSpeech(speechText)
                .withReprompt(reprompt)
                .build();
    }
}