
public class FitnessRecord {

	private String mode;
	private String duration;

	public FitnessRecord(String mode, String duration) {
		// TODO Auto-generated constructor stub

		this.mode = mode;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return mode + "\n" + duration;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
