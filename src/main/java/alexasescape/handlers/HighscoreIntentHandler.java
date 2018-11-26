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

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Collections;
import java.util.Optional;

import static alexasescape.handlers.RepeatIntentHandler.REPROMPT_KEY;
import static com.amazon.ask.request.Predicates.intentName;

public class HighscoreIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HighscoreIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Dein Highscore lautet:...";
        input.getAttributesManager().setSessionAttributes(Collections.singletonMap(REPROMPT_KEY, speechText));
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
