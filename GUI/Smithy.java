package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Smithy extends JFrame implements ActionListener {
	PlayerDAO playerDAO = new PlayerDAO();
	InventoryDAO inventoryDAO = new InventoryDAO();
	GoodsDAO goodsDAO = new GoodsDAO();
	
	private int weaponGrade, ingredientGrade, core, shoulder, claw, gold; 
	private JButton ingredientB, back, mini;
	private JLabel bgLabel, bsLabel, weaponL, weaponL2, weaponL3, titleL, goldL;
	private JLabel goldLabel, enforceLabel;
	private JLabel[] ingredientL = new JLabel[3];
	private JLabel[] ingredientL2 = new JLabel[3];
	private ImageIcon bsIcon = new ImageIcon("src/img/blacksmith.png");
	private ImageIcon bgIcon = new ImageIcon("src/img/smithybackground.png");
	private ImageIcon miniIcon = new ImageIcon("src/img/mini.png");
	private JLabel[] goodsL;
	
	private ImageIcon enforceIcon = new ImageIcon("src/img/enforce.png");
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	private ImageIcon[] weaponIcon = {
		    new ImageIcon("src/img/weapon0.png"),
		    new ImageIcon("src/img/weapon1.png"),
		    new ImageIcon("src/img/weapon2.png")
	};
	
	private ImageIcon[] ingredientIcon = {
		    new ImageIcon("src/img/monsterclaw.png"),
		    new ImageIcon("src/img/monstershoulder.png"),
		    new ImageIcon("src/img/monstercore.png")
	};
	
	private int[] clawI = {0,3,6,9,12,15,18,19,21,0};
	private int[] shoulderI = {0,0,0,3,6,9,12,15,18,0};
	private int[] coreI = {0,0,0,0,0,0,3,6,9,0};
	private int[] goldI = {100,500,1000,1500,2000,3000,5000,10000,20000,0};
	
	public Smithy(JLabel[] goodsL) {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("대장간");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		this.goodsL = goodsL;
		
		try {
			ingredientGrade = playerDAO.getPlayerUpgradeLv(1);
			weaponGrade = playerDAO.getPlayerWeaponLv(1);
			gold = goodsDAO.getGoodsGold(1);
			claw = inventoryDAO.getInventoryClaw(1);
			shoulder = inventoryDAO.getInventoryShoulder(1);
			core = inventoryDAO.getInventoryCore(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		

		
		JPanel bgPanel = new JPanel();
		bgPanel.setLayout(null);
		bgPanel.setSize(986,564); // 사이즈 설정
		add(bgPanel);
		
		enforceLabel = new JLabel();
		enforceLabel.setLayout(null);
		enforceLabel.setIcon(enforceIcon);
		enforceLabel.setBounds(550,120,340,450); // 사이즈 설정
		bgPanel.add(enforceLabel);
		
		weaponL = new JLabel();
		weaponL.setBounds(140,20,60,60);
		if(weaponGrade == 1) {
			weaponL.setIcon(weaponIcon[0]); 
		}
		else if(weaponGrade == 2) {
			weaponL.setIcon(weaponIcon[1]); 
    	}
		else if(weaponGrade == 3) {
			weaponL.setIcon(weaponIcon[2]); 
    	}
		enforceLabel.add(weaponL);
		
		weaponL2 = new JLabel();
		weaponL2.setBounds(0,80,340,30);
		if(weaponGrade == 1) {
			weaponL2.setText("쇠 망치");
		}
		else if(weaponGrade == 2) {
			weaponL2.setText("골든 망치");
    	}
		else if(weaponGrade == 3) {
			weaponL2.setText("전기톱");
    	}
		weaponL2.setFont(new Font("굴림", Font.BOLD, 14));
		weaponL2.setVerticalAlignment(SwingConstants.CENTER);
		weaponL2.setHorizontalAlignment(SwingConstants.CENTER);
		enforceLabel.add(weaponL2);
		
		weaponL3 = new JLabel("강화 수치 +" + ingredientGrade);
		weaponL3.setBounds(0,100,340,30);
		weaponL3.setFont(new Font("굴림", Font.BOLD, 14));
		weaponL3.setVerticalAlignment(SwingConstants.CENTER);
		weaponL3.setHorizontalAlignment(SwingConstants.CENTER);
		enforceLabel.add(weaponL3);
		
		titleL = new JLabel("필요 재료");
		titleL.setBounds(0,160,340,30);
		titleL.setFont(new Font("굴림", Font.BOLD, 14));
		titleL.setVerticalAlignment(SwingConstants.CENTER);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		enforceLabel.add(titleL);
		
		for(int i=0; i<3; i++) {
			ingredientL[i] = new JLabel();
			ingredientL[i].setBounds(60+(80*i),200,60,60);
			ingredientL[i].setIcon(ingredientIcon[i]);
			enforceLabel.add(ingredientL[i]);
			ingredientL2[i] = new JLabel();
			ingredientL2[i].setBounds(60+(80*i),260,60,30);
			ingredientL2[i].setFont(new Font("굴림", Font.BOLD, 14));
			ingredientL2[i].setVerticalAlignment(SwingConstants.CENTER);
			ingredientL2[i].setHorizontalAlignment(SwingConstants.CENTER);
			enforceLabel.add(ingredientL2[i]);
		}
		
		goldL = new JLabel("골드 : " + goldI[ingredientGrade]);
		goldL.setBounds(0,300,340,30);
		goldL.setFont(new Font("굴림", Font.BOLD, 14));
		goldL.setVerticalAlignment(SwingConstants.CENTER);
		goldL.setHorizontalAlignment(SwingConstants.CENTER);
		enforceLabel.add(goldL);
	    
	    ingredientB = new JButton("강화하기");
	    ingredientB.setBounds(120,350,100,50);
	    ingredientB.addActionListener(this);
	    enforceLabel.add(ingredientB);
		    
	    goldLabel = new JLabel("골드 : " + gold);
		goldLabel.setBounds(550,90,300,30);
		goldLabel.setForeground(Color.YELLOW);
		goldLabel.setFont(new Font("굴림", Font.BOLD, 16));
		goldLabel.setVerticalAlignment(SwingConstants.CENTER);
		goldLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    bgPanel.add(goldLabel);
	    
		bsLabel = new JLabel();
		bsLabel.setSize(506,564);
		bsLabel.setIcon(bsIcon);
		bgPanel.add(bsLabel);

		mini = new JButton();
		mini.setBounds(924,504,60,60);
		mini.setBorderPainted(false); // 선 없음
		mini.setContentAreaFilled(false); // 버튼 투명
		mini.setIcon(miniIcon);
		mini.addActionListener(this);
		bgPanel.add(mini);
		
		back = new JButton();
		back.setBounds(906,10,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		bgPanel.add(back);
		
		bgLabel = new JLabel();
		bgLabel.setSize(986,564);
		bgLabel.setIcon(bgIcon);
		bgPanel.add(bgLabel);
		
		reset();
		setVisible(true); // 보이게 설정	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) {
			dispose();
		}
		else if(obj == mini) {
			new MiniInventory();
		}
		else if(obj == ingredientB) {
		    if (ingredientGrade >= 9) {
		        JOptionPane.showMessageDialog(null, "최대 단계입니다.", "경고", JOptionPane.WARNING_MESSAGE);
		    }
		    else if(gold >= goldI[ingredientGrade] && claw >= clawI[ingredientGrade] && shoulder >= shoulderI[ingredientGrade] && core >= coreI[ingredientGrade]) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					gold -= goldI[ingredientGrade];
					claw -= clawI[ingredientGrade];
					shoulder -= shoulderI[ingredientGrade];
					core -= coreI[ingredientGrade];
					goldLabel.setText("골드 : " + gold);
					goodsL[0].setText("골드 : " + gold);
					
					ingredientGrade += 1;
					reset();
					
					try {
						playerDAO.updatePlayerUpgradeLv(1, ingredientGrade);
						goodsDAO.updateGoodsGold(1, gold);
						inventoryDAO.updateInventoryClaw(1, claw);
						inventoryDAO.updateInventoryShoulder(1, shoulder);
						inventoryDAO.updateInventoryCore(1, core);
					}
					catch(SQLException e2) {
						e2.printStackTrace();
					}
					
				}
			}
			else JOptionPane.showMessageDialog(null, "재료가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void reset() {
		goldL.setText("골드 : " + goldI[ingredientGrade]);
		ingredientL2[0].setText(clawI[ingredientGrade]+"개");
		ingredientL2[1].setText(shoulderI[ingredientGrade]+"개");
		ingredientL2[2].setText(coreI[ingredientGrade]+"개");
		weaponL3.setText("강화 수치 +" + ingredientGrade);
	}
}
