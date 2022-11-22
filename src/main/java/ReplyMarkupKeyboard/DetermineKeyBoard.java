package ReplyMarkupKeyboard;

import Buttons.Button;
import ReplyMarkupKeyboard.Cuisine.CuisineReplyMarkupKeyboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DetermineKeyBoard {
    public static ReplyMarkupKeyboard findKeyboard(Map<String,String> listOfKeyboards, Map<String, List<String>> listOfButtonsName , String messageText) throws IOException {
        if(listOfKeyboards.containsKey(messageText)) {
            return FactoryKeyBoard.determineFactory(listOfKeyboards.get(messageText), listOfButtonsName);
        }
        return new MainReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("main")));
    }
    private static class FactoryKeyBoard {
        private static ReplyMarkupKeyboard determineFactory(String typeOfKeyboard, Map<String, List<String>> listOfButtonsName) throws IOException {
            if (typeOfKeyboard.equals("cuisine")) return new CuisineReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("cuisine")));
            else if (typeOfKeyboard.equals("cuisineList")) return new CuisineReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("cuisineList")));
            else return new MainReplyMarkupKeyboard(Button.createButtonList(listOfButtonsName.get("main")));

        }
    }
}
