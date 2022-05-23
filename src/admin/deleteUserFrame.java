package admin;
import main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteUserFrame extends JFrame {

    private JPanel contentPane;
    private JTextField deleteUserField;
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();
    String deleteTel=null;




    public deleteUserFrame() {
        setTitle("删除用户");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 523, 399);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel deleteUserLabel = new JLabel("用户Uid");
        deleteUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteUserLabel.setBounds(41, 144, 118, 39);
        contentPane.add(deleteUserLabel);

        deleteUserField = new JTextField();
        deleteUserField.setBounds(158, 151, 181, 24);
        contentPane.add(deleteUserField);
        deleteUserField.setColumns(10);

        JButton deleteButton = new JButton("删除");
        deleteButton.setBounds(185, 234, 113, 27);
        contentPane.add(deleteButton);

       deleteButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               deleteTel= deleteUserField.getText();
               //再次确认是否删除
               int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
               if (n == JOptionPane.YES_OPTION) {
                   String sql = "delete from user where Uid=?";
                   try {
                       PreparedStatement ps = conn.prepareStatement(sql);
                       ps.setString(1,deleteTel);
                       ps.executeUpdate();
                       JOptionPane.showMessageDialog(null,"删除成功！","",JOptionPane.PLAIN_MESSAGE);
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                   }
               } else if (n == JOptionPane.NO_OPTION) {
                   // ......
               }


           }
       });

        //左上角返回主界面按钮
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(10, 10, 93, 23);
        contentPane.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminFrame adminFrame = new adminFrame();
                adminFrame.setVisible(true);
                dispose();
            }
        });
    }
}
