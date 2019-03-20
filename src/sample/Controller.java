package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Controller {
    public HTMLEditor htmledit;
    public Button create;
    public Button save;
    public Button load;
    public Button produce;
    public TextArea txtarea;
    public WebView web;
    public Button webbutton;
    public Pane pane;
    public WebEngine webEngine;
    public Button loadb2;
    public Button imgb;


    public void initialize() {
        webEngine= web.getEngine();
    String INITIAL_TEXT = "<html><body>Lorem ipsum dolor sit "
                + "amet, consectetur adipiscing elit. Nam tortor felis, pulvinar "
                + "in scelerisque cursus, pulvinar at ante. Nulla consequat"
                + "congue lectus in sodales. Nullam eu est a felis ornare "
                + "bibendum et nec tellus. Vivamus non metus tempus augue auctor "
                + "ornare. Duis pulvinar justo ac purus adipiscing pulvinar. "
                + "Integer congue faucibus dapibus. Integer id nisl ut elit "
                + "aliquam sagittis gravida eu dolor. Etiam sit amet ipsum "
                + "sem.</body></html>";
        htmledit.setHtmlText(INITIAL_TEXT);
        htmledit.setStyle(
                "-fx-font: 12 cambria;"
                        + "-fx-border-color: brown; "
                        + "-fx-border-style: dotted;"
                        + "-fx-border-width: 2;"
        );



    }

    public void Produce(ActionEvent bb){

        txtarea.setWrapText(true);
        txtarea.setText(htmledit.getHtmlText());
    }

    public void WebEng(ActionEvent aa) {

        webEngine.loadContent(htmledit.getHtmlText());

    }


    public void SaveHtml(ActionEvent cc) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(pane.getScene().getWindow());

        if(file != null){
            SaveFile(htmledit.getHtmlText(), file);
        }


    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void LoadHtml(ActionEvent dd) throws MalformedURLException {

       FileChooser fileChooser = new FileChooser();
       //fileChooser.setTitle("...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("html", "*.html"));
        File loadImageFile = fileChooser.showOpenDialog(pane.getScene().getWindow());
        URL url = loadImageFile.toURI().toURL();
        webEngine.load(url.toString());

    }


    public void LoadTxt(ActionEvent ee) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("html", "*.html"));
        File loadImageFile = fileChooser.showOpenDialog(pane.getScene().getWindow());


        try (BufferedReader reader = new BufferedReader(new FileReader(loadImageFile))) {

            String line;
            while ((line = reader.readLine()) != null)

            htmledit.setHtmlText(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void LoadImg(ActionEvent ff)   {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File loadImageFile = fileChooser.showOpenDialog(pane.getScene().getWindow());
        
        if (loadImageFile != null) {
             String imgs = "";
            imgs += "<img src=\""+loadImageFile.toURI()+"\" width='70'>";
            webEngine.loadContent("<div id='content'>"+imgs+"</div>");
        } 


    }
}