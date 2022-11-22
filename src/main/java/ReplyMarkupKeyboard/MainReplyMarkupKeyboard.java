package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class MainReplyMarkupKeyboard extends ButtonsBuilder{
    public MainReplyMarkupKeyboard() {
        super("Вас приветствует БОТ путеводитель по г.Алматы. Выберите желаемую опцию");

    }

    public MainReplyMarkupKeyboard(List<List<Button>> listOfButtonsName) {
        super(listOfButtonsName,"Вас приветствует БОТ путеводитель по г.Алматы. Выберите желаемую опцию");
    }
}