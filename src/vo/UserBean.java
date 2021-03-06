package vo;

import java.sql.Date;
import java.sql.Timestamp;

public class UserBean {
	private final String user_id;
	private String passwd;
	private String name;
	private String phone;
	private Date birth;
	private String gender;
	private String postcode;
	private String address;
	private String address_second;
	private String email;
	private String grade;
	private Timestamp edate;
	private String usalt;
	private int point;
	
	public UserBean(String user_id, String passwd, String name, String phone, Date birth, String gender,
			String postcode, String address, String address_second, String email, String grade, Timestamp edate,
			String usalt, int point) {
		super();
		this.user_id = user_id;
		this.passwd = passwd;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.postcode = postcode;
		this.address = address;
		this.address_second = address_second;
		this.email = email;
		this.grade = grade;
		this.edate = edate;
		this.usalt = usalt;
		this.point = point;
	}
	
	public UserBean(String user_id, String name, String phone, Date birth, String gender,
			String postcode, String address, String address_second, String email, String grade, Timestamp edate) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.postcode = postcode;
		this.address = address;
		this.address_second = address_second;
		this.email = email;
		this.grade = grade;
		this.edate = edate;
	}
	
	public UserBean(String user_id, String phone, Date birth, String gender,
			String postcode, String address, String address_second, String email) {
		super();
		this.user_id = user_id;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.postcode = postcode;
		this.address = address;
		this.address_second = address_second;
		this.email = email;
	}
	public String getUser_id() {
		return user_id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress_second() {
		return address_second;
	}

	public void setAddress_second(String address_second) {
		this.address_second = address_second;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUsalt() {
		return usalt;
	}
	
	public Timestamp getEdate() {
		return edate;
	}

	public void setUsalt(String usalt) {
		this.usalt = usalt;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
}
