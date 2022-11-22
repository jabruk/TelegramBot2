package ReplyMarkupKeyboard.Cuisine;

import Buttons.Button;
import ReplyMarkupKeyboard.ButtonsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CuisineReplyMarkupKeyboard extends ButtonsBuilder {

    public CuisineReplyMarkupKeyboard() {
        super("Cuisine");
    }

    public CuisineReplyMarkupKeyboard(List<List<Button>> listOfButtonsName) throws IOException {
        super(listOfButtonsName, "Cuisine");
    }
}
