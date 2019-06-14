import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LifePanel extends JPanel implements ActionListener {
	boolean[][] grid;
	Random random = new Random();
	Timer timer;

	public LifePanel(int sizeGrid) {
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setMaximumSize(new Dimension(1000, 1000));
		this.setBackground(Color.WHITE);
		this.grid = new boolean[sizeGrid][sizeGrid];
		this.randInit();

		this.timer = new Timer(200, this);
		this.timer.setRepeats(true);
		// this.timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int caseNb = this.grid.length;
		int caseSize = this.getWidth() / caseNb;

		g.setColor(Color.BLACK);

		for (int i = 0; i < caseNb; i += 1) 
			g.drawLine(0, i*caseSize, caseNb*caseSize, i*caseSize);

		for (int i = 0; i < caseNb; i += 1) 
			g.drawLine(i*caseSize, 0, i*caseSize, caseNb*caseSize);

		for (int i = 0; i < caseNb; i += 1) {
			for (int j = 0; j < caseNb; j += 1)
				if (this.grid[i][j]) {
					g.fillRect(i*caseSize, j*caseSize, caseSize, caseSize);
				}
		}
	}

	public void randInit() {
		for (int i = 0; i < this.grid.length; i += 1) {
			for (int j = 0; j < this.grid[i].length; j += 1)
				this.grid[i][j] = this.random.nextBoolean();
		}
	}

	public boolean inGrid(int x, int y) {
		return x >= 0 && y >= 0 && x < this.grid.length && y < this.grid.length;
	}

	public void cycle() {
		boolean[][] tmpGrid = new boolean[this.grid.length][this.grid.length];

		for (int i = 0; i < this.grid.length; i += 1) {
			for (int j = 0; j < this.grid[i].length; j += 1) {
				int count = 0;
				if (inGrid(i-1, j)) {
					if (this.grid[i-1][j])
						count += 1;
				}
				if (inGrid(i+1, j)) {
					if (this.grid[i+1][j])
						count += 1;
				}
				if (inGrid(i, j-1)) {
					if (this.grid[i][j-1])
						count += 1;
				}
				if (inGrid(i, j+1)) {
					if (this.grid[i][j+1])
						count += 1;
				}
				if (inGrid(i-1, j-1)) {
					if (this.grid[i-1][j-1])
						count += 1;
				}
				if (inGrid(i-1, j+1)) {
					if (this.grid[i-1][j+1])
						count += 1;
				}
				if (inGrid(i+1, j-1)) {
					if (this.grid[i+1][j-1])
						count += 1;
				}
				if (inGrid(i+1, j+1)) {
					if (this.grid[i+1][j+1])
						count += 1;
				}
				if (this.grid[i][j])
					tmpGrid[i][j] = count == 2 || count == 3 ? true : false;
				else 
					tmpGrid[i][j] = count == 3 ? true : false;
			}
		}
		this.grid = tmpGrid;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.timer) {
			this.cycle();
			this.repaint();
		}
	}

	public void write(String fileName) {
		File file = new File(fileName);
		try {
			FileOutputStream fileW = new FileOutputStream(file);
			fileW.write(this.grid.length);

			for (int i = 0; i < this.grid.length; i += 1) {
				for (int j = 0; j < this.grid[i].length; j += 1) {
					if (this.grid[i][j])
						fileW.write(1);
					else 
						fileW.write(0);
				}
			}

			fileW.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean[][] read(String fileName) {
		File file = new File(fileName);
		try {
			FileInputStream fileR = new FileInputStream(file);
			int size = fileR.read();

			boolean[][] grid = new boolean[size][size];

			for (int i = 0; i < size; i += 1) {
				for (int j = 0; j < size; j += 1) {
					if (fileR.read() == 1)
						grid[i][j] = true;
					else 
						grid[i][j] = false;
				}
			}

			fileR.close();

			return grid;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
