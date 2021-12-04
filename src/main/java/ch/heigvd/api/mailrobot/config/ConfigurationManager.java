package ch.heigvd.api.mailrobot.config;

import ch.heigvd.api.mailrobot.model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public class ConfigurationManager {

    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOFGroups;
    private List<Person> witnessesToCC;



    public ConfigurationManager() throws IOException {
        victims = loadAddressesFromFile("./config/victims.utf8");
        messages = loadMessagesFromFile("./config/messages.utf8");
        loadProperties("./config/config.properties");
    }


    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);

        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOFGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for (String address : witnessesAddresses) {
            this.witnessesToCC.add(new Person(address));
        }
    }

    private List<Person> loadAddressesFromFile(String fileName) throws IOException {
        List<Person> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String address = reader.readLine();
                while (address != null) {
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }


    private List<String> loadMessagesFromFile(String fileName) throws IOException {
        List<String> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    StringBuilder body = new StringBuilder();
                    while ((line != null) && (!line.equals("=="))) {
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<String> getMessages() {
        return messages;
    }


}
