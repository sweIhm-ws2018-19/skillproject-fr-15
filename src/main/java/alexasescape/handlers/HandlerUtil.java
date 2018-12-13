package alexasescape.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerUtil {

    public static Optional<Response> concat(HandlerInput input, Optional<Response> prefix, Optional<Response> suffix) {
        Objects.requireNonNull(input, "Input must not be null!");
        Objects.requireNonNull(prefix, "Prefix must not be null!");
        Objects.requireNonNull(suffix, "Suffix must not be null!");

        final String speech = String.format("%s %s", extractSpeech(prefix), extractSpeech(suffix));

        boolean shouldEndSession = false;
        if (prefix.isPresent() && suffix.isPresent()) {
            shouldEndSession = prefix.get().getShouldEndSession() && suffix.get().getShouldEndSession();
        }

        return input.getResponseBuilder()
                .withSpeech(speech)
                .withReprompt(speech)
                .withShouldEndSession(shouldEndSession)
                .build();
    }

    public static String extractSpeech(Optional<Response> response) {
        Objects.requireNonNull(response, "Response must not be null!");

        if (response.isPresent()) {
            final Pattern pattern = Pattern.compile("<speak>.*?</speak>");
            final Matcher matcher = pattern.matcher(response.get().getOutputSpeech().toString());
            return matcher.find() ? matcher.group().replaceAll("<[/]?speak>", "") : "Da hat was beim concat nicht geklappt!";
        } else
            return "";
    }
}
