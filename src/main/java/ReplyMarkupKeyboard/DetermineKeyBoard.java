package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.List;
import java.util.Map;

public class DetermineKeyBoard {
    public static ReplyMarkupKeyboard findKeyboard(Map<String,String> listOfKeyboards, Map<String, List<String>> listOfButtonsName , String buttonsText){
        if(listOfKeyboards.containsKey(buttonsText)) {
            return FactoryKeyBoard.determineFactory(listOfKeyboards.get(buttonsText), listOfButtonsName);
        }
        return new MainReplyMarkupKeyboard();
    }
    private static class FactoryKeyBoard {
        private static ReplyMarkupKeyboard determineFactory(String typeOfKeyboard, Map<String, List<String>> listOfButtonsName) {
            if (typeOfKeyboard.equals("cuisine")) return new CuisineReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("cuisine")));
            else return new MainReplyMarkupKeyboard(listOfButtonsName.get("main"));

        }
    }
}
