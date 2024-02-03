package me.grocery.grocerylist.ai;

/**
 * Generates prompts to a user based on their answer to an initial question.
 * The prompts are intended to refine the grocery list or meal plan the user is seeking to create.
 */
public class PromptConstructor {
    private String initialPrompt;
    private String initialAnswer;

    /**
     * @param initialPrompt the initial question asked to the user regarding meal plan
     * @param initialAnswer the answer to the initial question
     */
    public PromptConstructor(String initialPrompt, String initialAnswer) {
        this.initialPrompt = initialPrompt;
        this.initialAnswer = initialAnswer;
    }

    /**
     * @return the initial prompt
     */
    public String getInitialPrompt() {
        return initialPrompt;
    }

    /**
     * @return the answer to the initial prompt
     */
    public String getInitialAnswer() {
        return initialAnswer;
    }
}
