package Buttons;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private String text;
    private static final int maxAllowedButtons = 6;
    public Button(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        Button button = (Button) obj;
        return this.text.equals(button.getText());
    }

    public static List<List<Button>> createButtonList(List<String> list) {
        List<List<Button>> resultButtonList = new ArrayList<>();
        if(list.size() <= maxAllowedButtons) {
            int row = 0,indexOfRow = -1;
            for (String nameOfButton : list) {
                if(row % 2 == 0) {
                    resultButtonList.add(new ArrayList<>());
                    indexOfRow++;
                }
                resultButtonList.get(indexOfRow).add(new Button(nameOfButton));
                row++;
            }
        }
        return resultButtonList;
    }
}
