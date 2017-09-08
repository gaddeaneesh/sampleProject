package Sample_Group.Sample_Artifact;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ExcelWriting {
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheetwrite;
	String filePath = "D:\\SCREENSHOTS";

	public ExcelWriting(WebDriver driver) {
		this.driver = driver;
	}

	public void createExcel() {
		workbook = new XSSFWorkbook();
		sheetwrite = workbook.createSheet("QA Details");

		sheetwrite.createRow(0).createCell(0).setCellValue("user Name");
		sheetwrite.getRow(0).createCell(1).setCellValue("User Link");
	}

	public int EmployeeData(int rowcount, List<WebElement> contact_Names, List<WebElement> contact_Links)
			throws IOException {
		int count = 1;
		for (WebElement contact_name : contact_Names) {
			String name = contact_name.getText();
			String link = contact_Links.get(count - 1).getAttribute("href");
			if (count <= contact_Names.size()) {
				sheetwrite.createRow(rowcount).createCell(0).setCellValue(name);
				sheetwrite.getRow(rowcount).createCell(1).setCellValue(link);
				rowcount++;
				count++;
			}
			// System.out.println(name);
			// System.out.println(link);
			FileOutputStream fout = new FileOutputStream(new File("D:/workspace/Sample_Artifact/output.xlsx"));
			workbook.write(fout);
			fout.close();

		}
		return rowcount;
	}

	public void excelClose() throws IOException {
		workbook.close();
	}

}
