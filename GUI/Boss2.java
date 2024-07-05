package GUI;

import DB.*;

import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class Boss2 extends JFrame implements KeyListener{
	PlayerDAO playerDAO = new PlayerDAO();
	InventoryDAO inventoryDAO = new InventoryDAO();
	StoreDAO storeDAO = new StoreDAO();
	
	Random r = new Random();
	
	private Boss boss;
	
	private JLabel bossHpL, bossL, playerL, mapL, hpbarL; // 그림 및 글자 삽입 용 레이블
	private JLabel[] bossAttackL = new JLabel[8];
	
	private int weaponGrade, str, weaponUpGrade, totalDamage, potion1, potion2;
    private int waitTime = 0, bossJewel;
    // 각 객체의 크기를 가져옴
    private Rectangle[] bossAttackR = new Rectangle[8];
    private Rectangle bossR, playerR, playerhitR, playerLeftR, playerRightR, skillQLeftR, skillQRightR, skillWLeftR, skillWRightR,skillRR;
    // 각 스킬 세부조정 타이머 (스킬이 나오는 시간 조절)
	private Timer bossAttackTimer, bossAttackTimer2, attackTimer, jumpTimer, skillQTimer, skillWTimer, skillETimer, skillRTimer;
	private Timer coolTimeQ, noticeQ, coolTimeW, noticeW, coolTimeE, noticeE, coolTimeR, noticeR;
	private int bossMaxHp=400, bossHp=400; // 보스 체력 (DB 사용해야하나 생각중)
	private int playerMaxHp, playerHp;
	private JProgressBar bossHpBar, playerHpBar; // 보스 체력바
	private boolean direction, jumping = false; // 방향결정 true면 왼쪽, false면 오른쪽;

	
	private ImageIcon bossIcon = new ImageIcon("src/img/boss1.gif");
	private ImageIcon bossAttackIcon = new ImageIcon("src/img/boss1attack.png");
	private ImageIcon bossMapIcon = new ImageIcon("src/img/boss1map.png");
	
	private JLabel[] skillIconL = new JLabel[6];
	private JLabel[] skillIconL2 = new JLabel[6];
	private JLabel[] skillIconL3 = new JLabel[6];
	private JLabel[] NoticeL = new JLabel[2];
	// 스킬 이미지를 담을 레이블
	private JLabel [] skillL = new JLabel[6];
	private JLabel [] skillNoticeL = new JLabel[8];
	
	// ------------------------ 무기가 1단계인 경우 ------------------------ 
	private ImageIcon[] playerA1Icon = {
		    new ImageIcon("src/img/standLeft.gif"),
		    new ImageIcon("src/img/standRight.gif"),
		    new ImageIcon("src/img/walkLeft.gif"),
		    new ImageIcon("src/img/walkRight.gif")
	};
	private ImageIcon[] playerattackA1Icon = {
		    new ImageIcon("src/img/attackLeft1A1.png"),
		    new ImageIcon("src/img/attackLeft2A1.png"),
		    new ImageIcon("src/img/attackLeft3A1.png"),
		    new ImageIcon("src/img/attackRight1A1.png"),
		    new ImageIcon("src/img/attackRight2A1.png"),
		    new ImageIcon("src/img/attackRight3A1.png")
	};
	
	
	// ------------------------ 무기가 2단계인 경우 ------------------------ 
	private ImageIcon[] playerA2Icon = {
		    new ImageIcon("src/img/standLeft2.gif"),
		    new ImageIcon("src/img/standRight2.gif"),
		    new ImageIcon("src/img/walkLeft2.gif"),
		    new ImageIcon("src/img/walkRight2.gif")
	};
	private ImageIcon[] playerattackA2Icon = {
		    new ImageIcon("src/img/attackLeft1A2.png"),
		    new ImageIcon("src/img/attackLeft2A2.png"),
		    new ImageIcon("src/img/attackLeft3A2.png"),
		    new ImageIcon("src/img/attackRight1A2.png"),
		    new ImageIcon("src/img/attackRight2A2.png"),
		    new ImageIcon("src/img/attackRight3A2.png")
	};
	
	// ------------------------ 무기가 3단계인 경우 ------------------------ 
	private ImageIcon[] playerA3Icon = {
		    new ImageIcon("src/img/standLeft3.gif"),
		    new ImageIcon("src/img/standRight3.gif"),
		    new ImageIcon("src/img/walkLeft3.gif"),
		    new ImageIcon("src/img/walkRight3.gif")
	};
	private ImageIcon[] playerattackA3Icon = {
		    new ImageIcon("src/img/attackLeft1A3.png"),
		    new ImageIcon("src/img/attackLeft2A3.png"),
		    new ImageIcon("src/img/attackLeft3A3.png"),
		    new ImageIcon("src/img/attackRight1A3.png"),
		    new ImageIcon("src/img/attackRight2A3.png"),
		    new ImageIcon("src/img/attackRight3A3.png")
	};
	
	
	private ImageIcon hpBarIcon = new ImageIcon("src/img/hpbar.png");
	
	private ImageIcon[] NoticeIcon = {
			new ImageIcon("src/img/attack.png"),
			new ImageIcon("src/img/jump.png")
	};
	
	// ------------------------ 스킬 이미지 ------------------------ 
	private ImageIcon[] skillIcon = {
			new ImageIcon("src/img/skill1Left.gif"),
			new ImageIcon("src/img/skill1Right.gif"),
			new ImageIcon("src/img/skill2Left.gif"),
			new ImageIcon("src/img/skill2Right.gif"),
			new ImageIcon("src/img/skill3.gif"),
			new ImageIcon("src/img/skill4.gif")
	};
	
	private ImageIcon[] skillIconIcon = {
			new ImageIcon("src/img/potion1.png"),
			new ImageIcon("src/img/potion2.png"),
			new ImageIcon("src/img/skill1Icon.png"),
			new ImageIcon("src/img/skill2Icon.png"),
			new ImageIcon("src/img/skill3Icon.png"),
			new ImageIcon("src/img/skill4Icon.png")
	};
	
	private ImageIcon[] skillNoticeIcon = {
			new ImageIcon("src/img/zicon.png"),
			new ImageIcon("src/img/spaceicon.png"),
			new ImageIcon("src/img/1icon.png"),
			new ImageIcon("src/img/2icon.png"),
			new ImageIcon("src/img/qicon.png"),
			new ImageIcon("src/img/wicon.png"),
			new ImageIcon("src/img/eicon.png"),
			new ImageIcon("src/img/ricon.png")
	};
	
	private ImageIcon potionIcon = new ImageIcon("src/img/potion1_2.png");
	private ImageIcon potionIcon2 = new ImageIcon("src/img/potion2_2.png");
	
	private ImageIcon[] skillIconIcon2 = {
			new ImageIcon("src/img/skill1Icon2.png"),
			new ImageIcon("src/img/skill1Icon2.png"),
			new ImageIcon("src/img/skill1Icon2.png"),
			new ImageIcon("src/img/skill2Icon2.png"),
			new ImageIcon("src/img/skill3Icon2.png"),
			new ImageIcon("src/img/skill4Icon2.png")
	};
	
	
	private int player_x = 0, player_y = 384, player_y_origin; // 캐릭터의 초기 위치
	
	public Boss2(Boss boss) {
		this.boss = boss;
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("보스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		// DB에서 데이터 값 가져오기
		try {
			playerMaxHp = playerDAO.getPlayerMaxHp(1);
			playerHp = playerDAO.getPlayerHp(1);
			weaponGrade = playerDAO.getPlayerWeaponLv(1);
			str = playerDAO.getPlayerStr(1);
			potion1 = inventoryDAO.getInventoryPotion1(1);
			potion2 = inventoryDAO.getInventoryPotion2(1);
			bossJewel = inventoryDAO.getInventoryBossjewel2(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		totalDamage = ((str/4) * weaponGrade) + weaponUpGrade;
		
		System.out.println(totalDamage);
		
		bossAttackCoolTime();
		
		JPanel battleP = new JPanel();
		battleP.setSize(986,564);
		battleP.setLayout(null);
		ct.add(battleP);

		// ------------------- 아래 창 패널 -------------------
		JPanel statusBar = new JPanel();
		statusBar.setBounds(0, 490, 986, 74);
		statusBar.setLayout(null);
		statusBar.setBackground(Color.GRAY);
		battleP.add(statusBar);

		playerHpBar = new JProgressBar(0, 100);
		playerHpBar.setBounds(70, 23, 208, 28);
		playerHpBar.setForeground(Color.RED);
		playerHpBar.setValue((int) (((double) playerHp / playerMaxHp) * 100));
		statusBar.add(playerHpBar);
		
		hpbarL= new JLabel();
		hpbarL.setBounds(0,0,300,74);
		hpbarL.setIcon(hpBarIcon);
		statusBar.add(hpbarL);

		// ------------------- Battle Panel -------------------
		for (int i = 0; i < bossAttackL.length; i++) {
			bossAttackL[i] = new JLabel();
			bossAttackL[i].setSize(91, 91);
			bossAttackL[i].setVisible(false);
			bossAttackL[i].setIcon(bossAttackIcon);
			battleP.add(bossAttackL[i]);
		}

		// ------------------- 쿨타임 숫자 레이블 -------------------
		for (int i = 0; i < skillIconL3.length; i++) {
			skillIconL3[i] = new JLabel();
			skillIconL3[i].setBounds(596 + (i * 64), 7, 60, 60);
			skillIconL3[i].setVisible(false);
			skillIconL3[i].setForeground(Color.BLUE); // 글자색 설정
			skillIconL3[i].setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			skillIconL3[i].setVerticalAlignment(SwingConstants.CENTER);
			skillIconL3[i].setHorizontalAlignment(SwingConstants.CENTER);
			statusBar.add(skillIconL3[i]);
		}
		
		for (int i = 0; i < skillNoticeL.length; i++) {
			skillNoticeL[i] = new JLabel();
			skillNoticeL[i].setBounds(468 + (i * 64), 440, 60, 60);
			skillNoticeL[i].setIcon(skillNoticeIcon[i]);
			battleP.add(skillNoticeL[i]);
		}
		
		for (int i = 0; i < NoticeL.length; i++) {
			NoticeL[i] = new JLabel();
			NoticeL[i].setBounds(468 + (i * 64), 7, 60, 60);
			NoticeL[i].setIcon(NoticeIcon[i]);
			statusBar.add(NoticeL[i]);
		}
		 
		// ------------------- 회색 아이콘 레이블 -------------------
		for (int i = 0; i < skillIconL2.length; i++) {
			skillIconL2[i] = new JLabel();
			skillIconL2[i].setBounds(596 + (i * 64), 7, 60, 60);
			skillIconL2[i].setIcon(skillIconIcon2[i]);
			skillIconL2[i].setVisible(false);
			statusBar.add(skillIconL2[i]);
		}
		
		// ------------------- 스킬 아이콘 레이블 -------------------
		for (int i = 0; i < skillIconL.length; i++) {
			skillIconL[i] = new JLabel();
			skillIconL[i].setBounds(596 + (i * 64), 7, 60, 60);
			skillIconL[i].setIcon(skillIconIcon[i]);
			statusBar.add(skillIconL[i]);
		}
		
		if(potion1 <= 0 ) {
			skillIconL[0].setIcon(potionIcon);
		}
		if(potion2 <= 0) {
			skillIconL[1].setIcon(potionIcon2);
		}
		bossHpBar = new JProgressBar(0, 100);
		bossHpBar.setBounds(10, 10, 966, 30);
		bossHpBar.setForeground(Color.ORANGE);
		bossHpBar.setValue((int) (((double) bossHp / bossMaxHp) * 100));
		battleP.add(bossHpBar);

		bossHpL = new JLabel("테스트 " + waitTime);
		bossHpL.setBounds(10,40,966,30);
		battleP.add(bossHpL);
		
		// ------------------- 스킬 이펙트 레이블  -------------------
		for(int i=0; i<skillL.length; i++) {
			skillL[i] = new JLabel();
			skillL[i].setIcon(skillIcon[i]);
			skillL[i].setVisible(false);
			battleP.add(skillL[i]);
		}
		skillL[0].setSize(339,200);
		skillL[1].setSize(339,200);
		skillL[2].setSize(470,277);
		skillL[3].setSize(470,277);
		skillL[4].setSize(208,200);
		skillL[5].setSize(986,564);
		
		playerL = new JLabel();
		if(weaponGrade == 1) {
			playerL.setIcon(playerA1Icon[1]);
		}
		else if(weaponGrade == 2) {
			playerL.setIcon(playerA2Icon[1]);
		}
		else if(weaponGrade == 3) {
			playerL.setIcon(playerA3Icon[1]);
		}
		
		playerL.setBounds(player_x,player_y,90,70);
		playerL.setVerticalAlignment(JLabel.TOP);
		playerL.setHorizontalAlignment(JLabel.CENTER);
		battleP.add(playerL); // playerL을 bossL보다 먼저 add해야만 플레이어가 위로 나옴 
		
    	playerR = new Rectangle(playerL.getBounds());
    	playerhitR= new Rectangle(playerR.x+25, playerR.y, 40, playerR.height);
    	
		bossL = new JLabel();
		bossL.setIcon(bossIcon);
		bossL.setBounds(290,70,385,387);
		battleP.add(bossL);
		
		mapL = new JLabel();
		mapL.setIcon(bossMapIcon);
		mapL.setBounds(0,0,986,564);
		battleP.add(mapL);
		
		bossR = new Rectangle(bossL.getBounds());
		
		addKeyListener(this); // 키 입력에 반응하기 위한 KeyListener 추가
        setFocusable(true); // 키 입력을 받기 위해선 컴포넌트가 포커스를 가지고 있어야 함
        
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) { // 키 코드에 따라 캐릭터 위치 변경
            case KeyEvent.VK_LEFT: // 왼쪽 방향키
            	if(weaponGrade == 1) {
            	playerL.setIcon(playerA1Icon[2]);
            	}
            	else if(weaponGrade == 2) {
            	playerL.setIcon(playerA2Icon[2]);
            	}
            	else if(weaponGrade == 3) {
            	playerL.setIcon(playerA3Icon[2]);
            	}
            	direction = true;
				if (player_x - 0 >= -15) { // 화면 왼쪽 경계 검사
					player_x -= 10;
					locationRenewal();
				}  
                break;
            case KeyEvent.VK_RIGHT: // 오른쪽 방향키
            	if(weaponGrade == 1) {
            	playerL.setIcon(playerA1Icon[3]);
            	}
            	else if(weaponGrade == 2) {
            	playerL.setIcon(playerA2Icon[3]);
            	}
            	else if(weaponGrade == 3) {
            	playerL.setIcon(playerA3Icon[3]);
            	}
            	direction = false;
            	if (player_x + 10 <= 916) { // 화면 오른쪽 경계를 검사
                    player_x += 10;
                    locationRenewal();
                }
                break;
            case KeyEvent.VK_Z: // Z 버튼
            	if (attackTimer != null && attackTimer.isRunning()) {
                    // attackTimer가 실행 중이면 아무런 동작도 수행하지 않음
                    break;
                }
            	else if(direction == true) {
    	    		attackLeftA1();
					locationRenewal();
					if (bossR.intersects(playerLeftR)) {
						bossHp -= totalDamage; // bossHp 감소
						bossHpRenewal();
						gameOver();
					}
				} else {
					attackRightA1();
					locationRenewal();
					if (bossR.intersects(playerRightR)) {
						bossHp -= totalDamage; // bossHp 감소
						bossHpRenewal();
						gameOver();
					}
				}
				break;
			case KeyEvent.VK_SPACE: // 위쪽 방향키
				if (jumpTimer != null && jumpTimer.isRunning()) {
					// attackTimer가 실행 중이면 아무런 동작도 수행하지 않음
					break;
				} 
				else {
					if (!jumping) {
						jumping = true;
						player_y_origin = player_y;
						jump();
					}
				}
				break;
			case KeyEvent.VK_1: // 숫자 1
				if (potion1 > 0) {
					potion1 -= 1;
					try {
						storeDAO.updateStoreCount(5, 1);
						}
						catch (SQLException e2) {
							e2.printStackTrace();
						}
					playerHp += (int) (((double) playerMaxHp / 100) * 20);
					if(playerHp >= playerMaxHp) {
						playerHp = playerMaxHp;
					};
					try {
						playerDAO.updatePlayerHp(1, playerHp);
						inventoryDAO.updateInventoryPotion1(1, potion1);
					}
					catch(SQLException e2) {
						e2.printStackTrace();
					}
					playerHpRenewal();
					skillIconL[0].setIcon(potionIcon);
					
				}
				break;
			case KeyEvent.VK_2: // 숫자 2
				if (potion2 > 0) {
					potion2 -= 1;
					try {
						storeDAO.updateStoreCount(6, 1);
						}
						catch (SQLException e2) {
							e2.printStackTrace();
						}
					playerHp += (int) (((double) playerMaxHp / 100) * 30);
					if(playerHp >= playerMaxHp) {
						playerHp = playerMaxHp;
					};
					try {
						playerDAO.updatePlayerHp(1, playerHp);
						inventoryDAO.updateInventoryPotion2(1, potion2);
					}
					catch(SQLException e2) {
						e2.printStackTrace();
					}
					playerHpRenewal();		
					skillIconL[1].setIcon(potionIcon2);
				}
				break;
			case KeyEvent.VK_Q: // Q 버튼
				if (coolTimeQ != null && coolTimeQ.isRunning()) {
					// attackTimer가 실행 중이면 아무런 동작도 수행하지 않음
					break;
				} 
				else {
					if (direction == true) {
						attackLeftA1();
						skillQLeft();
						locationRenewal();
						if (bossR.intersects(skillQLeftR)) {
							bossHp -= totalDamage * 2; // bossHp 감소
							bossHpRenewal();
							gameOver();
						}

					} 
					else {
						attackRightA1();
						skillQRight();
						locationRenewal();
						if (bossR.intersects(skillQRightR)) {
							bossHp -= totalDamage * 2; // bossHp 감소
							bossHpRenewal();
							gameOver();
						}
					}
					skillIconL2[2].setVisible(true);
					skillIconL3[2].setVisible(true);
					coolTimeQ = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							coolTimeQ.stop();
						}
					});
					coolTimeQ.start();
			        if (noticeQ != null && noticeQ.isRunning()) {
			        	noticeQ.stop();
			        }
					double delay = 2000; // milliseconds
					double targetY = 60;
					double stepY = targetY / (delay / 100);
					noticeQ = new Timer(100, new ActionListener() {	
						int i=0;
						int second = 0;
						double currentY=0;
						public void actionPerformed(ActionEvent e) {
			                if (currentY <= targetY) {
			                    currentY += stepY;
			                    second = 2 - (i/10);
			                    skillIconL3[2].setText(""+second+"");
			                    skillIconL2[2].setVerticalAlignment(JLabel.BOTTOM);
			                    skillIconL2[2].setBounds(724,(int)(7+currentY), 60, (int)(60-currentY));
							}
			                else {
			                    noticeQ.stop();
			                    skillIconL3[2].setVisible(false);
			                    skillIconL2[2].setVisible(false);
			                    skillIconL2[2].setBounds(724, 7, 60, 60);
			                }
			                i++;
			            }
					});
					noticeQ.start();
				}
				break;
			case KeyEvent.VK_W: // W 버튼
				if (coolTimeW != null && coolTimeW.isRunning()) {
					// attackTimer가 실행 중이면 아무런 동작도 수행하지 않음
					break;
				} 
				else {
					if (direction == true) {
						attackLeftA1();
						skillWLeft();
						locationRenewal();
						if (bossR.intersects(skillWLeftR)) {
							bossHp -= totalDamage * 3; // bossHp 감소
							bossHpRenewal();
							gameOver();
						}

					} 
					else {
						attackRightA1();
						skillWRight();
						locationRenewal();
						if (bossR.intersects(skillWRightR)) {
							bossHp -= totalDamage * 3; // bossHp 감소
							bossHpRenewal();
							gameOver();
						}
					}
					skillIconL2[3].setVisible(true);
					skillIconL3[3].setVisible(true);
					coolTimeW = new Timer(6000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							coolTimeW.stop();
						}
					});
					coolTimeW.start();
			        if (noticeW != null && noticeW.isRunning()) {
			        	noticeW.stop();
			        }
					double delay = 6000; // milliseconds
					double targetY = 60;
					double stepY = targetY / (delay / 100);
					noticeW = new Timer(100, new ActionListener() {	
						int i=0;
						int second = 0;
						double currentY=0;
						public void actionPerformed(ActionEvent e) {
			                if (currentY <= targetY) {
			                    currentY += stepY;
			                    second = 6 - (i/10);
			                    skillIconL3[3].setText(""+second+"");
			                    skillIconL2[3].setVerticalAlignment(JLabel.BOTTOM);
			                    skillIconL2[3].setBounds(788,(int)(7+currentY), 60, (int)(60-currentY));
							}
			                else {
			                    noticeW.stop();
			                    skillIconL3[3].setVisible(false);
			                    skillIconL2[3].setVisible(false);
			                    skillIconL2[3].setBounds(788, 7, 60, 60);
			                }
			                i++;
			            }
					});
					noticeW.start();
				}
				break;
				
			case KeyEvent.VK_E: // E 버튼
				if (coolTimeE != null && coolTimeE.isRunning()) {
					// attackTimer가 실행 중이면 아무런 동작도 수행하지 않음
					break;
				}
				else {
					skillE();
					playerHp += (int)(((double)playerMaxHp / 100) * 10);
					if(playerHp >= playerMaxHp) {
						playerHp = playerMaxHp;
					};
					try {
						playerDAO.updatePlayerHp(1, playerHp);
					}
					catch(SQLException e2) {
						e2.printStackTrace();
					}
					playerHpRenewal();
					skillIconL3[4].setVisible(true);
					skillIconL2[4].setVisible(true);
					coolTimeE = new Timer(10000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							coolTimeE.stop();
						}
					});
					coolTimeE.start();
			        if (noticeE != null && noticeE.isRunning()) {
			        	noticeE.stop();
			        }
					double delay = 10000; // milliseconds
					double targetY = 60;
					double stepY = targetY / (delay / 100);
					noticeE = new Timer(100, new ActionListener() {	
						int i=0;
						int second = 0;
						double currentY=0;
						public void actionPerformed(ActionEvent e) {
			                if (currentY <= targetY) {
			                    currentY += stepY;
			                    second = 10 - (i/10);
			                    skillIconL3[4].setText(""+second+"");
			                    skillIconL2[4].setVerticalAlignment(JLabel.BOTTOM);
			                    skillIconL2[4].setBounds(852,(int)(8+currentY), 60, (int)(60-currentY));
							}
			                else {
			                    noticeE.stop();
			                    skillIconL3[4].setVisible(false);
			                    skillIconL2[4].setVisible(false);
			                    skillIconL2[4].setBounds(852, 7, 60, 60);
			                }
			                i++;
			            }
					});
					noticeE.start();
				}
				break;

			case KeyEvent.VK_R: // R 버튼
				if (coolTimeR != null && coolTimeR.isRunning()) {
					break;
				} 
				else {
					skillR();
					bossHp -= totalDamage * 10; // bossHp 감소
					bossHpRenewal();
					gameOver();
					skillIconL3[5].setVisible(true);
					skillIconL2[5].setVisible(true);
					coolTimeR = new Timer(20000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							coolTimeR.stop();
						}
					});
					coolTimeR.start();
					if (bossAttackTimer != null && bossAttackTimer.isRunning()) {
					    bossAttackTimer.stop();
					}
					for(int i=0; i<4; i++) {
						bossAttackL[i].setLocation(0,0);
						bossAttackL[i].setVisible(false);
					}
			        if (noticeR != null && noticeR.isRunning()) {
			        	noticeR.stop();
			        }
					double delay = 20000; // milliseconds
					double targetY = 60;
					double stepY = targetY / (delay / 100);
					noticeR = new Timer(100, new ActionListener() {	
						int i=0;
						int second = 0;
						double currentY=0;
						public void actionPerformed(ActionEvent e) {
			                if (currentY <= targetY) {
			                    currentY += stepY;
			                    second = 20 - (i/10);
			                    skillIconL3[5].setText(""+second+"");
			                    skillIconL2[5].setVerticalAlignment(JLabel.BOTTOM);
			                    skillIconL2[5].setBounds(916,(int)(8+currentY), 60, (int)(60-currentY));
							}
			                else {
			                    noticeE.stop();
			                    skillIconL3[5].setVisible(false);
			                    skillIconL2[5].setVisible(false);
			                    skillIconL2[5].setBounds(916, 7, 60, 60);
			                }
			                i++;
			            }
					});
					noticeR.start();
				}
				break;
			}
			playerL.setLocation(player_x, player_y); // 캐릭터의 위치를 변경
    }
	
	public void keyReleased(KeyEvent e) {
	    int key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
	    	if(direction == true) {
	    		if(weaponGrade == 1) {
	    		playerL.setIcon(playerA1Icon[0]); // 왼쪽 또는 오른쪽 방향키를 뗐을 때, 원래 이미지로 변경
	    		}
	    		else if(weaponGrade == 2) {
		    		playerL.setIcon(playerA2Icon[0]); // 왼쪽 또는 오른쪽 방향키를 뗐을 때, 원래 이미지로 변경
		    	}
	    		else if(weaponGrade == 3) {
		    		playerL.setIcon(playerA3Icon[0]); // 왼쪽 또는 오른쪽 방향키를 뗐을 때, 원래 이미지로 변경
		    	}
	    	}
	    	else {
	    		if(weaponGrade == 1) {
	    		playerL.setIcon(playerA1Icon[1]);
	    		}
	    		else if(weaponGrade == 2) {
		    		playerL.setIcon(playerA2Icon[1]);
		    	}
	    		else if(weaponGrade == 3) {
		    		playerL.setIcon(playerA3Icon[1]);
		    	}
	    	}
	    }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    
    
    private void bossAttack() {
		int attack_x[] = new int[8];
    	for(int i=0; i<bossAttackL.length; i++) {
    		int randomx = r.nextInt(0,895);
    		attack_x[i] = randomx;
        	bossAttackL[i].setVisible(true);
        	bossAttackL[i].setLocation(attack_x[i], 0);
    	}
        if (bossAttackTimer != null && bossAttackTimer.isRunning()) {
            bossAttackTimer.stop();
        }
        int delay = 3000; // milliseconds
        int targetY = 564; 
        int stepY = targetY / (delay / 100); // Assuming timer tick every 100 ms
        
        bossAttackTimer = new Timer(100, new ActionListener() {
            int currentY = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentY < targetY) {
                    currentY += stepY;
                    for(int i=0; i<bossAttackL.length; i++) {
                    	bossAttackL[i].setLocation(attack_x[i], currentY);
						bossAttackR[i] = bossAttackL[i].getBounds();
						if (bossAttackR[i] != null) {
							if (bossAttackR[i].intersects(playerhitR)) {
								playerHp -= 3;
								try {
									playerDAO.updatePlayerHp(1, playerHp);
								}
								catch(SQLException e2) {
									e2.printStackTrace();
								}
								playerHpRenewal();
								gameOver();
							}
						}
					}
				} 
                else {
					for (int i = 0; i < bossAttackL.length; i++) {
						bossAttackL[i].setLocation(attack_x[i], targetY);
						bossAttackL[i].setVisible(false);
					}
                    bossAttackTimer.stop();
                }
            }
        });
        bossAttackTimer.start(); // 타이머 시작
    }
    
    private void bossAttackCoolTime() {
   	 	bossAttackTimer2= new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	bossAttack();
            }
        });
   	 	bossAttackTimer2.start(); // 타이머 시작
    }
    
    private void attackLeftA1() {
    	if(weaponGrade == 1) {
    		playerL.setIcon(playerattackA1Icon[waitTime]);
    	}
    	else if(weaponGrade == 2) {
    		playerL.setIcon(playerattackA2Icon[waitTime]);
    	}
    	else if(weaponGrade == 3) {
    		playerL.setIcon(playerattackA3Icon[waitTime]);
    	}
    	if (attackTimer != null && attackTimer.isRunning()) {
    	        attackTimer.stop();
    	}
        attackTimer = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	waitTime += 1;
            	if(weaponGrade == 1) {
            		playerL.setIcon(playerattackA1Icon[waitTime]);
            	}
            	else if(weaponGrade == 2) {
            		playerL.setIcon(playerattackA2Icon[waitTime]);
            	}
            	else if(weaponGrade == 3) {
            		playerL.setIcon(playerattackA3Icon[waitTime]);
            	}
                if(waitTime >= 2) {
                	attackTimer.stop();
                	waitTime = 0;
                }
            }
        });
        attackTimer.start(); // 타이머 시작
    }
    
    private void attackRightA1() {
    	if(weaponGrade == 1) {
    		playerL.setIcon(playerattackA1Icon[waitTime+3]);
    	}
    	else if(weaponGrade == 2) {
    		playerL.setIcon(playerattackA2Icon[waitTime+3]);
    	}
    	else if(weaponGrade == 3) {
    		playerL.setIcon(playerattackA3Icon[waitTime+3]);
    	}
    	if (attackTimer != null && attackTimer.isRunning()) {
    	        attackTimer.stop();
    	}
        attackTimer = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waitTime += 1;
            	if(weaponGrade == 1) {
            		playerL.setIcon(playerattackA1Icon[waitTime+3]);
            	}
            	else if(weaponGrade == 2) {
            		playerL.setIcon(playerattackA2Icon[waitTime+3]);
            	}
            	else if(weaponGrade == 3) {
            		playerL.setIcon(playerattackA3Icon[waitTime+3]);
            	}
                if(waitTime >= 2) {
                	attackTimer.stop();
                	waitTime = 0;
                }
            }
        });
        attackTimer.start(); // 타이머 시작
    }
    
    private void jump() {
        jumpTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	jumpImplement();
            }
        });
        jumpTimer.start(); // 타이머 시작
    }
    
    private void skillQLeft() {
   	 	skillL[0].setLocation(player_x-180,player_y-110);
   		skillL[0].setVisible(true);
   		if (skillQTimer != null && skillQTimer.isRunning()) {
   	        skillQTimer.stop();
   	    }
        skillQTimer = new Timer(380, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[0].setVisible(false);
            	skillQTimer.stop();
            }
        });
        skillQTimer.start(); // 타이머 시작
    }
    
    private void skillQRight() {
   	 	skillL[1].setLocation(player_x-70,player_y-110);
   		skillL[1].setVisible(true);
   		if (skillQTimer != null && skillQTimer.isRunning()) {
   	        skillQTimer.stop();
   	    }
        skillQTimer = new Timer(380, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[1].setVisible(false);
            	skillQTimer.stop();
            }
        });
        skillQTimer.start(); // 타이머 시작
    }
	
    private void skillWLeft() {
   	 	skillL[2].setLocation(player_x-230,player_y-200);
   		skillL[2].setVisible(true);
   		if (skillWTimer != null && skillWTimer.isRunning()) {
   	        skillWTimer.stop();
   	    }
        skillWTimer = new Timer(380, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[2].setVisible(false);
            	skillWTimer.stop();
            }
        });
        skillWTimer.start(); // 타이머 시작
    }
    
    private void skillWRight() {
   	 	skillL[3].setLocation(player_x-140,player_y-200);
   		skillL[3].setVisible(true);
   		if (skillWTimer != null && skillWTimer.isRunning()) {
   	        skillWTimer.stop();
   	    }
        skillWTimer = new Timer(380, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[3].setVisible(false);
            	skillWTimer.stop();
            }
        });
        skillWTimer.start(); // 타이머 시작
    }
    
    private void skillE() {
   	 	skillL[4].setLocation(player_x-50,player_y-180);
   		skillL[4].setVisible(true);
   		if (skillETimer != null && skillETimer.isRunning()) {
   	        skillETimer.stop();
   	    }
        skillETimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[4].setVisible(false);
            	skillETimer.stop();
            }
        });
        skillETimer.start(); // 타이머 시작
    }
    
    private void skillR() {
   	 	skillL[5].setLocation(0,0);
   		skillL[5].setVisible(true);
   		if (skillRTimer != null && skillRTimer.isRunning()) {
   	        skillRTimer.stop();
   	    }
        skillRTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	skillL[5].setVisible(false);
            	skillRTimer.stop();
            }
        });
        skillRTimer.start(); // 타이머 시작
    }
    
    private void jumpImplement() {
    	if(jumping) {
        	if (player_y > player_y_origin - 140) { // 점프 높이가 140픽셀
                    player_y -= 10; // 점프 속도
        	}
			else
				jumping = !jumping;
		} 
        else {
			if (player_y < player_y_origin) {
				player_y += 10; // 중력 속도
			}
			else if (player_y == player_y_origin) {
				jumpTimer.stop();
			}
        }
		playerL.setLocation(player_x, player_y); // 캐릭터의 위치를 변경
    }
    
    private void locationRenewal() {
    	playerR = new Rectangle(playerL.getBounds());
    	playerhitR= new Rectangle(playerR.x+25, playerR.y, 40, playerR.height);
    	playerLeftR = new Rectangle(playerR.x, playerR.y, playerR.width / 4, playerR.height);
        playerRightR = new Rectangle(playerR.x + ((playerR.width/4)*3), playerR.y, playerR.width / 4, playerR.height);
        skillQLeftR = new Rectangle(skillL[0].getBounds());
        skillQRightR = new Rectangle(skillL[1].getBounds());
        skillWLeftR = new Rectangle(skillL[2].getBounds());
        skillWRightR = new Rectangle(skillL[3].getBounds());
    }
    
    private void bossHpRenewal() {
        bossHpBar.setValue((int)(((double)bossHp/bossMaxHp)*100)); // BossHpBar 값 갱신
        bossHpL.setText(bossHp + "/" +bossMaxHp);
    }
    
    private void playerHpRenewal() {
    	playerHpBar.setValue((int) (((double) playerHp / playerMaxHp) * 100));
    }

	private void gameOver() {
		if (playerHp <= 0) {
			playerHp = 0;
			if (bossAttackTimer != null && bossAttackTimer.isRunning()) {
				bossAttackTimer.stop();
			}
			if (bossAttackTimer2 != null && bossAttackTimer2.isRunning()) {
				bossAttackTimer2.stop();
			}
			try {
				playerDAO.updatePlayerHp(1, playerHp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			boss.hpRenewal();
			JOptionPane.showMessageDialog(null, "결국... 사망에 이르고 마는데...", "RIP", JOptionPane.WARNING_MESSAGE);
			dispose();
		} else if (bossHp <= 0) {
			if (bossAttackTimer != null && bossAttackTimer.isRunning()) {
				bossAttackTimer.stop();
			}
			if (bossAttackTimer2 != null && bossAttackTimer2.isRunning()) {
				bossAttackTimer2.stop();
			}
			JOptionPane.showMessageDialog(null, "클리어 했습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
			bossJewel += 1;
			try {
				playerDAO.updatePlayerHp(1, playerHp);
				inventoryDAO.updateInventoryBossjewel2(1, bossJewel);	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			boss.hpRenewal();
			dispose();
		}
	}
}