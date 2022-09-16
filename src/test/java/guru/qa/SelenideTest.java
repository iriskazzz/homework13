package guru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {

  @Test
  void downloadTest() throws Exception {
    open("https://github.com/junit-team/junit5/blob/main/README.md");
    File file = $("#raw-url").download();
    try (InputStream is = new FileInputStream(file)) {
      byte[] fileContent = is.readAllBytes();
      assertThat(new String(fileContent, UTF_8)).contains("Contributions to JUnit 5");
    }
  }

  @Test
  void unloadTest() {
    open("https://the-internet.herokuapp.com/upload");
    $("input[type='file']").uploadFromClasspath("files/1.txt");
    $("#file-submit").click();
    $(".example").shouldHave(text("File Uploaded!")).shouldBe(visible);
  }
}
