package alexasescape.handlers;

import alexasescape.constants.*;
import alexasescape.model.Game;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

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
        Optional<String> stateString = StorageKey.STATE.get(input, Storage.SESSION, String.class);
        if (stateString.map(GameStatus::valueOf).orElse(null) == GameStatus.PLAY) {
            final Optional<String> optionalItemName = Slots.ITEM_NAME.value(input);
            final String itemName;
            String repeatText = SpeechText.ERROR;

            if (optionalItemName.isPresent()) {
                itemName = optionalItemName.get();
                Optional<Game> optionalGame = StorageKey.GAME.get(input, Storage.SESSION, Game.class);
                if (optionalGame.isPresent()) {
                    Game game = optionalGame.get();
                    speechText = game.nextTurn(itemName);
                    StorageKey.GAME.put(input, Storage.SESSION, game);
                    repeatText = game.getCurrentRoomDescription();
                    if (game.isWon() || game.isLost()) {
                        StorageKey.STATE.put(input, Storage.SESSION, GameStatus.FINISHED);
                        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);
                        return new FinishGameIntentHandler().handle(input);
                    }
                } else
                    speechText = SpeechText.NO_GAME;
            } else
                speechText = SpeechText.NO_ITEM;

            StorageKey.REPEAT.put(input, Storage.SESSION, repeatText);
        } else
            speechText = SpeechText.WRONG_HANDLER;

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
