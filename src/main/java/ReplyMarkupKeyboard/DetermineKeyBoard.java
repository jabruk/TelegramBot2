package ReplyMarkupKeyboard;

import java.util.Map;

public class DetermineKeyBoard {
    public static ReplyMarkupKeyboard findKeyboard(Map<String,String> listOfKeyboards, String buttonsText){
        if(listOfKeyboards.containsKey(buttonsText)) {
            return FactoryKeyBoard.determineFactory(listOfKeyboards.get(buttonsText));
        }
        return new MainReplyMarkupKeyboard();
    }
    private static class FactoryKeyBoard {
        private static ReplyMarkupKeyboard determineFactory(String typeOfKeyboard) {
            if (typeOfKeyboard.equals("cuisine")) return new CuisineReplyMarkupKeyboard();
            else return new MainReplyMarkupKeyboard();

        }
    }
}
