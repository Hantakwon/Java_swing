package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Store extends JFrame implements ActionListener {
	GoodsDAO goodsDAO = new GoodsDAO();
	PlayerDAO playerDAO = new PlayerDAO();
	InventoryDAO inventoryDAO = new InventoryDAO();
	StoreDAO storeDAO = new StoreDAO();
	
	private int temp;
	private JTextField countText;
	private JButton countB;
	private JLabel storeBackgroundL, titleL, countL;
	private JLabel merchantL, merchantLineL, merchantL2, merchantLineL2;
	private JButton[] productB = new JButton[12];
	private JButton[] invenB = new JButton[12];
	private JLabel[] productL = new JLabel[12];
	private JLabel[] invenL = new JLabel[12];
	private int[] count;
	private int[] count2;
	private int[] price;
	private int[] price2 = {300, 1000, 2000, 50, 100, 200, 3000, 5000, 10000, 20000, 100 , 200};
	private int gold, playerHp, playerMaxHp;
	private boolean store = true, player = false;
	private JLabel[] goodsL;
	private JLabel goldLabel, playerHpL;
	
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	private ImageIcon merchantLineIcon = new ImageIcon("src/img/speechbubble.png");
	private ImageIcon merchantIcon = new ImageIcon("src/img/merchant.png");
	private ImageIcon storeBackgroundIcon = new ImageIcon("src/img/storebackground.png"); // 이미지 가져오기
	private ImageIcon changeIcon = new ImageIcon("src/img/change.png");
	
	private ImageIcon[] productIcon = {
		    new ImageIcon("src/img/pick1.png"),
		    new ImageIcon("src/img/pick2.png"),
		    new ImageIcon("src/img/weapon1.png"),
		    new ImageIcon("src/img/weapon2.png"),
		    new ImageIcon("src/img/storepotion1.png"),
		    new ImageIcon("src/img/storepotion2.png"),
		    new ImageIcon("src/img/drug1.png"),
		    new ImageIcon("src/img/drug2.png"),
		    new ImageIcon("src/img/drug3.png"),
		    new ImageIcon("src/img/drug4.png"),
		    new ImageIcon("src/img/drug5.png"),
		    new ImageIcon("src/img/drug6.png")
	};
	
	private ImageIcon[] InvenIcon = {
		    new ImageIcon("src/img/goldore.png"),
		    new ImageIcon("src/img/ruby.png"),
		    new ImageIcon("src/img/emerald.png"),
		    new ImageIcon("src/img/monsterclaw.png"),
		    new ImageIcon("src/img/monstershoulder.png"),
		    new ImageIcon("src/img/monstercore.png"),
		    new ImageIcon("src/img/bossjewel1.png"),
		    new ImageIcon("src/img/bossjewel2.png"),
		    new ImageIcon("src/img/bossjewel3.png"),
		    new ImageIcon("src/img/bossjewel4.png"),
		    new ImageIcon("src/img/storepotion1.png"),
		    new ImageIcon("src/img/storepotion2.png"),
	};
	
	private JButton back, transform; // 뒤로가기 버튼
	
	public Store(JLabel[] goodsL) {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("상점");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		this.goodsL = goodsL;
		
		JPanel product = new JPanel(); // 모든 걸 담고있는 패널
		product.setSize(986, 564);
		product.setLayout(null);
		add(product);
		
		try {
			gold = goodsDAO.getGoodsGold(1);
			count = storeDAO.getStoreCountArray();
			price = storeDAO.getStorePriceArray();
			playerHp = playerDAO.getPlayerHp(1);
			playerMaxHp = playerDAO.getPlayerMaxHp(1);
			
			count2 = new int[] { inventoryDAO.getInventoryGoldOre(1), inventoryDAO.getInventoryRuby(1),
					inventoryDAO.getInventoryEmerald(1), inventoryDAO.getInventoryClaw(1),
					inventoryDAO.getInventoryShoulder(1), inventoryDAO.getInventoryCore(1),
					inventoryDAO.getInventoryBossjewel1(1), inventoryDAO.getInventoryBossjewel2(1),
					inventoryDAO.getInventoryBossjewel3(1), inventoryDAO.getInventoryBossjewel4(1),
					inventoryDAO.getInventoryPotion1(1), inventoryDAO.getInventoryPotion2(1) };
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		String[] merchantLine = {
			    "기존 곡괭이보단 좋은 곡괭이지",
			    "가장 좋은 성능의 곡괭이일세",
			    "황금 망치일세",
			    "살벌한 전기톱일세 아주 강력하지",
			    "<html>보스전에서 사용할 수 있는 상품일세<br>최대체력의 20% 채워주지<br>한 개씩만 살 수 있다네</html>",
			    "<html>보스전에서 사용할 수 있는 상품일세<br>최대체력의 30% 채워주지<br>한 개씩만 살 수 있다네</html>",
			    "<html>에너지가 담긴 커피일세<br>최대체력의 10% 채워주지</html>",
			    "<html>에너지가 담긴 당근주스일세<br>최대체력의 20% 채워주지</html>",
			    "<html>동굴에서 발견된 뱀으로 만든걸세<br>최대체력의 30% 채워주지</html>",
			    "<html>내가 직접 만든 수제일세<br>최대체력의 40% 채워주지</html>",
			    "<html>모든 병을 치유해준다고 유명한 물건일세<br>최대체력의 50% 채워주지</html>",
			    "<html>구하긴 힘든 물건일세<br>최대체력의 100% 채워주지</html>"
			};
		
		String[] merchantLine2 = {
			    "금광석이군",
			    "루비군",
			    "에메랄드군",
			    "발톱이 아닌가",
			    "어깨장식이군",
			    "<html>몬스터의 핵...<br>자네 강력한 사람이었군</html>",
			    "돌댕이의 결정석이군",
			    "오 강력한 돌댕이의 결정석이로군",
			    "루시드의 결정석이군",
			    "강력한 루시드의 결정석이로군",
			    "재판매하겠나?",
			    "재판매하겠나?"
			};
		
		countL = new JLabel("<html><br><br>몇 개 판매하시겠습니까?</html>");
		countL.setBounds(333,193,260,140);
		countL.setOpaque(true);
		countL.setBackground(Color.WHITE);
		countL.setVisible(false);
		countL.setFont(new Font("굴림", Font.BOLD, 16)); 
		countL.setVerticalAlignment(SwingConstants.TOP);
		countL.setHorizontalAlignment(SwingConstants.CENTER);
		product.add(countL);
		
		countText = new JTextField(10);
		countText.setBounds(40,80,110,30);
		countL.add(countText);
		
		countB = new JButton("확인");
		countB.setBounds(160,80,60,30);
		countB.addActionListener(this);
		countL.add(countB);
		
		titleL = new JLabel("구입");
		titleL.setBounds(564,70,200,50);
		titleL.setForeground(Color.BLACK); // 글자색 설정
		titleL.setFont(new Font("굴림", Font.BOLD, 24)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		titleL.setVerticalAlignment(SwingConstants.CENTER);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		product.add(titleL);

		goldLabel = new JLabel("골드 : " + gold);
		goldLabel.setBounds(433,180,100,30);
		goldLabel.setForeground(Color.YELLOW); // 글자색 설정
		goldLabel.setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		goldLabel.setVerticalAlignment(SwingConstants.CENTER);
		goldLabel.setHorizontalAlignment(SwingConstants.LEFT);
		product.add(goldLabel);
		
		playerHpL = new JLabel("체력 : " + playerHp + "/" + playerMaxHp);
		playerHpL.setBounds(533,180,100,30);
		playerHpL.setForeground(Color.RED); // 글자색 설정
		playerHpL.setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		playerHpL.setVerticalAlignment(SwingConstants.CENTER);
		playerHpL.setHorizontalAlignment(SwingConstants.LEFT);
		product.add(playerHpL);
		
		int x=0; // 버튼 개수 조절 용
	
		for(int i=0; i<3; i++) {
			for(int j=0; j<4; j++) {
				final int z = x;  // 이 코드를 추가합니다.
				productB[x] = new JButton(); // 버튼 초기화 
				productB[x].setBounds(433+(132*j),220+(114*i),68,68); // 위치 조정
				productB[x].setVerticalAlignment(JLabel.CENTER);
				productB[x].setHorizontalAlignment(JLabel.CENTER);
				productB[x].setBorderPainted(false); // 선 없음
				productB[x].setContentAreaFilled(false); // 버튼 투명
				productB[x].addActionListener(this); // 이벤트 리스너 추가 (해야만 클릭 시 이벤트 발생)
				productB[x].addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent evt) {
				    	merchantLineL.setText(merchantLine[z]);
				    	merchantLineL.setVisible(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	merchantLineL.setVisible(false);
				    }
				});
				product.add(productB[x]);

				productL[x] = new JLabel("<html>가격 : " + price[x] + "<br>재고량 : " + count[x] +"</html>");  
				productL[x].setBounds(430+(132*j),278+(114*i),74,60); // 위치 조정
				productL[x].setForeground(Color.WHITE); // 글자색 설정
				productL[x].setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
				productL[x].setVerticalAlignment(SwingConstants.CENTER);
				productL[x].setHorizontalAlignment(SwingConstants.CENTER);

				product.add(productL[x]);
				x++;
			}
		}
		
		for(int i=6; i<12; i++) {
			productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : ∞ </html>");  
		}
		
		for(int i=0; i<productIcon.length; i++) {
			productB[i].setIcon(productIcon[i]); // 버튼에 이미지 삽입
		}
		
		
		int x2 = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<4; j++) {
				final int z2 = x2;
				invenB[x2] = new JButton(); // 버튼 초기화 
				invenB[x2].setBounds(433+(132*j),220+(114*i),68,68); // 위치 조정
				invenB[x2].setVerticalAlignment(JLabel.CENTER);
				invenB[x2].setHorizontalAlignment(JLabel.CENTER);
				invenB[x2].setBorderPainted(false); // 선 없음
				invenB[x2].setContentAreaFilled(false); // 버튼 투명
				invenB[x2].setVisible(false);
				invenB[x2].addActionListener(this); // 이벤트 리스너 추가 (해야만 클릭 시 이벤트 발생)
				invenB[x2].addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent evt) {
				    	merchantLineL2.setText(merchantLine2[z2]);
				    	merchantLineL2.setVisible(true);
				    }
				    public void mouseExited(MouseEvent evt) {
				    	merchantLineL2.setVisible(false);
				    }
				});
				product.add(invenB[x2]);

				invenL[x2] = new JLabel("<html>가격 : " + price2[x2] + "<br>보유수 : " + count2[x2] +"</html>");  
				invenL[x2].setBounds(420+(132*j),278+(114*i),94,60); // 위치 조정
				invenL[x2].setForeground(Color.WHITE); // 글자색 설정
				invenL[x2].setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
				invenL[x2].setVerticalAlignment(SwingConstants.CENTER);
				invenL[x2].setHorizontalAlignment(SwingConstants.CENTER);
				invenL[x2].setVisible(false);
				product.add(invenL[x2]);
				x2++;
			}
		}
		
		for(int i=0; i<InvenIcon.length; i++) {
			invenB[i].setIcon(InvenIcon[i]); // 버튼에 이미지 삽입
		}
		
		back = new JButton();
		back.setBounds(906,10,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		product.add(back);
		
		transform = new JButton();
		transform.setBounds(910,494,60,60);
		transform.addActionListener(this);
		transform.setBorderPainted(false); // 선 없음
		transform.setContentAreaFilled(false); // 버튼 투명
		transform.setIcon(changeIcon);
		product.add(transform);
		
		merchantLineL = new JLabel();
		merchantLineL.setBounds(300,40,350,140);
		merchantLineL.setIcon(merchantLineIcon);
		merchantLineL.setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		merchantLineL.setVerticalAlignment(SwingConstants.CENTER);
		merchantLineL.setHorizontalAlignment(SwingConstants.CENTER);
		merchantLineL.setHorizontalTextPosition(SwingConstants.CENTER); // 텍스트를 아이콘의 왼쪽에 위치시킵니다.
		merchantLineL.setVisible(false);
		product.add(merchantLineL);
		
		merchantLineL2 = new JLabel();
		merchantLineL2.setBounds(300,40,350,140);
		merchantLineL2.setIcon(merchantLineIcon);
		merchantLineL2.setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		merchantLineL2.setVerticalAlignment(SwingConstants.CENTER);
		merchantLineL2.setHorizontalAlignment(SwingConstants.CENTER);
		merchantLineL2.setHorizontalTextPosition(SwingConstants.CENTER); // 텍스트를 아이콘의 왼쪽에 위치시킵니다.
		merchantLineL2.setVisible(false);
		product.add(merchantLineL2);
		
		merchantL = new JLabel();
		merchantL.setSize(407,564);
		merchantL.setIcon(merchantIcon);
		product.add(merchantL);
		
		storeBackgroundL = new JLabel();
		storeBackgroundL.setSize(986,564);
		storeBackgroundL.setIcon(storeBackgroundIcon);
		product.add(storeBackgroundL);
		
		setVisible(true); // 보이게 설정	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) { // 눌린 버튼이 뒤로가기 라면
			dispose();
		}
		else if (obj == transform) {
			store = !store;
			player = !player;
			for (int i = 0; i < productB.length; i++) {
		        productB[i].setVisible(store);
		        productL[i].setVisible(store);
		        invenB[i].setVisible(player);
		        invenL[i].setVisible(player);
		    }
			if(store == true) {
				titleL.setText("구입");
			}
			else {
				titleL.setText("판매");
			}

		}
		else if (obj == countB) {
			int check = temp;
			String text = countText.getText();
			try {
				int num = Integer.parseInt(text);
				if(num < 1 || num > count2[check]) {
					JOptionPane.showMessageDialog(null, "0~"+count2[check]+"사이의 값을 입력해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else {
					purchaseItem2(check, num);
				}
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
				countL.setVisible(true);
			}
			countL.setVisible(false);
		}
	    for (int i = 0; i < productB.length; i++) {
	        if (obj == productB[i]) { // 눌린 버튼이 i번째 버튼이라면
	            purchaseItem(i); // puchaseItem 메서드에 매개변수 i를 넣어서 실행
	            break; 
	        }
	    }
		for (int i = 0; i < invenB.length; i++) {
			if (obj == invenB[i]) { // 눌린 버튼이 i번째 버튼이라면
				if (count2[i] > 0) {
					countL.setVisible(true);
					checkCount(i);
				} else {
					JOptionPane.showMessageDialog(null, "가지고 있지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
	    }
	}
	
	private void checkCount(int i) {
		temp = i;
	}
	private void purchaseItem(int i) {
	    if (gold >= price[i]) { // 가격 판단 가격보다 높은 돈을 가지고 있느지
	        if (i != 1 && i != 3 || count[i - 1] == 0) { 
	        	// i가 1,3이 아니거나 전 단계가 0이면 즉, 2번째 4번째 물품이면 전 단계가 구입돼야함
	        	// = 무기 및 곡괭이 2단계를 구매하려면 1단계를 먼저 구매해야한다는 뜻
	            if (count[i] > 0) { //재고가 있는지 없는지 확인
	                int response = JOptionPane.showConfirmDialog(null, "구입하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
	                // 메시지 출력 확인 누를 시 response에 0이 들어감
	                if (response == 0) {
	                    count[i] -= 1; // 물품 구매
						if (i == 0) {
							try {
								playerDAO.updatePlayerPickLv(1, 2);
								storeDAO.updateStoreCount(1, 0);
								productL[0].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 1) {
							try {
								playerDAO.updatePlayerPickLv(1, 3);
								storeDAO.updateStoreCount(2, 0);
								productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 2) {
							try {
								playerDAO.updatePlayerWeaponLv(1, 2);
								storeDAO.updateStoreCount(3, 0);
								productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 3) {
							try {
								playerDAO.updatePlayerWeaponLv(1, 3);
								storeDAO.updateStoreCount(4, 0);
								productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 4) {
							try {
								inventoryDAO.updateInventoryPotion1(1, 1);
								storeDAO.updateStoreCount(5, 0);
								productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
								invenL[10].setText("<html>가격 : " + price2[10] + "<br>보유수 : 1 </html>");
								count2[10] += 1;
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 5) {
							try {
								inventoryDAO.updateInventoryPotion2(1, 1);
								storeDAO.updateStoreCount(6, 0);
								productL[i].setText("<html>가격 : " + price[i] + "<br>재고량 : 0 </html>");  
								invenL[11].setText("<html>가격 : " + price2[11] + "<br>보유수 : 1 </html>"); 
								count2[11] +=1 ;
							}
							catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if (i == 6) {
							if(playerHp < playerMaxHp) {
								playerHp += (int)(((double)playerMaxHp / 100) * 10);
								if(playerHp >= playerMaxHp) {
									playerHp = playerMaxHp;
								};
								count[i] +=1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						if (i == 7) {
							if(playerHp < playerMaxHp) {
								playerHp += (int)(((double)playerMaxHp / 100) * 20);
								if(playerHp >= playerMaxHp) {
									playerHp = playerMaxHp;
								};
								count[i] +=1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						if (i == 8) {
							if(playerHp < playerMaxHp) {
								playerHp += (int)(((double)playerMaxHp / 100) * 30);
								if(playerHp >= playerMaxHp) {
									playerHp = playerMaxHp;
								};
								count[i] +=1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						if (i == 9) {
							if(playerHp < playerMaxHp) {
								playerHp += (int)(((double)playerMaxHp / 100) * 40);
								if(playerHp >= playerMaxHp) {
									playerHp = playerMaxHp;
								};
								count[i] +=1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						if (i == 10) {
							if(playerHp < playerMaxHp) {
								playerHp += (int)(((double)playerMaxHp / 100) * 50);
								if(playerHp >= playerMaxHp) {
									playerHp = playerMaxHp;
								};
								count[i] +=1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						if (i == 11) {
							if(playerHp < playerMaxHp) {
								playerHp = playerMaxHp;
								count[i] += 1;
							}
							else {
								JOptionPane.showMessageDialog(null, "이미 최대 체력입니다.", "경고",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						gold -= price[i];
						goodsL[0].setText("골드 : " + gold);
						goldLabel.setText("골드 : " + gold);
						playerHpL.setText("체력 : " + playerHp +"/" + playerMaxHp);
						try {
							playerDAO.updatePlayerHp(1, playerHp);
							goodsDAO.updateGoodsGold(1, gold);
						}
						catch(SQLException e) {
							e.printStackTrace();
						}
	                } else if (response == 1) {
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "남은 재고가 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "전 단계를 먼저 구입해야 합니다.", "경고", JOptionPane.WARNING_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "돈이 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	private void purchaseItem2(int i, int num) {
			int response = JOptionPane.showConfirmDialog(null, "판매하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			// 메시지 출력 확인 누를 시 response에 0이 들어감
			if (response == 0) {
				if (i == 10) {
					num = 1;
					try {
						inventoryDAO.updateInventoryPotion1(1, 0);
						storeDAO.updateStoreCount(5, 1);
						productL[4].setText("<html>가격 : " + price[4] + "<br>재고량 : 1 </html>");
						invenL[10].setText("<html>가격 : " + price2[10] + "<br>보유수 : 0 </html>");
						count[4] += 1;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (i == 11) {
					num = 1;
					try {
						inventoryDAO.updateInventoryPotion2(1, 0);
						storeDAO.updateStoreCount(6, 1);
						productL[5].setText("<html>가격 : " + price[5] + "<br>재고량 : 1 </html>");
						invenL[11].setText("<html>가격 : " + price2[11] + "<br>보유수 : 0 </html>");
						count[5] += 1;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				count2[i] -= num; // 물품 판매
				gold += price2[i] * num;
				goodsL[0].setText("골드 : " + gold);
				goldLabel.setText("골드 : " + gold);
				invenL[i].setText("<html>가격 : " + price2[i] + "<br>보유수 : " + count2[i] + "</html>");
				try {
					goodsDAO.updateGoodsGold(1, gold);
					inventoryDAO.updateInventoryGoldOre(1, count2[0]);
					inventoryDAO.updateInventoryRuby(1, count2[1]);
					inventoryDAO.updateInventoryEmerald(1, count2[2]);
					inventoryDAO.updateInventoryClaw(1, count2[3]);
					inventoryDAO.updateInventoryShoulder(1, count2[4]);
					inventoryDAO.updateInventoryCore(1, count2[5]);
					inventoryDAO.updateInventoryBossjewel1(1, count2[6]);
					inventoryDAO.updateInventoryBossjewel2(1, count2[7]);
					inventoryDAO.updateInventoryBossjewel3(1, count2[8]);
					inventoryDAO.updateInventoryBossjewel4(1, count2[9]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
}