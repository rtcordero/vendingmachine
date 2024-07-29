package domain.entities.coins;

import domain.entities.Amount;

public class Nickel implements Coin {

  public Amount getValue() {
    return new Amount(0.05F);
  }
}
