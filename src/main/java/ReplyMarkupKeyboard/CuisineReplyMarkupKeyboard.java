package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class CuisineReplyMarkupKeyboard implements ReplyMarkupKeyboard{

    private String text;
    private List<List<Button>> keyboard;

    public CuisineReplyMarkupKeyboard() {
        this.text = "cuisine";
        this.keyboard = keyBoardBuilder();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<List<Button>> keyBoardBuilder() {
        List<List<Button>> buttons = new ArrayList<>();

        buttons.add(new ArrayList<>());
        buttons.get(0).add(new Button("По кухне"));
        buttons.get(0).add(new Button("По популярности места"));

        return buttons;
    }

    @Override
    public List<List<Button>> getKeyboard() {
        return keyboard;
    }
}
