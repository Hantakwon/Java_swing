package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class Menu extends JFrame implements ActionListener {
	PlayerDAO playerDAO = new PlayerDAO();
	GoodsDAO goodsDAO = new GoodsDAO();
	InventoryDAO inventoryDAO = new InventoryDAO();

	private int pickGrade, weaponGrade;
	private JLabel[] player = new JLabel[5];
	private JLabel[] inventoryL = new JLabel[12];
	private JLabel[] countL = new JLabel[12];
	private JLabel menuBackgroundL, pickL, weaponL;
	private JButton back;
	private ImageIcon menuBackgroundIcon = new ImageIcon("src/img/menubackground.png");
	private ImageIcon characterIcon = new ImageIcon("src/img/avatar1.gif");
	private ImageIcon pickIcon1 = new ImageIcon("src/img/pick0.png");
	private ImageIcon pickIcon2 = new ImageIcon("src/img/pick1.png");
	private ImageIcon pickIcon3 = new ImageIcon("src/img/pick2.png");
	private ImageIcon weaponIcon1 = new ImageIcon("src/img/weapon0.png");
	private ImageIcon weaponIcon2 = new ImageIcon("src/img/weapon1.png");
	private ImageIcon weaponIcon3 = new ImageIcon("src/img/weapon2.png");
	private ImageIcon backIcon = new ImageIcon("src/img/back.png");
	
	private ImageIcon[] inventoryIcon = {
			new ImageIcon("src/img/storepotion1.png"),
			new ImageIcon("src/img/storepotion2.png"),
			new ImageIcon("src/img/goldore.png"),
			new ImageIcon("src/img/ruby.png"),
			new ImageIcon("src/img/emerald.png"),
			new ImageIcon("src/img/monstercore.png"),
			new ImageIcon("src/img/monstershoulder.png"),
			new ImageIcon("src/img/monsterclaw.png"),
			new ImageIcon("src/img/bossjewel1.png"),
		    new ImageIcon("src/img/bossjewel2.png"),
		    new ImageIcon("src/img/bossjewel3.png"),
		    new ImageIcon("src/img/bossjewel4.png")
	};
	
	public Menu() {			
		try {
			pickGrade = playerDAO.getPlayerPickLv(1);
			weaponGrade = playerDAO.getPlayerWeaponLv(1);
	    } 
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
		
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정

		JPanel playerP = new JPanel();
		playerP.setLayout(null);
		playerP.setBounds(0,0,986,564);
		ct.add(playerP);

		pickL = new JLabel();
		pickL.setBounds(243,334,60,60);
		if (pickGrade == 1) {
			pickL.setIcon(pickIcon1);
		} 
		else if (pickGrade == 2) {
			pickL.setIcon(pickIcon2);
		} 
		else if (pickGrade == 3) {
			pickL.setIcon(pickIcon3);
		}
		pickL.setVerticalAlignment(JLabel.CENTER);  // 아이콘을 세로 방향으로 중앙에 배치
		pickL.setHorizontalAlignment(JLabel.CENTER);  // 아이콘을 가로 방향으로 중앙에 배치
		playerP.add(pickL);
		
		weaponL = new JLabel();
		weaponL.setBounds(385,334,60,60);
		if (weaponGrade == 1) {
			weaponL.setIcon(weaponIcon1);
		} 
		else if (weaponGrade == 2) {
			weaponL.setIcon(weaponIcon2);
		} 
		else if (weaponGrade == 3) {
			weaponL.setIcon(weaponIcon3);
		}
		weaponL.setVerticalAlignment(JLabel.CENTER);  // 아이콘을 세로 방향으로 중앙에 배치
		weaponL.setHorizontalAlignment(JLabel.CENTER);  // 아이콘을 가로 방향으로 중앙에 배치
		playerP.add(weaponL);

		for (int i = 0; i < player.length; i++) {
    	    player[i] = new JLabel();
    	    player[i].setFont(new Font("굴림", Font.BOLD, 14));
    	    player[i].setVerticalAlignment(SwingConstants.CENTER);
    	    player[i].setHorizontalAlignment(SwingConstants.LEFT);
    	    player[i].setBounds(554, 124+(i*24), 120, 24);
    	    playerP.add(player[i]);
    	}
		
		int x = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<4; j++) {
				inventoryL[x] = new JLabel();
				inventoryL[x].setIcon(inventoryIcon[x]);
				inventoryL[x].setBounds(532+(60*j),280+(60*i),60,60); // 위치 조정
				countL[x] = new JLabel();
				countL[x].setBounds(532+(60*j),320+(60*i),60,20);
				countL[x].setFont(new Font("굴림", Font.BOLD, 14));
				countL[x].setVerticalAlignment(SwingConstants.CENTER);
				countL[x].setHorizontalAlignment(SwingConstants.RIGHT);
				playerP.add(countL[x]);
				playerP.add(inventoryL[x]);
				x++;
			}
		}
		
	    try {
	    	player[0].setText("이름: " + playerDAO.getPlayerName(1));
	    	player[1].setText("레벨: " + playerDAO.getPlayerLv(1));
	    	player[2].setText("체력: " + playerDAO.getPlayerHp(1) + "/" + playerDAO.getPlayerMaxHp(1));
	    	player[3].setText("힘: " + playerDAO.getPlayerStr(1));
	    	player[4].setText("경험치: " + playerDAO.getPlayerExp(1) + "/" + playerDAO.getPlayerLv(1)*10);
	    	
	    	countL[0].setText("" + inventoryDAO.getInventoryPotion1(1) + "");
	    	countL[1].setText("" + inventoryDAO.getInventoryPotion2(1) + "");
	    	countL[2].setText("" + inventoryDAO.getInventoryGoldOre(1) + "");
	    	countL[3].setText("" + inventoryDAO.getInventoryRuby(1) + "");
	    	countL[4].setText("" + inventoryDAO.getInventoryEmerald(1) + "");
	    	countL[5].setText("" + inventoryDAO.getInventoryCore(1) + "");
	    	countL[6].setText("" + inventoryDAO.getInventoryShoulder(1) + "");
	    	countL[7].setText("" + inventoryDAO.getInventoryClaw(1) + "");
	    	countL[8].setText("" + inventoryDAO.getInventoryBossjewel1(1) + "");
	    	countL[9].setText("" + inventoryDAO.getInventoryBossjewel1(1) + "");
	    	countL[10].setText("" + inventoryDAO.getInventoryBossjewel1(1) + "");
	    	countL[11].setText("" + inventoryDAO.getInventoryBossjewel1(1) + "");
	    } 
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
		back = new JButton();
		back.setBounds(10,494,60,60);
		back.setBorderPainted(false); // 선 없음
		back.setContentAreaFilled(false); // 버튼 투명
		back.setIcon(backIcon);
		back.addActionListener(this);
		playerP.add(back);
		
		menuBackgroundL = new JLabel();
		menuBackgroundL.setSize(986,564);
		menuBackgroundL.setIcon(menuBackgroundIcon);
		playerP.add(menuBackgroundL);
		
		setVisible(true); // 보이게 설정	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == back) {
			dispose();
		}
	}
}