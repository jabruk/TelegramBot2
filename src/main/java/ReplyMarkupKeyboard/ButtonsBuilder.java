package ReplyMarkupKeyboard;

import java.util.List;

public abstract class ButtonsBuilder<T> implements ReplyMarkupKeyboard{
    private List<T> keyboard;
    private String text;

    public ButtonsBuilder(List<T> keyboard, String text) {
        this.keyboard = keyboard;
        this.text = text;
    }

    public ButtonsBuilder(List<T> keyboard) {
        this.keyboard = keyboard;
    }

    public ButtonsBuilder(String text) {
        this.text = text;
    }

    @Override
    public List<T> getKeyboard() {
        return this.keyboard;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
