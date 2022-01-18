package Loader;

import Loader.Loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SqlLoader extends Loader {
    private String url = "";
    private Statement statement;
    private HashMap<String,String> parameterTypes;
    private String tableName;
    public SqlLoader(String url,String tableName,HashMap<String,String> parametersTypes) {
        this.url = url;
        this.parameterTypes = parametersTypes;
        try {
            Connection conn = DriverManager.getConnection(url);
            this.statement= conn.createStatement();
            this.tableName = tableName;
            createTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        for(HashMap<String,String> record: this.data.getData()){
            for(String parameter :this.data.getParameters()){
                try {
                    this.statement.execute("INSERT " + this.tableName + "(" + parameter + ") VALUES " + record.get(parameter));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void createTable(String tableName){
        try {
            String sql = "CREATE TABLE "+ tableName+" (" ;
            for(String parameter :this.data.getParameters()){
                sql += parameter+ " " +this.parameterTypes.get(parameter) + ",\n";
            }
            sql += ");";
            this.statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
