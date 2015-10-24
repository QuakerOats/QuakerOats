import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToCsv {
public LearningAlgorithm learningalgorithm;
public ToCsv(LearningAlgorithm learningalgorithm){
	this.learningalgorithm = learningalgorithm;
}

private static final String COMMA_DELIMITER = ",";
private static final String NEW_LINE_SEPARATOR = "\n";
private static final String FILE_HEADER = "epochsize,learningrate,trainerror,testerror";
public void writeCsvFile(String fileName) {
	FileWriter fileWriter = null;     
       try {
           fileWriter = new FileWriter(fileName);

           //Write the CSV file header
           fileWriter.append(FILE_HEADER.toString());
            
           //Add a new line separator after the header
           fileWriter.append(NEW_LINE_SEPARATOR);
            
           //Write a new student object list to the CSV file
           for (Output output : this.learningalgorithm.outputdata.data) {
               fileWriter.append(String.valueOf(output.epochsize));
               fileWriter.append(COMMA_DELIMITER);
               fileWriter.append(String.valueOf(output.learningrate));
               fileWriter.append(COMMA_DELIMITER);
              fileWriter.append(String.valueOf(output.trainerror));
               fileWriter.append(COMMA_DELIMITER);
               fileWriter.append(String.valueOf(output.testerror));
               fileWriter.append(COMMA_DELIMITER);
               fileWriter.append(NEW_LINE_SEPARATOR);
          }          
            
           System.out.println("CSV file was created successfully !!!");         
       } catch (Exception e) {
           System.out.println("Error in CsvFileWriter !!!");
           e.printStackTrace();
       } finally {
            
           try {
               fileWriter.flush();
               fileWriter.close();
           } catch (IOException e) {
               System.out.println("Error while flushing/closing fileWriter !!!");
               e.printStackTrace();

           }

            

       }

   }


}
