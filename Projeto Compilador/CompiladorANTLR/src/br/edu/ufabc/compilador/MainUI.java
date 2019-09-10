package br.edu.ufabc.compilador;
/*
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.ByteArrayInputStream;
import javafx.scene.control.*;

public class MainCarlos extends Application{

    private static String linguagem = "java";

    public static void main(String[] args) {

        launch(args);
    }

    public static InputStream  StringToInputStream(String texto) throws IOException{

        String string = texto;

        //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());


        return inputStream;
    }


    public static void compilador(String texto) {
        InputStream inputstream = null;

        try{
            System.out.println( "User directory" + System.getProperty("user.dir"));

            inputstream = StringToInputStream(texto);



            System.out.println(inputstream.toString());
            MyLexer lexer = new MyLexer(inputstream);
            MyParser parser = new MyParser(lexer);

            if(inputstream == null) System.out.println("Input null");

            System.out.println("Starting compiling process ...");

            parser.prog();

            System.out.println("Compilation finished!");



        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group(), 450, 800);

        TextArea notification = new TextArea();
        TextArea saida = new TextArea ();
        Button b = new Button("Compilador");

        notification.setText("Insira Texto");
        saida.setText ("");

        notification.setWrapText(true);

        notification.clear();
        saida.clear();

        notification.setWrapText(true);

        notification.setPrefWidth(400);
        notification.setPrefHeight(300);

        saida.setPrefWidth(400);
        saida.setPrefHeight(300);

        final ComboBox comboBox = new ComboBox();

        comboBox.getItems().addAll(
                "Java",
                "Arduino"
        );

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
//        grid.add(new Label("Para: "), 0, 0);
        grid.add(notification, 0, 0);
        grid.add(saida, 0, 7);
        grid.add(b, 0, 13);
        grid.add(comboBox, 0, 15);


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {

                linguagem = (String) comboBox.getValue() ;
                compilador(notification.getText());
            }
        };


        b.setOnAction(event);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);


        stage.setScene(scene);
        stage.show();
    }

}
*/