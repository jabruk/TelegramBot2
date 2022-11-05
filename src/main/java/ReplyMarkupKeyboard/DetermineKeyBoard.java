package ReplyMarkupKeyboard;

import Buttons.Button;

import java.util.List;
import java.util.Map;

public class DetermineKeyBoard {
    public static ReplyMarkupKeyboard findKeyboard(Map<String,String> listOfKeyboards, Map<String, List<String>> listOfButtonsName , String messageText){
        if(listOfKeyboards.containsKey(messageText)) {
            return FactoryKeyBoard.determineFactory(listOfKeyboards.get(messageText), listOfButtonsName);
        }
        return new MainReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("main")));
    }
    private static class FactoryKeyBoard {
        private static ReplyMarkupKeyboard determineFactory(String typeOfKeyboard, Map<String, List<String>> listOfButtonsName) {
            if (typeOfKeyboard.equals("cuisine")) return new CuisineReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("cuisine")));
            else return new MainReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("main")));

        }
    }
}
