package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class MainReplyMarkupKeyboard implements ReplyMarkupKeyboard{

    private String text;
    private List<List<Button>> keyboard;

    public MainReplyMarkupKeyboard() {
    }

    public MainReplyMarkupKeyboard(List<String> listOfButtonsName) {
        text = "Вас приветствует БОТ путеводитель по г.Алматы. Выберите желаемую опцию";
        this.keyboard = Button.createButtonList(listOfButtonsName);
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
//        buttons.get(0).add(new Button("Поесть"));
//        buttons.get(0).add(new Button("Прогуляться"));
//
//        buttons.add(new ArrayList<>());
//        buttons.get(1).add(new Button("Повеселиться"));
//        buttons.get(1).add(new Button("Посетить"));
//        return buttons;
//    }
}