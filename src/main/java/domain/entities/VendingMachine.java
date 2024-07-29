package domain.entities;

import java.util.ArrayList;

import domain.entities.coins.Coin;

public class VendingMachine {

  public static final String NO_COINS_MESSAGE = "INSERT COIN";

  public static final String PRODUCT_DISPENSED_MESSAGE = "THANK YOU";

  private final ArrayList<Coin> coins = new ArrayList<>();

  private String displayMessage = NO_COINS_MESSAGE;

  public void requestProduct(Product product) {
    if (getAmountOfInsertedCoins().isGreaterOrEqualThan(product.getPrice())) {
      coins.clear();
      setDisplayMessage(PRODUCT_DISPENSED_MESSAGE);
    } else {
      setDisplayMessage(getNotEnoughMoneyMessage(product));
    }
  }

  public void insertCoin(Coin coin) {
    this.coins.add(coin);
    if (getAmountOfInsertedCoins().isGreaterThanZero()) {
      setDisplayMessage(getAmountFormattedToDisplay(getAmountOfInsertedCoins()));
    } else {
      setDisplayMessage(NO_COINS_MESSAGE);
    }
  }

  public Amount getAmountOfInsertedCoins() {
    Amount currentAmount = Amount.empty();
    coins.forEach(
        coin -> currentAmount.sumAmount(coin.getValue())
    );
    return currentAmount;
  }

  public String getDisplayMessage() {
    return displayMessage;
  }

  public void setDisplayMessage(String displayMessage) {
    this.displayMessage = displayMessage;
  }

  private String getNotEnoughMoneyMessage(Product product) {
    return "PRICE " + getAmountFormattedToDisplay(product.getPrice());
  }

  private String getAmountFormattedToDisplay(Amount amount) {
    return String.format("%.02f", amount.getValue()) + " €";
  }
}

