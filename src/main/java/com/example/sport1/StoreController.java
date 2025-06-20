package com.example.sport1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StoreController {
    @FXML private ListView<String> productsList;
    @FXML private ListView<String> cartList;
    @FXML private Label totalLabel;

    @FXML
    public void initialize() {
        productsList.getItems().addAll(
                "Кроссовки - 2300 руб",
                "Гири - 500 руб",
                "Велосипед - 14500 руб",
                "Футбольный мяч - 1000 руб",
                "Спортивные штаны - 1100 руб"
        );
    }

    @FXML
    private void addToCart() {
        String selected = productsList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cartList.getItems().add(selected);
            updateTotal();
        }
    }

    @FXML
    private void checkout() {
        if (!cartList.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Покупка");
            alert.setHeaderText("Спасибо за покупку!");
            alert.setContentText("Сумма: " + totalLabel.getText());
            alert.showAndWait();
            cartList.getItems().clear();
            updateTotal();
        }
    }

    @FXML
    private void clearCart() {
        if (!cartList.getItems().isEmpty()) {
            // Создаем диалоговое окно для подтверждения
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Очистка корзины");
            alert.setHeaderText("Вы уверены, что хотите очистить корзину?");
            alert.setContentText("Все товары будут удалены.");

            // Ждем ответа пользователя
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                cartList.getItems().clear();
                updateTotal();
            }
        }
    }

    private void updateTotal() {
        double total = 0;
        for (String item : cartList.getItems()) {
            String priceStr = item.split(" - ")[1].replace(" руб", "");
            total += Double.parseDouble(priceStr);
        }
        totalLabel.setText(String.format("Итого: %.2f руб", total));
    }
}