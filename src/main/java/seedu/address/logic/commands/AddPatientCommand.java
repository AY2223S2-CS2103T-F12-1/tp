package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patient.Patient;

/**
 * Adds a patient to the address book.
 */
public class AddPatientCommand extends Command {
    public static final String COMMAND_WORD = "add-pat";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_REMARK + "REMARK "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "jdoe@gmail.com "
            + PREFIX_HEIGHT + "180.55 "
            + PREFIX_WEIGHT + "70.50 "
            + PREFIX_DIAGNOSIS + "Fever "
            + PREFIX_STATUS + "Outpatient "
            + PREFIX_REMARK + "Patient was given paracetamol for fever "
            + PREFIX_TAG + "friends";



    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book";

    private final Patient toAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Patient}
     */
    public AddPatientCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                && toAdd.equals(((AddPatientCommand) other).toAdd));
    }
}
