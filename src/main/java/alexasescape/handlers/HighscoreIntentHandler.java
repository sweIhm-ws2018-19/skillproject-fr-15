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

import static alexasescape.handlers.RepeatIntentHandler.REPROMPT_KEY;
import static com.amazon.ask.request.Predicates.intentName;

public class HighscoreIntentHandler implements RequestHandler {

    public static final String PLAYER_NAME_SLOT = "PlayerName";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HighscoreIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();

        // Get the playerName slot from the list of slots.
        Slot playerNameSlot = intent.getSlots().get(PLAYER_NAME_SLOT);

        String speechText, repromptText;
        boolean isAskResponse = false;

        // Check for player name and create output to user.
        if (playerNameSlot != null) {
            // Try to read score from player
            String playerName = playerNameSlot.getValue();

            Object scoreObject = input.getAttributesManager().getPersistentAttributes().get(playerName);
            if (scoreObject != null) {
                final Highscore highscore = new ObjectMapper().convertValue(scoreObject, Highscore.class);
                speechText =
                        String.format("%s hat insgesamt %d Spiele erfolgreich beendet. " +
                                "Für den besten Versuch hat er %d Minuten und %d Sekunden gebraucht!", playerName, highscore.minutes, highscore.seconds);
                repromptText = "Du kannst zum Beispiel nach dem Highscore von Tim fragen, indem du frägst, wie ist der Highscore für Tim?";
            } else {
                speechText =
                        String.format("Ich konnte keinen Highscore für %s finden.", playerName);
                repromptText =
                        "Du kannst zum Beispiel nach dem Highscore von Tim fragen, indem du frägst, wie ist der Highscore für Tim?";
                isAskResponse = true;
            }

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "Ich weiß nicht für wen ich den Highscore nachschlagen soll?";
            repromptText = "Du kannst zum Beispiel nach dem Highscore von Tim fragen, indem du frägst, wie ist der Highscore für Tim?";
            isAskResponse = true;
        }

        input.getAttributesManager().getSessionAttributes().put(REPROMPT_KEY , speechText);


        ResponseBuilder responseBuilder = input.getResponseBuilder();

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
