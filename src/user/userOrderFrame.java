package user;
import main.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userOrderFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();
    String Uid= login.acc,userName;
    String borrowID,borrowStartTime,borrowEndTime,bookName,Sid;



    /**
     * Create the frame.
     */
    public userOrderFrame() {
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
        String sql =  "select UserName from user where Uid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login.acc);
            //执行SQL语句
            rs=ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString("UserName");
            }
            userNameLabel.setText(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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



        JButton orderButton = new JButton("历史订单");
        orderButton.setFont(new Font("宋体", Font.PLAIN, 18));
        orderButton.setBackground(UIManager.getColor("Button.highlight"));
        orderButton.setBounds(0, 205, 150, 50);
        panel.add(orderButton);

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

        //创建右半部分JPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 0, 736, 411);
        contentPane.add(scrollPane);
        //创建表格
        String[] nums = {"订单号", "书籍编号","书籍名称", "借阅时间", "归还时间" };
        DefaultTableModel dm = new DefaultTableModel(nums, 0);

        JTable table = new JTable(dm);
        scrollPane.setColumnHeaderView(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);

        scrollPane.setViewportView(table);


        tableStyle.setTableStyle(table);//调用表格设置

        String sql1 = "select  * from borrowing,book where Uid=? and  book.Sid=borrowing.Sid;";
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement(sql1);
            ps.setString(1,Uid);
            //执行SQL语句
            rs = ps.executeQuery();
            //将查询出来的数据插入表格
            while (rs.next()) {
                borrowID = rs.getString("Oid");
                Sid = rs.getString("Sid");
                bookName = rs.getString("bookName");
                borrowStartTime = rs.getString("StartTime");
                borrowEndTime = rs.getString("EndTime");
                String[] nums1 = {borrowID,Sid,bookName, borrowStartTime, borrowEndTime};
                dm.addRow(nums1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
