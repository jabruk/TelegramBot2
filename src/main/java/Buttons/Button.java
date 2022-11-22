package Buttons;

import java.util.ArrayList;
import java.util.List;

public class Button<T> {
    private String text;
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

    public static List<List<Button>> createButtonList(List<String> list,int split) {
        List<List<Button>> resultButtonList = new ArrayList<>();
            int row = 0,indexOfRow = -1;
            for (String nameOfButton : list) {
                if(row % split == 0) {
                    resultButtonList.add(new ArrayList<>());
                    indexOfRow++;
                }
                resultButtonList.get(indexOfRow).add(new Button(nameOfButton));
                row++;
            }
        return resultButtonList;
    }
}
