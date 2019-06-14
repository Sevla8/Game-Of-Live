import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.Box;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

public class MainPanel extends JPanel implements ActionListener {
	LifePanel lifePanel;
	JButton start;
	JButton stop;
	JButton read;
	JButton write;

	public MainPanel(LifePanel lifePanel) {
		this.setBackground(Color.RED);
		this.setMinimumSize(new Dimension(1000, 1000));

		Box box0 = Box.createHorizontalBox();
		this.lifePanel = lifePanel;
		box0.add(lifePanel);

		Box box1 = Box.createHorizontalBox();
		this.start = new JButton("start");
		this.start.addActionListener(this);
		box1.add(this.start);
		this.stop = new JButton("stop");
		this.stop.addActionListener(this);
		box1.add(this.stop);
		this.read = new JButton("read");
		this.read.addActionListener(this);
		box1.add(this.read);
		this.write = new JButton("write");
		this.write.addActionListener(this);
		box1.add(this.write);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(box0);
		this.add(box1);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.start) {
			this.lifePanel.timer.start();
		}
		else if (e.getSource() == this.stop) {
			this.lifePanel.timer.stop();
		}
		else if (e.getSource() == this.read) {
			this.lifePanel.grid = this.lifePanel.read("file.txt");
			this.lifePanel.repaint();
		}
		else if (e.getSource() == this.write) {
			this.lifePanel.write("file.txt");
		}
	}
}
