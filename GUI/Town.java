package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Town extends JFrame {
	public Town() {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬 기준 없음 (좌표)
		setTitle("마을"); // 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		ct.add(new bgPanel()); // 패널 추가
		
		setVisible(true); // 보이게 설정	
	}
}

class bgPanel extends JPanel implements ActionListener { // 배경패널 
	PlayerDAO playerDAO = new PlayerDAO();
	GoodsDAO goodsDAO = new GoodsDAO();
	BuildingDAO buildingDAO = new BuildingDAO();
	
	// 필요 돈, 나무, 돌
	private int[] requiredGold, requiredWood, requiredRock;
	private int buildingGold = 1000, buildingWood = 200, buildingRock = 200;
	// 타이머
	private Timer townTimer, bossTimer; 
	// 각각의 변수 플레이어 체력, 돈, 나무, 돌, 인구수, 벌목장 레벨, 채석장 레벨, 카지노 레벨, 각 건물들 레벨
	private int playerHp, gold, wood, rock, population, 
				lumberyardGrade, quarryGrade, casinoGrade, 
				building1Grade, building2Grade, building3Grade, building4Grade, building5Grade;
	// 글이나 그림을 넣기 위한 레이블
	private JLabel boardLabel, bgLabel;
	private JLabel caveLabel, storeLabel, lumberyardLabel, lumberyardLvLabel, quarryLabel, quarryLvLabel, smithyLabel,
			casinoLabel, casinoLabel2, bossLabel;
	private JLabel[] goodsL = new JLabel[4]; // 재화 돈, 나무, 돌, 인구 표시
	
	// 동굴, 상점, 벌목장, 채석장, 대장간, 카지노, 보스, 메뉴 건물 버튼
	private JButton cave, store, lumberyard, quarry, smithy, casino, boss, menu;

	// 이미지 가져오기
	private ImageIcon bossIcon = new ImageIcon("src/img/bossTown.png"); // 보스
	private ImageIcon caveIcon = new ImageIcon("src/img/caveTown.png"); // 동굴
	private ImageIcon menuIcon = new ImageIcon("src/img/menu.png"); // 메뉴(상태창)
	private ImageIcon lumberyardIcon1 = new ImageIcon("src/img/lumberyard1.png"); // 벌목장1
	private ImageIcon lumberyardIcon2 = new ImageIcon("src/img/lumberyard2.png"); // 벌목장2
	private ImageIcon quarryIcon1 = new ImageIcon("src/img/quarry1.png"); // 채석장1
	private ImageIcon quarryIcon2 = new ImageIcon("src/img/quarry2.png"); // 채석장2
	private ImageIcon storeIcon1 = new ImageIcon("src/img/store1.png"); // 상점
	private ImageIcon smithyIcon1 = new ImageIcon("src/img/smithy1.png"); // 대장간
	private ImageIcon casinoIcon1 = new ImageIcon("src/img/casino1.png"); // 카지노1
	private ImageIcon casinoIcon2 = new ImageIcon("src/img/casino2.png"); // 카지노2
	private ImageIcon bgIcon = new ImageIcon("src/img/bg.png"); // 배경
	private ImageIcon boardIcon = new ImageIcon("src/img/board.png"); // 재화 표시판

	// 나머지 건물들
	private JButton[] structureB = new JButton[5]; // 건물버튼
	private JLabel[] structureL = new JLabel[5];
	private ImageIcon[] structureBeforeIcon = { // 업그레이드 전 건물 이미지
		    new ImageIcon("src/img/structure1.png"),
		    new ImageIcon("src/img/structure2.png"),
		    new ImageIcon("src/img/structure3.png"),
		    new ImageIcon("src/img/structure4.png"),
		    new ImageIcon("src/img/structure5.png")
	};
	private ImageIcon[] structureAfterIcon = { // 업그레이드 후 건물 이미지
		    new ImageIcon("src/img/structure1-1.png"),
		    new ImageIcon("src/img/structure2-1.png"),
		    new ImageIcon("src/img/structure3-1.png"),
		    new ImageIcon("src/img/structure4-1.png"),
		    new ImageIcon("src/img/structure5-1.png")
	};
	
