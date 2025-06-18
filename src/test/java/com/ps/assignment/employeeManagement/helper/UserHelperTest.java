package com.ps.assignment.employeeManagement.helper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.assignment.employeeManagement.model.*;
import com.ps.assignment.employeeManagement.repository.*;

@ExtendWith(MockitoExtension.class)
public class UserHelperTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private CoordinatesRepository coordinatesRepository;

	@Mock
	private AddressRepository addressRepository;

	@Mock
	private CryptoRepository cryptoRepository;

	@Mock
	private HairRepository hairRepository;

	@Mock
	private BankRepository bankRepository;

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private UserHelper userHelper;

	private JSONObject coordinatesJson;
	private JSONObject addressJson;
	private JSONObject cryptoJson;
	private JSONObject hairJson;
	private JSONObject bankJson;
	private JSONObject companyJson;
	private JSONObject userJson;

	@BeforeEach
	public void setUp() throws JSONException {
		coordinatesJson = new JSONObject();
		coordinatesJson.put("lat", 40.7128);
		coordinatesJson.put("lng", -74.0060);

		addressJson = new JSONObject();
		addressJson.put("address", "123 Main St");
		addressJson.put("city", "New York");
		addressJson.put("state", "NY");
		addressJson.put("stateCode", "NY");
		addressJson.put("postalCode", "10001");
		addressJson.put("country", "USA");
		addressJson.put("coordinates", coordinatesJson);

		cryptoJson = new JSONObject();
		cryptoJson.put("coin", "Bitcoin");
		cryptoJson.put("wallet", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
		cryptoJson.put("network", "BTC");

		hairJson = new JSONObject();
		hairJson.put("color", "Brown");
		hairJson.put("type", "Curly");

		bankJson = new JSONObject();
		bankJson.put("cardExpire", "12/25");
		bankJson.put("cardNumber", "1234567890123456");
		bankJson.put("cardType", "Visa");
		bankJson.put("currency", "USD");
		bankJson.put("iban", "US1234567890123456");

		companyJson = new JSONObject();
		companyJson.put("name", "Engineering");
		companyJson.put("department", "Engineering");
		companyJson.put("title", "Software Engineer");
		companyJson.put("address", addressJson);

		userJson = new JSONObject();
		userJson.put("firstName", "John");
		userJson.put("lastName", "Doe");
		userJson.put("maidenName", "Smith");
		userJson.put("age", 30);
		userJson.put("email", "john.doe@example.com");
		userJson.put("phone", "123-456-7890");
		userJson.put("username", "johndoe");
		userJson.put("password", "password");
		userJson.put("birthDate", "1990-01-01");
		userJson.put("image", "image.jpg");
		userJson.put("bloodGroup", "O+");
		userJson.put("height", 180.0);
		userJson.put("weight", 75.0);
		userJson.put("eyeColor", "Blue");
		userJson.put("ip", "192.168.1.1");
		userJson.put("macAddress", "00:1A:2B:3C:4D:5E");
		userJson.put("university", "MIT");
		userJson.put("ein", "123456789");
		userJson.put("ssn", "123-45-6789");
		userJson.put("userAgent", "Mozilla/5.0");
		userJson.put("role", "Admin");
	}

	@Test
	public void testFindAndSaveCoordinates() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Mockito.lenient().when(coordinatesRepository.findByLatAndLng("40.7128", "-74.0060")).thenReturn(Optional.of(coordinates));

		Coordinates result = userHelper.findAndSaveCoordinates(coordinatesJson);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveAddress() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Address address = new Address("123 Main St", "New York", "NY", "NY", "10001", "USA");
		address.setCoordinates(coordinates);
		Mockito.lenient().when(addressRepository.findByCoordinates(coordinates)).thenReturn(Optional.of(address));

		Address result = userHelper.findAndSaveAddress(addressJson, coordinates);
		assertNotNull(result);
		assertEquals("123 Main St", result.getAddress());
		assertEquals("New York", result.getCity());
	}

	@Test
	public void testFindAndSaveCrypto() {
		Crypto crypto = new Crypto("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC");
		Mockito.lenient().when(cryptoRepository.findByCoinWalletNetwork("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC"))
				.thenReturn(Optional.of(crypto));

		Crypto result = userHelper.findAndSaveCrypto(cryptoJson);
		assertNotNull(result);
		assertEquals("Bitcoin", result.getCoin());
		assertEquals("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", result.getWallet());
	}

	@Test
	public void testFindAndSaveHair() {
		Hair hair = new Hair("Brown", "Curly");
		Mockito.lenient().when(hairRepository.findByColorAndType("Brown", "Curly")).thenReturn(Optional.of(hair));

		Hair result = userHelper.findAndSaveHair(hairJson);
		assertNotNull(result);
		assertEquals("Brown", result.getColor());
		assertEquals("Curly", result.getType());
	}

	@Test
	public void testFindAndSaveBank() {
		Bank bank = new Bank("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456");
		Mockito.lenient().when(bankRepository.findByObjectParams("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456"))
				.thenReturn(Optional.of(bank));

		Bank result = userHelper.findAndSaveBank(bankJson);
		assertNotNull(result);
		assertEquals("12/25", result.getCardExpire());
		assertEquals("1234567890123456", result.getCardNumber());
	}

	@Test
	public void testFindAndSaveCompany() {
		Company company = new Company("Engineering", "Engineering", "Software Engineer");
		Mockito.lenient().when(companyRepository.findByName("Engineering")).thenReturn(Optional.of(company));

		Company result = userHelper.findAndSaveCompany(companyJson);
		assertNotNull(result);
		assertEquals("Engineering", result.getName());
		assertEquals("Engineering", result.getDepartment());
	}

	@Test
	public void testFindAndSaveCompany_ExistingCompany() {
		Company company = new Company("Engineering", "Engineering", "Software Engineer");
		Mockito.lenient().when(companyRepository.findByName("Engineering")).thenReturn(Optional.of(company));

		Company result = userHelper.findAndSaveCompany(companyJson);
		assertNotNull(result);
		assertEquals("Engineering", result.getName());
		assertEquals("Engineering", result.getDepartment());
	}

	@Test
	public void testFindAndSaveCompany_NewCompany() {
		Mockito.lenient().when(companyRepository.findByName("Engineering")).thenReturn(Optional.empty());

		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Mockito.lenient().when(coordinatesRepository.findByLatAndLng("40.7128", "-74.0060")).thenReturn(Optional.of(coordinates));

		Address address = new Address("123 Main St", "New York", "NY", "NY", "10001", "USA");
		address.setCoordinates(coordinates);
		Mockito.lenient().when(addressRepository.findByCoordinates(coordinates)).thenReturn(Optional.of(address));

		Company company = new Company("Engineering", "Engineering", "Software Engineer");
		company.setAddress(address);
		Mockito.lenient().when(companyRepository.save(any(Company.class))).thenReturn(company);
		Mockito.lenient().when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

		Company result = userHelper.findAndSaveCompany(companyJson);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveBank_ExistingBank() {
		Bank bank = new Bank("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456");
		Mockito.lenient().when(bankRepository.findByObjectParams("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456"))
				.thenReturn(Optional.of(bank));

		Bank result = userHelper.findAndSaveBank(bankJson);
		assertNotNull(result);
		assertEquals("12/25", result.getCardExpire());
		assertEquals("1234567890123456", result.getCardNumber());
	}

	@Test
	public void testFindAndSaveBank_NewBank() {
		Mockito.lenient().when(bankRepository.findByObjectParams("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456"))
				.thenReturn(Optional.empty());

		Bank bank = new Bank("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456");
		Mockito.lenient().when(bankRepository.save(any(Bank.class))).thenReturn(bank);
		Mockito.lenient().when(bankRepository.findById(any(Integer.class))).thenReturn(Optional.of(bank));

		Bank result = userHelper.findAndSaveBank(bankJson);
		assertNull(result);
	}

	@Test
	public void testCreateAndSaveUser() {
		Hair hair = new Hair("Brown", "Curly");
		Address address = new Address("123 Main St", "New York", "NY", "NY", "10001", "USA");
		Bank bank = new Bank("12/25", "1234567890123456", "Visa", "USD", "US1234567890123456");
		Company company = new Company("Engineering", "Engineering", "Software Engineer");
		Crypto crypto = new Crypto("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC");

		userHelper.createAndSaveUser(userJson, hair, address, bank, company, crypto);

		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void testFindAndSaveCoordinates_ExistingCoordinates() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Mockito.lenient().when(coordinatesRepository.findByLatAndLng("40.7128", "-74.0060")).thenReturn(Optional.of(coordinates));

		Coordinates result = userHelper.findAndSaveCoordinates(coordinatesJson);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveCoordinates_NewCoordinates() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Mockito.lenient().when(coordinatesRepository.findByLatAndLng("40.7128", "-74.0060")).thenReturn(Optional.empty());
		Mockito.lenient().when(coordinatesRepository.save(any(Coordinates.class))).thenReturn(coordinates);
		Mockito.lenient().when(coordinatesRepository.findById(any(Integer.class))).thenReturn(Optional.of(coordinates));

		Coordinates result = userHelper.findAndSaveCoordinates(coordinatesJson);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveAddress_ExistingAddress() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Address address = new Address("123 Main St", "New York", "NY", "NY", "10001", "USA");
		address.setCoordinates(coordinates);
		Mockito.lenient().when(addressRepository.findByCoordinates(coordinates)).thenReturn(Optional.of(address));

		Address result = userHelper.findAndSaveAddress(addressJson, coordinates);
		assertNotNull(result);
	}

	@Test
	public void testFindAndSaveAddress_NewAddress() {
		Coordinates coordinates = new Coordinates("40.7128", "-74.0060");
		Address address = new Address("123 Main St", "New York", "NY", "NY", "10001", "USA");
		address.setCoordinates(coordinates);
		Mockito.lenient().when(addressRepository.findByCoordinates(coordinates)).thenReturn(Optional.empty());
		Mockito.lenient().when(addressRepository.save(any(Address.class))).thenReturn(address);
		Mockito.lenient().when(addressRepository.findById(any(Integer.class))).thenReturn(Optional.of(address));

		Address result = userHelper.findAndSaveAddress(addressJson, coordinates);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveCrypto_ExistingCrypto() {
		Crypto crypto = new Crypto("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC");
		Mockito.lenient().when(cryptoRepository.findByCoinWalletNetwork("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC"))
				.thenReturn(Optional.of(crypto));

		Crypto result = userHelper.findAndSaveCrypto(cryptoJson);
		assertNotNull(result);
		assertEquals("Bitcoin", result.getCoin());
		assertEquals("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", result.getWallet());
	}

	@Test
	public void testFindAndSaveCrypto_NewCrypto() {
		Crypto crypto = new Crypto("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC");
		Mockito.lenient().when(cryptoRepository.findByCoinWalletNetwork("Bitcoin", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "BTC"))
				.thenReturn(Optional.empty());
		Mockito.lenient().when(cryptoRepository.save(any(Crypto.class))).thenReturn(crypto);
		Mockito.lenient().when(cryptoRepository.findById(anyLong())).thenReturn(Optional.of(crypto));

		Crypto result = userHelper.findAndSaveCrypto(cryptoJson);
		assertNull(result);
	}

	@Test
	public void testFindAndSaveHair_ExistingHair() {
		Hair hair = new Hair("Brown", "Curly");
		Mockito.lenient().when(hairRepository.findByColorAndType("Brown", "Curly")).thenReturn(Optional.of(hair));

		Hair result = userHelper.findAndSaveHair(hairJson);
		assertNotNull(result);
		assertEquals("Brown", result.getColor());
		assertEquals("Curly", result.getType());
	}

	@Test
	public void testFindAndSaveHair_NewHair() {
		Hair hair = new Hair("Brown", "Curly");
		Mockito.lenient().when(hairRepository.findByColorAndType("Brown", "Curly")).thenReturn(Optional.empty());
		Mockito.lenient().when(hairRepository.save(any(Hair.class))).thenReturn(hair);
		Mockito.lenient().when(hairRepository.findById(anyLong())).thenReturn(Optional.of(hair));

		Hair result = userHelper.findAndSaveHair(hairJson);
		assertNull(result);
	}
}