package admin;

import main.MySQLLink;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteBookFrame extends JFrame {
    Connection conn = MySQLLink.getConnection();
    String deleteBook = null;

    public deleteBookFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 523, 399);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel deleteBookLabel = new JLabel("书籍编号");
        deleteBookLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteBookLabel.setBounds(41, 144, 118, 39);
        contentPane.add(deleteBookLabel);

        JTextField deleteBookField = new JTextField();
        deleteBookField.setBounds(158, 151, 181, 24);
        contentPane.add(deleteBookField);
        deleteBookField.setColumns(10);

        JButton deleteButton = new JButton("删除");
        deleteButton.setBounds(185, 234, 113, 27);
        contentPane.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook = deleteBookField.getText();
                //再次确认是否删除
                int n = JOptionPane.showConfirmDialog(null, "该操作将把书籍将库中删除，但无法删除正在被借阅的书籍。确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    String sql = "delete from book where Sid=? AND State='0'";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, deleteBook);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "操作完成！", "", JOptionPane.PLAIN_MESSAGE);
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
