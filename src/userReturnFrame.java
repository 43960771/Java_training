import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class userReturnFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();




    /**
     * Create the frame.
     */
    public userReturnFrame() {
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

        JTextField userNameField = new JTextField();
        userNameField.setBackground(new Color(245,245,245));
        userNameField.setText("111");
        userNameField.setFont(new Font("微软雅黑", Font.BOLD, 18));
        userNameField.setBounds(37, 43, 103, 36);
        userNameField.setEditable(false);
        panel.add(userNameField);
        userNameField.setColumns(10);

        JButton infoButton = new JButton("个人信息");
        infoButton.setBackground(UIManager.getColor("Button.highlight"));
        infoButton.setFont(new Font("宋体", Font.PLAIN, 18));
        infoButton.setBounds(0, 105, 150, 50);
        panel.add(infoButton);
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userInfoFrame userInfoFrame = new userInfoFrame();
                userInfoFrame.setVisible(true);
                dispose();
            }
        });

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

        JButton borInfoButton = new JButton("当前借阅信息");
        borInfoButton.setFont(new Font("宋体", Font.PLAIN, 18));
        borInfoButton.setBackground(UIManager.getColor("Button.highlight"));
        borInfoButton.setBounds(0, 205, 150, 50);
        panel.add(borInfoButton);
        borInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userBorrowInfoFrame userBorrowInfoFrame = new userBorrowInfoFrame();
                userBorrowInfoFrame.setVisible(true);
                dispose();
            }
        });


        JButton orderButton = new JButton("历史订单");
        orderButton.setFont(new Font("宋体", Font.PLAIN, 18));
        orderButton.setBackground(UIManager.getColor("Button.highlight"));
        orderButton.setBounds(0, 255, 150, 50);
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
        returnButton.setBounds(0, 305, 150, 50);
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
        reLoginButton.setBounds(0, 353, 150, 50);
        panel.add(reLoginButton);
        reLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login login = new login();
                login.setVisible(true);
                dispose();
            }
        });



    }
}
