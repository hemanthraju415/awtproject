package table_info;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCUTILITIES.JdbcUtil;

/**
 * 
 * @author hemanthraju.v
 * @version 1.0.0
 * @company PENNANT TECHNOLOGIES PVT LTD
 */

public class Jdbc_Table {
	public static final ArrayList<String> TABLENAME = new ArrayList<>();
	public static final ArrayList<String> COLUMNNAME = new ArrayList<>();
	private static DatabaseMetaData tnames = null;
	static {
		try {
			tnames = JdbcUtil.getConnection().getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getTableNames() {
		try (ResultSet rs = tnames.getTables(null, null, "%", new String[] { "TABLE" })) {
			while (rs.next()) {
				TABLENAME.add(rs.getString("TABLE_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TABLENAME;
	}

	public static ArrayList<String> getColumnNames() {
		COLUMNNAME.clear();
		try (ResultSet rs = tnames.getColumns(null, null, Data_Dictonary.SELECTED, null)) {
			boolean is_columns_exists = false;
			while (rs.next()) {
				COLUMNNAME.add(rs.getString("COLUMN_NAME"));
				is_columns_exists = true;
			}
			if (!is_columns_exists) {
				COLUMNNAME.add("NO COLUMNS AVAILABLE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return COLUMNNAME;
	}

	public static ArrayList<String> getColumnDetails() {
		return null;
	}

	public static void main(String[] args) {
	}
}
