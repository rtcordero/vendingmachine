package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.entities.coins.Dime;
import domain.entities.coins.Nickel;
import domain.entities.coins.Pennie;
import domain.entities.coins.Quarters;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

  @Nested
  class AcceptCoins {

    @Test
    void shouldGetCurrentAmountOfOneCoin() {
      VendingMachine vendingMachine = new VendingMachine();
      Nickel nickel = new Nickel();
      Amount expected = nickel.getValue();

      vendingMachine.insertCoin(nickel);

      assertEquals(expected, vendingMachine.getAmountOfInsertedCoins());
    }

    @Test
    void shouldGetCurrentAmountOfSeveralSameCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      Nickel nickel = new Nickel();
      Amount expected = new Amount(0.10F);

      vendingMachine.insertCoin(nickel);
      vendingMachine.insertCoin(nickel);

      assertEquals(expected, vendingMachine.getAmountOfInsertedCoins());
    }

    @Test
    void shouldGetCurrentAmountOfSeveralDifferentCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      Nickel nickel = new Nickel();
      Dime dime = new Dime();
      Quarters quarters = new Quarters();
      Amount expected = new Amount(0.40F);

      vendingMachine.insertCoin(nickel);
      vendingMachine.insertCoin(dime);
      vendingMachine.insertCoin(quarters);

      assertEquals(expected.getValue(), vendingMachine.getAmountOfInsertedCoins().getValue());
    }

    @Test
    void shouldNotAllowInvalidCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      Pennie pennie = new Pennie();//TODO Value 0?
      Amount expected = Amount.empty(); //TODO Rename?

      vendingMachine.insertCoin(pennie);

      assertEquals(expected, vendingMachine.getAmountOfInsertedCoins());
    }
  }

  @Nested
  class Display {

    @Test
    void shouldDisplayNoCoinsMessageWhenHasNotCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      String expected = VendingMachine.NO_COINS_MESSAGE;

      String displayMessage = vendingMachine.getDisplayMessage();

      assertEquals(expected, displayMessage);
    }

    @Test
    void shouldDisplayNoCoinsMessageWhenHasInsertOnlyInvalidCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      Pennie pennie = new Pennie();
      String expected = VendingMachine.NO_COINS_MESSAGE;

      vendingMachine.insertCoin(pennie);

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }

    @Test
    void shouldDisplayCurrentAmountValueWhenHasCoins() {
      VendingMachine vendingMachine = new VendingMachine();
      Nickel nickel = new Nickel();
      Quarters quarters = new Quarters();
      Pennie pennie = new Pennie();
      String expected = "0,30 €";

      vendingMachine.insertCoin(nickel);
      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(pennie);

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }
  }

  @Nested
  class DisplaySelectProducts {

    @Test
    void shouldProductNotBeDispensedWhenHasNotEnoughMoney() {
      VendingMachine vendingMachine = new VendingMachine();
      Quarters quarters = new Quarters();
      Product chips = chipsProduct();
      String expected = "PRICE 0,50 €";

      vendingMachine.insertCoin(quarters);
      vendingMachine.requestProduct(chips);

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }

    @Test
    void shouldProductBeDispensedWhenCoinsValueExactlyCoversTheProductPrice() {
      VendingMachine vendingMachine = new VendingMachine();
      Quarters quarters = new Quarters();
      Product chips = chipsProduct();
      String expected = VendingMachine.PRODUCT_DISPENSED_MESSAGE;

      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(quarters);
      vendingMachine.requestProduct(chips);

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }

    @Test
    void shouldProductBeDispensedWhenCoinsValueExceedsTheProductPrice() {
      VendingMachine vendingMachine = new VendingMachine();
      Quarters quarters = new Quarters();
      Product chips = chipsProduct();
      String expected = VendingMachine.PRODUCT_DISPENSED_MESSAGE;

      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(quarters);
      vendingMachine.requestProduct(chips);

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }

    @Test
    void shouldFoo() {
      VendingMachine vendingMachine = new VendingMachine();
      Quarters quarters = new Quarters();
      Product chips = chipsProduct();
      String expected = VendingMachine.NO_COINS_MESSAGE;

      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(quarters);
      vendingMachine.insertCoin(quarters);
      vendingMachine.requestProduct(chips);
      vendingMachine.getDisplayMessage();

      assertEquals(expected, vendingMachine.getDisplayMessage());
    }

    private Product chipsProduct() {
      return new Product("Chips", new Amount(0.50F));
    }
  }
}