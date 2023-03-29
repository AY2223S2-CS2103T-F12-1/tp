package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * The Contact Display displaying the list of doctors,
 * patients and their respective information.
 */
public class ContactDisplay extends UiPart<Region> {

    private static final String FXML = "ContactDisplay.fxml";

    private static final Logger logger = LogsCenter.getLogger(ContactDisplay.class);

    private Logic logic;

    // To toggle between displaying Doctor and Patient Info
    private EnlargedInfoCardDisplayController infoCardDisplayController;

    // Independent Ui parts residing in this Ui container
    private EnlargedDoctorInfoCard enlargedDoctorInfoCard;
    private EnlargedPatientInfoCard enlargedPatientInfoCard;
    private DoctorListPanel doctorListPanel;
    private PatientListPanel patientListPanel;

    @FXML
    private GridPane contactDisplay;

    @FXML
    private StackPane enlargedPersonInfoCardPlaceholder;

    @FXML
    private StackPane doctorListPanelPlaceholder;

    @FXML
    private StackPane patientListPanelPlaceholder;

    /**
     * Creates a {@code ContactDisplay}
     * with the given {@code Logic}.
     */
    public ContactDisplay(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.infoCardDisplayController = new EnlargedInfoCardDisplayController(this);
        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this {@code ContactDisplay}.
     */
    private void fillInnerParts() {
        enlargedDoctorInfoCard = new EnlargedDoctorInfoCard();
        enlargedPatientInfoCard = new EnlargedPatientInfoCard();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList(),
                enlargedPatientInfoCard, infoCardDisplayController);
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredDoctorList(),
                enlargedDoctorInfoCard, infoCardDisplayController, patientListPanel);
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    /**
     * Updates the enlarged info card placeholder to show the
     * appropriate enlarged information.
     *
     * This information is either that of a doctor or a patient.
     *
     * @param hasGuiInteraction true if command entered is a GUI interaction.
     */
    public void setFeedbackToUser(boolean hasGuiInteraction) {
        // If command does not need GUI Interaction, function call ends.
        if (!hasGuiInteraction) {
            logger.info("Command did not result in GUI Interaction");
            return;
        }
        // If app reaches here, then command should be select-doc or select-ptn
        enlargedPersonInfoCardPlaceholder.getChildren().clear();
        if (infoCardDisplayController.shouldDisplayDoctorInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());
        } else if (infoCardDisplayController.shouldDisplayPatientInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedPatientInfoCard.getRoot());
        } else {
            logger.severe("shouldDisplayDoctor and shouldDisplayPatient"
                    + "in EnlargedInfoCardDisplayController should never be equal!");
        }
    }

    /**
     * Updates the enlarged info card placeholder
     * to show information about selected {@code Doctor}.
     *
     * @param doctor a selected doctor.
     */
    public void showSelectedDoctor(Doctor doctor) {
        enlargedDoctorInfoCard.updateSelectedDoctorOptional(Optional.ofNullable(doctor));
        infoCardDisplayController.displayDoctor();
        setFeedbackToUser(true);
    }

    /**
     * Updates the enlarged info card placeholder
     * to show information about selected {@code Patient}.
     *
     * @param patient a selected patient.
     */
    public void showSelectedPatient(Patient patient) {
        enlargedPatientInfoCard.updateSelectedPatientOptional(Optional.ofNullable(patient));
        infoCardDisplayController.displayPatient();
        setFeedbackToUser(true);
    }

    /**
     * Scrolls down the doctor list panel
     * to show information about selected {@code Doctor}.
     *
     * @param doctorIndex the Index of the selected doctor.
     */
    public void scrollToSelectedDoctor(Index doctorIndex) {
        doctorListPanel.scrollTo(doctorIndex);
    }

    /**
     * Scrolls down the patient list panel
     * to show information about selected {@code Patient}.
     *
     * @param patientIndex the Index of the selected patient.
     */
    public void scrollToSelectedPatient(Index patientIndex) {
        patientListPanel.scrollTo(patientIndex);
    }
}
