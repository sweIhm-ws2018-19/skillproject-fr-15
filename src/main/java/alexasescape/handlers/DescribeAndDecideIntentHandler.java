package alexasescape.handlers;

import alexasescape.constants.Slots;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
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
        return input.matches(intentName("DescribeAndDecideIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        final String speechText;
        Optional<Game> optionalGame = StorageKey.GAME.get(input, Storage.SESSION, Game.class);
        if(optionalGame.isPresent())
            speechText="test succeeded";
        else
            speechText="test failed";

        /*
        if (optionalItemName.isPresent()) {
                Game game = (Game) gameObj;
                speechText = game.nextTurn(optionalItemName.get());
        }*/

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}