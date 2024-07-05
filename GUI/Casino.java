package GUI;

import DB.*;

import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class Casino extends JFrame implements ActionListener {
	GoodsDAO goodsDAO = new GoodsDAO();
	
	Random r = new Random();
	
	private JButton back, explanation;
	private int gold, bettinggold, sum = 0, chance = 3; // 주사위 3개의 합계, 기회
	private int[] dice = new int[3]; // 주사위 값 저장
    private JTextField diceText, goldText; // 사용자가 입력할 곳
    private JButton playB, diceB, goldB; // 플레이 버튼과, 입력한 후 확인 버튼
    private Timer diceTimer, diceresultTimer, resultTimer, chanceTimer; // 주사위 굴리는 이미지 시간, 결과 보여주는 시간
    private JLabel[] diceResultL = new JLabel[3]; // 주사위 결과 이미지
	private JLabel bgLabel, dealerL, diceTextL, goldTextL, diceL, diceRollingL, chanceL, chanceL2, resultL, casinoRuleL;
	private JLabel[] goodsL;
	private boolean isOpen = false;

	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	private ImageIcon casinoRuleIcon = new ImageIcon("src/img/casinorule.png");
	private ImageIcon ruleIcon = new ImageIcon("src/img/rule.png");
	private ImageIcon[] bgIcon = {
		    new ImageIcon("src/img/casinobackground.png"),
		    new ImageIcon("src/img/casinobackground2.png")
	};
	private ImageIcon dealrIcon = new ImageIcon("src/img/casinodealer.gif");
	private ImageIcon diceIcon = new ImageIcon("src/img/casinodice.png");
	private ImageIcon chanceIcon = new ImageIcon("src/img/casinoline.png");
	private ImageIcon playBIcon = new ImageIcon("src/img/casinobutton.png");
	private ImageIcon diceRolling = new ImageIcon("src/img/diceRolling.gif");
	private ImageIcon[] diceResultIcon = {
			new ImageIcon("src/img/dice1.png"),
			new ImageIcon("src/img/dice2.png"),
			new ImageIcon("src/img/dice3.png"),
			new ImageIcon("src/img/dice4.png"),
			new ImageIcon("src/img/dice5.png"),
			new ImageIcon("src/img/dice6.png")
	};
	
	public Casino(JLabel[] goodsL) {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("카지노"); // 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정

		this.goodsL = goodsL;
		
		try {
			gold = goodsDAO.getGoodsGold(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		JPanel bgPanel = new JPanel(); // 배경 패널
		bgPanel.setLayout(null);
		bgPanel.setSize(986,564); // 사이즈 설정
		add(bgPanel);
		
		casinoRuleL = new JLabel();
		casinoRuleL.setBounds(243,32,500,500);
		casinoRuleL.setIcon(casinoRuleIcon);
		casinoRuleL.setVisible(false);
		bgPanel.add(casinoRuleL);
		
		back = new JButton();
		back.setBounds(916,494,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		bgPanel.add(back);
	
		explanation = new JButton();
		explanation.setBounds(10,494,60,60);
		explanation.setBorderPainted(false); // 선 없음
		explanation.setContentAreaFilled(false); // 버튼 투명
		explanation.setIcon(ruleIcon);
		explanation.addActionListener(this);
		bgPanel.add(explanation);
		
		resultL = new JLabel();
		resultL.setBounds(0,480,986,80);
		resultL.setForeground(Color.GREEN);
		resultL.setFont(new Font("돋움", Font.BOLD, 20));
		resultL.setVerticalAlignment(SwingConstants.CENTER);
		resultL.setHorizontalAlignment(SwingConstants.CENTER);
		resultL.setVisible(false);
		bgPanel.add(resultL);
	
		chanceL2 = new JLabel();
		chanceL2.setBounds(0,180,986,80);
		chanceL2.setForeground(Color.RED);
		chanceL2.setFont(new Font("궁서", Font.BOLD, 20));
		chanceL2.setVerticalAlignment(SwingConstants.CENTER);
		chanceL2.setHorizontalAlignment(SwingConstants.CENTER);
		chanceL2.setVisible(false);
		bgPanel.add(chanceL2);
		
		chanceL = new JLabel();
		chanceL.setBounds(0,180,986,80);
		chanceL.setIcon(chanceIcon);
		chanceL.setVisible(false);
		bgPanel.add(chanceL);
		
		playB = new JButton("PLAY");
		playB.setBounds(421,420,143,64);
		playB.setIcon(playBIcon);
		playB.setBorderPainted(false);
		playB.setContentAreaFilled(false);
		playB.addActionListener(this);
		bgPanel.add(playB);
		
		goldText = new JTextField(20);
		goldText.setBounds(456,310,80,80);
		goldText.setVisible(false);
		bgPanel.add(goldText);
		
		goldTextL = new JLabel("<html>배팅할 금액을 입력해주세요<br>현재 소지한 금액 : " + gold +"</html>");
		goldTextL.setBounds(366, 230, 260, 60);
		goldTextL.setFont(new Font("돋움", Font.BOLD, 14));
		goldTextL.setVerticalAlignment(SwingConstants.CENTER);
		goldTextL.setHorizontalAlignment(SwingConstants.CENTER);
		goldTextL.setVisible(false);
		bgPanel.add(goldTextL);
		
		diceText = new JTextField(20);
		diceText.setBounds(456,310,80,80);
		diceText.setVisible(false);
		bgPanel.add(diceText);
		
		diceTextL = new JLabel("<html>주사위의 합을 입력해 주세요<br>입력(최대 : 18)</html>");
		diceTextL.setBounds(366, 230, 260, 60);
		diceTextL.setFont(new Font("돋움", Font.BOLD, 14));
		diceTextL.setVerticalAlignment(SwingConstants.CENTER);
		diceTextL.setHorizontalAlignment(SwingConstants.CENTER);
		diceTextL.setVisible(false);
		bgPanel.add(diceTextL);
		
		for(int i=0; i<3; i++) {
			diceResultL[i] = new JLabel();
			diceResultL[i].setBounds(372+(i*90),300,63,63);
			diceResultL[i].setVisible(false);
			bgPanel.add(diceResultL[i]);
		}
		
		diceRollingL = new JLabel();
		diceRollingL.setBounds(210,102,567,360);
		diceRollingL.setIcon(diceRolling);
		diceRollingL.setVisible(false);
		bgPanel.add(diceRollingL);
		
		goldB = new JButton("확인");
		goldB.setBounds(421,420,143,64);
		goldB.setVisible(false);
		goldB.addActionListener(this);
		bgPanel.add(goldB);
		
		diceB = new JButton("확인");
		diceB.setBounds(421,420,143,64);
		diceB.setVisible(false);
		diceB.addActionListener(this);
		bgPanel.add(diceB);

		dealerL = new JLabel();
		dealerL.setBounds(223, -30, 540, 360);
		dealerL.setIcon(dealrIcon);
		bgPanel.add(dealerL);
		
		diceL = new JLabel();
		diceL.setBounds(372,300,242,63);
		diceL.setIcon(diceIcon);
		bgPanel.add(diceL);
		
		bgLabel = new JLabel();
		bgLabel.setSize(986,564);
		bgLabel.setIcon(bgIcon[0]);
		bgPanel.add(bgLabel);
		
		setVisible(true); // 보이게 설정	
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if (obj == back) {
			if(chance == 3) {
				dispose();
			}
			else JOptionPane.showMessageDialog(null, "게임 중에는 나갈 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		} 
		if (obj == explanation) {
			isOpen = !isOpen;
			casinoRuleL.setVisible(isOpen);
		} 
		else if (obj == playB) {
			bgLabel.setIcon(bgIcon[1]);
			dealerL.setVisible(false);
			playB.setVisible(false);
			diceL.setVisible(false);
			if(chance == 3) {
			goldText.setVisible(true);
			goldB.setVisible(true);
			goldTextL.setVisible(true);
			}
			else {
				diceText.setVisible(true);
				diceB.setVisible(true);
				diceTextL.setVisible(true);
			}
		} 
		else if (obj == goldB) {
			String text = goldText.getText();
			try {
				int num = Integer.parseInt(text);
				if(num > gold) {
					JOptionPane.showMessageDialog(null, "소지한 금액보다 큰 금액입니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else {
					bettinggold = num;
					goldText.setVisible(false);
					goldB.setVisible(false);
					goldTextL.setVisible(false);
					diceText.setVisible(true);
					diceB.setVisible(true);
					diceTextL.setVisible(true);
				}
			}
			catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			}
			goldText.setText("");
		}
		else if (obj == diceB) {
			String text = diceText.getText();
			try {
				int num = Integer.parseInt(text);
				if(num < 3 || num > 18) {
					JOptionPane.showMessageDialog(null, "3~18사이 숫자를 입력해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if (chance > 0) {
						if (diceTimer != null && diceTimer.isRunning()) {
							diceTimer.stop();
						}
						diceB.setVisible(false);
						bgLabel.setIcon(bgIcon[0]);
				   		dealerL.setVisible(true);
						diceText.setVisible(false);
						diceTextL.setVisible(false);
						diceRollingL.setVisible(true);
						diceTimer = new Timer(1200, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								diceRollingL.setVisible(false);
								for (int i = 0; i < 3; i++) {
									dice[i] = r.nextInt(1, 6);
									diceResultL[i].setVisible(true);
									diceResultL[i].setIcon(diceResultIcon[dice[i] - 1]);
									sum += dice[i];
									diceresultTime();
								}
								diceTimer.stop();
								chance -= 1;
								resultL.setText("나온 숫자 : " + sum + "넣은 값 : " + num);
								resultTime();
								if (sum == num) {
									JOptionPane.showMessageDialog(null, "축하드립니다\n 획득한 골드 : " + bettinggold*(chance+1), "당첨", JOptionPane.INFORMATION_MESSAGE);
									gold += bettinggold*(chance+1);
									System.out.println(gold);
									goodsL[0].setText("골드 : " + gold);
									goldTextL.setText("<html>배팅할 금액을 입력해주세요<br>현재 소지한 금액 : " + gold +"</html>");
									chance = 3;
									try {
										goodsDAO.updateGoodsGold(1, gold);
									}
									catch(SQLException e2) {
										e2.printStackTrace();
									}
								} 
								else {
									chanceL2.setText(chance + "번 남았다....");
									if(chance == 0) {
										chanceL2.setText("기회를 모두 잃었다");
										gold -= bettinggold;
										System.out.println(gold);
										goodsL[0].setText("골드 : " + gold);
										goldTextL.setText("<html>배팅할 금액을 입력해주세요<br>현재 소지한 금액 : " + gold +"</html>");
										chance = 3;
										try {
											goodsDAO.updateGoodsGold(1, gold);
										}
										catch(SQLException e2) {
											e2.printStackTrace();
										}
									}							
									chanceTime();
								}
							}
						});
						diceTimer.start(); // 타이머 시작
						sum = 0;
					}
				}
			} 
			catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			}
			diceText.setText("");
		}
	}
	
	private void diceresultTime() {
		if (diceresultTimer != null && diceresultTimer.isRunning()) {
			diceresultTimer.stop();
   	    }
		diceresultTimer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				for(int i=0; i<3; i++) {
					diceResultL[i].setVisible(false);
				}		
				diceresultTimer.stop();
            }
        });
   		diceresultTimer.start(); // 타이머 시작
	}
	
	private void chanceTime() {
		if (chanceTimer != null && chanceTimer.isRunning()) {
			chanceTimer.stop();
   	    }
		chanceL.setVisible(true);
		chanceL2.setVisible(true);
		resultL.setVisible(true);
		chanceTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				chanceL.setVisible(false);
				chanceL2.setVisible(false);
				resultL.setVisible(false);
				chanceTimer.stop();
            }
        });
   		chanceTimer.start(); // 타이머 시작
	}
	
	private void resultTime() {
		if (resultTimer != null && resultTimer.isRunning()) {
			resultTimer.stop();
   	    }
		resultL.setVisible(true);
		resultTimer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				resultL.setVisible(false);
				resultTimer.stop();
		   		playB.setVisible(true);
		   		diceL.setVisible(true);
            }
        });
   		resultTimer.start(); // 타이머 시작
	}
}