package alexasescape.handlers;

import alexasescape.constants.*;
import alexasescape.model.Game;
import alexasescape.model.Player;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class TellStoryIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("MyNameIsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String speechText;
        if(StorageKey.STATE.get(input,Storage.SESSION, GameStatus.class).orElse(null) == GameStatus.PLAY) {

            final Optional<String> optionalPlayerName = Slots.PLAYER_NAME.value(input);

            if (optionalPlayerName.isPresent()) {
                final String playerName = optionalPlayerName.get();

                Player player = new Player(playerName);
                Game game = Game.setUp(player);

                speechText = String.format(SpeechText.STORY, playerName).concat(game.getCurrentRoomDescription())
                        .concat(SpeechText.STORY_2);
                StorageKey.GAME.put(input, Storage.SESSION, game);
            } else {
                speechText = SpeechText.NAME_WRONG;
            }
            StorageKey.REPEAT.put(input, Storage.SESSION, speechText);
        }
        else
            speechText = SpeechText.NOT_POSSIBLE;

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
