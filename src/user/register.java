package user;

import main.MySQLLink;
import main.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author DeerSky
 */
public class register extends JFrame {

    private JPanel contentPane;
    private JTextField regAccField;
    private JTextField regNameField;
    private JPasswordField regPwdField;
    private JPasswordField regPwdConField;
    private JLabel regTelLabel;
    private JTextField regTelField;
    String reg_acc;
    String reg_name;
    String reg_pwd;
    String reg_tel;
    String reg_conpwd;
    Connection conn = MySQLLink.getConnection();
    ResultSet rs = null;

    /**
     * Create the frame.
     */
    public register() {

        //更改GUI页面风格
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        //创建Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 675, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel regUidLebel = new JLabel("用户名");
        regUidLebel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regUidLebel.setBounds(212, 76, 55, 26);
        contentPane.add(regUidLebel);

        //用户名输入框
        regAccField = new JTextField();
        regAccField.setBounds(312, 78, 159, 28);
        contentPane.add(regAccField);
        regAccField.setColumns(10);
        //键盘监听，限制文本框最大输入字符与只能输入数字
        regAccField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //限定最大输入字符
                String s = regAccField.getText();
                if (s.length() >= 32) {
                    e.consume();
                }
                //限制只能输入数字
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
                } else {
                    e.consume();
                }
            }
        });

        JLabel regNameLabel = new JLabel("姓名");
        regNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regNameLabel.setBounds(212, 131, 55, 26);
        contentPane.add(regNameLabel);

        //姓名输入框
        regNameField = new JTextField();
        regNameField.setColumns(10);
        regNameField.setBounds(312, 132, 159, 28);
        contentPane.add(regNameField);
        //键盘监听，限制文本框最大输入字符
        regNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = regNameField.getText();
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });


        JLabel regPwdLabel = new JLabel("密码");
        regPwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regPwdLabel.setBounds(212, 186, 55, 26);
        contentPane.add(regPwdLabel);

        //密码输入框
        regPwdField = new JPasswordField();
        regPwdField.setBounds(312, 190, 159, 28);
        contentPane.add(regPwdField);
        //键盘监听，限制文本框最大输入字符
        regPwdField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = String.valueOf(regPwdField.getPassword());
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });

        JLabel regPwdConLabel = new JLabel("确认密码");
        regPwdConLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regPwdConLabel.setBounds(212, 241, 70, 26);
        contentPane.add(regPwdConLabel);

        //【确认密码】输入框
        regPwdConField = new JPasswordField();
        regPwdConField.setBounds(312, 245, 159, 28);
        contentPane.add(regPwdConField);
        //键盘监听，限制文本框最大输入字符
        regPwdConField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = String.valueOf(regPwdConField.getPassword());
                if (s.length() >= 32) {
                    e.consume();
                }
            }
        });

        regTelLabel = new JLabel("联系电话");
        regTelLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regTelLabel.setBounds(212, 296, 70, 26);
        contentPane.add(regTelLabel);

        //联系电话输入框
        regTelField = new JTextField();
        regTelField.setColumns(10);
        regTelField.setBounds(312, 300, 159, 28);
        contentPane.add(regTelField);
        //键盘监听，限制文本框最大输入字符
        regTelField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = regTelField.getText();
                if (s.length() >= 11) {
                    e.consume();
                    //限制只能输入数字
                    int keyChar = e.getKeyChar();
                    if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
                    } else {
                        e.consume();
                    }
                }
            }
        });

        JButton regButton = new JButton("确认注册");
        regButton.setBounds(289, 364, 113, 53);
        contentPane.add(regButton);
        //【确认注册】按钮点击事件
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户名框内容
                reg_acc = regAccField.getText();
                //获取姓名框内容
                reg_name = regNameField.getText();
                //获取密码框内容
                reg_pwd = String.valueOf(regPwdField.getPassword());
                reg_conpwd = String.valueOf(regPwdConField.getPassword());
                //获取联系电话框内容
                reg_tel = regTelField.getText();

                //确认信息是否输入完全
                if (reg_acc.length() != 0 && reg_name.length() != 0 && reg_pwd.length() != 0 && reg_conpwd.length() != 0 && reg_tel.length() != 0) {
                    //比较【密码】和【确认密码】框
                    if (reg_conpwd.equals(reg_pwd)) {

                        String sql = "INSERT INTO user (Uid,UserName,Password,Tel) VALUES (?,?,?,?)";
                        PreparedStatement ps = null;
                        try {
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, reg_acc);
                            ps.setString(2, reg_name);
                            ps.setString(3, reg_pwd);
                            ps.setString(4, reg_tel);
                            int i = ps.executeUpdate();
                            if (i > 0) {
                                JOptionPane.showMessageDialog(null, "注册成功！", "", JOptionPane.INFORMATION_MESSAGE);
                                login login = new login();
                                login.setVisible(true);
                                dispose();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            //数据库中已存在相同用户名时弹窗
                            JOptionPane.showMessageDialog(null, "已存在此用户名！", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    //密码框比较不相同
                    else {
                        JOptionPane.showMessageDialog(null, "两次输入的密码不相同！", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //注册信息没填写完整时弹窗
                } else {
                    JOptionPane.showMessageDialog(null, "请填写信息！", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //左上角返回登陆界面按钮
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(10, 10, 93, 23);
        contentPane.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login login = new login();
                login.setVisible(true);
                dispose();
            }
        });
    }
}
