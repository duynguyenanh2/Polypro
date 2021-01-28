/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class TestDAO {

    public void insert(Test model) {
        String sql = "INSERT INTO Test (Ma, Ten) values (?, ?)";
        JDBC.executeUpdate(sql, model.getMa(), model.getTen());
    }

    public void update(Test model) {
        String sql = "UPDATE Test SET Ten = ? WHERE Ma = ?";
        JDBC.executeUpdate(sql, model.getTen(), model.getMa());
    }

    public void delete(String ma) {
        String sql = "DELETE FROM Test WHERE ma = ?";
        JDBC.executeUpdate(sql, ma);
    }

    public List<Test> select() {
        String sql = "SELECT * FROM test";
        return select(sql);
    }
    
    public Test findById(Integer ma) {
        String sql = "SELECT * FROM Test WHERE ma = ?";
        List<Test> list = select(sql, ma);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<Test> select(String sql, Object... args) {
        List<Test> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.executeQuery(sql, args);
                while (rs.next()) {
                    Test model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    private Test readFromResultSet(ResultSet rs) throws SQLException{
        Test model = new Test();
        model.setMa(rs.getInt("Ma"));
        model.setTen(rs.getString("Ten"));
        return model;
    }
}
