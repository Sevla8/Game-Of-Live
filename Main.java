import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		int size = 100;
		try {
			size = Integer.parseInt(args[0]);	
		}
		catch (Exception e) {
			// e.printStackTrace();
		}
		finally {
			JFrame jFrame = new JFrame();

			jFrame.setSize(1100, 1100);
			jFrame.setLocation(0, 25);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setTitle("Game of Life");	
			
			LifePanel lifePanel = new LifePanel(size);
			lifePanel.setOpaque(true);
			MainPanel mainPanel = new MainPanel(lifePanel);
			mainPanel.setOpaque(true);

			jFrame.add(mainPanel);
			jFrame.setVisible(true);
		}

		// LifePanel lifePanel = new LifePanel(10);
		// lifePanel.write("test.txt");

		// boolean[][] grid = lifePanel.read("test.txt");
		// for (int i = 0; i < grid.length; i += 1) {
		// 	for (int j = 0; j < grid[i].length; j += 1) {
		// 		if (grid[i][j])
		// 			System.out.print("1");
		// 		else 
		// 			System.out.print("0");
		// 	}
		// 	System.out.print("\n");
		// }
	}
}
