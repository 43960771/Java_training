import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame {
    private JPanel contentPane;
    private JTextField accField;
    private JPasswordField pwdFeild;
    public static String  acc;
    String pwd;
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();

    /**
     * Launch the application.
     */


    public login() {
        //变更GUI页面风格
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }

        //创建GUI窗体
        setTitle("图书管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 675, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 659, 461);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("欢迎来到图书管理系统");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 30));
        lblNewLabel_2.setBounds(166, 41, 335, 32);
        panel.add(lblNewLabel_2);

        JLabel acclable = new JLabel("账号");
        acclable.setFont(new Font("微软雅黑", Font.BOLD, 26));
        acclable.setBounds(166, 136, 52, 40);
        panel.add(acclable);

        JLabel pwdlable = new JLabel("密码");
        pwdlable.setFont(new Font("微软雅黑", Font.BOLD, 26));
        pwdlable.setBounds(166, 206, 52, 40);
        panel.add(pwdlable);

        /*账号输入框*/
        accField = new JTextField();
        accField.setFont(new Font("微软雅黑", Font.BOLD, 18));
        accField.setBounds(265, 144, 236, 32);
        panel.add(accField);
        accField.setColumns(10);
        accField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                    //限制只能输入数字
                    int keyChar=e.getKeyChar();
                    if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
                    } else {
                        e.consume();
                    }
            }
        });

        /*密码输入框*/
        pwdFeild = new JPasswordField();
        pwdFeild.setBounds(265, 211, 236, 32);
        panel.add(pwdFeild);

        //登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        loginButton.setBounds(382, 281, 119, 40);
        panel.add(loginButton);
        //点击【登录】按钮事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //获取账号文本框内容
                acc = accField.getText();
                //获取密码框内容
                pwd = String.valueOf(pwdFeild.getPassword());
                //SQL查询语句
                String sql =  "select * from user where Uid=? and Password=?";

                try {
                    //用于发送SQL语句
                    PreparedStatement ps = conn.prepareStatement(sql);
                    //设置SQL语句中？代表的内容
                    ps.setString(1,acc);
                    ps.setString(2,pwd);
                    //执行SQL指令
                    rs=ps.executeQuery();
                    if(rs.next()){
                        userFrame userFrame = new userFrame();
                        userFrame.setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"账号或密码错误！","",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //注册按钮
        JButton regButton = new JButton("注册");
        regButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        regButton.setBounds(166, 281, 119, 40);
        panel.add(regButton);
        //点击【注册】按钮事件
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                register register = new register();
                register.setVisible(true);
                dispose();
            }
        });


        //忘记密码跳转
        JLabel forgetpwd = new JLabel("忘记密码？");
        forgetpwd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        forgetpwd.setBounds(589, 436, 70, 15);
        panel.add(forgetpwd);
    }


}