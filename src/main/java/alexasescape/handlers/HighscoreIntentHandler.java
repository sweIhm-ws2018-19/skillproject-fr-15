/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package alexasescape.handlers;

import alexasescape.constants.*;
import alexasescape.model.Highscore;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HighscoreIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HighscoreIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText;
        boolean isAskResponse = false;

        final Optional<String> optionalPlayerName = Slots.PLAYER_NAME.value(input);

        // Check for player name and create output to user.
        if (optionalPlayerName.isPresent()) {

            // Read score from player
            final String playerName = optionalPlayerName.get();
            final Optional<Highscore> score = StorageKey.get(input, Storage.PERSISTENCE, playerName, Highscore.class);

            speechText = "Hey " + playerName + "! ";
            speechText += score.orElse(new Highscore()).toString();
        } else {
            speechText = SpeechText.WHOS_HIGHSCORE;
            isAskResponse = true;
        }

        // Put repeat key
        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);

        // Build response
        final ResponseBuilder responseBuilder = input.getResponseBuilder();
        responseBuilder.withSimpleCard(CardsText.HIGHSCORE, speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder
                    .withShouldEndSession(false)
                    .withReprompt(Reprompts.HIGHSCORE);
        }

        return responseBuilder.build();
    }
}
