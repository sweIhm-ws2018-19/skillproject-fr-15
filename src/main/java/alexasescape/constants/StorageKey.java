package alexasescape.constants;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public enum StorageKey {
    REPEAT("Repeat"), GAME("Game"), STATE("Game-State");

    private final String key;

    StorageKey(String key) {
        this.key = key;
    }

    public static void put(HandlerInput input, Storage storage, String key, Object value) {
        validateKeyAndInputAndStorage(key, input, storage);
        Objects.requireNonNull(value, "Value to put in target map must not be null!");

        storage.get(input).put(key, value);
    }

    public static <T> Optional<T> get(HandlerInput input, Storage storage, String key, Class<T> clazz) {
        validateKeyAndInputAndStorage(key, input, storage);
        Objects.requireNonNull(clazz, "Class that should be read from source map must not be null!");

        Object retVal = storage.get(input).get(key);
        if (Objects.nonNull(retVal)) {
            try {
                return Optional.of(new ObjectMapper().convertValue(retVal, clazz));
            } catch (IllegalArgumentException e) {
                final String jsonVal = retVal.toString();
                try {
                    return Optional.of(new ObjectMapper().readValue(jsonVal, clazz));
                } catch (IOException e1) {
                    throw new IllegalStateException(e1);
                }
            }
        } else {
            return Optional.empty();
        }

    }

    public String getKey() {
        return key;
    }

    public void put(HandlerInput input, Storage storage, Object value) {
        put(input, storage, key, value);
    }

    public <T> Optional<T> get(HandlerInput input, Storage storage, Class<T> clazz) {
        return get(input, storage, key, clazz);
    }

    private static void validateKeyAndInputAndStorage(String key, HandlerInput input, Storage storage) {
        Objects.requireNonNull(key, "Key must not be null!");
        Objects.requireNonNull(input, "Input must not be null!");
        Objects.requireNonNull(storage, "Storage must not be null!");
    }
}
