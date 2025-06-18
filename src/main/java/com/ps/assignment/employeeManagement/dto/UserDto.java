package com.ps.assignment.employeeManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User details", name = "Users")
public class UserDto {

    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User first name", example = "John")
    private String firstName;

    @Schema(description = "User last name", example = "Doe")
    private String lastName;

    @Schema(description = "User maiden name", example = "Smith")
    private String maidenName;

    @Schema(description = "User email", example = "abc@gmail.com")
    private String email;

    @Schema(description = "User phone", example = "1234567890")
    private String phone;

    @Schema(description = "User age", example = "25")
    private Integer age;

    @Schema(description = "User university", example = "XYZ University")
    private String university;

    @Schema(description = "User name", example = "john.doe")
    private String userName;

    @Schema(description = "User password", example = "password")
    private String password;

    @Schema(description = "User birth date", example = "01-01-1990")
    private String birthDate;

    @Schema(description = "User image", example = "image.jpg")
    private String image;

    @Schema(description = "User blood group", example = "A+")
    private String bloodGroup;

    @Schema(description = "User height", example = "5.8")
    private Double height;

    @Schema(description = "User weight", example = "70")
    private Double weight;

    @Schema(description = "User eye color", example = "Brown")
    private String eyeColor;

    @Schema(description = "User hair color", example = "Black")
    private String hairColor;

    @Schema(description = "User hair type", example = "Curly")
    private String hairType;

    @Schema(description = "User ip", example = "10.50.556.04")
    private String ip;

    @Schema(description = "User mac address", example = "00:0a:95:9d:68:16")
    private String macAddress;

    @Schema(description = "User address", example = "123, Main Street")
    private String address;

    @Schema(description = "User city", example = "New York")
    private String city;

    @Schema(description = "User state", example = "New York")
    private String state;

    @Schema(description = "User state code", example = "NY")
    private String stateCode;

    @Schema(description = "User country", example = "USA")
    private String country;

    @Schema(description = "User postal code", example = "10001")
    private String postalCode;

    @Schema(description = "User address latitude", example = "40.7128")
    private String addressLat;

    @Schema(description = "User address longitude", example = "74.0060")
    private String addressLong;

    @Schema(description = "User card expire", example = "01-01-2025")
    private String cardExpire;

    @Schema(description = "User card number", example = "1234 5678 9012 3456")
    private String cardNumber;

    @Schema(description = "User card type", example = "Visa")
    private String cardType;

    @Schema(description = "User currency", example = "USD")
    private String currency;

    @Schema(description = "User iban", example = "US123456789")
    private String iban;

    @Schema(description = "User company name", example = "ABC Corp")
    private String companyName;

    @Schema(description = "User department", example = "IT")
    private String department;

    @Schema(description = "User title", example = "Software Engineer")
    private String title;

    @Schema(description = "User company address", example = "123, Main Street")
    private String companyAddress;

    @Schema(description = "User company city", example = "New York")
    private String companyCity;

    @Schema(description = "User company state", example = "New York")
    private String companyState;

    @Schema(description = "User company state code", example = "NY")
    private String companyStateCode;

    @Schema(description = "User company country", example = "USA")
    private String companyCountry;

    @Schema(description = "User company postal code", example = "10001")
    private String companyPostalCode;

    @Schema(description = "User company address latitude", example = "40.7128")
    private String companyAddressLat;

    @Schema(description = "User company address longitude", example = "74.0060")
    private String companyAddressLong;

    @Schema(description = "User EIN", example = "123456789")
    private String ein;

    @Schema(description = "User SSN", example = "123-45-6789")
    private String ssn;

    @Schema(description = "User agent", example = "Mozilla/5.0")
    private String userAgent;

    @Schema(description = "User coin", example = "Bitcoin")
    private String coin;

    @Schema(description = "User wallet", example = "123456789")
    private String wallet;

    @Schema(description = "User network", example = "Facebook")
    private String network;

