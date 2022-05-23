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

public class userInfoFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();

    //用于保存从数据库取出的数据
    String acc = null;
    String name = null;
    String tel = null;

    //用于保存从文本框读取的数据
    String saveTel = null;

    /**
     * Create the frame.
     */
    public userInfoFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 900, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245,245,245));
        panel.setBounds(0, 0, 150, 511);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel welLabel = new JLabel("欢迎你");
        welLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        welLabel.setBounds(10, 10, 51, 23);
        panel.add(welLabel);

        JLabel userNameLabel = new JLabel();
        userNameLabel.setBackground(new Color(245,245,245));

        userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        userNameLabel.setBounds(37, 43, 103, 36);
        panel.add(userNameLabel);

        JButton infoButton = new JButton("个人信息");
        infoButton.setBackground(UIManager.getColor("Button.highlight"));
        infoButton.setFont(new Font("宋体", Font.PLAIN, 18));
        infoButton.setBounds(0, 105, 150, 50);
        panel.add(infoButton);

        JButton borrowButton = new JButton("借阅书籍");
        borrowButton.setFont(new Font("宋体", Font.PLAIN, 18));
        borrowButton.setBackground(UIManager.getColor("Button.highlight"));
        borrowButton.setBounds(0, 155, 150, 50);
        panel.add(borrowButton);
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userBorrowFrame userBorrowFrame = new userBorrowFrame();
                userBorrowFrame.setVisible(true);
                dispose();
            }
        });



        JButton orderButton = new JButton("历史订单");
        orderButton.setFont(new Font("宋体", Font.PLAIN, 18));
        orderButton.setBackground(UIManager.getColor("Button.highlight"));
        orderButton.setBounds(0, 205, 150, 50);
        panel.add(orderButton);
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userOrderFrame userOrderFrame = new userOrderFrame();
                userOrderFrame.setVisible(true);
                dispose();
            }
        });

        JButton returnButton = new JButton("归还图书");
        returnButton.setFont(new Font("宋体", Font.PLAIN, 18));
        returnButton.setBackground(UIManager.getColor("Button.highlight"));
        returnButton.setBounds(0, 255, 150, 50);
        panel.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userReturnFrame userReturnFrame = new userReturnFrame();
                userReturnFrame.setVisible(true);
                dispose();
            }
        });

        JButton reLoginButton = new JButton("返回登陆界面");
        reLoginButton.setFont(new Font("宋体", Font.PLAIN, 18));
        reLoginButton.setBackground(Color.WHITE);
        reLoginButton.setBounds(0, 305, 150, 50);
        panel.add(reLoginButton);
        reLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login login = new login();
                login.setVisible(true);
                dispose();
            }
        });


        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(150, 0, 735, 511);
        contentPane.add(infoPanel);
        infoPanel.setLayout(null);

        JLabel uidLabel = new JLabel("用户名");
        uidLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        uidLabel.setBounds(245, 118, 62, 25);
        infoPanel.add(uidLabel);

        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        nameLabel.setBounds(261, 168, 62, 25);
        infoPanel.add(nameLabel);

        JLabel telLabel = new JLabel("联系电话");
        telLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        telLabel.setBounds(229, 218, 73, 25);
        infoPanel.add(telLabel);
        //联系电话显示、更改框
        JTextField telField = new JTextField();
        telField.setFont(new Font("幼圆", Font.PLAIN, 16));
        telField.setColumns(10);
        telField.setBounds(333, 218, 215, 28);
        infoPanel.add(telField);
        //键盘监听，限制文本框最大字符
        telField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = telField.getText();
                if(s.length() >= 11) {
                    e.consume();
                    //限制只能输入数字
                    int keyChar=e.getKeyChar();
                    if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
                    } else {
                        e.consume();
                    }
                }
            }
        });
        //用户名显示框
        JLabel uidShowLabel = new JLabel();
        uidShowLabel.setFont(new Font("幼圆", Font.PLAIN, 16));
        uidShowLabel.setBounds(333, 120, 215, 25);
        infoPanel.add(uidShowLabel);
        //姓名显示框
        JLabel nameShowLabel = new JLabel("New label");
        nameShowLabel.setFont(new Font("幼圆", Font.PLAIN, 16));
        nameShowLabel.setBounds(333, 168, 215, 25);
        infoPanel.add(nameShowLabel);
        //保存按钮
        JButton saveButton = new JButton("\u4FDD\u5B58");
        saveButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        saveButton.setBounds(302, 347, 114, 36);
        infoPanel.add(saveButton);


        //查询语句
        String sql =  "select * from user where Uid=?";
        try {
            //用于发送SQL语句
            PreparedStatement ps = conn.prepareStatement(sql);
            //设置SQL语句中？代表的内容
            ps.setString(1,login.acc);
            //执行SQL语句
            rs=ps.executeQuery();
            while (rs.next()) {
                acc = rs.getString("Uid");
                name = rs.getString("UserName");
                tel = rs.getString("Tel");
            }
            userNameLabel.setText(name);//将姓名显示到左上角欢迎语中
            uidShowLabel.setText(acc);//将用户名显示
            nameShowLabel.setText(name);//将姓名显示
            telField.setText(tel);//将联系电话显示
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //【保存按钮】点击事件，用于修改联系电话
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTel = telField.getText();
                String sql = "UPDATE user SET Tel=? WHERE Uid=?";
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,saveTel);
                    ps.setString(2,acc);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"保存成功！","",JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
