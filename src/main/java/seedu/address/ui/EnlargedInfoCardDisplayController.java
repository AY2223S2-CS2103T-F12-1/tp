package seedu.address.ui;

/**
 * A controller to choose between displaying {@code EnlargedDoctorInfoCard}
 * and {@code EnlargedPatientInfoCard} on {@code ContactDisplay}.
 */
public class EnlargedInfoCardDisplayController {

    private boolean shouldDisplayDoctorInfoCard;
    private boolean shouldDisplayPatientInfoCard;
    private ContactDisplay parent;

    /**
     * Creates a {@code EnlargedInfoCardDisplayController}
     * with the given parent {@code ContactDisplay}.
     */
    public EnlargedInfoCardDisplayController(ContactDisplay parent) {
        // Show Doctor by default
        this.parent = parent;
    }

    /**
     * Updates controller state to display {@code EnlargedDoctorInfoCard}.
     */
    public void displayDoctor() {
        shouldDisplayDoctorInfoCard = true;
        shouldDisplayPatientInfoCard = false;
        parent.setFeedbackToUser();
    }

    /**
     * Updates controller state to display {@code EnlargedPatientInfoCard}.
     */
    public void displayPatient() {
        shouldDisplayDoctorInfoCard = false;
        shouldDisplayPatientInfoCard = true;
        parent.setFeedbackToUser();
    }

    public boolean shouldDisplayDoctorInfoCard() {
        return shouldDisplayDoctorInfoCard;
    }

    public boolean shouldDisplayPatientInfoCard() {
        return shouldDisplayPatientInfoCard;
    }

}
