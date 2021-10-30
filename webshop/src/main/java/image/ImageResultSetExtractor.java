package image;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ImageResultSetExtractor implements ResultSetExtractor<byte[]> {

	@Override
	public byte[] extractData(ResultSet rs) throws SQLException, DataAccessException {

		byte[] img = null;
		while(rs.next()) {
			img = rs.getBytes("image");
		}
		
		return img;
	}

}
