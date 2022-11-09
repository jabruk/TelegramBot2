package ReplyMarkupKeyboard;

import Buttons.Button;
import ReplyMarkupKeyboard.Cuisine.Cuisine;

import java.util.List;


public interface ReplyMarkupKeyboard <T> {

    List<T> getKeyboard();
    String getText();

}
