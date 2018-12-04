package alexasescape.constants;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.Map;
import java.util.Objects;

public enum Storage {
    SESSION, PERSISTENCE, REQUEST;

    public Map<String, Object> get(HandlerInput input) {
        Objects.requireNonNull(input, "Input to grab slot from must not be null!");
        final AttributesManager attributes = input.getAttributesManager();

        switch (this) {
            case SESSION:
                return attributes.getSessionAttributes();
            case PERSISTENCE:
                return attributes.getPersistentAttributes();
            case REQUEST:
                return attributes.getRequestAttributes();
            default:
                throw new IllegalArgumentException();
        }
    }
}
