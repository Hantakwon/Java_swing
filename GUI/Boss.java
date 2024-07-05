package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class Boss extends JFrame implements ActionListener{
	PlayerDAO playerDAO = new PlayerDAO();
	
	private int playerHp;
	private JButton back, ruleB;
	private JLabel bgLabel;
	private JLabel[] bossP = new JLabel[4]; 
	private JLabel[] bossL = new JLabel[4];
	private JLabel[] bossBack = new JLabel[4];
	private JButton[] bossB = new JButton[5];
	private int[] bossHp = {100,400,700,1000};
	private JLabel[] rewardL = new JLabel[4];
	private JLabel[] rewardL2 = new JLabel[4];
	private JLabel[] noticeL = new JLabel[4];
	private JLabel[] noticeL2 = new JLabel[4];

	private ImageIcon ruleIcon = new ImageIcon("src/img/rule.png");
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	private ImageIcon bgIcon = new ImageIcon("src/img/bosstower.png");
	private ImageIcon panelIcon = new ImageIcon("src/img/bossnotice.png");
	private ImageIcon[] reward2Icon = {
			new ImageIcon("src/img/bossjewel1.png"),
		    new ImageIcon("src/img/bossjewel2.png"),
		    new ImageIcon("src/img/bossjewel3.png"),
		    new ImageIcon("src/img/bossjewel4.png")
	};
		    
	public Boss() {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("보스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정

		JPanel infoPanel = new JPanel();
		infoPanel.setSize(986,564);
		infoPanel.setLayout(null);
		ct.add(new TowerScreen());
		ct.add(infoPanel);
		
		try {
			playerHp = playerDAO.getPlayerHp(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<4; i++) {
			bossP[i] = new JLabel();
			bossP[i].setLayout(null);
			bossP[i].setBounds(542,50,400,464);
			bossP[i].setVisible(false);
			
			bossL[i] = new JLabel();
			bossL[i].setBounds(10,10,380,40);
			bossL[i].setForeground(Color.BLACK); // 글자색 설정
			bossL[i].setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			bossL[i].setVerticalAlignment(SwingConstants.CENTER);
			bossL[i].setHorizontalAlignment(SwingConstants.CENTER);
			bossP[i].add(bossL[i]);
			
			bossB[i] = new JButton("도전");
			bossB[i].setBounds(150,408,100,50);
			bossB[i].addActionListener(this);
			bossP[i].add(bossB[i]);
				
			noticeL[i] = new JLabel("보스 정보");
			noticeL[i].setBounds(10,70,380,50);
			noticeL[i].setForeground(Color.BLACK); // 글자색 설정
			noticeL[i].setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			noticeL[i].setVerticalAlignment(SwingConstants.CENTER);
			noticeL[i].setHorizontalAlignment(SwingConstants.CENTER);
			bossP[i].add(noticeL[i]);
			
			noticeL2[i] = new JLabel("보스 체력 : " + bossHp[i]);
			noticeL2[i].setBounds(10,146,380,50);
			noticeL2[i].setForeground(Color.BLACK); // 글자색 설정
			noticeL2[i].setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			noticeL2[i].setVerticalAlignment(SwingConstants.CENTER);
			noticeL2[i].setHorizontalAlignment(SwingConstants.CENTER);
			bossP[i].add(noticeL2[i]);
			
			rewardL[i] = new JLabel("보상 정보");
			rewardL[i].setBounds(10,198,380,50);
			rewardL[i].setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			rewardL[i].setVerticalAlignment(SwingConstants.CENTER);
			rewardL[i].setHorizontalAlignment(SwingConstants.CENTER);
			bossP[i].add(rewardL[i]);	
			
			rewardL2[i] = new JLabel();
			rewardL2[i].setBounds(170,280,60,60);
			rewardL2[i].setIcon(reward2Icon[i]);
			bossP[i].add(rewardL2[i]);

			bossBack[i] = new JLabel();
			bossBack[i].setSize(400,464);
			bossBack[i].setIcon(panelIcon);
			bossP[i].add(bossBack[i]);
			
			infoPanel.add(bossP[i]);
		}
		bossL[0].setText("자쿰 (노멀)");
		bossL[1].setText("자쿰 (하드)");
		bossL[2].setText("루시드 (노멀)");
		bossL[3].setText("루시드 (하드)");
		ruleB = new JButton();
		ruleB.setBounds(70,494,60,60);
		ruleB.setBorderPainted(false); // 선 없음
		ruleB.setContentAreaFilled(false); // 버튼 투명
		ruleB.setIcon(ruleIcon);
		ruleB.addActionListener(this);
		infoPanel.add(ruleB);
		
		back = new JButton();
		back.setBounds(10,494,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		infoPanel.add(back);
		
		bgLabel = new JLabel();
		bgLabel.setSize(986,564);
		bgLabel.setIcon(bgIcon);
		infoPanel.add(bgLabel);
		
		setVisible(true); // 보이게 설정	
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) {
			dispose();
		}
		else if(obj == ruleB) {
			new BossRule();
		}
		else if(obj == bossB[0]) {
			if(playerHp > 0) {
				new Boss1(Boss.this);
			}
			else
				JOptionPane.showMessageDialog(null, "캐릭터가 사망상태입니다.","경고", JOptionPane.WARNING_MESSAGE);
		}
		else if(obj == bossB[1]) {
			if(playerHp > 0) {
				new Boss2(Boss.this);
			}
			else
				JOptionPane.showMessageDialog(null, "캐릭터가 사망상태입니다.","경고", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(obj == bossB[2]) {
			if(playerHp > 0) {
				new Boss3(Boss.this);
			}
			else
				JOptionPane.showMessageDialog(null, "캐릭터가 사망상태입니다.","경고", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(obj == bossB[3]) {
			if(playerHp > 0) {
				new Boss4(Boss.this);
			}
			else
				JOptionPane.showMessageDialog(null, "캐릭터가 사망상태입니다.","경고", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void alloff() {
		for(int i=0; i<4; i++) {
			bossP[i].setVisible(false);
		}
	}
	
	protected void hpRenewal() {
		try {
			playerHp = playerDAO.getPlayerHp(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	class TowerScreen extends JScrollPane implements ActionListener{ // 스크롤 화면
		private JPanel TowerPanel;
		
		private JButton[] TowerB = new JButton[5];
		private JLabel towerbg;
		private ImageIcon towerIcon = new ImageIcon("src/img/tower.png");
		
		private ImageIcon[] bossIcon = new ImageIcon[] {
			    new ImageIcon("src/img/boss1B.png"),
			    new ImageIcon("src/img/boss2B.png"),
			    new ImageIcon("src/img/boss3B.png"),
			    new ImageIcon("src/img/boss4B.png"),
			    new ImageIcon("src/img/boss5B.png")
			};
		
		
		public TowerScreen() {
			TowerPanel = new JPanel();
			TowerPanel.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
			TowerPanel.setPreferredSize(new Dimension(300, 1260)); // HomePanel의 크기 지정

			for(int i=0; i<5; i++) {
				TowerB[i] = new JButton();
				TowerB[i].setBounds(30,1010-(i*250),240,240);
				TowerB[i].setIcon(bossIcon[i]);
				TowerB[i].setContentAreaFilled(false); // 버튼의 배경색을 투명하게 만듭니다.
				TowerB[i].addActionListener(this);
				TowerPanel.add(TowerB[i]);
			}
			
			towerbg = new JLabel();
			towerbg.setSize(300,1260);
			towerbg.setIcon(towerIcon);
			TowerPanel.add(towerbg);
			// ------------------------------------ ScrollPane 설정 ------------------------------------
			
			setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // 세로 축 스크롤은 보이지 않게 해놓음
			getVerticalScrollBar().setUnitIncrement(50); // 스크롤 속도 설정
			setBorder(BorderFactory.createEmptyBorder()); // JScrollPane 테두리 보이지 않게 설정
	        setViewportView(TowerPanel); // JScrollPane에 표시할 컴포넌트(HomePanel)추가
	        setBounds(200, 0, 300, 564); // 켜지는 위치 및 크기 설정
	        
	        // 스크롤 바 최하단으로 이동
	        SwingUtilities.invokeLater(() -> {
	            JScrollBar verticalScrollBar = getVerticalScrollBar();
	            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	        });
		}
		
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource(); // 오브젝트 판단
			if(obj == TowerB[0]) {
				alloff();
				bossP[0].setVisible(true);
			}
			else if(obj == TowerB[1]) {
				alloff();
				bossP[1].setVisible(true);
			} 
			else if (obj == TowerB[2]) {
				alloff();
				bossP[2].setVisible(true);
			} 
			else if (obj == TowerB[3]) {
				alloff();
				bossP[3].setVisible(true);
			}
			else if (obj == TowerB[4]) {
				alloff();
				JOptionPane.showMessageDialog(null, "추후 추가예정입니다 \n기대해주세요.","알림", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