    @Schema(description = "User role", example = "Admin")
    private String role;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String maidenName, String email, String phone,
            String companyName, Integer age, String university) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.age = age;
        this.university = university;
    }

    public UserDto(String firstName, String lastName, String maidenName, String email, String phone, String companyName,
            Integer age, String university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.age = age;
        this.university = university;
    }

    public UserDto(Long id, String firstName, String lastName, String maidenName, String email, String phone,
            Integer age, String university, String userName, String password, String birthDate, String image,
            String bloodGroup, Double height, Double weight, String eyeColor, String hairColor, String hairType,
            String ip, String macAddress, String address, String city, String state, String stateCode, String country,
            String postalCode, String addressLat, String addressLong, String cardExpire, String cardNumber,
            String cardType, String currency, String iban, String companyName, String department, String title,
            String companyAddress, String companyCity, String companyState, String companyStateCode,
            String companyCountry, String companyPostalCode, String companyAddressLat, String companyAddressLong,
            String ein, String ssn, String userAgent, String coin, String wallet, String network, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.university = university;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.hairType = hairType;
        this.ip = ip;
        this.macAddress = macAddress;
        this.address = address;
        this.city = city;
        this.state = state;
        this.stateCode = stateCode;
        this.country = country;
        this.postalCode = postalCode;
        this.addressLat = addressLat;
        this.addressLong = addressLong;
        this.cardExpire = cardExpire;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.currency = currency;
        this.iban = iban;
        this.companyName = companyName;
        this.department = department;
        this.title = title;
        this.companyAddress = companyAddress;
        this.companyCity = companyCity;
        this.companyState = companyState;
        this.companyStateCode = companyStateCode;
        this.companyCountry = companyCountry;
        this.companyPostalCode = companyPostalCode;
        this.companyAddressLat = companyAddressLat;
        this.companyAddressLong = companyAddressLong;
        this.ein = ein;
        this.ssn = ssn;
        this.userAgent = userAgent;
        this.coin = coin;
        this.wallet = wallet;
        this.network = network;
        this.role = role;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(String addressLat) {
        this.addressLat = addressLat;
    }

    public String getAddressLong() {
        return addressLong;
    }

    public void setAddressLong(String addressLong) {
        this.addressLong = addressLong;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyStateCode() {
        return companyStateCode;
    }

    public void setCompanyStateCode(String companyStateCode) {
        this.companyStateCode = companyStateCode;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyAddressLat() {
        return companyAddressLat;
    }

    public void setCompanyAddressLat(String companyAddressLat) {
        this.companyAddressLat = companyAddressLat;
    }

    public String getCompanyAddressLong() {
        return companyAddressLong;
    }

    public void setCompanyAddressLong(String companyAddressLong) {
        this.companyAddressLong = companyAddressLong;
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

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", maidenName="
                + maidenName + ", email=" + email + ", phone=" + phone + ", age=" + age + ", university=" + university
                + ", userName=" + userName + ", password=" + password + ", birthDate=" + birthDate + ", image=" + image
                + ", bloodGroup=" + bloodGroup + ", height=" + height + ", weight=" + weight + ", eyeColor=" + eyeColor
                + ", hairColor=" + hairColor + ", hairType=" + hairType + ", ip=" + ip + ", macAddress=" + macAddress
                + ", address=" + address + ", city=" + city + ", state=" + state + ", stateCode=" + stateCode
                + ", country=" + country + ", postalCode=" + postalCode + ", addressLat=" + addressLat
                + ", addressLong=" + addressLong + ", cardExpire=" + cardExpire + ", cardNumber=" + cardNumber
                + ", cardType=" + cardType + ", currency=" + currency + ", iban=" + iban + ", companyName="
                + companyName + ", department=" + department + ", title=" + title + ", companyAddress=" + companyAddress
                + ", companyCity=" + companyCity + ", companyState=" + companyState + ", companyStateCode="
                + companyStateCode + ", companyCountry=" + companyCountry + ", companyPostalCode=" + companyPostalCode
                + ", companyAddressLat=" + companyAddressLat + ", companyAddressLong=" + companyAddressLong + ", ein="
                + ein + ", ssn=" + ssn + ", userAgent=" + userAgent + ", coin=" + coin + ", wallet=" + wallet
                + ", network=" + network + ", role=" + role + "]";
    }

    

}
