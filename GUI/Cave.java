package GUI;

import DB.*;

import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class Cave extends JFrame implements ActionListener {
	Random r = new Random(); // 랜덤 사용
	
	PlayerDAO playerDAO = new PlayerDAO();
	GoodsDAO goodsDAO = new GoodsDAO();
	CaveDAO caveDAO = new CaveDAO();
	InventoryDAO inventoryDAO = new InventoryDAO();
	
	private JProgressBar ExpBar;
	private JLabel[] goodsL; // Town클래스 골드 텍스트변경
    private JTextField floorText;
    private JButton floorButton, back, mini;
	// 타이머
	private Timer timer, timer2;
	
	// 진행 상태 표시용 바
	private JProgressBar oreBar;
	private JProgressBar monsterBar;
	
	// 각각의 변수 돈, 곡괭이 레벨, 무기 레벨, 힘, 플레이어 최대 체력, 플레이어 체력, 동굴 단계, 동굴 경험치, 광물 최대 체력, 광물 체력, 몬스터 최대 체력, 몬스터 체력
	private int gold, pickgrade, weapongrade, str, playerMaxHp, playerHp, playerLv, playerExp, caveMaxGrade, caveGrade, caveExp, oreMaxHp, oreHp, monsterMaxHp, monsterHp, division;
	private int claw, shoulder, core, goldOre, ruby, emerald;
	// 글이나 그림을 넣기 위한 레이블
	private JLabel chLabel, playerLvLabel, playerHpLabel, goldLabel, avatarLabel, oreLabel, oreHpLabel, caveLabel, monsterLabel, monsterHpLabel, caveGradeLabel, caveMaxGradeLabel, caveExpLabel;
	
	private ImageIcon chIcon = new ImageIcon("src/img/character.png");
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	private ImageIcon caveIcon = new ImageIcon("src/img/cave.png");
	private ImageIcon avatarIcon1 = new ImageIcon("src/img/avatar1.gif");
	private ImageIcon avatarIcon2 = new ImageIcon("src/img/avatar2.gif");
	private ImageIcon avatarIcon3 = new ImageIcon("src/img/avatar3.gif");
	private ImageIcon avatarAIcon1 = new ImageIcon("src/img/avatarA1.gif");
	private ImageIcon avatarAIcon2 = new ImageIcon("src/img/avatarA2.gif");
	private ImageIcon avatarAIcon3 = new ImageIcon("src/img/avatarA3.gif");
	private ImageIcon oreIcon = new ImageIcon("src/img/ore.png");
	private ImageIcon monsterIcon = new ImageIcon("src/img/monster.gif");
	private ImageIcon miniIcon = new ImageIcon("src/img/mini.png");
	
	// 곡괭이 단계에 따른 그림 설정
    private void setPickAvatar() {
        if(pickgrade == 1) {
            avatarLabel.setIcon(avatarIcon1);
        }
        else if(pickgrade == 2){
            avatarLabel.setIcon(avatarIcon2);
        }
        else if(pickgrade == 3){
            avatarLabel.setIcon(avatarIcon3);
        }
    }

	// 무기 단계에 따른 그림 설정
    private void setWeaponAvatar() {
    	if(weapongrade == 1) {
            avatarLabel.setIcon(avatarAIcon1);
        }
        else if(weapongrade == 2){
            avatarLabel.setIcon(avatarAIcon2);
        }
        else if(weapongrade == 3){
            avatarLabel.setIcon(avatarAIcon3);
        }
    }
    
    // 생성자
	public Cave(JLabel[] goodsL) {
		this.goodsL = goodsL;
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("동굴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정

		JPanel Status = new JPanel();
		Status.setBounds(0,0,300,600);
		Status.setLayout(null);
		add(Status);
		
		JPanel Screen = new JPanel();
		Screen.setBounds(300, 0, 700, 600);
		Screen.setLayout(null);
		add(Screen);
		
		
		try { // DB값 가져오기
			gold = goodsDAO.getGoodsGold(1);
			
			playerMaxHp = playerDAO.getPlayerMaxHp(1);
			playerHp = playerDAO.getPlayerHp(1);
			playerLv = playerDAO.getPlayerLv(1);
			playerExp = playerDAO.getPlayerExp(1);
		    pickgrade = playerDAO.getPlayerPickLv(1);
		    weapongrade = playerDAO.getPlayerWeaponLv(1);
		    str = playerDAO.getPlayerStr(1);
		    playerHp = playerDAO.getPlayerHp(1);
		    
			caveMaxGrade = caveDAO.getCaveMaxGrade(1);
			caveGrade = caveDAO.getCaveGrade(1);
			caveExp = caveDAO.getCaveExp(1);
			oreMaxHp = caveDAO.getOreMaxHp(1);
			oreHp = caveDAO.getOreHp(1);
			monsterMaxHp = caveDAO.getMonsterMaxHp(1);
			monsterHp = caveDAO.getMonsterHp(1);	
			division = caveDAO.getDivison(1);
			
			claw = inventoryDAO.getInventoryClaw(1);
			shoulder = inventoryDAO.getInventoryShoulder(1);
			core = inventoryDAO.getInventoryCore(1);
			goldOre = inventoryDAO.getInventoryGoldOre(1);
			ruby = inventoryDAO.getInventoryRuby(1);
			emerald = inventoryDAO.getInventoryEmerald(1);
		} 
		catch (SQLException e) { // 오류 발생 시
			e.printStackTrace();
		}


		playerLvLabel = new JLabel("레벨 : " + playerLv);
		playerLvLabel.setBounds(0, 200, 300, 30);
		playerLvLabel.setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		playerLvLabel.setVerticalAlignment(SwingConstants.CENTER);
		playerLvLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Status.add(playerLvLabel);
	
		ExpBar = new JProgressBar(0, 100);
		ExpBar.setBounds(10, 235, 280, 20);
		ExpBar.setForeground(Color.ORANGE);
		ExpBar.setValue((int) (((double) playerExp /  (playerLv*10)) * 100));
		Status.add(ExpBar);
		
		playerHpLabel = new JLabel("체력 : " + playerHp + "/" + playerMaxHp);
		playerHpLabel.setBounds(10, 260, 140, 30);
		playerHpLabel.setForeground(Color.RED); // 글자색 설정
		playerHpLabel.setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		playerHpLabel.setVerticalAlignment(SwingConstants.CENTER);
		playerHpLabel.setHorizontalAlignment(SwingConstants.LEFT);
		Status.add(playerHpLabel);

		goldLabel = new JLabel("돈 : " + gold);
		goldLabel.setBounds(150, 260, 140, 30);
		goldLabel.setForeground(new Color(255,255,0)); // 글자색 설정
		goldLabel.setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		goldLabel.setVerticalAlignment(SwingConstants.CENTER);
		goldLabel.setHorizontalAlignment(SwingConstants.LEFT);
		Status.add(goldLabel);

		caveGradeLabel = new JLabel("지하 " + caveGrade +"층");
		caveGradeLabel.setBounds(50,446,200,30);
		caveGradeLabel.setFont(new Font("굴림", Font.BOLD, 16)); 
		caveGradeLabel.setVerticalAlignment(SwingConstants.CENTER);
		caveGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Status.add(caveGradeLabel);
		
		caveMaxGradeLabel = new JLabel("도달한 최고층 " + caveMaxGrade +"층");
		caveMaxGradeLabel.setBounds(50,384,200,30);
		caveMaxGradeLabel.setFont(new Font("굴림", Font.BOLD, 16)); 
		caveMaxGradeLabel.setVerticalAlignment(SwingConstants.CENTER);
		caveMaxGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Status.add(caveMaxGradeLabel);
		
		caveExpLabel = new JLabel("동굴 경험치 : " + caveExp);
		caveExpLabel.setBounds(50,320,200,30);
		caveExpLabel.setFont(new Font("굴림", Font.BOLD, 16)); 
		caveExpLabel.setVerticalAlignment(SwingConstants.CENTER);
		caveExpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Status.add(caveExpLabel);
		
		floorText = new JTextField(20);
		floorText.setBounds(50,500,140,30);
		Status.add(floorText);
		
		floorButton = new JButton("이동");
		floorButton.setBounds(190,500,60,30);
		floorButton.addActionListener(this);
		Status.add(floorButton);
	
		back = new JButton();
		back.setBounds(10,10,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		Status.add(back);
		
		mini = new JButton();
		mini.setBounds(230,10,60,60);
		mini.setBorderPainted(false); // 선 없음
		mini.setContentAreaFilled(false); // 버튼 투명
		mini.setIcon(miniIcon);
		mini.addActionListener(this);
		Status.add(mini);
		
		chLabel = new JLabel();
		chLabel.setBounds(0, 0, 300, 564);
		chLabel.setIcon(chIcon);
		Status.add(chLabel);

		// -----------------------------------------------------------------
		
		avatarLabel = new JLabel();
		setPickAvatar();
		avatarLabel.setBounds(500,370,200,200);
		Screen.add(avatarLabel);

		// 바위 체력 설정 메소드
		oreHpLabel = new JLabel(oreHp + "/" + oreMaxHp);
		oreHpLabel.setBounds(190,160,330,30);
		oreHpLabel.setForeground(Color.RED); 
		oreHpLabel.setFont(new Font("굴림", Font.BOLD, 20)); 
		oreHpLabel.setVerticalAlignment(SwingConstants.CENTER);
		oreHpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Screen.add(oreHpLabel);

		// 바위 체력 바 설정 메소드
		oreBar = new JProgressBar(0,100);
		oreBar.setBounds(190,190,330,30);
		oreBar.setForeground(Color.ORANGE);
		oreBar.setValue((int)(((double)oreHp/oreMaxHp)*100));
		Screen.add(oreBar);

		// 바위 레이블 설정 메소드
		oreLabel = new JLabel();
		oreLabel.setIcon(oreIcon);
		oreLabel.setBounds(220,250,267,328);
		Screen.add(oreLabel);

		// 몬스터 체력 바 설정 메소드
		monsterBar = new JProgressBar(0,100);
		monsterBar.setBounds(150,260,330,30);
		monsterBar.setForeground(Color.ORANGE);
		monsterBar.setValue((int)(((double)monsterHp/monsterMaxHp)*100));
		monsterBar.setVisible(false);
		Screen.add(monsterBar);

		// 몬스터 레이블 설정 메소드
		monsterLabel = new JLabel();
		monsterLabel.setBounds(194,311,248,238);
		monsterLabel.setIcon(monsterIcon);
		monsterLabel.setVisible(false);
		Screen.add(monsterLabel);		

		// 몬스터 체력 레이블 설정 메소드
		monsterHpLabel = new JLabel(monsterHp + "/" + monsterMaxHp);
		monsterHpLabel.setBounds(150,230,330,30);
		monsterHpLabel.setForeground(Color.RED); 
		monsterHpLabel.setFont(new Font("굴림", Font.BOLD, 20)); 
		monsterHpLabel.setVerticalAlignment(SwingConstants.CENTER);
		monsterHpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monsterHpLabel.setVisible(false);
		Screen.add(monsterHpLabel);

		
		// 동굴 레이블 설정 메소드
		caveLabel = new JLabel();
		caveLabel.setBounds(0,0,700,564);
		caveLabel.setIcon(caveIcon);
		Screen.add(caveLabel);

		timer = new Timer(500, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        oreHp -= (str/4)*pickgrade;	        
		        try {
		        	caveDAO.updateDivision(1, 0);
                    caveDAO.updateOreHp(1, oreHp);
                } 
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
		        oreBar.setValue((int) (((double) oreHp / oreMaxHp) * 100));
		        oreHpLabel.setText(oreHp + "/" + oreMaxHp);
		        if(oreHp <= 0) {
		        	oreHp = 0;
		            int reward = r.nextInt(1,4);
		            if(reward == 1) {
		            	gold += (10 * caveGrade);
		            	goldLabel.setText("골드 : " + gold);
		            	goodsL[0].setText("골드 : " + gold);
		            	try {
							goodsDAO.updateGoodsGold(1, gold);
						} 
						catch (SQLException e2) {
							e2.printStackTrace();
						}
		            }
		            else if(reward == 2) {
		            	if(caveGrade < 10) {
		            		goldOre += 1;
		            	}
		            	else if(caveGrade >= 10) {
		            		ruby += 1;
		            	}
		            	else if(caveGrade >= 20) {
		            		emerald += 1;
		            	}
		            	try {
							inventoryDAO.updateInventoryGoldOre(1, goldOre);
							inventoryDAO.updateInventoryRuby(1, ruby);
							inventoryDAO.updateInventoryEmerald(1, emerald);
						} 
						catch (SQLException e2) {
							e2.printStackTrace();
						}
		            }
		            else if (reward == 3) {
		                monster();
		            }
					caveExp += 1;
					if (caveExp >= 10) {
						caveExp = 0;
						if(caveGrade < 30) {
							caveGrade += 1;
						}
						if(caveGrade > caveMaxGrade) {
							caveMaxGrade = caveGrade;
						}
						oreMaxHp += 10;
						monsterMaxHp += 10;
						caveGradeLabel.setText("지하 " + caveGrade + "층");
						monsterHp = monsterMaxHp;		
						try {
							caveDAO.updateCaveMaxGrade(1, caveMaxGrade);
							caveDAO.updateCaveGrade(1, caveGrade);
							caveDAO.updateMonsterMaxHp(1, 10*caveGrade);
							caveDAO.updateOreMaxHp(1, 10*caveGrade);
						}
						catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
					try {
						caveDAO.updateCaveExp(1, caveExp);
					} 
					catch (SQLException e3) {
						e3.printStackTrace();
					}
					caveExpLabel.setText("동굴 경험치 : " + caveExp);
					oreHp = oreMaxHp;
	            	try {
						caveDAO.updateOreHp(1, oreHp);
					} 
					catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
		    }
		});
		if(division == 0) {
			timer.start(); // 타이머 시작
		}
		else { 
			monster();
		}
		
		setVisible(true); // 보이게 설정	
	}
	
	private void monster() {
		oreHpLabel.setVisible(false);
		oreBar.setVisible(false);
		oreLabel.setVisible(false);
		monsterHpLabel.setVisible(true);
		monsterBar.setVisible(true);
		monsterLabel.setVisible(true);
		setWeaponAvatar();
		timer.stop();
		timer2 = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				monsterHp -= (str / 4) * weapongrade;
				playerHp -= caveGrade;
				monsterBar.setValue((int) (((double) monsterHp / monsterMaxHp) * 100));
				monsterHpLabel.setText(monsterHp + "/" + monsterMaxHp);
				playerHpLabel.setText("체력 : " + playerHp + "/" + playerMaxHp);
				if (monsterHp <= 0) {
					monsterHp = 0;
					oreHpLabel.setVisible(true);
					oreBar.setVisible(true);
					oreLabel.setVisible(true);
					monsterHpLabel.setVisible(false);
					monsterBar.setVisible(false);
					monsterLabel.setVisible(false);
					setPickAvatar();
					timer2.stop();
					timer.start();
					monsterHp = monsterMaxHp;
					playerExp += caveGrade;
					
					if(playerExp >= (playerLv*10)) {
						playerLv += 1;
						str += 4;
						playerMaxHp += 10;
						playerExp = 0;
					}
					playerLvLabel.setText("레벨 : " + playerLv);
					ExpBar.setValue((int) (((double) playerExp /  (playerLv*10)) * 100));
					if(caveGrade < 10) {
	            		claw += 1;
	            	}
	            	else if(caveGrade >= 10) {
	            		shoulder += 1;
	            	}
	            	else if(caveGrade >= 20) {
	            		core += 1;
	            	}
	            	try {
						inventoryDAO.updateInventoryClaw(1, claw);
						inventoryDAO.updateInventoryShoulder(1, shoulder);
						inventoryDAO.updateInventoryCore(1, core);
						playerDAO.updatePlayerExp(1, playerExp);
						playerDAO.updatePlayerLv(1, playerLv);
						playerDAO.updatePlayerMaxHp(1, playerMaxHp);
						playerDAO.updatePlayerStr(1, str);
					} 
					catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				if (playerHp <= 0) {
					playerHp = 0;
					timer.stop();
					timer2.stop();
					JOptionPane.showMessageDialog(null, "캐릭터가 사망하였습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					dispose();
				}
				try {
					caveDAO.updateDivision(1, 1);
					caveDAO.updateMonsterHp(1, monsterHp);
					playerDAO.updatePlayerHp(1, playerHp);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		timer2.start();
	}
	public void actionPerformed(ActionEvent e) { // 프레임 닫으면 작동 중지
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) {
			dispose();
			timer.stop();
			if(timer2 != null) { // timer2가 null이 아닌지 체크
	            timer2.stop();
	        }
		}
		else if(obj == mini) {
			new MiniInventory();
		}
		else if(obj == floorButton) {
            String text = floorText.getText();
            try {
                int num = Integer.parseInt(text);
                if(num <= 0) {
                	JOptionPane.showMessageDialog(null, "1층 이하로는 내려갈 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
                else if (num > caveMaxGrade) {
                	JOptionPane.showMessageDialog(null, "내려간 적 없는 층에는 갈 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
                else {
					caveGrade = num;
					try {
						caveGradeLabel.setText("지하 " + caveGrade + "층");
						caveDAO.updateCaveGrade(1, caveGrade);
						caveDAO.updateMonsterMaxHp(1, 10 * caveGrade);
						caveDAO.updateOreMaxHp(1, 10 * caveGrade);
						oreMaxHp = num * 10;
						oreHp = num * 10;
						monsterMaxHp = num * 10;
						monsterHp = num * 10;
					} catch (SQLException e3) { // 오류 발생 시
						e3.printStackTrace();
					}
				}
            } 
            catch (NumberFormatException e2) {
            	JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
            floorText.setText("");
		}
	}
}

