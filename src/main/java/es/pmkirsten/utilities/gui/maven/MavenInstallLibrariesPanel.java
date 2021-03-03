package es.pmkirsten.utilities.gui.maven;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MavenInstallLibrariesPanel extends JPanel {
	private final JTextField textFieldGroupId;
	private final JTextField textFieldArtifactId;
	private final JTextField textFieldVersion;
	protected JFileChooser fileLibrarieChooser = new JFileChooser();
	protected JFileChooser filePomChooser = new JFileChooser();

	private final JLabel lblJarSelectedFileToInstall;
	private final JComboBox comboBoxPackaging;
	private final JTextArea txtAreaResult;
	private final JLabel lblGroupid;
	private final JLabel lblArtifactid;
	private final JLabel lblPackaging;
	private final JLabel lblVersion;
	private final JButton btnSelectedPomFilePath;
	private final JLabel lblSelectedPomFilePath;
	private final JLabel lblPomFile;

	/**
	 * Create the panel.
	 */
	public MavenInstallLibrariesPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gridBagLayout);

		JLabel lblContentType = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesPanel.lblType.text"));
		GridBagConstraints gbc_lblContentType = new GridBagConstraints();
		gbc_lblContentType.anchor = GridBagConstraints.EAST;
		gbc_lblContentType.insets = new Insets(0, 0, 5, 5);
		gbc_lblContentType.gridx = 0;
		gbc_lblContentType.gridy = 0;
		this.add(lblContentType, gbc_lblContentType);

		final JRadioButton rdbtnNoPom = new JRadioButton(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesPanel.rdbtnNewRadioButton.text"));
		rdbtnNoPom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MavenInstallLibrariesPanel.this.getLblGroupid().setVisible(true);
				MavenInstallLibrariesPanel.this.getTextFieldGroupId().setVisible(true);
				MavenInstallLibrariesPanel.this.getLblArtifactid().setVisible(true);
				MavenInstallLibrariesPanel.this.getTextFieldArtifactId().setVisible(true);
				MavenInstallLibrariesPanel.this.getLblVersion().setVisible(true);
				MavenInstallLibrariesPanel.this.getTextFieldVersion().setVisible(true);
				MavenInstallLibrariesPanel.this.getLblPackaging().setVisible(true);
				MavenInstallLibrariesPanel.this.getComboBoxPackaging().setVisible(true);
				MavenInstallLibrariesPanel.this.getLblPomFile().setVisible(false);
				MavenInstallLibrariesPanel.this.getLblSelectedPomFilePath().setVisible(false);
				MavenInstallLibrariesPanel.this.getBtnSelectedPomFilePath().setVisible(false);
			}
		});
		GridBagConstraints gbc_rdbtnNoPom = new GridBagConstraints();
		gbc_rdbtnNoPom.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNoPom.gridx = 1;
		gbc_rdbtnNoPom.gridy = 0;
		this.add(rdbtnNoPom, gbc_rdbtnNoPom);

		final JRadioButton rdbtnPomFile = new JRadioButton(
				ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesPanel.rdbtnNewRadioButton_1.text"));
		rdbtnPomFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MavenInstallLibrariesPanel.this.getLblGroupid().setVisible(false);
				MavenInstallLibrariesPanel.this.getTextFieldGroupId().setVisible(false);
				MavenInstallLibrariesPanel.this.getLblArtifactid().setVisible(false);
				MavenInstallLibrariesPanel.this.getTextFieldArtifactId().setVisible(false);
				MavenInstallLibrariesPanel.this.getLblVersion().setVisible(false);
				MavenInstallLibrariesPanel.this.getTextFieldVersion().setVisible(false);
				MavenInstallLibrariesPanel.this.getLblPackaging().setVisible(false);
				MavenInstallLibrariesPanel.this.getComboBoxPackaging().setVisible(false);
				MavenInstallLibrariesPanel.this.getLblPomFile().setVisible(true);
				MavenInstallLibrariesPanel.this.getLblSelectedPomFilePath().setVisible(true);
				MavenInstallLibrariesPanel.this.getBtnSelectedPomFilePath().setVisible(true);
			}
		});
		GridBagConstraints gbc_rdbtnPomFile = new GridBagConstraints();
		gbc_rdbtnPomFile.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPomFile.gridx = 2;
		gbc_rdbtnPomFile.gridy = 0;
		this.add(rdbtnPomFile, gbc_rdbtnPomFile);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNoPom);
		group.add(rdbtnPomFile);

		JLabel lblJarFilePath = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblJarFilePath.text"));
		GridBagConstraints gbc_lblJarFilePath = new GridBagConstraints();
		gbc_lblJarFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_lblJarFilePath.anchor = GridBagConstraints.EAST;
		gbc_lblJarFilePath.gridx = 0;
		gbc_lblJarFilePath.gridy = 1;
		this.add(lblJarFilePath, gbc_lblJarFilePath);

		this.lblJarSelectedFileToInstall = new JLabel(
				ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblJarSelectedFileToInstall.text"));
		GridBagConstraints gbc_lblJarSelectedFileToInstall = new GridBagConstraints();
		gbc_lblJarSelectedFileToInstall.gridwidth = 2;
		gbc_lblJarSelectedFileToInstall.insets = new Insets(0, 0, 5, 5);
		gbc_lblJarSelectedFileToInstall.gridx = 1;
		gbc_lblJarSelectedFileToInstall.gridy = 1;
		this.add(this.lblJarSelectedFileToInstall, gbc_lblJarSelectedFileToInstall);

		JButton btnJarSelectedFilePath = new JButton(
				ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.btnJarSelectedFilePath.text"));
		btnJarSelectedFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MavenInstallLibrariesPanel.this.fileLibrarieChooser.showOpenDialog(MavenInstallLibrariesPanel.this);
				if (MavenInstallLibrariesPanel.this.fileLibrarieChooser.getSelectedFile() != null) {
					MavenInstallLibrariesPanel.this.getLblJarSelectedFileToInstall().setText(MavenInstallLibrariesPanel.this.fileLibrarieChooser.getSelectedFile().getName());
				}
			}
		});
		GridBagConstraints gbc_btnJarSelectedFilePath = new GridBagConstraints();
		gbc_btnJarSelectedFilePath.insets = new Insets(0, 0, 5, 0);
		gbc_btnJarSelectedFilePath.gridx = 3;
		gbc_btnJarSelectedFilePath.gridy = 1;
		this.add(btnJarSelectedFilePath, gbc_btnJarSelectedFilePath);

		this.lblGroupid = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblGroupid.text"));
		GridBagConstraints gbc_lblGroupid = new GridBagConstraints();
		gbc_lblGroupid.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroupid.anchor = GridBagConstraints.EAST;
		gbc_lblGroupid.gridx = 0;
		gbc_lblGroupid.gridy = 2;
		this.add(this.lblGroupid, gbc_lblGroupid);

		this.textFieldGroupId = new JTextField();
		GridBagConstraints gbc_textFieldGroupId = new GridBagConstraints();
		gbc_textFieldGroupId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldGroupId.gridwidth = 3;
		gbc_textFieldGroupId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGroupId.gridx = 1;
		gbc_textFieldGroupId.gridy = 2;
		this.add(this.textFieldGroupId, gbc_textFieldGroupId);
		this.textFieldGroupId.setColumns(10);

		this.lblArtifactid = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblArtifactid.text"));
		GridBagConstraints gbc_lblArtifactid = new GridBagConstraints();
		gbc_lblArtifactid.anchor = GridBagConstraints.EAST;
		gbc_lblArtifactid.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtifactid.gridx = 0;
		gbc_lblArtifactid.gridy = 3;
		this.add(this.lblArtifactid, gbc_lblArtifactid);

		this.textFieldArtifactId = new JTextField();
		GridBagConstraints gbc_textFieldArtifactId = new GridBagConstraints();
		gbc_textFieldArtifactId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldArtifactId.gridwidth = 3;
		gbc_textFieldArtifactId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldArtifactId.anchor = GridBagConstraints.NORTH;
		gbc_textFieldArtifactId.gridx = 1;
		gbc_textFieldArtifactId.gridy = 3;
		this.add(this.textFieldArtifactId, gbc_textFieldArtifactId);
		this.textFieldArtifactId.setColumns(10);

		this.lblVersion = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblVersion.text"));
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.anchor = GridBagConstraints.EAST;
		gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
		gbc_lblVersion.gridx = 0;
		gbc_lblVersion.gridy = 4;
		this.add(this.lblVersion, gbc_lblVersion);

		this.textFieldVersion = new JTextField();
		GridBagConstraints gbc_textFieldVersion = new GridBagConstraints();
		gbc_textFieldVersion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVersion.gridwidth = 3;
		gbc_textFieldVersion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVersion.gridx = 1;
		gbc_textFieldVersion.gridy = 4;
		this.add(this.textFieldVersion, gbc_textFieldVersion);
		this.textFieldVersion.setColumns(10);

		this.lblPackaging = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblPackaging.text"));
		GridBagConstraints gbc_lblPackaging = new GridBagConstraints();
		gbc_lblPackaging.anchor = GridBagConstraints.EAST;
		gbc_lblPackaging.insets = new Insets(0, 0, 5, 5);
		gbc_lblPackaging.gridx = 0;
		gbc_lblPackaging.gridy = 5;
		this.add(this.lblPackaging, gbc_lblPackaging);

		this.comboBoxPackaging = new JComboBox();
		this.comboBoxPackaging.setEnabled(false);
		this.comboBoxPackaging.setModel(new DefaultComboBoxModel(new String[] { "jar", "pom" }));
		GridBagConstraints gbc_comboBoxPackaging = new GridBagConstraints();
		gbc_comboBoxPackaging.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPackaging.gridwidth = 3;
		gbc_comboBoxPackaging.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPackaging.gridx = 1;
		gbc_comboBoxPackaging.gridy = 5;
		this.add(this.comboBoxPackaging, gbc_comboBoxPackaging);

		this.lblPomFile = new JLabel(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblPomFile.text"));
		GridBagConstraints gbc_lblPomFile = new GridBagConstraints();
		gbc_lblPomFile.anchor = GridBagConstraints.EAST;
		gbc_lblPomFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblPomFile.gridx = 0;
		gbc_lblPomFile.gridy = 6;
		this.add(this.lblPomFile, gbc_lblPomFile);

		this.btnSelectedPomFilePath = new JButton(
				ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.btnSelectedPomFilePath.text"));
		this.btnSelectedPomFilePath.setEnabled(true);
		this.btnSelectedPomFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MavenInstallLibrariesPanel.this.filePomChooser.showOpenDialog(MavenInstallLibrariesPanel.this);
				if (MavenInstallLibrariesPanel.this.filePomChooser.getSelectedFile() != null) {
					MavenInstallLibrariesPanel.this.getLblSelectedPomFilePath().setText(MavenInstallLibrariesPanel.this.filePomChooser.getSelectedFile().getName());
				}
			}
		});

		this.lblSelectedPomFilePath = new JLabel(
				ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.lblSelectedPomFilePath.text"));
		GridBagConstraints gbc_lblSelectedPomFilePath = new GridBagConstraints();
		gbc_lblSelectedPomFilePath.gridwidth = 2;
		gbc_lblSelectedPomFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectedPomFilePath.gridx = 1;
		gbc_lblSelectedPomFilePath.gridy = 6;
		this.add(this.lblSelectedPomFilePath, gbc_lblSelectedPomFilePath);
		GridBagConstraints gbc_btnSelectedPomFilePath = new GridBagConstraints();
		gbc_btnSelectedPomFilePath.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelectedPomFilePath.gridx = 3;
		gbc_btnSelectedPomFilePath.gridy = 6;
		this.add(this.btnSelectedPomFilePath, gbc_btnSelectedPomFilePath);

		JScrollPane scrollPaneResult = new JScrollPane();
		GridBagConstraints gbc_scrollPaneResult = new GridBagConstraints();
		gbc_scrollPaneResult.gridwidth = 4;
		gbc_scrollPaneResult.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneResult.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneResult.gridx = 0;
		gbc_scrollPaneResult.gridy = 7;
		this.add(scrollPaneResult, gbc_scrollPaneResult);

		this.txtAreaResult = new JTextArea();
		this.txtAreaResult.setRows(8);
		this.txtAreaResult.setText(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.txtAreaResult.text"));
		scrollPaneResult.setViewportView(this.txtAreaResult);

		JPanel btnPane = new JPanel();
		GridBagConstraints gbc_btnPane = new GridBagConstraints();
		gbc_btnPane.gridwidth = 4;
		gbc_btnPane.fill = GridBagConstraints.BOTH;
		gbc_btnPane.gridx = 0;
		gbc_btnPane.gridy = 8;
		this.add(btnPane, gbc_btnPane);
		btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnOk = new JButton(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibrariesDialog.btnOk.text"));
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StringBuilder builder = new StringBuilder();

				if (rdbtnNoPom.isSelected() && (MavenInstallLibrariesPanel.this.getFileLibrarieChooserValue() != null) && !MavenInstallLibrariesPanel.this.getTextFieldGroupIdValue().isEmpty()
						&& !MavenInstallLibrariesPanel.this.getTextFieldArtifactIdValue().isEmpty() && !MavenInstallLibrariesPanel.this.getTextFieldVersionValue().isEmpty()
						&& !MavenInstallLibrariesPanel.this.getComboBoxPackagingValue().isEmpty()) {
					builder.append("cmd /C mvn install:install-file -Dfile=");
					builder.append(MavenInstallLibrariesPanel.this.getFileLibrarieChooserValue().getAbsolutePath());
					builder.append(" -DgroupId=");
					builder.append(MavenInstallLibrariesPanel.this.getTextFieldGroupIdValue());
					builder.append(" -DartifactId=");
					builder.append(MavenInstallLibrariesPanel.this.getTextFieldArtifactIdValue());
					builder.append(" -Dversion=");
					builder.append(MavenInstallLibrariesPanel.this.getTextFieldVersionValue());
					builder.append(" -Dpackaging=");
					builder.append(MavenInstallLibrariesPanel.this.getComboBoxPackagingValue());
					installFile(builder);
				}else if (rdbtnPomFile.isSelected() && (MavenInstallLibrariesPanel.this.getFileLibrarieChooserValue() != null) && MavenInstallLibrariesPanel.this.getFilePomChooserValue() != null){
					builder.append("cmd /C mvn install:install-file -Dfile=");
					builder.append(MavenInstallLibrariesPanel.this.getFileLibrarieChooserValue().getAbsolutePath());
					builder.append(" -DpomFile=");
					builder.append(MavenInstallLibrariesPanel.this.getFilePomChooserValue().getAbsolutePath());
					installFile(builder);
				} else {
					MavenInstallLibrariesPanel.this.getTxtAreaResult()
					.setText(ResourceBundle.getBundle("es.pmkirsten.utilities.i18n.bundle").getString("MavenInstallLibraries.errorResult.text"));
				}
			}
		});
		btnPane.add(btnOk);
		rdbtnNoPom.setSelected(true);
		this.getLblPomFile().setVisible(false);
		this.getLblSelectedPomFilePath().setVisible(false);
		this.getBtnSelectedPomFilePath().setVisible(false);
	}

	public void installFile(StringBuilder builder) {
		try {
			Process p = Runtime.getRuntime().exec(builder.toString());
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			MavenInstallLibrariesPanel.this.getTxtAreaResult().setText("");
			while ((line = in.readLine()) != null) {
				MavenInstallLibrariesPanel.this.getTxtAreaResult().setText(MavenInstallLibrariesPanel.this.getTxtAreaResult().getText().concat("\n" + line));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public JFileChooser getFileLibrarieChooser() {
		return this.fileLibrarieChooser;
	}

	public File getFileLibrarieChooserValue() {
		return this.fileLibrarieChooser.getSelectedFile();
	}

	public JLabel getLblJarSelectedFileToInstall() {
		return this.lblJarSelectedFileToInstall;
	}

	public String getTextFieldGroupIdValue() {
		return this.textFieldGroupId.getText();
	}

	public String getTextFieldArtifactIdValue() {
		return this.textFieldArtifactId.getText();
	}

	public String getTextFieldVersionValue() {
		return this.textFieldVersion.getText();
	}

	public String getComboBoxPackagingValue() {
		return (String) this.comboBoxPackaging.getSelectedItem();
	}

	public JTextArea getTxtAreaResult() {
		return this.txtAreaResult;
	}

	public JLabel getLblGroupid() {
		return this.lblGroupid;
	}

	public JLabel getLblArtifactid() {
		return this.lblArtifactid;
	}

	public JComboBox getComboBoxPackaging() {
		return this.comboBoxPackaging;
	}

	public JTextField getTextFieldArtifactId() {
		return this.textFieldArtifactId;
	}

	public JLabel getLblPackaging() {
		return this.lblPackaging;
	}

	public JTextField getTextFieldVersion() {
		return this.textFieldVersion;
	}

	public JTextField getTextFieldGroupId() {
		return this.textFieldGroupId;
	}

	public JLabel getLblVersion() {
		return this.lblVersion;
	}

	public JButton getBtnSelectedPomFilePath() {
		return this.btnSelectedPomFilePath;
	}

	public JLabel getLblSelectedPomFilePath() {
		return this.lblSelectedPomFilePath;
	}

	public JLabel getLblPomFile() {
		return this.lblPomFile;
	}

	public JFileChooser getFilePomChooser() {
		return filePomChooser;
	}

	public File getFilePomChooserValue() {
		return this.filePomChooser.getSelectedFile();
	}
}
