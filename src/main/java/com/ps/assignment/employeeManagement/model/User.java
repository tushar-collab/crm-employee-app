package com.ps.assignment.employeeManagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_details")
public class User {

    // private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "maiden_name")
    private String maidenName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "image")
    private String image;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "eye_color")
    private String eyeColor;

    @ManyToOne
    @JoinColumn(name = "hair_id")
    private Hair hair;

    @Column(name = "ip")
    private String ip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "university")
    private String university;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "ein")
    private String ein;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "user_agent")
    private String userAgent;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column(name = "role")
    private String role;

	public User(Long id, String firstName, String lastName, String maidenName, Integer age, String email, String phone,
			String username, String password, String birthDate, String image, String bloodGroup, Double height,
			Double weight, String eyeColor, Hair hair, String ip, Address address, String macAddress, String university,
			Bank bank, Company company, String ein, String ssn, String userAgent, Crypto crypto, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.maidenName = maidenName;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.image = image;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.eyeColor = eyeColor;
		this.hair = hair;
		this.ip = ip;
		this.address = address;
		this.macAddress = macAddress;
		this.university = university;
		this.bank = bank;
		this.company = company;
		this.ein = ein;
		this.ssn = ssn;
		this.userAgent = userAgent;
		this.crypto = crypto;
		this.role = role;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public Hair getHair() {
		return hair;
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEin() {
		return ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Crypto getCrypto() {
		return crypto;
	}

	public void setCrypto(Crypto crypto) {
		this.crypto = crypto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", maidenName=" + maidenName
				+ ", age=" + age + ", email=" + email + ", phone=" + phone + ", username=" + username + ", password="
				+ password + ", birthDate=" + birthDate + ", image=" + image + ", bloodGroup=" + bloodGroup
				+ ", height=" + height + ", weight=" + weight + ", eyeColor=" + eyeColor + ", hair=" + hair + ", ip="
				+ ip + ", address=" + address + ", macAddress=" + macAddress + ", university=" + university + ", bank="
				+ bank + ", company=" + company + ", ein=" + ein + ", ssn=" + ssn + ", userAgent=" + userAgent
				+ ", crypto=" + crypto + ", role=" + role + "]";
	}
    
    
}
