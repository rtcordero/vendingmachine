package domain.entities.coins;

import domain.entities.Amount;

public class Pennie implements Coin {

  @Override
  public Amount getValue() {
    return new Amount(0.0F);
  }

}
