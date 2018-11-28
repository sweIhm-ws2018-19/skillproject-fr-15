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

import alexasescape.model.Highscore;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static alexasescape.handlers.RepeatIntentHandler.REPEAT_KEY;
import static com.amazon.ask.request.Predicates.intentName;

public class HighscoreIntentHandler implements RequestHandler {

    private static final String PLAYER_NAME_SLOT = "PlayerName";
    private static final String REPROMPT_STRING = "Du kannst zum Beispiel nach dem Highscore von Tim fragen, indem du fragst, wie ist der Highscore für Tim?";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HighscoreIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        final Request request = input.getRequestEnvelope().getRequest();
        final IntentRequest intentRequest = (IntentRequest) request;
        final Intent intent = intentRequest.getIntent();

        // Get the playerName slot from the list of slots.
        final Slot playerNameSlot = intent.getSlots().get(PLAYER_NAME_SLOT);

        String speechText;
        String repromptText;
        boolean isAskResponse = false;

        // Check for player name and create output to user.
        if (playerNameSlot != null) {
            // Try to read score from player
            final String playerName = playerNameSlot.getValue();

            final Object scoreObject = input.getAttributesManager().getPersistentAttributes().get(playerName);
            if (scoreObject != null) {
                final Highscore highscore = new ObjectMapper().convertValue(scoreObject, Highscore.class);
                speechText =
                        String.format("%s hat insgesamt %d Spiele erfolgreich beendet. " +
                                "Fuer den besten Versuch hat er %d Minuten und %d Sekunden gebraucht!", playerName, highscore.getTotalGames(), highscore.getMinutes(), highscore.getSeconds());
                repromptText = REPROMPT_STRING;
            } else {
                speechText =
                        String.format("Ich konnte keinen Highscore fuer %s finden.", playerName);
                repromptText = REPROMPT_STRING;
                isAskResponse = true;
            }

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "Ich weiß nicht fuer wen ich den Highscore nachschlagen soll?";
            repromptText = REPROMPT_STRING;
            isAskResponse = true;
        }

        input.getAttributesManager().getSessionAttributes().put(REPEAT_KEY, speechText);


        final ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("Highscore", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }
}
