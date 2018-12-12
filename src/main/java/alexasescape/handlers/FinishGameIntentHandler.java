package alexasescape.handlers;

import alexasescape.constants.GameStatus;
import alexasescape.constants.SpeechText;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import alexasescape.model.Game;
import alexasescape.model.Highscore;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

public class FinishGameIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        Optional<String> stateString = StorageKey.STATE.get(input, Storage.SESSION, String.class);
        return stateString.isPresent() && stateString.map(GameStatus::valueOf).orElse(null) == GameStatus.FINISHED;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = SpeechText.NO_GAME;
        Optional<Game> optionalGame = StorageKey.GAME.get(input, Storage.SESSION, Game.class);

        if (optionalGame.isPresent()) {
            final Game game = optionalGame.get();
            final String playerName = game.getPlayer().getName();
            final Highscore lastPlayerScore = game.getPlayer().getScore();

            // Calc duration of current game
            final Duration duration = Duration.between(game.getStartTime().toInstant(), new Date().toInstant());
            final long minutes = duration.toMinutes();
            final long seconds = duration.minusMinutes(minutes).getSeconds();

            // Game won
            if (game.isWon()) {
                lastPlayerScore.winGame((int) minutes, (int) seconds);
                speechText = String.format(SpeechText.GAME_FINISHED_AND_WON, minutes, seconds) + lastPlayerScore.toString();
            }
            // Game lost
            else {
                lastPlayerScore.looseGame();
                speechText = String.format(SpeechText.GAME_FINISHED_AND_LOST, minutes, seconds) + lastPlayerScore.toString();
            }
            StorageKey.put(input, Storage.PERSISTENCE, playerName, lastPlayerScore);
        }

        speechText += SpeechText.LAUNCH_GAME;
        StorageKey.STATE.put(input, Storage.SESSION, GameStatus.MENU);
        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
