package Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class creates HTML-reports about results of search
 * @author WORK-06
 *
 */
public class Report {

	public StringBuilder htmlStringBuilder;
	
	public void createTableParticipants(ResultSet result) throws SQLException, IOException {
		htmlStringBuilder = new StringBuilder();
		htmlStringBuilder.append("<html><head><title>Part in tenders</title></head>");
		htmlStringBuilder.append("<body>");
		htmlStringBuilder.append("table border=\"1\" bordercolor=\"#000000\">");
		htmlStringBuilder.append("<tr><td><b>TenderId</b></td><td><b>Company Name</b></td><td><b>Price</b></td></tr>");
		while(result.next()) {
			htmlStringBuilder.append("<tr><td>"+"Хуй"+"</td>"
					                    +"<td>"+"Пизда"+"</td>"
					                    +"<td>"+"Джагурда"+"</td></tr>");
		}
		htmlStringBuilder.append("</table></body></html>");
		writeToFile(htmlStringBuilder.toString(), "Output/FirstReport.html");
	}

	private void writeToFile(String fileContent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath+File.separator+fileName;
		File file = new File(tempFile);
		if(file.exists()) {
			try {
			File newFileName = new File(projectPath+File.separator+"backUp"+fileName);
			file.renameTo(newFileName);
			file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();
		
	}

	public Report() {
		super();
		 
	}
}
