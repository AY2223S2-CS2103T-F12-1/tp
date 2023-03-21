package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.doctor.Doctor;

/**
 * Panel containing the list of doctors.
 */
public class DoctorListPanel extends UiPart<Region> {
    private static final String FXML = "DoctorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DoctorListPanel.class);

    @FXML
    private ListView<Doctor> doctorListView;

    private Doctor selectedDoctor;

    /**
     * Creates a {@code DoctorListPanel} with the given {@code ObservableList}.
     */
    public DoctorListPanel(ObservableList<Doctor> doctorList,
                           EnlargedDoctorInfoCard enlargedDoctorInfoCard,
                           EnlargedInfoCardDisplayController infoCardDisplayController) {
        super(FXML);
        doctorListView.setItems(doctorList);
        doctorListView.setCellFactory(listView -> {
            DoctorListViewCell generatedCell = new DoctorListViewCell();
            generatedCell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                infoCardDisplayController.displayDoctor();
            });
            return generatedCell;
        });
        setSelectedDoctor(doctorList);
        showSelectedDoctorInfo(enlargedDoctorInfoCard);
    }

    /**
     * Returns the {@code Doctor} selected by the user.
     *
     * @return doctor selected by user
     */
    public Doctor getSelectedDoctor() {
        return this.selectedDoctor;
    }

    /**
     * Sets the {@code Doctor} selected by the user.
     *
     * @param doctorList list of {@code Doctor} objects queried by the user
     */
    private void setSelectedDoctor(ObservableList<Doctor> doctorList) {
        selectedDoctor = null;
        if (!doctorList.isEmpty()) {
            selectedDoctor = doctorList.get(0);
        }
    }

    /**
     * Prompts {@code EnlargedDoctorInfoCard} to display the information of the {@code Doctor} selected by the user.
     * This is done by configuring a {@code ChangeListener} to listen to user selection.
     *
     * @param enlargedDoctorInfoCard the UI part displaying the information of {@code Doctor} selected
     */
    private void showSelectedDoctorInfo(
            EnlargedDoctorInfoCard enlargedDoctorInfoCard) {
        ChangeListener<Doctor> changeListener = (observable, oldValue, newValue) -> {
            selectedDoctor = observable.getValue();
            enlargedDoctorInfoCard.updateSelectedDoctorOptional(Optional.ofNullable(selectedDoctor));
        };
        doctorListView.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Doctor} using a {@code DoctorCard}.
     */
    class DoctorListViewCell extends ListCell<Doctor> {
        @Override
        protected void updateItem(Doctor doctor, boolean empty) {
            super.updateItem(doctor, empty);

            if (empty || doctor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoctorCard(doctor, getIndex() + 1).getRoot());
            }
        }
    }

}
