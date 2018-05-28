package com.java.project.utils;

import com.java.project.services.DBConnection;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class SetupTestDataBase {



    public static void setup() {

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            URL resource = SetupTestDataBase.class.getClassLoader().getResource("SetupTestDataBase.sql");
            if (resource != null) {
                File scriptFile = new File(resource.toURI());
                Reader reader = new BufferedReader(
                        new FileReader(scriptFile));

                ScriptRunner sr = new ScriptRunner(connection);
                sr.setLogWriter(null);


                sr.runScript(reader);
            }
        } catch (SQLException | FileNotFoundException | URISyntaxException e) {
            //TODO logging
            e.printStackTrace();
        }


    }
}
