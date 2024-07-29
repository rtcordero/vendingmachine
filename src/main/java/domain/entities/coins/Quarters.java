package domain.entities.coins;

import domain.entities.Amount;

public class Quarters implements Coin {

  @Override
  public Amount getValue() {
    return new Amount(0.25F);
  }

}
