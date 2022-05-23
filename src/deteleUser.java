import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deteleUser extends JFrame {

    private JPanel contentPane;
    private JTextField deleteUserField;
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();
    String deleteTel=null;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    deteleUser frame = new deteleUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public deteleUser() {
        setTitle("\u5220\u9664\u4E66\u7C4D\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 523, 399);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel deleteUserLabel = new JLabel("\u7528\u6237Uid");
        deleteUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteUserLabel.setBounds(41, 144, 118, 39);
        contentPane.add(deleteUserLabel);

        deleteUserField = new JTextField();
        deleteUserField.setBounds(158, 151, 181, 24);
        contentPane.add(deleteUserField);
        deleteUserField.setColumns(10);

        JButton deleteButton = new JButton("\u5220\u9664");
        deleteButton.setBounds(161, 234, 113, 27);
        contentPane.add(deleteButton);

       deleteButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               deleteTel= deleteUserField.getText();
               String sql = "delete from user where Uid=?";
               try {
                   PreparedStatement ps = conn.prepareStatement(sql);
                   ps.setString(1,deleteTel);
                   ps.setString(1,login.acc);
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null,"删除成功！","",JOptionPane.ERROR_MESSAGE);
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
           }
       });
    }
}
