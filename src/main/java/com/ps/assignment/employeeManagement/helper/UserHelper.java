package com.ps.assignment.employeeManagement.helper;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.assignment.employeeManagement.model.Address;
import com.ps.assignment.employeeManagement.model.Bank;
import com.ps.assignment.employeeManagement.model.Company;
import com.ps.assignment.employeeManagement.model.Coordinates;
import com.ps.assignment.employeeManagement.model.Crypto;
import com.ps.assignment.employeeManagement.model.Hair;
import com.ps.assignment.employeeManagement.model.User;
import com.ps.assignment.employeeManagement.repository.AddressRepository;
import com.ps.assignment.employeeManagement.repository.BankRepository;
import com.ps.assignment.employeeManagement.repository.CompanyRepository;
import com.ps.assignment.employeeManagement.repository.CoordinatesRepository;
import com.ps.assignment.employeeManagement.repository.CryptoRepository;
import com.ps.assignment.employeeManagement.repository.HairRepository;
import com.ps.assignment.employeeManagement.repository.UserRepository;

@Component
public class UserHelper {

    private static final Logger LOG = LogManager.getLogger(UserHelper.class);

    private final UserRepository userRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final AddressRepository addressRepository;
    private final CryptoRepository cryptoRepository;
    private final HairRepository hairRepository;
    private final BankRepository bankRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UserHelper(UserRepository userRepository, CoordinatesRepository coordinatesRepository,
                      AddressRepository addressRepository, CryptoRepository cryptoRepository,
                      HairRepository hairRepository, BankRepository bankRepository,
                      CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.coordinatesRepository = coordinatesRepository;
        this.addressRepository = addressRepository;
        this.cryptoRepository = cryptoRepository;
        this.hairRepository = hairRepository;
        this.bankRepository = bankRepository;
        this.companyRepository = companyRepository;
    }



    public Coordinates findAndSaveCoordinates(JSONObject coordinates) {
        String lat = Double.toString(coordinates.getDouble("lat"));
        String lng = Double.toString(coordinates.getDouble("lng"));
        Optional<Coordinates> coordinate = Optional.empty();
        try {
            coordinate = coordinatesRepository.findByLatAndLng(lat, lng);
            if (!coordinate.isPresent()) {
                Coordinates coordinateObj = new Coordinates(lat, lng);
                coordinatesRepository.save(coordinateObj);
                coordinate = coordinatesRepository.findById(coordinateObj.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinate.isPresent() ? coordinate.get() : null;
    }

    public Address findAndSaveAddress(JSONObject addressJson, Coordinates coordinates) {
        Optional<Address> address = Optional.empty();
        try {
            address = addressRepository.findByCoordinates(coordinates);
            if (!address.isPresent()) {
                Address addressObj = new Address(addressJson.getString("address"),
                        addressJson.getString("city"),
                        addressJson.getString("state"), addressJson.getString("stateCode"),
                        addressJson.getString("postalCode"), addressJson.getString("country"));
                addressObj.setCoordinates(coordinates);
                addressRepository.save(addressObj);
                address = addressRepository.findById(addressObj.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address.isPresent() ? address.get() : null;
    }

    public Crypto findAndSaveCrypto(JSONObject cryptoJson) {
        Optional<Crypto> crypto = Optional.empty();
        try {
            crypto = cryptoRepository.findByCoinWalletNetwork(cryptoJson.getString("coin"),
                    cryptoJson.getString("wallet"), cryptoJson.getString("network"));
            if (!crypto.isPresent()) {
                Crypto cryptoObj = new Crypto(cryptoJson.getString("coin"), cryptoJson.getString("wallet"),
                        cryptoJson.getString("network"));
                cryptoRepository.save(cryptoObj);
                crypto = cryptoRepository.findById(Long.parseLong(cryptoObj.getId().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crypto.isPresent() ? crypto.get() : null;
    }

    public Hair findAndSaveHair(JSONObject hairJson) {
        Optional<Hair> hair = Optional.empty();
        try {
            hair = hairRepository.findByColorAndType(hairJson.getString("color"),
                    hairJson.getString("type"));
            if (!hair.isPresent()) {
                Hair hairObj = new Hair(hairJson.getString("color"), hairJson.getString("type"));
                hairRepository.save(hairObj);
                hair = hairRepository.findById(Long.parseLong(hairObj.getId().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hair.isPresent() ? hair.get() : null;
    }

    public Bank findAndSaveBank(JSONObject bankJson) {
        Optional<Bank> bank = Optional.empty();
        try {
            bank = bankRepository.findByObjectParams(bankJson.getString("cardExpire"),
                    bankJson.getString("cardNumber"), bankJson.getString("cardType"),
                    bankJson.getString("currency"),
                    bankJson.getString("iban"));
            if (!bank.isPresent()) {
                Bank bankObj = new Bank(bankJson.getString("cardExpire"), bankJson.getString("cardNumber"),
                        bankJson.getString("cardType"), bankJson.getString("currency"),
                        bankJson.getString("iban"));
                bankRepository.save(bankObj);
                bank = bankRepository.findById(bankObj.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bank.isPresent() ? bank.get() : null;
    }

    public Company findAndSaveCompany(JSONObject companyJson) {
        Optional<Company> company = Optional.empty();
        try {
            company = companyRepository.findByName(companyJson.getString("name"));
            if (!company.isPresent()) {
                Company companyObj = new Company(companyJson.getString("name"),
                        companyJson.getString("department"), companyJson.getString("title"));
                JSONObject addressJson = (JSONObject) companyJson.get("address");
                JSONObject coordinatesJson = (JSONObject) addressJson.get("coordinates");
                Coordinates coordinates = findAndSaveCoordinates(coordinatesJson);
                Address address = findAndSaveAddress(addressJson, coordinates);
                companyObj.setAddress(address);
                companyRepository.save(companyObj);
                company = companyRepository.findById(Long.valueOf(companyObj.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company.isPresent() ? company.get() : null;
    }

    public void createAndSaveUser(JSONObject jsonUser, Hair hair, Address address, Bank bank, Company company,
            Crypto crypto) {
        User user = new User();
        user.setFirstName(jsonUser.getString("firstName"));
        user.setLastName(jsonUser.getString("lastName"));
        user.setMaidenName(jsonUser.getString("maidenName"));
        user.setAge(jsonUser.getInt("age"));
        user.setEmail(jsonUser.getString("email"));
        user.setPhone(jsonUser.getString("phone"));
        user.setUsername(jsonUser.getString("username"));
        user.setPassword(jsonUser.getString("password"));
        user.setBirthDate(jsonUser.getString("birthDate"));
        user.setImage(jsonUser.getString("image"));
        user.setBloodGroup(jsonUser.getString("bloodGroup"));
        user.setHeight(jsonUser.getDouble("height"));
        user.setWeight(jsonUser.getDouble("weight"));
        user.setEyeColor(jsonUser.getString("eyeColor"));
        user.setHair(hair);
        user.setIp(jsonUser.getString("ip"));
        user.setAddress(address);
        user.setMacAddress(jsonUser.getString("macAddress"));
        user.setUniversity(jsonUser.getString("university"));
        user.setBank(bank);
        user.setCompany(company);
        user.setEin(jsonUser.getString("ein"));
        user.setSsn(jsonUser.getString("ssn"));
        user.setUserAgent(jsonUser.getString("userAgent"));
        user.setCrypto(crypto);
        user.setRole(jsonUser.getString("role"));

        userRepository.save(user);
    }

}
