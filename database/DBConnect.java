package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class DBConnect {
	public final static ResultSetToBean<GameStatistics> gameStatisticsConverter = new ResultSetToBean<GameStatistics>() {
		public GameStatistics convert(ResultSet rs) throws Exception {
			GameStatistics e = new GameStatistics();
			e.setIdStat(rs.getInt("ID_STAT"));
			e.setDateOfPlaying(rs.getString("DATE_OF_PLAYING"));
			e.setTimeOfPlaying(rs.getFloat("TIME_OF_PLAYING"));
			e.setMoney(rs.getInt("MONEY"));;
			e.setFinalResult(rs.getString("FINAL_RESULT"));
			return e;
		}
	};
	
	public List<GameStatistics> getAllRows() {
		List<GameStatistics> statisticsRows = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
					}
				}, gameStatisticsConverter,
						"select * from gamestatistics");
		return statisticsRows;
	}
	
	public void addNewRow(GameStatistics example) {
		/*boolean result = */DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setString(1, example.getDateOfPlaying());
				ps.setDouble(2, example.getTimeOfPlaying());
				ps.setInt(3, example.getMoney());
				ps.setString(4, example.getFinalResult());
				return ps.executeUpdate() > 0;
			}
		}, "insert into gamestatistics values (game_seq.nextval, ?, ?, ?, ?)");
		
		//System.out.println(result ? "Udalo sie" : "Nie udalo sie");
	}
}


