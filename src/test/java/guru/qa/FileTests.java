package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.domain.House;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class FileTests {

  ClassLoader classLoader = FileTests.class.getClassLoader();
  String archiveName = "files/docs.zip";
  String xlsFileName = "docs/Report.xlsx";
  String pdfFileName = "docs/OutDocs.pdf";
  String csvFileName = "docs/House.csv";
  String jsonFileName = "docs/house.json";

  @Test
  void pdfTest() throws Exception {
    InputStream pdfFileStream = getFile(archiveName, pdfFileName);
    PDF pdf = new PDF(pdfFileStream);
    assertThat(pdf.text).contains("выписка из ЕГРН о переходе прав на объект\n" +
            "недвижимости");
    closeInputStream(pdfFileStream);
  }

  @Test
  void xlsxFileTest() throws Exception {
    InputStream xlsFileStream = getFile(archiveName, xlsFileName);
    XLS xls = new XLS(xlsFileStream);
    assertThat(
            xls.excel
                    .getSheetAt(1)
                    .getRow(0)
                    .getCell(5)
                    .getStringCellValue()).contains("Госпошлина (руб)");
    closeInputStream(xlsFileStream);
  }

  @Test
  void csvTest() throws Exception {
    InputStream csvFileStream = getFile(archiveName, csvFileName);
    CSVReader csvReader = new CSVReader(new InputStreamReader(csvFileStream, UTF_8));
    List<String[]> csv = csvReader.readAll();
    assertThat(csv).contains(
            new String[]{"2;124;Kirov;5430"});
    closeInputStream(csvFileStream);
  }

  @Test
  void jsonTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    InputStream jsonFileStream = getFile(archiveName, jsonFileName);
    House[] jsonObject = mapper.readValue(jsonFileStream, House[].class);
    assertThat(jsonObject[1].getCity()).isEqualTo("Kirov");
    assertThat(jsonObject[1].getPrice()).isEqualTo(5430);
  }

  private InputStream getFile(String archiveName, String fileName) throws Exception {
    URL url = classLoader.getResource(archiveName);
    File file = new File(url.toURI());
    ZipFile zipFile = new ZipFile(file);
    return zipFile.getInputStream(zipFile.getEntry(fileName));
  }

  private void closeInputStream(InputStream inputStream) throws IOException {
    if (inputStream != null)
      inputStream.close();
  }

}
