package ch.heigvd.api.mailrobot.model.mail;

import java.util.ArrayList;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public class Group {

    private final ArrayList<Person> members = new ArrayList<>();

    public void addMemeber(Person person){
        members.add(person);
    }

    public ArrayList<Person> getMembers(){
        return new ArrayList<>(members);
    }
}
