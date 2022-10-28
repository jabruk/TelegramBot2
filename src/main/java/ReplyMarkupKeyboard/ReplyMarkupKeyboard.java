package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.List;


public interface ReplyMarkupKeyboard {

    List<List<Button>> keyBoardBuilder();
    List<List<Button>> getKeyboard();
    String getText();
}
