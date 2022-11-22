package ReplyMarkupKeyboard;


import java.util.List;


public interface ReplyMarkupKeyboard <T> {

    List<T> getKeyboard();
    String getText();

}
