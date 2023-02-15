package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class userAccountController implements Initializable  /*,controlledScreen*/ {


   //private static java.awt.Label displayWN;
    @FXML
    private javafx.scene.control.TextField courseCode;
    @FXML
    private javafx.scene.control.TextField courseName;
    @FXML
    private javafx.scene.control.TextField profName;
    @FXML
    private Label welcomeName;
    @FXML
    private Label displayData;
    @FXML
    private TextField stu_ID;
    @FXML
    private TableView<student> tableView;
    @FXML
    public TableColumn<student, String> userName;
    @FXML
    public TableColumn<student, String> cCode;
    @FXML
    public TableColumn<student, String> cName;
    @FXML private TextField mathTF;
    @FXML private TextField coTF;
    @FXML private TextField cTF;
    @FXML private TextField indTF;
    @FXML private TextField admTF;
    @FXML private Label totSumL;



    ObservableList<student> studs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // loginSession getName = new loginSession();
       // String info = getName.getUserName();
        userName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        cCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        cName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));

        //welcomeName.setText("Welcome "+ info);


    }


    public void calcButtonClicked(ActionEvent actionEvent) {
        int marks[] = new int[6];
        int i=0;
        float total=0, avg;
        //   Scanner scanner = new Scanner(System.in);

        int mt = Integer.parseInt(mathTF.getText());
        int c2420 = Integer.parseInt(coTF.getText());
        int c2300 = Integer.parseInt(cTF.getText());
        int indT = Integer.parseInt(indTF.getText());
        int admT = Integer.parseInt(admTF.getText());

        //  for(i=0; i<6; i++) {
        // System.out.print("Enter Marks of Subject"+(i+1)+":");
        marks[i] = mt;
        marks[i] = c2420;
        marks[i] = c2300;
        marks[i] = indT;
        marks[i] = admT;


        total = total + marks[i];

        avg = total/6;


        if(avg>=80)
        {
            totSumL.setText(" Your Grade is A");

        }
        else if(avg>=70 && avg<80)
        {
            totSumL.setText(" Your Grade is B");

        }
        else if(avg>=60 && avg<70)
        {
            totSumL.setText(" Your Grade is C");

        }
        else
        {
            totSumL.setText(" Your Grade is D");

        }

    }


     public void addButtonClicked(ActionEvent actionEvent)
    {
        String cC = courseCode.getText();
        String cN = courseName.getText();
        String pN = profName.getText();
        int stuID = Integer.parseInt(stu_ID.getText());
        studentHelper.InsertRecord(Helper.ConnectToDatabase(), cC, cN, pN, stuID);

    }
    loginSession getName = new loginSession();
    String info = getName.getUserName();
    public void viewButtonClicked(ActionEvent actionEvent)
    {

        //studentHelper.viewData(Helper.ConnectToDatabase(),info);
       // tableView.setItems(getObservableList());
        for ( int i = 0; i<tableView.getItems().size(); i++) {
            tableView.getItems().clear();
        }
        displayTable(Helper.ConnectToDatabase());
    }

    public void exitButtonClicked(ActionEvent actionEvent)
    {
        final Node source = (Node)actionEvent.getSource();
        final Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }


    public void show(){

        welcomeName.setText("Welcome "+ info);
    }

    public void displayTable(Connection conn){
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM studentInfo as a INNER JOIN studentCourse WHERE username LIKE '"+ info +"' ");

            // query.execute("SELECT username, courseCode from studentInfo, studentCourse where studentInfo.stu_ID = studentCourse.track_ID");
            while(rs.next()) {
                studs.add(new student(rs.getString("username"), rs.getString("courseCode"),  rs.getString("courseName")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        tableView.setItems(studs);
    }
}
