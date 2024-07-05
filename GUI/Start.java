package GUI;

import DB.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Start extends JFrame implements ActionListener {
	PlayerDAO playerDAO = new PlayerDAO();
	
	private int story; // 스토리 봤는지 여부
	// 스토리 대사
	private String[] lines = {"2○○○년 엄청난 산업화로 인해 급격한 도시화가 이루어지고", "모든 사람들이 시골을 떠나 시골은 황폐해졌다", "와중 이 시골을 구하기 위해 한 영웅이 등장하는데...","이걸 봤다고?"};
	private JLabel storyLabel, lineLabel, StartLabel; // 대사 및 이미지
	private JButton bgButton; // 시작화면
	// 이미지 가져오기
	private ImageIcon bgIcon = new ImageIcon("src/img/startbackground.png");
	private ImageIcon[] storyIcon = {
			new ImageIcon("src/img/startcity.png"),
			new ImageIcon("src/img/startdowntown.png"),
			new ImageIcon("src/img/hero.png"),
			new ImageIcon("src/img/black.png")
	};
	// 스토리 타이머, 로딩바 타이머
	private Timer storyTimer, lodingBarTimer;
	// 로딩바
	private JProgressBar lodingbar;
	
	public Start() {
		Container ct = getContentPane();
		setBounds(300, 100, 1000, 600); // 프레임이 켜지는 위치 및 크기 설정
		ct.setLayout(null); // 정렬판 생성 정렬 기준 없음 (좌표)
		setTitle("시작화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 정상적으로 종료되게 설정
		
		try {
			story = playerDAO.getPlayerStory(1); // 스토리 여부 가져오기
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		JPanel bgPanel = new JPanel();
		bgPanel.setLayout(null);
		bgPanel.setSize(986,564);
		add(bgPanel);
		
		// 로딩 바 설정
		lodingbar = new JProgressBar(0, 100); 
		lodingbar.setBounds(43, 484, 900, 40);
		lodingbar.setForeground(Color.RED);
		lodingbar.setVisible(false);
		bgPanel.add(lodingbar);
		
		// 대사 설정
		lineLabel = new JLabel();
		lineLabel.setBounds(43, 484, 900, 40);
		lineLabel.setBackground(Color.WHITE);
		lineLabel.setOpaque(true);
		lineLabel.setForeground(Color.BLACK); // 글자색 설정
		lineLabel.setFont(new Font("굴림", Font.BOLD, 20)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		lineLabel.setVerticalAlignment(SwingConstants.CENTER);
		lineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bgPanel.add(lineLabel);
	
		// 스토리 이미지
		storyLabel = new JLabel();
		storyLabel.setSize(986,564);
		bgPanel.add(storyLabel);
		
		// 시작화면
		bgButton = new JButton();
		bgButton.setSize(986,564);
		bgButton.setLayout(null);
		bgButton.setIcon(bgIcon);
		bgButton.addActionListener(this);
		bgPanel.add(bgButton);
		
		// 아무 곳이나 클릭해주세요 글자
		StartLabel = new JLabel("아무 곳이나 클릭해주세요");
		StartLabel.setBounds(0, 480, 986, 40);
		StartLabel.setForeground(Color.BLACK); // 글자색 설정
		StartLabel.setFont(new Font("굴림", Font.BOLD, 30)); // 글자 모양, 효과, 크기 설정 - 효과(BOLD : 진하게, ITALIC : 기울여서, PLAIN : 기본)
		StartLabel.setVerticalAlignment(SwingConstants.CENTER);
		StartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bgButton.add(StartLabel);
		
		setVisible(true); // 보이게 설정	
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 오브젝트 판단
		if(obj == bgButton) { // 배경 클릭 시
			if(story == 0) { // 스토리를 보지 않았다면
				storing(); // 스토리 메서드 실행
				try {
				playerDAO.updatePlayerStory(1, 1); // 스토리 값을 1로 업데이트 (즉 스토리를 본 걸로 만듦)
				}
				catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
			else { // 그렇지 않으면 바로 실행
				storyLabel.setIcon(storyIcon[3]); 
				loding();
			}
		}
	}
	
	private void storing() {
		if (storyTimer != null && storyTimer.isRunning()) { // 스토리 타이머가 초기화되지 않았거나 실행중이라면
			storyTimer.stop(); // 중지 오류 방지용임 
        }
		bgButton.setVisible(false); // 배경화면 안보이게
		storyLabel.setIcon(storyIcon[0]); // 스토리 배경 보이게
		lineLabel.setText(lines[0]); // 스토리 대사 바꾸기
		storyTimer = new Timer(2000, new ActionListener() {	// 2000 = 2초마다
			int i = 1; // i를 1로 초기화
			public void actionPerformed(ActionEvent e) {
				storyLabel.setIcon(storyIcon[i]); // i 값에 따라 배경 및
				lineLabel.setText(lines[i]); // 대사 변경
				i++; // i값 1씩 증가
				if(i==4){ //i가 4가 되면
					storyTimer.stop(); // 스토리 타이머 멈추고
					loding(); // 로딩 실행
				}
            }
		});
		storyTimer.start(); // 스토리 타이머 시작
	}
	
	private void loding() {
		if (lodingBarTimer != null && lodingBarTimer.isRunning()) {
			lodingBarTimer.stop();
        }
		double delay = 2000; // 걸리는 시간 2초
		double targetY = 2000; // 얼마만큼 가야되는지
		double stepY = 100; // 얼마나 갈 것인지
		lodingbar.setVisible(true); // 로딩 바 보이게 
		bgButton.setVisible(false); // 배경 안보이게
		lodingBarTimer = new Timer(100, new ActionListener() { // 100 = 0.1초 마다
			double currentY=0; // 현재 Y값 저장용
			public void actionPerformed(ActionEvent e) {
                if (currentY <= targetY) { // 가야될 Y값 보다 현재 Y값이 작거나 같다면
                    currentY += stepY; // 현재 Y값에 증가할 Y값 만큼 추가
            		lodingbar.setValue((int) (((double) currentY / delay) * 100)); // 로딩 바 갱신
				}
                else {
                	lodingBarTimer.stop(); // 로딩 바가 전부 찼다면 타이머 멈춤
    				new Town(); // 마을(게임) 실행
    				dispose(); // 이 프레임 닫기
                }
            }
		});
		lodingBarTimer.start(); // 로딩바 타이머 시작
	}
	// 즉 0.1초마다 갈 거리를 stepY로 정해서 몇 초(delay)동안 움직일 거리를 targetY
	// 여기서는 0.1초마다 100씩 2초 동안 2000만큼 이동
}
