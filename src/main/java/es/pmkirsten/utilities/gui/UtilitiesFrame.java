package es.pmkirsten.utilities.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import es.pmkirsten.utilities.gui.maven.MavenInstallLibrariesPanel;
import es.pmkirsten.utilities.gui.nativeOpen.NativeApplicationOpenFilePanel;

public class UtilitiesFrame extends JFrame {

	private final JPanel contentPane;
	protected JPanel openNativePanel = new NativeApplicationOpenFilePanel();
	protected JPanel installM2Pane = new MavenInstallLibrariesPanel();

	public class MenuItemChangeActionListener implements ActionListener {

		JPanel panel;

		public MenuItemChangeActionListener(JPanel panel) {
			this.panel = panel;
		}

		public void actionPerformed(ActionEvent e) {
			UtilitiesFrame.this.getContentPane().removeAll();
			UtilitiesFrame.this.getContentPane().add(this.panel);
			UtilitiesFrame.this.getContentPane().repaint();
			UtilitiesFrame.this.getContentPane().revalidate();
		}

	}

	/**
	 * Create the frame.
	 */
	public UtilitiesFrame() {
		this.setTitle(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("UtilitiesFrame.this.title"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 450);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnMaven = new JMenu(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("UtilitiesFrame.mnMaven.text"));
		menuBar.add(mnMaven);

		JMenuItem mntmInstallm = new JMenuItem(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("UtilitiesFrame.mntmInstallm.text"));
		mntmInstallm.addActionListener(new MenuItemChangeActionListener(this.installM2Pane));
		mnMaven.add(mntmInstallm);

		JMenu mnOpennative = new JMenu(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("UtilitiesFrame.mnOpennative.text"));
		menuBar.add(mnOpennative);

		JMenuItem mntmOpennative = new JMenuItem(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("UtilitiesFrame.mntmOpennative.text"));
		mntmOpennative.addActionListener(new MenuItemChangeActionListener(this.openNativePanel));
		mnOpennative.add(mntmOpennative);
	}

	@Override
	public JPanel getContentPane() {
		return this.contentPane;
	}

	public static boolean checkUnixOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
			return true;
		}
		return false;
	}

	public static boolean isJava8orBelow() {
		String version = System.getProperty("java.version");
		if (version.startsWith("1.")) {
			version = version.substring(2, 3);
		} else {
			int dot = version.indexOf(".");
			if (dot != -1) {
				version = version.substring(0, dot);
			}
		}

		if (version.equalsIgnoreCase("6")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (!UtilitiesFrame.checkUnixOS()) {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} else {
						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					UtilitiesFrame frame = new UtilitiesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
