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

import alexasescape.constants.CardsText;
import alexasescape.constants.SpeechText;
import alexasescape.constants.Storage;
import alexasescape.constants.StorageKey;
import alexasescape.constants.GameStatus;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String speechText = SpeechText.LAUNCH_GAME;
        StorageKey.REPEAT.put(input, Storage.SESSION, speechText);
        StorageKey.STATE.put(input, Storage.SESSION, GameStatus.MENU);
        return input.getResponseBuilder()
                .withSimpleCard(CardsText.LAUNCH_GAME, speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
