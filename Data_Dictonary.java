package table_info;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * 
 * @author hemanthraju.v
 * @version 1.0.0
 * @company PENNANT TECHNOLOGIES PVT LTD
 */
public class Data_Dictonary {

	public static final ArrayList<String> TABLE_NAMES = Jdbc_Table.getTableNames();
	public static String SELECTED = null;
	public static ArrayList<String> COLUMN_NAMES = null;

	public static void main(String[] args) {
		Frame f = new Frame("Data Dictonary");
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hemanthraju.v\\Desktop\\icon.png");
		f.setIconImage(icon);
		Font mainfont = new Font("SansSerif", Font.BOLD, 30);
		Label mainlabel = new Label("DATABASE DICTIONARY");
		mainlabel.setAlignment(Label.CENTER);
		mainlabel.setBounds(100, 0, 500, 200);
		mainlabel.setFont(mainfont);

		Font font = new Font("SansSerif", Font.BOLD, 15);
		Label tabellabel = new Label("select a table from the below options");
		tabellabel.setBounds(80, 200, 300, 20);
		tabellabel.setFont(font);

		Label columnlabel = new Label("select a column from the below options");
		columnlabel.setBounds(450, 200, 300, 20);
		columnlabel.setFont(font);

		Label constraintlabel = new Label("The following are the respected constraints and columns in for the table");
		constraintlabel.setBounds(120, 270, 550, 30);
		constraintlabel.setFont(font);

		Choice tables = new Choice();
		tables.setBounds(120, 230, 100, 75);
		Choice columns = new Choice();
		columns.setBounds(520, 235, 150, 75);

		TABLE_NAMES.forEach(tables::add);
		tables.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				SELECTED = tables.getItem(tables.getSelectedIndex());
				COLUMN_NAMES = Jdbc_Table.getColumnNames();
				columns.removeAll();
				COLUMN_NAMES.forEach(columns::add);
			}
		});
		TextArea area = new TextArea("S.no\t Column name \t\t Constraint name");
		area.setBounds(150, 300, 450, 400);

		f.add(mainlabel);
		f.add(tabellabel);
		f.add(columnlabel);
		f.add(tables);
		f.add(columns);
		f.add(constraintlabel);
		f.add(area);
		f.setSize(800, 800);
		f.setLayout(null);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

}