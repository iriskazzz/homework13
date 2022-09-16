package guru.qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties ({"optionalField"})
@JsonInclude(NON_NULL) //включать только не нулевые поля
public class Teacher {

  private String name;
  private Boolean isGoodTeacher;
  private Integer age;
  private Passport passport;

  @JsonIgnore
  private String optionalField;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("isGoodTeacher")
  public Boolean isGoodTeacher() {
    return isGoodTeacher;
  }

  @JsonProperty("isGoodTeacher")
  public void setGoodTeacher(Boolean goodTeacher) {
    isGoodTeacher = goodTeacher;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Passport getPassport() {
    return passport;
  }

  public void setPassport(Passport passport) {
    this.passport = passport;
  }

  public static class Passport {
    private Integer number;

    public Integer getNumber() {
      return number;
    }

    public void setNumber(Integer number) {
      this.number = number;
    }
  }
}