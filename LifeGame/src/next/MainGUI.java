package next;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private Timer timer;
	private TimerTask timerTask;
	private Generation generation;
	private JButton[][] Buttons;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Generation g=new Generation();
					//LifeTimer Lt = new LifeTimer(g);
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
	    generation = new Generation();
	    timer=new Timer();
	  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Button_Start = new JButton("\u5F00\u59CB");
		Button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Button_Start.setBounds(414, 10, 133, 40);
		contentPane.add(Button_Start);
		
		JPanel grid = new JPanel();
		grid.setBounds(10, 10, 300, 300);
		contentPane.add(grid);
		grid.setLayout(new GridLayout(30,30));
		
		JButton Button_Clear = new JButton("\u6E05\u7A7A");
		Button_Clear.setBounds(414, 268, 133, 40);
		contentPane.add(Button_Clear);
		
		JButton Button_Init = new JButton("\u968F\u673A\u751F\u6210\u65B0\u753B\u9762");
		Button_Init.setBounds(414, 139, 133, 40);
		contentPane.add(Button_Init);
		Buttons = new JButton[30][30];
		for (int i = 0; i < 30 ;i++) {
            for (int j = 0; j < 30; j++) {
            	Buttons[i][j] = new JButton(""); //按钮内容置空以表示细胞
            	Buttons[i][j].setEnabled(false);
            	Buttons[i][j].setBackground(Color.WHITE); //初始时所有细胞均为死
                grid.add(Buttons[i][j]);
            }
        }
		
		
		Button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("开始")) {
					//Lt.start(Buttons);
				    start();
				    Button_Start.setText("结束");
				}
				else {
					//Lt.end();
			        timerTask.cancel();
			        timerTask = null;
					Button_Start.setText("开始");
				}			
			}
		});
		
		Button_Init.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Button_Start.getText().equalsIgnoreCase("结束")) {
					//Lt.end();
					timerTask.cancel();
                    timerTask = null;
					Button_Start.setText("开始");
				}
				//Lt.init(Buttons);
				generation = new Generation();
		        //根据数组更新表格颜色
		        for(int i=0;i<30;i++) {
		            for(int j=0;j<30;j++) {
		                if(generation.select(i+1, j+1)==1)
		                    Buttons[i][j].setBackground(Color.BLACK);
		                else
		                    Buttons[i][j].setBackground(Color.WHITE);
		            }
		        }
			}
		});
		
		Button_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 30 ;i++) {
		            for (int j = 0; j < 30; j++) {
		            	Buttons[i][j].setBackground(Color.WHITE);
		            }
		        }
				//Lt.clear();
				generation = null;
				if(Button_Start.getText().equalsIgnoreCase("结束")) {
				//Lt.end();
				timerTask.cancel();
	            timerTask = null;
				Button_Start.setText("开始");
				}
				
			}
		});
	}
	
	private void start() {
	  timerTask = new TimerTask() {
        public void run() {
            if(generation == null)
                generation = new Generation();
            //更新细胞数组
            generation.update();
            //根据数组更新表格颜色
            for(int i=0;i<30;i++) {
              for(int j=0;j<30;j++) {
                  if(generation.select(i+1, j+1)==1)
                      Buttons[i][j].setBackground(Color.BLACK);
                  else
                      Buttons[i][j].setBackground(Color.WHITE);
              }
          }
        }
    };
    timer.schedule(timerTask,0,1000);
	}
}
