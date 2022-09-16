package guru.qa.domain;

public class House {
  private Integer houseNumber;
  private Integer square;
  private String city;
  private Integer price;

  public Integer getHouseNumber() {
    return houseNumber;
  }
  public void setHouseNumber(Integer houseNumber) {
    this.houseNumber = houseNumber;
  }

  public Integer getSquare() {
    return square;
  }
  public void setSquare(Integer square) {
    this.square = square;
  }

  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  public Integer getPrice() {
    return price;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }
}
