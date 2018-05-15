package database;


public class GameStatistics {
	private int idStat;
	private String dateOfPlaying;
	private double timeOfPlaying;
	private int money;
	private String finalResult;


	public int getIdStat() {
		return idStat;
	}

	public void setIdStat(int idStat) {
		this.idStat = idStat;
	}

	public String getDateOfPlaying() {
		return dateOfPlaying;
	}

	public void setDateOfPlaying(String dateOfPlaying) {
		this.dateOfPlaying = dateOfPlaying;
	}

	public double getTimeOfPlaying() {
		return timeOfPlaying;
	}

	public void setTimeOfPlaying(double timeOfPlaying) {
		this.timeOfPlaying = timeOfPlaying;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	@Override
	public String toString() {
		return "GameStatistics [idStat=" + idStat + ", dateOfPlaying=" + dateOfPlaying + ", timeOfPlaying=" + timeOfPlaying
				+ ", money=" + money + ", finalResult=" + finalResult + "]";
	}

	public GameStatistics(){
		
	}
}

