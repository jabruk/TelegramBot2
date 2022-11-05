package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.List;

public class CuisineReplyMarkupKeyboard implements ReplyMarkupKeyboard{

    private String text;
    private List<List<Button>> keyboard;

    public CuisineReplyMarkupKeyboard() {
    }

    public CuisineReplyMarkupKeyboard(List<List<Button>> listOfButtonsName) {
        this.text = "cuisine";
        this.keyboard = listOfButtonsName;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<List<Button>> getKeyboard() {
        return keyboard;
    }

//    @Override
//    public List<List<Button>> keyBoardBuilder(List<String> list) {
//        List<List<Button>> buttons = new ArrayList<>();
//
//        buttons.add(new ArrayList<>());
//        buttons.get(0).add(new Button("По кухне"));
//        buttons.get(0).add(new Button("По популярности места"));
//
//        return buttons;
//    }


}
