package ch.heigvd.api.mailrobot.model.prank;

import ch.heigvd.api.mailrobot.config.ConfigurationManager;
import ch.heigvd.api.mailrobot.config.IConfigurationManager;
import ch.heigvd.api.mailrobot.model.mail.Group;
import ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Bogale Tegest & Ferchichi Ahmed Farouk
 */
public class PrankGenerator {
    private IConfigurationManager configurationManager;

    public PrankGenerator(IConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }


    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<>();

        List<String> messages = configurationManager.getMessages();
        int messageIndex = 0;

        int numberOfGroups = configurationManager.getNumberOfGroups();
        int numberOfVictims = configurationManager.getVictims().size();

        // Au moins 3 victimes par groupe
        if (numberOfVictims / numberOfGroups < 3) {
            numberOfGroups = numberOfVictims / 3;
            LOG.warning("There are not enough victims to generate the desired number of groups." +
                    " We can only generate a max of " + numberOfGroups + " groups" +
                    "to have at least 3 victims per group.");
        }

        List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);
        for (Group group : groups) {
            Prank prank = new Prank();

            List<Person> victims = group.getMembers();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);

            prank.addWitnessRecipients(configurationManager.getWitnessesToCC());

            String message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % message.length() - 1;
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());

    public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        List<Person> availableVictims = new ArrayList<>(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; ++i) {
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while (availableVictims.size() > 0) {
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availableVictims.remove(0);
            targetGroup.addMemeber(victim);
        }
        return groups;
    }
}
