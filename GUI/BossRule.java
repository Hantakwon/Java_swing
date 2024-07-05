package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BossRule extends JFrame implements ActionListener {
	private JLabel ruleL;
	private JButton back;
	private ImageIcon bossRuleIcon = new ImageIcon("src/img/bossrule.png");
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	public BossRule() {
		Container ct = getContentPane();
		setBounds(543, 102, 530, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("보스 룰"); // 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		JPanel ruleP = new JPanel();
		ruleP.setSize(500,560);
		ruleP.setLayout(null);
		ruleP.setBackground(Color.WHITE);
		ct.add(ruleP);
		
		back = new JButton();
		back.setBounds(10,490,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		ruleP.add(back);
		
		ruleL = new JLabel();
		ruleL.setSize(500,560);
		ruleL.setIcon(bossRuleIcon);
		ruleP.add(ruleL);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) {
			dispose();
		}
	}
}
