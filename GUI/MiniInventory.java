package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;

public class MiniInventory extends JFrame implements ActionListener {
	InventoryDAO inventoryDAO = new InventoryDAO();

	private JButton back;
	private JLabel[] inventoryL = new JLabel[8];
	private JLabel[] countL = new JLabel[8];

	private ImageIcon[] inventoryIcon = { new ImageIcon("src/img/storepotion1.png"), new ImageIcon("src/img/storepotion2.png"),
			new ImageIcon("src/img/goldore.png"), new ImageIcon("src/img/ruby.png"),
			new ImageIcon("src/img/emerald.png"), new ImageIcon("src/img/monstercore.png"),
			new ImageIcon("src/img/monstershoulder.png"), new ImageIcon("src/img/monsterclaw.png") };

	public MiniInventory() {
		Container ct = getContentPane();
		setBounds(650, 500, 276, 204); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("미니 인벤토리");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(MiniInventory.this,
                        "정말로 종료하시겠습니까?", "종료 확인",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                	 System.exit(0);
            }
        });
		JPanel playerP = new JPanel();
		playerP.setLayout(null);
		playerP.setBounds(0, 0, 266, 164);
		ct.add(playerP);

		int x = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				inventoryL[x] = new JLabel();
				inventoryL[x].setIcon(inventoryIcon[x]);
				inventoryL[x].setBounds(10 + (60 * j), 10 + (60 * i), 60, 60); // 위치 조정
				countL[x] = new JLabel();
				countL[x].setBounds(10 + (60 * j), 50 + (60 * i), 60, 20);
				countL[x].setFont(new Font("굴림", Font.BOLD, 14));
				countL[x].setVerticalAlignment(SwingConstants.CENTER);
				countL[x].setHorizontalAlignment(SwingConstants.RIGHT);
				playerP.add(countL[x]);
				playerP.add(inventoryL[x]);
				x++;
			}
		}

		try {
			countL[0].setText("" + inventoryDAO.getInventoryPotion1(1) + "");
			countL[1].setText("" + inventoryDAO.getInventoryPotion2(1) + "");
			countL[2].setText("" + inventoryDAO.getInventoryGoldOre(1) + "");
			countL[3].setText("" + inventoryDAO.getInventoryRuby(1) + "");
			countL[4].setText("" + inventoryDAO.getInventoryEmerald(1) + "");
			countL[5].setText("" + inventoryDAO.getInventoryCore(1) + "");
			countL[6].setText("" + inventoryDAO.getInventoryShoulder(1) + "");
			countL[7].setText("" + inventoryDAO.getInventoryClaw(1) + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		back = new JButton();
		back.setBounds(10,140,244,20);
		back.addActionListener(this);
		playerP.add(back);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if (obj == back) {
			dispose();
		}

	}
}
