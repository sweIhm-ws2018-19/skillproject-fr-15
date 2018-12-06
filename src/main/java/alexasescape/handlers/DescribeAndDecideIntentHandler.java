package alexasescape.handlers;

import alexasescape.constants.Slots;
import alexasescape.model.Game;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DescribeAndDecideIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("DescribeAndDecideIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Was laberscht du?";
        final Optional<String> optionalItemName = Slots.ItemName.value(input);

        Map<String, Object> map = input.getAttributesManager().getSessionAttributes();
        Object gameObj = map.get("game");

        if (optionalItemName.isPresent()) {
            try {
                Game game = (Game) gameObj;
                speechText = game.nextTurn(optionalItemName.get());
            } catch (ClassCastException e) {
                System.out.println("Failed to cast the game...");
                e.printStackTrace();
            }
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(true)
                .build();
    }
}