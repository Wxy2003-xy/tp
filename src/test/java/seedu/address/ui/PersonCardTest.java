package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.EMPTY_BIRTHDAY;
import static seedu.address.model.util.SampleDataUtil.EMPTY_REMARK;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfCreation;
import seedu.address.model.person.Email;
import seedu.address.model.person.History;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class PersonCardTest {

    private Person person;
    private PersonCard personCard;

    @BeforeEach
    public void setUp() {
        // Create a mock Person object with test data
        person = new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK, EMPTY_BIRTHDAY,
                getTagSet("friends", "classmates"),
                new DateOfCreation(LocalDate.of(2024, 1, 1)),
                new History(LocalDate.of(2024, 1, 1)));

        // Create the PersonCard object
        personCard = new PersonCard(person, 1); // Displayed index is 1
    }

    @Test
    public void testPersonCardData() {
        // Verify the name, phone, and email from the Person object are correctly passed to PersonCard
        assertEquals("Alex Yeoh", person.getName().fullName);
        assertEquals("87438807", person.getPhone().value);
        assertEquals("alexyeoh@example.com", person.getEmail().value);
    }

    @Test
    public void testTagsRendering() {
        // Verify the tags were correctly passed to the PersonCard
        assertEquals(2, person.getTags().size()); // Expecting 2 tags: friends, classmates
        assertEquals("friends", person.getTags().stream().findFirst().get().tagName);
    }

    @Test
    public void testHistoryRendering() {
        // Verify the history data was correctly passed to the PersonCard
        assertEquals(1, person.getHistory().getHistoryEntries().size()); // Expecting 1 history entry
    }
}
