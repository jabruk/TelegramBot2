package ReplyMarkupKeyboard.Cuisine;

import Buttons.Button;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CuisineReplyMarkupInlineKeyboard extends AbstractCuisineMarkupKeyboard{

    private String text;
    private List<Cuisine> inline_keyboard;
    private final String pathFile = "CuisineList.txt";

    public CuisineReplyMarkupInlineKeyboard() {
        super("list of cuisine");
    }

    @Override
    public List<List<Button>> getKeyboard() {
        return inline_keyboard;
    }

    @Override
    public String getText() {
        return text;
    }

    private List<Cuisine> creatCuisineList() throws IOException {
        return Files.readAllLines(Paths.get(pathFile)).stream()
                .map(p -> new Cuisine(p.trim()))
                .collect(Collectors.toList());

    }
}
