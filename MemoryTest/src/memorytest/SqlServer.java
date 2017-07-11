/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorytest;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yujian
 */
public class SqlServer {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/memory_test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String user = "root";
    private static String password = "123";
    private Connection conn = null;
    private PreparedStatement ps =null;
    private ResultSet rs = null;
    private String selectSql = "select * from tblRanking where difficulty=? order by score";
    private String deleteSql = "delete from tblRanking where difficulty=?";
    private String insertSql = "insert into tblRanking(name,score,difficulty,time) values(?,?,?,?)";
    public SqlServer(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void selectBydifficulty(int difficulty,DefaultTableModel dtm){
        try {
            ps = conn.prepareStatement(selectSql);
            ps.setInt(1, difficulty);
            rs = ps.executeQuery();
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString("name"),rs.getInt("score"),rs.getDate("time")+" "+rs.getTime("time")});
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(int difficulty){
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, difficulty);
            ps.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert(Ranking ranking){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new java.util.Date());
        try {
            ps = conn.prepareStatement(insertSql);
            ps.setString(1, ranking.getName());
            ps.setInt(2, ranking.getScore());
            ps.setInt(3, ranking.getDifficulty());
            ps.setString(4, time);
            ps.execute();
            JOptionPane.showMessageDialog(null,
                    "本次成绩保存成功！",
                    "提示框",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(conn!=null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
