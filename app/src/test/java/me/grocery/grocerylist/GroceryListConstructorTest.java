package me.grocery.grocerylist;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import me.grocery.grocerylist.ai.GroceryListConstructor;

/**
 * Tests the GroceryListConstructor class.
 */
public class GroceryListConstructorTest {
    private final String INITIAL_PROMPT = "What kind of meal plan or diet are you looking to create?";
    private final String INITIAL_ANSWER = "I am looking for a diet that allows me to lose weight " +
            "while still having energy to function during the day.";
    private final List<String> QUESTIONS = Arrays.asList("What is your target daily caloric " +
            "intake for " +
            "weight loss while maintaining energy levels?", "Are you inclined towards a specific " +
            "macronutrient distribution, such as higher protein, lower carbs, or balanced " +
            "proportions?", "How many meals and snacks per day do you prefer, and are there " +
            "specific times that work best for your eating schedule?", "Are there specific foods " +
            "you enjoy or dislike?", "Can you share details about your current physical activity " +
            "level and any planned exercise routines?");
    private final List<String> ANSWERS = Arrays.asList("2000 calories", "I enjoy balanced " +
                    "proportions",
            "I prefer 3 meals a day with minimal snacks. I like to eat at 7:30am, 11:00pm, and " +
                    "5:00pm", "I do not like salad at all, but I love vegetables and fruits. Meat" +
                    " that isn't lean makes my stomach hurt as well as fried food.", "I go on " +
                    "many walks each day, probably with a minimum of about an hour.");

    /**
     * Ensures follow up questions actually are generated.
     */
    @Test
    public void followUpQuestions_doesGenerate() {
        GroceryListConstructor groceryListConstructor = new GroceryListConstructor(INITIAL_PROMPT
                , INITIAL_ANSWER);
        List<String> questions = groceryListConstructor.followUpQuestions();

        log("Initial Prompt", INITIAL_PROMPT);
        log("Initial Answer", INITIAL_ANSWER);

        questions.forEach(question -> log("Questions", question));

        assertNotEquals(0, questions.size(), "The questions should not be empty.");
    }

    /**
     * Ensures the grocery list generation functions.
     */
    @Test
    public void groceryGeneration_doesGenerate() {
        GroceryListConstructor groceryListConstructor = new GroceryListConstructor(INITIAL_PROMPT
                , INITIAL_ANSWER);
        JSONObject jsonObject = groceryListConstructor.generateGroceryList(QUESTIONS, ANSWERS);

        log("Initial Prompt", INITIAL_PROMPT);
        log("Initial Answer", INITIAL_ANSWER);

        QUESTIONS.forEach(question -> log("Questions", question));
        ANSWERS.forEach(answer -> log("Answers", answer));

        try {
            log("JSON", jsonObject.toString(2));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(String a, String b) {
        System.out.println(a + ": " + b);
    }
}
