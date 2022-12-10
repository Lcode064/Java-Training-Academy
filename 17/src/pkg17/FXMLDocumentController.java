
package pkg17;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLDocumentController implements Initializable {

    @FXML
    private RadioButton male;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton female;
    @FXML
    private Label age;
    @FXML
    private TextField std_name;
    @FXML
    private TextField std_surname;
    @FXML
    private TextField std_id;
    @FXML
    private TextField birthYear;
    @FXML
    private ComboBox course_name;
    @FXML
    private Label course_code;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        course_name.setItems(getCourse());
    }    

    @FXML
    private void addDetails(ActionEvent event) throws IOException //method for getting all data from the user
    {
        String name=std_name.getText();
        String surname=std_surname.getText();
        try
        {
        Integer birth=Integer.parseInt(birthYear.getText());
        int lilemo=2022-birth;
        age.setText(""+lilemo);
        String StudentNumber=std_id.getText();
        String sex=null;
        
            if(male.isSelected())
        {
            sex="male";
        }
        else if(female.isSelected())
        {
            sex="female";
        }
        String courseCode = null;
        if("Intro to Java".equals(course_name.getValue()))
        {
            courseCode="JP1";
            course_code.setText(courseCode);
        }
        else if("Java 2".equals(course_name.getValue()))
        {
            courseCode="JP2";
            course_code.setText(courseCode);
        }
        else if("Swing".equals(course_name.getValue()))
        {
            courseCode="JP3";
            course_code.setText(courseCode);
        }
        else if("Java FX".equals(course_name.getValue()))
        {
            courseCode="JP4";
            course_code.setText(courseCode);
        }
        
        String cos=(String) course_name.getValue();
        StudentDetails std=new StudentDetails(name,surname,StudentNumber,sex,lilemo);
        CourseDetails course=new CourseDetails(StudentNumber,cos,courseCode);
        WriteStudent(std);
        WriteCourse(course);
        
        }
        catch(IOException ex)
        {
            Alert al=new Alert(AlertType.ERROR,""+ex+"",ButtonType.OK);
            al.showAndWait();
        }
    }
    public void WriteStudent(StudentDetails std) throws IOException //method for writing student details to the txtfile
    {
        File mf=new File("myfile.txt");
        mf.createNewFile();
        try (FileWriter rd = new FileWriter("myfile.txt",true)) {
            rd.append("\nStudent Name: "+std.Name+"\n");
            rd.append("Student Surname: "+std.Surname+"\n");
            rd.append("Gender: "+std.Gender+"\n");
            rd.append("Age: "+std.Age+"\n"+"\n");
        }
    }
    public void WriteCourse(CourseDetails cou) throws IOException //method for writing student course details to the txtfile
    {
        try (FileWriter rd = new FileWriter("myfile.txt",true)) {
            rd.append("Student Number: "+cou.StudentNumber+"\n");
            rd.append("Course Name: "+cou.CourseName+"\n");
            rd.append("Course Code: "+cou.Course_Code+"\n"+"\n");
        }
        Alert al=new Alert(AlertType.INFORMATION,"Successfully added student course details to txt file",ButtonType.OK);
        al.showAndWait();
    }
    public ObservableList<String> getCourse()
    {
        ObservableList<String> courses=FXCollections.observableArrayList("Intro to Java","Java 2","Swing","Java FX");
        return courses;
    }
}
