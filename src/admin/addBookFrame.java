package admin;
import main.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;


/**
 * @author DeerSky
 */
public class addBookFrame extends JFrame {

    private JPanel contentPane;
    private JTextField SidField;
    private JTextField bookNameField;
    private JPasswordField authorField;
    private JPasswordField CateField;
    private JLabel PriceLabel;
    private JTextField PriceField;
    String reg_bookSid;
    String reg_bookName;
    String reg_Author;
    String reg_Cate;
    String reg_Price;
    Connection conn = MySQLLink.getConnection();
    ResultSet rs = null;

    /**
     *
     * Create the frame.
     */
    public addBookFrame() {

        //更改GUI页面风格
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


        //创建Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 300, 675, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel SidLebel = new JLabel("书籍编号");
        SidLebel.setHorizontalAlignment(SwingConstants.CENTER);
        SidLebel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        SidLebel.setBounds(198, 78, 83, 26);
        contentPane.add(SidLebel);

        //书籍编号输入框
        SidField = new JTextField();
        SidField.setBounds(312, 78, 159, 28);
        contentPane.add(SidField);
        SidField.setColumns(10);
        //键盘监听，限制文本框最大输入字符与只能输入数字与大写字母B
        SidField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //限定最大输入字符
                String s = SidField.getText();
                if(s.length() >= 32) {
                    e.consume();
                }
                //限制只能输入数字
                int keyChar=e.getKeyChar();
                if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9 || keyChar == KeyEvent.VK_B) {
                } else {
                    e.consume();
                }
            }
        });

        JLabel bookNameLabel = new JLabel("书籍名称");
        bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        bookNameLabel.setBounds(198, 132, 83, 26);
        contentPane.add(bookNameLabel);

        //书名输入框
        bookNameField = new JTextField();
        bookNameField.setColumns(10);
        bookNameField.setBounds(312, 132, 159, 28);
        contentPane.add(bookNameField);
        //键盘监听，限制文本框最大输入字符
        bookNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = bookNameField.getText();
                if(s.length() >= 32) {
                    e.consume();
                }
            }
        });


        JLabel authorLabel = new JLabel("作者");
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        authorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        authorLabel.setBounds(228, 190, 55, 26);
        contentPane.add(authorLabel);

        //作者输入框
        JTextField authorField = new JTextField();
        authorField.setBounds(312, 190, 159, 28);
        contentPane.add(authorField);
        //键盘监听，限制文本框最大输入字符
        authorField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = authorField.getText() ;
                if(s.length() >= 32) {
                    e.consume();
                }
            }
        });

        JLabel CateLabel = new JLabel("出版社");
        CateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        CateLabel.setBounds(212, 245, 70, 26);
        contentPane.add(CateLabel);

        //出版社输入框
        JTextField CateField = new JTextField();
        CateField.setBounds(312, 245, 159, 28);
        contentPane.add(CateField);
        //键盘监听，限制文本框最大输入字符
        CateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = String.valueOf(CateField.getText());
                if(s.length() >= 32) {
                    e.consume();
                }
            }
        });

        PriceLabel = new JLabel("价格");
        PriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PriceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        PriceLabel.setBounds(218, 300, 70, 26);
        contentPane.add(PriceLabel);

        //价格输入框
        PriceField = new JTextField();
        PriceField.setColumns(10);
        PriceField.setBounds(312, 300, 159, 28);
        contentPane.add(PriceField);
        //键盘监听，限制文本框最大输入字符
        PriceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = PriceField.getText();
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

        JButton regButton = new JButton("确认添加");
        regButton.setBounds(292, 387, 113, 53);
        contentPane.add(regButton);
        //【确认注册】按钮点击事件
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取书籍编号Sid框内容
                reg_bookSid = SidField.getText();
                //获取书名框内容
                reg_bookName = bookNameField.getText();
                //获取作者框内容
                reg_Author = authorField.getText();
                //获取出版社内容
                reg_Cate= CateField.getText();
                //获取价格框内容
                reg_Price = PriceField.getText();

                //确认信息是否输入完全
                if (reg_bookSid.length()!=0 && reg_bookName.length()!=0 && reg_Author.length()!=0 && reg_Cate.length()!=0 && reg_Price.length()!=0) {

                        String sql = "INSERT INTO book (Sid,BookName,Author,Categories,Price) VALUES (?,?,?,?,?)";
                        PreparedStatement ps = null;
                        try {
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, reg_bookSid);
                            ps.setString(2, reg_bookName);
                            ps.setString(3, reg_Author);
                            ps.setString(4,reg_Cate);
                            ps.setString(5, reg_Price);
                            int i = ps.executeUpdate();
                            if (i > 0) {
                                JOptionPane.showMessageDialog(null, "添加成功！", "", JOptionPane.INFORMATION_MESSAGE);
                                addBookFrame addBookFrame = new addBookFrame();
                                addBookFrame.setVisible(true);
                                dispose();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            //数据库中已存在相同书籍时弹窗
                            JOptionPane.showMessageDialog(null,"已存在此书籍！","",JOptionPane.INFORMATION_MESSAGE);
                        }
                    //添加信息没填写完整时弹窗
                }else {JOptionPane.showMessageDialog(null,"请填写信息！","",JOptionPane.INFORMATION_MESSAGE);}
            }
        });
        //左上角返回主界面按钮
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

