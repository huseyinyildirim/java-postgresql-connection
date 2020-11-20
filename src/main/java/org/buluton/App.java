package org.buluton;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private void btnConnection() {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Northwind", "postgres", "");
            if (conn != null) {
                System.out.println("Veritabanına bağlandı!");
            } else {
                System.out.println("Bağlantı girişimi başarısız!");
            }

            String sql= "SELECT \"CustomerID\", \"CompanyName\", \"Country\"  FROM \"customers\"";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            conn.close();

            String musteriNo= null;
            String sirketAdi=null;
            String ulke;

            while(rs.next())
            {
                musteriNo  = rs.getString("CustomerID");
                sirketAdi = rs.getString("CompanyName");
                ulke = rs.getString("Country");

                System.out.print("Sıra No:"+ musteriNo);
                System.out.print(", Şirket Adı:" + sirketAdi);
                System.out.println(", Ulke:" + ulke);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}