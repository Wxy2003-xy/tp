package seedu.address.model.person;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for {@code History}.
 */
public class HistoryTest {

    private History history;

    @BeforeEach
    public void setUp() {
        history = new History(of(2024, 1, 1)); // Date of creation for history is 2024-01-01
    }

    @Test
    public void addActivity_beforeDateOfCreation_throwsIllegalArgumentException() {
        LocalDate dateBeforeCreation = of(2023, 12, 31); // Date before creation date
        String message = "Task performed before creation date";

        // Expect IllegalArgumentException for dates before the creation date
        assertThrows(IllegalArgumentException.class, () -> history.addActivity(dateBeforeCreation, message));
    }

    @Test
    public void addActivity_nullMessage_throwsIllegalArgumentException() {
        LocalDate validDate = of(2024, 1, 10); // Valid date after creation

        // Expect IllegalArgumentException for null message
        assertThrows(IllegalArgumentException.class, () -> history.addActivity(validDate, null));
    }

    @Test
    public void getActivitiesOnDay_noActivityForDate_throwsNoSuchElementException() {
        LocalDate validDate = of(2024, 1, 10); // Valid date after creation

        // Expect NoSuchElementException for date with no activities
        assertThrows(NullPointerException.class, () -> history.getActivitiesOnDay(validDate));
    }

    @Test
    public void getActivitiesOnDay_withActivity_success() {
        LocalDate validDate = of(2024, 1, 10); // Valid date after creation
        String message = "Completed task A";

        // Add an activity and retrieve it
        history.addActivity(validDate, message);
        List<Activity> activities = history.getActivitiesOnDay(validDate);

        // Verify the activity
        assertEquals(1, activities.size());
        assertEquals("[2024-01-10] Completed task A", activities.get(0).toString());
    }

    @Test
    public void addActivity_immutableAdd_returnsNewHistoryObject() {
        LocalDate validDate = of(2024, 1, 10);
        String message = "Completed task A";

        // Add activity in immutable way
        History newHistory = History.addActivity(history, validDate, message);

        // Ensure the original history is unchanged
        assertThrows(NullPointerException.class, () -> history.getActivitiesOnDay(validDate));

        // Ensure the new history contains the new activity
        List<Activity> activities = newHistory.getActivitiesOnDay(validDate);
        assertEquals(1, activities.size());
        assertEquals("[2024-01-10] Completed task A", activities.get(0).toString());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        // Same object should return true
        assertEquals(history, history);
    }

    @Test
    public void equals_differentObjectSameContent_returnsFalse() {
        // Create another history object with the same content
        TreeMap<LocalDate, ArrayList<String>> sameHistoryMap = new TreeMap<>();
        History sameHistory = new History(sameHistoryMap, LocalDate.of(2024, 1, 1));

        // Even though the content is the same, they are different instances, so should return false
        assertNotEquals(history, sameHistory);
    }


    @Test
    public void equals_differentObjectDifferentContent_returnsFalse() {
        // Create another history object with different content
        TreeMap<LocalDate, ArrayList<String>> differentHistoryMap = new TreeMap<>();
        History differentHistory = new History(differentHistoryMap, of(2023, 12, 31));

        // Should return false for different content
        assertNotEquals(history, differentHistory);
    }

    @Test
    public void equals_null_returnsFalse() {
        // Comparing with null should return false
        assertNotEquals(history, null);
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        // Comparing with a different class should return false
        assertNotEquals(history, new Object());
    }

    @Test
    public void toString_validHistory_success() {
        LocalDate validDate = of(2024, 1, 10);
        String message = "Completed task A";

        // Add an activity
        history.addActivity(validDate, message);

        // Verify the toString output
        String expectedString = "Date of Creation: 2024-01-01\n[2024-01-10]:\n  [2024-01-10] Completed task A\n";
        assertEquals(expectedString, history.toString());
    }
}
