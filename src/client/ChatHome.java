package client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class ChatHome extends Application {
 	private static Label username_label;
	private static TextField username_field;
	private static Label hostname_label;
	private static TextField hostname_field;
	private static Label port_label;
	private static TextField port_field;
	private static Button login_button;
	private static Label chat_heading;
	private static Stage stage;
	
    @Override
    public void start(Stage primaryStage) {
    	stage = primaryStage;
		

    	Pane root = new Pane();
    	root.setStyle("-fx-background-color: #343434");
    	
    	Scene scene = new Scene(root, 450, 350);
    	scene.getStylesheets().add("includes/Stylesheet.css");
    	
    	username_label = new Label("Username : ");
    	username_label.setLayoutX(60);
    	username_label.setLayoutY(110);
    	root.getChildren().add(username_label);
    	
    	username_field = new TextField();
    	username_field.setLayoutX(130);
    	username_field.setLayoutY(107);
    	root.getChildren().add(username_field);
    	
    	hostname_label = new Label("Hostname : ");
    	hostname_label.setLayoutX(60);
    	hostname_label.setLayoutY(160);
    	root.getChildren().add(hostname_label);
       
    	hostname_field = new TextField();
    	hostname_field.setLayoutX(130);
    	hostname_field.setLayoutY(157);
    	root.getChildren().add(hostname_field);
    	
    	port_label = new Label("Port : ");
    	port_label.setLayoutX(95);
    	port_label.setLayoutY(210);
    	root.getChildren().add(port_label);
       
    	port_field = new TextField("3423");
    	port_field.setLayoutX(130);
    	port_field.setLayoutY(207);
    	root.getChildren().add(port_field);
    	
    	login_button = new Button();
    	login_button.setLayoutX(60);
    	login_button.setLayoutY(260);
    	login_button.setText("Connect To Server");
    	login_button.setPrefSize(300, 30);
    	root.getChildren().add(login_button);
    	
    	chat_heading = new Label("Chat Application");
    	chat_heading.setLayoutX(60);
    	chat_heading.setLayoutY(20);
    	chat_heading.setFont(new Font(45));
        root.getChildren().add(chat_heading);

        primaryStage.setTitle("Chat Application");
        primaryStage.setScene(scene);
		
        primaryStage.getIcons().add(new Image("includes/icon.png"));
    

        primaryStage.setResizable(false);
        primaryStage.show();
        
        root.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent key)
            {
                if (key.getCode().equals(KeyCode.ENTER))
                {
                	connect();
                }
            }
        });
          
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	connect();
            }
        });
    }
    
    public static void connect(){
    	stage.hide();
    	Client client = new Client(hostname_field.getText(), Integer.parseInt(port_field.getText()), username_field.getText());
    	Thread x = new Thread(client);
    	Client_GUI gui = new Client_GUI(hostname_field.getText(), Integer.parseInt(port_field.getText()), username_field.getText(), client);
    	client.setGUI(gui);
    	try {
			gui.start(new Stage());
		} catch (Exception e1) {

			e1.printStackTrace();
		}
    	x.start();
    }
    
 public static void main(String[] args) {
        launch(args);
    }
}