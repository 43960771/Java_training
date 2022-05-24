package user;
import main.MySQLLink;
import main.login;
import  main.tableStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class userReturnFrame extends JFrame {
    ResultSet rs = null;
    //调用MySQLLink类，连接数据库
    Connection conn = MySQLLink.getConnection();
    String Uid = login.acc;
    String returnSid;
    String Sid,bookName,borrowStartTime,planEndTime;
    String[] tempSid = new String[100];
    int i = 0;


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
            String userName = null;
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

        JLabel lblNewLabel = new JLabel("未归还书籍");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        lblNewLabel.setBounds(160, 0, 190, 25);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 30, 736, 411);
        contentPane.add(scrollPane);

        String[] nums = {"书籍编号", "书籍名称", "借阅时间", "预计归还时间"};
        DefaultTableModel dm = new DefaultTableModel(nums, 0);

        JTable table = new JTable(dm);
        scrollPane.setColumnHeaderView(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);

        scrollPane.setViewportView(table);

        tableStyle.setTableStyle(table);//调用表格设置

        JLabel SidLabel = new JLabel("编号");
        SidLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        SidLabel.setBounds(315, 465, 54, 23);
        contentPane.add(SidLabel);

        //归还书籍编号输入框
        JTextField returnSidField = new JTextField();
        returnSidField.setBounds(366, 462, 180, 30);
        contentPane.add(returnSidField);
        returnSidField.setColumns(15);
        returnSidField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        returnSidField.setText("请输入要归还的书籍编号");
        returnSidField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                returnSidField.setText("");
            }
        });

        //归还按钮
        JButton returnButton1 = new JButton("确认归还");
        returnButton1.setBounds(581, 461, 93, 30);
        contentPane.add(returnButton1);

        //查询用户未归还书籍
        //日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前日期
        Date date = new Date(System.currentTimeMillis());
        int x = 0;
        Date pDate = null;
        String sql1 = "SELECT * FROM borrowing,book WHERE Uid =? and borrowing.EndTime is null  and book.State=1 AND borrowing.Sid=book.Sid";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql1);
            ps.setString(1,Uid);
            //执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()) {
                Sid = rs.getString("Sid");
                //将查询出来的书籍编号存入数组
                i++;
                tempSid[i] = Sid;
                bookName = rs.getString("bookName");
                borrowStartTime = rs.getString("StartTime");
                planEndTime = rs.getString("planEndTime");
                //记录逾期未归还书籍数量
                pDate = rs.getDate("planEndTime");
                if (date.after(pDate)){
                    x++;
                }
                String[] nums1 = {Sid, bookName, borrowStartTime, planEndTime};
                dm.addRow(nums1);
            }
            if (x != 0) {//弹窗：提醒未归还书籍数量
                JOptionPane.showMessageDialog(null, "你有【"+ x + "】本逾期未归还图书，请尽快归还！", "", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //归还按钮监听事件
        returnButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnSid = returnSidField.getText();
                //再次查询书籍是否能被归还
                int x;
                for (x=0;x <= i;x++){
                    if (returnSid.equals(tempSid[x])) break;
                }

                if (x <= i){
                    //日期
                    SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //获取当前日期
                    Date date = new Date(System.currentTimeMillis());
                    String borrowEndTime = endTime.format(date);
                    //更新book表中书籍状态State为0
                    String sql1 = "UPDATE book SET State=0 WHERE book.Sid=? AND book.State=1";
                    PreparedStatement ps1 = null;
                    try {
                        ps1 = conn.prepareStatement(sql1);
                        ps1.setString(1, returnSid);
                        ps1.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    //在borrowing表中加入EndTime归还时间信息
                    String sql2 = "UPDATE borrowing SET EndTime =? where borrowing.Sid=? AND borrowing.Uid=?";
                    PreparedStatement ps2 = null;
                    try {
                        ps2 = conn.prepareStatement(sql2);
                        ps2.setString(1, borrowEndTime);
                        ps2.setString(2, returnSid);
                        ps2.setString(3,Uid);
                        ps2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "归还书籍成功！", "", JOptionPane.INFORMATION_MESSAGE);
                        userReturnFrame userReturnFrame = new userReturnFrame();
                        userReturnFrame.setVisible(true);
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else{JOptionPane.showMessageDialog(null, "请输入正确的书籍编号！", "", JOptionPane.INFORMATION_MESSAGE);}
            }
        });

    }
}
