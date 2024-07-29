package domain.entities;

public class Product {

  public Amount getPrice() {
    return amount;
  }

  private final String name;
  private final Amount amount;

  public Product(String name, Amount amount) {
    this.name = name;
    this.amount = amount;
  }
}
