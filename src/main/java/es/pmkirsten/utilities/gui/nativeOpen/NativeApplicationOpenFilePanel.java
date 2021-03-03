package es.pmkirsten.utilities.gui.nativeOpen;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import es.pmkirsten.utilities.gui.UtilitiesFrame;

public class NativeApplicationOpenFilePanel extends JPanel {

	/**
	 * Create the panel.
	 */

	protected JFileChooser fileChooser = new JFileChooser();
	private final JLabel lblFile;
	public JTextArea txtAreaResult;

	public NativeApplicationOpenFilePanel() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_contentPane);

		JLabel lblFilePath = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.lblFilePath.text"));
		GridBagConstraints gbc_lblFilePath = new GridBagConstraints();
		gbc_lblFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilePath.gridx = 0;
		gbc_lblFilePath.gridy = 0;
		this.add(lblFilePath, gbc_lblFilePath);

		this.lblFile = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.lblFile.text"));
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblFile.gridx = 1;
		gbc_lblFile.gridy = 0;
		this.add(this.lblFile, gbc_lblFile);

		JButton btnUpload = new JButton(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.btnUpload.text"));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NativeApplicationOpenFilePanel.this.fileChooser.showOpenDialog(NativeApplicationOpenFilePanel.this);
				if (NativeApplicationOpenFilePanel.this.fileChooser.getSelectedFile() != null) {
					NativeApplicationOpenFilePanel.this.getLblFile().setText(NativeApplicationOpenFilePanel.this.fileChooser.getSelectedFile().getName());
				}
			}
		});
		GridBagConstraints gbc_btnUpload = new GridBagConstraints();
		gbc_btnUpload.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpload.gridx = 2;
		gbc_btnUpload.gridy = 0;
		this.add(btnUpload, gbc_btnUpload);

		JScrollPane scrollPaneResult = new JScrollPane();
		GridBagConstraints gbc_scrollPaneResult = new GridBagConstraints();
		gbc_scrollPaneResult.gridwidth = 3;
		gbc_scrollPaneResult.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneResult.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneResult.gridx = 0;
		gbc_scrollPaneResult.gridy = 1;
		this.add(scrollPaneResult, gbc_scrollPaneResult);

		this.txtAreaResult = new JTextArea();
		this.txtAreaResult.setRows(8);
		this.txtAreaResult.setText(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.txtAreaResult.text"));
		scrollPaneResult.setViewportView(this.txtAreaResult);

		JPanel btnPane = new JPanel();
		GridBagConstraints gbc_btnPane = new GridBagConstraints();
		gbc_btnPane.gridwidth = 3;
		gbc_btnPane.fill = GridBagConstraints.BOTH;
		gbc_btnPane.gridx = 0;
		gbc_btnPane.gridy = 2;
		this.add(btnPane, gbc_btnPane);
		btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		JButton btnReset = new JButton(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.btnReset.text"));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NativeApplicationOpenFilePanel.this.getTxtAreaResult().setText("");
			}
		});
		btnPane.add(btnReset);
		JButton btnOk = new JButton(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("NativeApplicationOpenFilePanel.btnOk.text"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NativeApplicationOpenFilePanel.this.fileChooser.getSelectedFile() != null) {

					try {
						if (NativeApplicationOpenFilePanel.this.checkUnixOSAndJava8orBelowVersion()) {
							// Java 1.6 y Linux//
							StringBuilder cmds = new StringBuilder();
							cmds.append(String.format("xdg-open %s", NativeApplicationOpenFilePanel.this.fileChooser.getSelectedFile().getAbsolutePath()));
							NativeApplicationOpenFilePanel.this.getTxtAreaResult().setText(cmds.toString());
							ProcessBuilder processBuilder = new ProcessBuilder();
							processBuilder.command("bash", "-c", cmds.toString());
							Process process = processBuilder.start();

							StringBuilder output = new StringBuilder();

							BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

							String line;
							while ((line = reader.readLine()) != null) {
								System.out.println(line);
							}
						} else {
							// Java 1.8//
							Desktop desktop = null;
							if (Desktop.isDesktopSupported()) {
								desktop = Desktop.getDesktop();
								desktop.open(NativeApplicationOpenFilePanel.this.fileChooser.getSelectedFile());
							} else {

							}
						}
					} catch (Exception e1) {
						StringWriter sw = new StringWriter();
						e1.printStackTrace(new PrintWriter(sw));
						NativeApplicationOpenFilePanel.this.getTxtAreaResult().setText(sw.toString());
					}
				}
			}
		});
		btnPane.add(btnOk);


	}

	public JLabel getLblFile() {
		return this.lblFile;
	}

	public JTextArea getTxtAreaResult() {
		return this.txtAreaResult;
	}

	public boolean checkUnixOSAndJava8orBelowVersion() {

		boolean javaVersion = UtilitiesFrame.isJava8orBelow();
		boolean os = UtilitiesFrame.checkUnixOS();

		return javaVersion && os;

	}


}