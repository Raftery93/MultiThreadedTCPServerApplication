
public class User {

	private String name;
	private String address;
	private String PPSNumber;
	private String age;
	private String weight;
	private String height;

	public User(String name, String address, String PPSNumber, String age, String weight, String height) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.address = address;
		this.PPSNumber = PPSNumber;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPPSNumber() {
		return PPSNumber;
	}

	public void setPPSNumber(String pPSNumber) {
		PPSNumber = pPSNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return name + "\n" + address + "\n" + PPSNumber + "\n" + age + "\n" + weight + "\n" + height;
	}

}
