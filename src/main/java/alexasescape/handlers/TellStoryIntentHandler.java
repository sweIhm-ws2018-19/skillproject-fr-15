package alexasescape.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static alexasescape.handlers.RepeatIntentHandler.REPROMPT_KEY;
import static com.amazon.ask.request.Predicates.intentName;

public class TellStoryIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MyNameIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Gott sei Dank! Du musst mir helfen! Ich wurde entführt und in irgendein Hauses gesperrt! Ich bin in einem dunklen Raum. Ich sehe eine Kiste, einen Schrank und eine Tür. Was soll ich tuen?";
        input.getAttributesManager().getSessionAttributes().put(REPROMPT_KEY, speechText);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
