package domain.entities.coins;

import domain.entities.Amount;

public class Dime implements Coin {

  @Override
  public Amount getValue() {
    return new Amount(0.10F);
  }
}
