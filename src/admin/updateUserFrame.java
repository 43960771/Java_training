package admin;

import main.MySQLLink;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class updateUserFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();

    private JPanel contentPane;
    private JTextField UidField;
    private JTextField user_NameField;
    private JPasswordField PasswordField;
    private JTextField TelField;


    String UserName=null;
    String Pass=null;
    String Tele=null;
    String Uid=null;

    String reg_UserName;
    String reg_Password;
    String reg_Tel;


    /**
     * Create the frame.
     */
    public updateUserFrame() {
        setTitle("\u66F4\u6539\u4E66\u7C4D\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 512, 436);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel UidLabel = new JLabel("用户id");
        UidLabel.setBounds(39, 65, 72, 18);
        contentPane.add(UidLabel);

        JLabel user_NameLabel = new JLabel("用户姓名");
        user_NameLabel.setBounds(39, 108, 72, 18);
        contentPane.add(user_NameLabel);

        JLabel PasswordLabel = new JLabel("用户密码");
        PasswordLabel.setBounds(39, 158, 72, 18);
        contentPane.add(PasswordLabel);

        JLabel TelLabel = new JLabel("用户电话");
        TelLabel.setBounds(39, 202, 72, 18);
        contentPane.add(TelLabel);

        //用户id
        UidField = new JTextField();
        UidField.setBounds(247, 62, 86, 24);
        contentPane.add(UidField);

        //用户姓名
        user_NameField = new JTextField();
        user_NameField.setBounds(247, 105, 86, 24);
        contentPane.add(user_NameField);
        user_NameField.setColumns(10);
        user_NameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = user_NameField.getText();
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });

        //用户密码
        PasswordField = new JPasswordField();
        PasswordField.setBounds(247, 172, 86, 24);
        contentPane.add(PasswordField);
        PasswordField.setColumns(10);
        PasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = String.valueOf(PasswordField.getPassword());
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });

        //用户电话
        TelField = new JTextField();
        TelField.setBounds(247, 209, 86, 24);
        contentPane.add(TelField);
        TelField.setColumns(10);
        TelField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = TelField.getText();
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });

        JButton regButton = new JButton("提交");
        regButton.setBounds(142, 314, 113, 27);
        contentPane.add(regButton);
        //【提交】按钮点击事件
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取书籍编号
                Uid=UidField.getText();
                //获取姓名框内容
                reg_UserName = user_NameField.getText();
                //获取密码框内容
                reg_Password = String.valueOf(PasswordField.getPassword());
                //获取电话内容
                reg_Tel = TelField.getText();

                //确认信息是否输入完全
                if (Uid.length() !=0 && reg_UserName.length() != 0 && reg_Password.length() != 0 && reg_Tel.length() != 0) {

                    String sql = "UPDATE user SET UserName=?,Password=?,Tel=? WHERE Uid=?";
                    PreparedStatement ps = null;
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, reg_UserName);
                        ps.setString(2, reg_Password);
                        ps.setString(3, reg_Tel);
                        ps.setString(4, Uid);
                        //界面刷新
                        int i = ps.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(null, "提交成功！", "", JOptionPane.INFORMATION_MESSAGE);
                            updateUserFrame updateUserFrame = new updateUserFrame();
                            updateUserFrame.setVisible(true);
                            dispose();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        //数据库中已存在相同书籍时弹窗
                        JOptionPane.showMessageDialog(null, "修改信息与数据库一致", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //添加信息没填写完整时弹窗
                } else {
                    JOptionPane.showMessageDialog(null, "请填写信息！", "", JOptionPane.INFORMATION_MESSAGE);
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

