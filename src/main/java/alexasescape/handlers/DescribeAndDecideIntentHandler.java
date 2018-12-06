package alexasescape.handlers;

import alexasescape.constants.SpeechText;
import alexasescape.model.Game;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DescribeAndDecideIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        //anpassen
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        
        return input.getResponseBuilder()
                .withSpeech(SpeechText.CANCEL_OR_STOP)
                .withShouldEndSession(true)
                .build();
    }
}