package ch.heigvd.api.mailrobot.config;

import ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.Collection;
import java.util.List;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public interface IConfigurationManager {


    int getNumberOfGroups();

    List<String> getMessages();

    List<Person> getVictims();

    List<Person> getWitnessesToCC();


}