	public bgPanel() { // 생성자
		setLayout(null); // 정렬 없음
		setBounds(0,0,986,564); // 위치 및 크기 설정

		// DB에서 데이터 값 가져오기
		try {
			playerHp = playerDAO.getPlayerHp(1);
			gold = goodsDAO.getGoodsGold(1);
			wood = goodsDAO.getGoodsWood(1);
			rock = goodsDAO.getGoodsRock(1);
			population = goodsDAO.getGoodsPopulation(1);
			lumberyardGrade = buildingDAO.getLumberyardGrade(1);
			quarryGrade = buildingDAO.getQuarryGrade(1);
			casinoGrade = buildingDAO.getCasinoGrade(1);
			building1Grade = buildingDAO.getBuilding1Grade(1);
			building2Grade = buildingDAO.getBuilding2Grade(1);
			building3Grade = buildingDAO.getBuilding3Grade(1);
			building4Grade = buildingDAO.getBuilding4Grade(1);
			building5Grade = buildingDAO.getBuilding5Grade(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 업그레이드 시 필요한 재화 설정
		requiredGold = new int[] {100,2000,10000,30000,0};
		requiredWood = new int[] {0,100,400,1000,0};
		requiredRock = new int[] {0,100,400,1000,0};
		
		// -------- 오른쪽 상단 재화 --------
		for(int i=0; i<goodsL.length; i++) {
			goodsL[i] = new JLabel();
			goodsL[i].setBounds(812,20+(i*34),150,30); // 위치 및 크기 설정
			goodsL[i].setFont(new Font("굴림", Font.BOLD, 16)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
			goodsL[i].setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 정렬
			goodsL[i].setHorizontalAlignment(SwingConstants.RIGHT); // 가로 오른쪽 정렬
			add(goodsL[i]);
		}
		goodsL[0].setText("골드 : " + gold);
		goodsL[0].setForeground(Color.YELLOW); // 글자색 설정
		
		goodsL[1].setText("돌 : " + rock);
		goodsL[1].setForeground(Color.DARK_GRAY); // 글자색 설정
		
		goodsL[2].setText("나무 : " + wood);
		goodsL[2].setForeground(Color.GREEN); // 글자색 설정
		
		goodsL[3].setText("인구 : " + population);
		goodsL[3].setForeground(Color.WHITE); // 글자색 설정
		
		boardLabel = new JLabel();
		boardLabel.setBounds(826,10,150,150);
		boardLabel.setIcon(boardIcon);
		add(boardLabel); // 패널에 추가
		
		// ---------------- 동굴 ----------------
		cave = new JButton();
		cave.setBounds(395,-86,200,200);
		cave.setIcon(caveIcon); 
		cave.setBorderPainted(false); // 선 없음
		cave.setContentAreaFilled(false); // 버튼 투명
		cave.addActionListener(this);
		add(cave); 
		
		caveLabel = new JLabel("동굴");
		caveLabel.setBounds(395,46,200,30);
		caveLabel.setFont(new Font("굴림", Font.BOLD, 14)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		caveLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		caveLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데 맞추기
		add(caveLabel);
		
		// ---------------- 대장간 ----------------
		smithy = new JButton();
		smithy.setBounds(52,136,200,200);
		smithy.setIcon(smithyIcon1);
		smithy.setBorderPainted(false);
		smithy.setContentAreaFilled(false);
		smithy.addActionListener(this);
		add(smithy);
		
		smithyLabel = new JLabel("대장간");
		smithyLabel.setBounds(52,216,200,200);
		smithyLabel.setFont(new Font("굴림", Font.BOLD, 14)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		smithyLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		smithyLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데  맞추기
		add(smithyLabel);
		
		// ---------------- 상점 ----------------	
		store = new JButton();
		store.setBounds(270,140,200,200);
		store.setIcon(storeIcon1);
		store.setBorderPainted(false);
		store.setContentAreaFilled(false);
		store.addActionListener(this);
		add(store);
		
		storeLabel = new JLabel("상점");
		storeLabel.setBounds(270,216,200,200);
		storeLabel.setFont(new Font("굴림", Font.BOLD, 14)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		storeLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데 맞추기
		add(storeLabel);

		// ---------------- 벌목장 ----------------
		lumberyard = new JButton();
		lumberyard.setBounds(512,258,200,200);
		if(lumberyardGrade == 0) { // 업그레이드 전이라면
			lumberyard.setIcon(lumberyardIcon1); // 업그레이드 전 이미지
		}
		else { // 업그레이드 됐다면
			lumberyard.setIcon(lumberyardIcon2); // 업그레이드 후 이미지
			lumberyard.setLocation(514,238); // 이미지에 맞춰서 위치 조정
		}
		lumberyard.setBorderPainted(false);
		lumberyard.setContentAreaFilled(false);
		lumberyard.addActionListener(this);
		lumberyard.addMouseListener(new MouseAdapter() { // 마우스리스너 추가
		    public void mouseEntered(MouseEvent evt) { // 마우스가 버튼 위에 있을 시
		        lumberyardLabel.setVisible(true); // 필요 재화 표시
		    }
		    public void mouseExited(MouseEvent evt) { // 마우스가 버튼을 벗어났을 시
		        lumberyardLabel.setVisible(false); // 필요 재화 표시 x
		    }
		});
		add(lumberyard);

		lumberyardLabel = new JLabel("<html>업그레이드에 필요한 재화<br>골드 : " + requiredGold[lumberyardGrade] + "나무 : " + requiredWood[lumberyardGrade] + "돌 : " + requiredRock[lumberyardGrade] + "</html>");
		if(lumberyardGrade == 0) { // 업그레이드 전이라면
			lumberyardLabel.setBounds(512,290,200,30);
		}
		else { // 업그레이드 됐다면
			lumberyardLabel.setBounds(512,260,200,30);
		}
		lumberyardLabel.setVisible(false);
		lumberyardLabel.setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		lumberyardLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		lumberyardLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데 맞추기
		add(lumberyardLabel);

		lumberyardLvLabel = new JLabel("벌목장 " + lumberyardGrade + "단계");
		lumberyardLvLabel.setBounds(572,400,80,30);
		add(lumberyardLvLabel);
		
		// ---------------- 채석장 ----------------
		quarry = new JButton();
		quarry.setBounds(764,268,200,200);
		if(quarryGrade == 0) {
			quarry.setIcon(quarryIcon1);
		}
		else {
			quarry.setIcon(quarryIcon2);
			quarry.setLocation(768,242);
		}
		quarry.setBorderPainted(false);
		quarry.setContentAreaFilled(false);
		quarry.addActionListener(this);
		quarry.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	quarryLabel.setVisible(true);
		    }
		    public void mouseExited(MouseEvent evt) {
		    	quarryLabel.setVisible(false);
		    }
		});
		add(quarry);

		quarryLabel = new JLabel("<html>업그레이드에 필요한 재화<br>골드 : " + requiredGold[quarryGrade] + "나무 : " + requiredWood[quarryGrade] + "돌 : " + requiredRock[quarryGrade] + "</html>");
		if(quarryGrade == 0) {
			quarryLabel.setBounds(764,290,200,30);
		}
		else {
			quarryLabel.setBounds(764,260,200,30);
		}
		quarryLabel.setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		quarryLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		quarryLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데  맞추기
		quarryLabel.setVisible(false);
		add(quarryLabel);
		
		quarryLvLabel = new JLabel("채석장 " + quarryGrade + "단계");
		quarryLvLabel.setBounds(824,400,80,30);
		add(quarryLvLabel);
		
		// ---------------- 카지노 ----------------
		casino = new JButton();
		casino.setBounds(647,-50,200,200);
		if(casinoGrade == 0) {
			casino.setIcon(casinoIcon1);
			casino.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent evt) {
			    	casinoLabel2.setVisible(true);
			    }
			    public void mouseExited(MouseEvent evt) {
			    	casinoLabel2.setVisible(false);
			    }
			});
		}
		else {
			casino.setIcon(casinoIcon2);
			casino.setLocation(647,-80);
		}
		casino.setBorderPainted(false);
		casino.setContentAreaFilled(false);
		casino.addActionListener(this);
		add(casino);
		
		casinoLabel = new JLabel("도박장");
		casinoLabel.setBounds(647,20,200,200);
		casinoLabel.setFont(new Font("굴림", Font.BOLD, 14)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		casinoLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		casinoLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데 맞추기
		add(casinoLabel);
		
		casinoLabel2 = new JLabel("<html>소요 재화 골드 : 5000<br>필요 재화 인구 수 : 3000");
		casinoLabel2.setBounds(487,54,200,30);
		casinoLabel2.setFont(new Font("굴림", Font.BOLD, 12)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		casinoLabel2.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		casinoLabel2.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데 맞추기
		casinoLabel2.setVisible(false);
		add(casinoLabel2);
		
		// ---------------- 보스 ----------------
		boss = new JButton();
		boss.setBounds(-2,-36,200,200);
		boss.setIcon(bossIcon);
		boss.setBorderPainted(false);
		boss.setContentAreaFilled(false);
		boss.addActionListener(this);
		if (population >= 2000) {
			boss.setVisible(true);
		}
		else boss.setVisible(false);
		add(boss);
		
		bossLabel = new JLabel("보스 탑");
		bossLabel.setBounds(-6,50,200,200);
		bossLabel.setFont(new Font("굴림", Font.BOLD, 14)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		bossLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
		bossLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데  맞추기
		if (population >= 2000) {
			bossLabel.setVisible(true);
		}
		else bossLabel.setVisible(false);
		add(bossLabel);

		// ---------------- 상태창 ----------------
		menu = new JButton("상태창");
		menu.setBounds(862,420,200,200);
		menu.setIcon(menuIcon);
		menu.setBorderPainted(false);
		menu.setContentAreaFilled(false);
		menu.addActionListener(this);
		add(menu);
		
		// ---------------- 일반건물 ----------------	
		for(int i=0; i<structureB.length; i++) {
			structureL[i] = new JLabel("<html>업그레이드에 필요한 재화<br>골드 : " + buildingGold + "나무 : " + buildingWood + "돌 : " + buildingRock + "</html>");
			structureL[i].setVisible(false);
			structureL[i].setVerticalAlignment(SwingConstants.CENTER); // 세로 가운데 맞추기
			structureL[i].setHorizontalAlignment(SwingConstants.CENTER); // 가로 가운데  맞추기
			structureL[i].setSize(200,30);
			add(structureL[i]);
		}
		if(building1Grade == 0) {
			structureL[0].setLocation(-2,366);
		}
		else {
			structureL[0].setLocation(-2,326);
		}
		if(building2Grade == 0) {
			structureL[1].setLocation(216,426);
		}
		else {
			structureL[1].setLocation(216,366);
		}
		structureL[2].setLocation(222,140);
		if(building4Grade == 0) {
			structureL[3].setLocation(630,450);
		}
		else {
			structureL[3].setLocation(630,410);
		}
		structureL[4].setLocation(636,238);
		
		for(int i=0; i<structureB.length; i++) {
			final int j = i;
			structureB[i] = new JButton();
			structureB[i].setSize(200,200);
			structureB[i].setBorderPainted(false);
			structureB[i].setContentAreaFilled(false);
			structureB[i].addActionListener(this);
			structureB[i].addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent evt) {
			    	structureL[j].setVisible(true);
			    }
			    public void mouseExited(MouseEvent evt) {
			    	structureL[j].setVisible(false);
			    }
			});
			add(structureB[i]);
		}
		structureB[0].setLocation(-2,326);
		structureB[1].setLocation(216,386);
		structureB[2].setLocation(222,-14);
		structureB[3].setLocation(630,410);
		structureB[4].setLocation(636,82);
		
		if(building1Grade == 0) {
			structureB[0].setIcon(structureBeforeIcon[0]);
		}
		else {
			structureB[0].setIcon(structureAfterIcon[0]);
			structureB[0].setLocation(-2,310);
		}
		
		if(building2Grade == 0) {
			structureB[1].setIcon(structureBeforeIcon[1]);
		}
		else {
			structureB[1].setIcon(structureAfterIcon[1]);
			structureB[1].setLocation(220,362);
		}
		
		if(building3Grade == 0) {
			structureB[2].setIcon(structureBeforeIcon[2]);
		}
		else {
			structureB[2].setIcon(structureAfterIcon[2]);
        	structureB[2].setLocation(216,-32);
		}
		
		if(building4Grade == 0) {
			structureB[3].setIcon(structureBeforeIcon[3]);
		}
		else {
			structureB[3].setIcon(structureAfterIcon[3]);
        	structureB[3].setLocation(628,398);
		}
		
		if(building5Grade == 0) {
			structureB[4].setIcon(structureBeforeIcon[4]);
		}
		else {
			structureB[4].setIcon(structureAfterIcon[4]);
			structureB[4].setLocation(636,66);
		}

		// ---------------- 배경 ----------------
		// 다른 JLabel, JButton등 각종 객체보다 아래 있어야 다른 객체가 보임
		// 배경이 위에 있다면 다른 객체들을 가려버림
		bgLabel = new JLabel();
		bgLabel.setIcon(bgIcon);
		bgLabel.setBounds(0,0,986,564);
		add(bgLabel);
		
		// ---------------- 벌목장,채석장 나무,돈 올라가게 ----------------
		townTimer = new Timer(1000, new ActionListener() { // 1000=1초마다
			@Override
			public void actionPerformed(ActionEvent e) { 
				wood += lumberyardGrade * 1; // 단계만큼 증가
				rock += quarryGrade * 1;
				goodsL[1].setText("돌 : " + rock); // 개수 갱신
				goodsL[2].setText("나무 : " + wood);
				try {
					goodsDAO.updateGoodsWood(1, wood); // DB 갱신
					goodsDAO.updateGoodsRock(1, rock);
					gold = goodsDAO.getGoodsGold(1);
					wood = goodsDAO.getGoodsWood(1);
					rock = goodsDAO.getGoodsRock(1);
					population = goodsDAO.getGoodsPopulation(1);
					playerHp = playerDAO.getPlayerHp(1);
				} 
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		townTimer.start();
		
		if (population < 2000) { // 인구 수가 2000미만이라면
			bossTimer = new Timer(500, new ActionListener() { // 500=0.5초마다
				@Override
				public void actionPerformed(ActionEvent e) {
					if (population >= 2000) { // 인구 수가 2000이상인 되면
						boss.setVisible(true); // 보스가 보이게
						bossLabel.setVisible(true); 
						// 메시지 출력
						JOptionPane.showMessageDialog(null, "강력한 힘을 가진 탑이 등장했습니다.", "경고", JOptionPane.WARNING_MESSAGE);
						bossTimer.stop(); // 한 번만 실행되게 멈추기
					}
				}
			});
			bossTimer.start();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == cave) { // 동굴 클릭 시 
			if(playerHp <= 0) {
				 JOptionPane.showMessageDialog(null, "캐릭터의 체력이 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
			else new Cave(goodsL);
		} 
		
		else if (obj == store) { // 상점 클릭 시
			new Store(goodsL);
		} 
		
		else if (obj == lumberyard) { // 벌목장 클릭 시
			if (lumberyardGrade >= 4) {
				JOptionPane.showMessageDialog(null, "최대 단계입니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
			else if (gold >= requiredGold[lumberyardGrade] && wood >= requiredWood[lumberyardGrade] && rock >= requiredRock[lumberyardGrade]) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까? \n현재 생산량 : " +lumberyardGrade, "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					lumberyard.setIcon(lumberyardIcon2);
					lumberyard.setLocation(514,238);
					gold -= requiredGold[lumberyardGrade];
					wood -= requiredWood[lumberyardGrade];
					rock -= requiredRock[lumberyardGrade];
					lumberyardGrade += 1;
					goodsL[0].setText("골드 : " + gold);
					lumberyardLabel.setLocation(512,260);
					try {
						buildingDAO.updateLumberyardGrade(1, lumberyardGrade);
						goodsDAO.updateGoodsGold(1, gold);
						goodsDAO.updateGoodsWood(1, wood);
						goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					lumberyardLvLabel.setText("벌목장 " + lumberyardGrade + "단계");
					if(lumberyardGrade >= 4) {
						lumberyardLabel.setText("최대 단계입니다.");
					}
					else {
						lumberyardLabel.setText("<html>업그레이드에 필요한 재화<br>골드 : " + requiredGold[lumberyardGrade] + "나무 : " + requiredWood[lumberyardGrade] + "돌 : " + requiredRock[lumberyardGrade] + "</html>");
					}
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
		} 
		
		else if (obj == quarry) { // 채석장 클릭 시
		    if (quarryGrade >= 4) {
		        JOptionPane.showMessageDialog(null, "최대 단계입니다.", "경고", JOptionPane.WARNING_MESSAGE);
		    }
		    else if (gold >= requiredGold[quarryGrade] && wood >= requiredWood[quarryGrade] && rock >= requiredRock[quarryGrade]) {
		        int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까? \n현재 생산량 : " + quarryGrade, "확인", JOptionPane.YES_NO_OPTION);
		        if (response == 0) {
		        	quarry.setIcon(quarryIcon2);
		        	quarry.setLocation(768,242);
		            gold -= requiredGold[quarryGrade];
		            wood -= requiredWood[quarryGrade];
		            rock -= requiredRock[quarryGrade];
		            quarryGrade += 1;
		            goodsL[0].setText("골드 : " + gold);
		            quarryLabel.setLocation(764,260);
		            try {
		                buildingDAO.updateQuarryGrade(1, quarryGrade);
		                goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
		            } catch (SQLException e2) {
		                e2.printStackTrace();
		            }
		            quarryLvLabel.setText("채석장 " + quarryGrade + "단계");
		            if (quarryGrade >= 4) {
		            	quarryLabel.setText("최대 단계입니다.");
		            }
		            else {
		            	 quarryLabel.setText("<html>업그레이드에 필요한 재화<br>골드 : " + requiredGold[quarryGrade] + "나무 : " + requiredWood[quarryGrade] + "돌 : " + requiredRock[quarryGrade] + "</html>");
		            }
		        } else if (response == 1) {
		            System.out.println("아니오 선택");
		        }
		    }
		    else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}

		
		else if (obj == menu) { // 메뉴 클릭 시
			new Menu();
		} 
		
		else if (obj == smithy) {
			new Smithy(goodsL);
		}
		
		else if (obj == casino) {
			if (casinoGrade >= 1) {
				new Casino(goodsL);
		    }
		    else if (gold >= 5000 && population >= 3000) {
		        int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까? \n현재 생산량 : " + quarryGrade, "확인", JOptionPane.YES_NO_OPTION);
		        if (response == 0) {
		            gold -= 5000;
		            goodsL[0].setText("골드 : " + gold);
		            casino.setIcon(casinoIcon2);
					casino.setLocation(647,-80);
		            casinoGrade += 1;
		            casinoLabel2.setText("");
		            try {
		                buildingDAO.updateCasinoGrade(1, casinoGrade);
		                goodsDAO.updateGoodsGold(1, gold);
		            } catch (SQLException e2) {
		                e2.printStackTrace();
		            }
		        } else if (response == 1) {
		            System.out.println("아니오 선택");
		        }
		    }
		    else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
		
		else if (obj == boss) {
			new Boss();
		}
		
		else if (obj == structureB[0]) {
			if (gold >= buildingGold && wood >= buildingWood && rock >= buildingRock) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					structureB[0].setIcon(structureAfterIcon[0]);
					structureB[0].setLocation(-2, 310);
					structureL[0].setLocation(-2, 326);
					gold -= buildingGold;
					wood -= buildingWood;
					rock -= buildingRock;
					population += 1000;
					building1Grade += 1;
					goodsL[0].setText("골드 : " + gold);
					goodsL[3].setText("인구 : " + population);
					try {
						buildingDAO.updateBuilding1Grade(1, 1);
						goodsDAO.updateGoodsPopulation(1, population);
		                goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					System.out.println(building1Grade);
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			 else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
		else if (obj == structureB[1]) {
			if (gold >= buildingGold && wood >= buildingWood && rock >= buildingRock) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					structureB[1].setIcon(structureAfterIcon[1]);
					structureB[1].setLocation(220, 362);
					structureL[1].setLocation(216, 366);
					gold -= buildingGold;
					wood -= buildingWood;
					rock -= buildingRock;
					population += 1000;
					building1Grade += 1;
					goodsL[0].setText("골드 : " + gold);
					goodsL[3].setText("인구 : " + population);
					try {
						buildingDAO.updateBuilding2Grade(1, 1);
						goodsDAO.updateGoodsPopulation(1, population);
						goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					System.out.println(building2Grade);
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			 else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
		else if (obj == structureB[2]) {
			if (gold >= buildingGold && wood >= buildingWood && rock >= buildingRock) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					structureB[2].setIcon(structureAfterIcon[2]);
					structureB[2].setLocation(216, -32);
					structureL[2].setLocation(222, 140);
					gold -= buildingGold;
					wood -= buildingWood;
					rock -= buildingRock;
					population += 1000;
					building1Grade += 1;
					goodsL[0].setText("골드 : " + gold);
					goodsL[3].setText("인구 : " + population);
					try {
						buildingDAO.updateBuilding3Grade(1, 1);
						goodsDAO.updateGoodsPopulation(1, population);
						goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					System.out.println(building3Grade);
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			 else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
		else if (obj == structureB[3]) {
			if (gold >= buildingGold && wood >= buildingWood && rock >= buildingRock) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					structureB[3].setIcon(structureAfterIcon[3]);
					structureB[3].setLocation(628, 398);
					structureL[3].setLocation(630, 410);
					gold -= buildingGold;
					wood -= buildingWood;
					rock -= buildingRock;
					population += 1000;
					building1Grade += 1;
					goodsL[0].setText("골드 : " + gold);
					goodsL[3].setText("인구 : " + population);
					try {
						buildingDAO.updateBuilding4Grade(1, 1);
						goodsDAO.updateGoodsPopulation(1, population);
						goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					System.out.println(building4Grade);
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			 else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
		else if (obj == structureB[4]) {
			if (gold >= buildingGold && wood >= buildingWood && rock >= buildingRock) {
				int response = JOptionPane.showConfirmDialog(null, "업그레이드하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					structureB[4].setIcon(structureAfterIcon[4]);
					structureB[4].setLocation(636, 66);
					structureL[4].setLocation(636, 238);
					gold -= buildingGold;
					wood -= buildingWood;
					rock -= buildingRock;
					population += 1000;
					building1Grade += 1;
					goodsL[0].setText("골드 : " + gold);
					goodsL[3].setText("인구 : " + population);
					try {
						buildingDAO.updateBuilding5Grade(1, 1);
						goodsDAO.updateGoodsPopulation(1, population);
						goodsDAO.updateGoodsGold(1, gold);
		                goodsDAO.updateGoodsWood(1, wood);
		                goodsDAO.updateGoodsRock(1, rock);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					System.out.println(building5Grade);
				} else if (response == 1) {
					System.out.println("아니오 선택");
				}
			}
			 else JOptionPane.showMessageDialog(null, "재화가 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);	
		}
	}
}