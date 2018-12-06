/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at
         http://aws.amazon.com/apache2.0/
     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package alexasescape;

import alexasescape.handlers.*;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import alexasescape.handlers.LaunchRequestHandler;
import alexasescape.handlers.HangUpIntentHandler;
import alexasescape.handlers.RepeatIntentHandler;

import alexasescape.handlers.CancelOrStopIntentHandler;
import alexasescape.handlers.SessionEndedRequestHandler;


public class AlexasEscapeStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new RepeatIntentHandler(),
                        new HighscoreIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new CancelOrStopIntentHandler(),
						new HangUpIntentHandler(),
                        new StartIntentHandler(),
                        new TellStoryIntentHandler(),
                        new DescribeAndDecideIntentHandler()
                )
                .withTableName("escapeData")
                .withAutoCreateTable(true)
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public AlexasEscapeStreamHandler() {
        super(getSkill());
    }

}