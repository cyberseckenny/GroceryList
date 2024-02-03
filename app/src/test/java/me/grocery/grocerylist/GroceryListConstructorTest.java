package me.grocery.grocerylist;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import me.grocery.grocerylist.ai.GroceryListConstructor;

/**
 * Tests the GroceryListConstructor class.
 */
public class GroceryListConstructorTest {
    private final String INITIAL_PROMPT = "What kind of meal plan or diet are you looking to create?";
    private final String INITIAL_ANSWER = "I am looking for a diet that allows me to lose weight " +
            "while still having energy to function during the day.";

    /**
     * Ensures follow up questions actually are generated.
     */
    @Test
    public void followUpQuestions_doesGenerate() {
        GroceryListConstructor groceryListConstructor = new GroceryListConstructor(INITIAL_PROMPT
                , INITIAL_ANSWER);

        assertDoesNotThrow(() -> {
            groceryListConstructor.followUpQuestions().forEach(System.out::println);
        });
    }
}
