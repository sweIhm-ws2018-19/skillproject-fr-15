package alexasescape.constants;

import alexasescape.handlers.TestUtil;
import alexasescape.model.Item;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class StorageKeyTest {

    private final static String ITEM_KEY = "Item";
    private final static Item ITEM_VALUE = new Item("ItemName", "ItemDescription", false);
    private final static String STRING_KEY = "AnyKey";
    private final static String STRING_VALUE = "AnyValue";

    // Spies
    private Map<String, Object> sessionAttributesSpy;

    // Mocks
    private HandlerInput inputMock;

    @Before
    public void setup() {
        sessionAttributesSpy = Mockito.spy(
                new HashMap<String, Object>() {{
                    put(STRING_KEY, STRING_VALUE);
                    put(ITEM_KEY, ITEM_VALUE);
                }}
        );

        inputMock = TestUtil.mockHandlerInput("Peter", sessionAttributesSpy, null, null);
    }

    @Test
    public void verify_getStringKey_onSessionAttributes_whenValidGet() {
        // Call under test
        final Optional<String> have = StorageKey
                .get(inputMock, Storage.SESSION, STRING_KEY, String.class);

        // Verify interactions
        verify(sessionAttributesSpy).get(eq(STRING_KEY));
        verifyNoMoreInteractions(sessionAttributesSpy);
        verify(inputMock, times(1)).getAttributesManager();

        // Assert result
        assertTrue(have.isPresent());
        assertEquals(STRING_VALUE, have.get());
    }

    @Test
    public void verify_getItemKey_onSessionAttributes_whenValidGet() {
        // Call under test
        final Optional<Item> have = StorageKey
                .get(inputMock, Storage.SESSION, ITEM_KEY, Item.class);

        // Verify interactions
        verify(sessionAttributesSpy).get(eq(ITEM_KEY));
        verifyNoMoreInteractions(sessionAttributesSpy);
        verify(inputMock, times(1)).getAttributesManager();

        // Assert result
        assertTrue(have.isPresent());
        assertEquals(ITEM_VALUE, have.get());
    }


}
