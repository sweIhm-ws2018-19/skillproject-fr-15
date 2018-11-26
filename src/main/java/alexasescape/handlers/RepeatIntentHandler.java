package alexasescape.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RepeatIntentHandler implements RequestHandler{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RepeatIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
        speechText = (String)attributes.get("Reprompt");

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();


    }

}
