package org.example.java_lab_4;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;
import java.util.function.UnaryOperator;

public class Filter {
    @NotNull
    public static TextFormatter<String> numericFilter(int max) {
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) {
                if ((newText.isEmpty() || Double.parseDouble(newText) <= max) && newText.length() < 15) {
                    return change;
                }
            }
            return null;
        };
        return new TextFormatter<>(numberFilter);
    }

    public static void StringFilter(@NotNull TextField text, int lenth) {
        text.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();

            if (!character.matches("[a-zA-Zа-яА-ЯёЁ\\s]*") || (text.getText().length() > lenth)) {
                event.consume();
            }
        });
    }
}
