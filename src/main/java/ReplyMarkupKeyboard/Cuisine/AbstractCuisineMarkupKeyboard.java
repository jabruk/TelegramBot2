package ReplyMarkupKeyboard.Cuisine;

import ReplyMarkupKeyboard.ReplyMarkupKeyboard;

public abstract class AbstractCuisineMarkupKeyboard implements ReplyMarkupKeyboard {
    private String text;

    public AbstractCuisineMarkupKeyboard(String text) {
        this.text = "cuisine";

    }
}
