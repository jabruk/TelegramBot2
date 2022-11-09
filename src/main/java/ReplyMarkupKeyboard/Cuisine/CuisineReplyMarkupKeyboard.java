package ReplyMarkupKeyboard.Cuisine;

import Buttons.Button;
import ReplyMarkupKeyboard.ReplyMarkupKeyboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CuisineReplyMarkupKeyboard extends AbstractCuisineMarkupKeyboard {

    private String text;
    private List<List<Button>> keyboard;
    private List<Cuisine> inline_keyboard;
    private final String pathFile = "CuisineList.txt";


    public CuisineReplyMarkupKeyboard() {
        super("cuisine");
    }

    public CuisineReplyMarkupKeyboard(List<List<Button>> listOfButtonsName) throws IOException {
        super("cuisine");
        this.keyboard = listOfButtonsName;
        this.inline_keyboard = creatCuisineList();
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
