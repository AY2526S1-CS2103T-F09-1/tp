package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;





/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane reminders;
    @FXML
    private AnchorPane remindersPlaceholder;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (person.getReminders().isEmpty()) {
            System.out.println("No reminders for this person.");
        } else {
            System.out.println("Found " + person.getReminders().size() + " reminders.");
        }
        person.getReminders().stream()
                .sorted(Comparator.comparing(reminder -> reminder.header))
                .forEach(reminder -> {
                    if (reminder.header == null || reminder.header.isEmpty()) {
                        System.out.println("Reminder has no header.");
                    } else {
                        reminders.getChildren().add(new Label(reminder.header));
                    }
                });
        
//        ObservableList<String> tempReminders = FXCollections.observableArrayList(
//                "Call to review policy renewal on 21/11/2025 14:30",
//                "Send birthday voucher on 03/12/2025",
//                "Follow-up: claim form status next Mon"
//        );
//        ReminderListPanel reminderListPanel = new ReminderListPanel(tempReminders);
//        remindersPlaceholder.getChildren().add(reminderListPanel.getRoot());
//        AnchorPane.setTopAnchor(reminderListPanel.getRoot(), 0.0);
//        AnchorPane.setLeftAnchor(reminderListPanel.getRoot(), 0.0);
//        AnchorPane.setRightAnchor(reminderListPanel.getRoot(), 0.0);

    }
}
