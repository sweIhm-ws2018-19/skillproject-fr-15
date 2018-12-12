package alexasescape.handlers;

import alexasescape.constants.*;
import alexasescape.model.Game;
import alexasescape.model.Highscore;
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
        Optional<String> stateString = StorageKey.STATE.get(input, Storage.SESSION, String.class);
        if (stateString.map(GameStatus::valueOf).orElse(null) == GameStatus.PLAY) {

            final Optional<String> optionalPlayerName = Slots.PLAYER_NAME.value(input);

            if (optionalPlayerName.isPresent()) {
                final String playerName = optionalPlayerName.get();
                final Optional<Highscore> score = StorageKey.get(input, Storage.PERSISTENCE, playerName, Highscore.class);

                final Player player = new Player(playerName, score.orElse(new Highscore()));
                final Game game = Game.setUp(player);

                speechText = String.format(SpeechText.STORY, playerName).concat(game.getCurrentRoomDescription())
                        .concat(SpeechText.STORY_2);

                StorageKey.put(input, Storage.PERSISTENCE, playerName, player.getScore());
                StorageKey.GAME.put(input, Storage.SESSION, game);
            } else {
                speechText = SpeechText.NAME_WRONG;
            }
            StorageKey.REPEAT.put(input, Storage.SESSION, speechText);
        } else
            speechText = SpeechText.WRONG_HANDLER;

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .withReprompt(speechText)
                .build();
    }
}
