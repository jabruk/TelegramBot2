package Buttons;

public class Button {
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
}
