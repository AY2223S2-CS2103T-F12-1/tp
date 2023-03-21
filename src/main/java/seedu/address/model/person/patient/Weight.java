package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's weight in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS =
            "Weight should be in kilograms (kg), can have at most 1 decimal place, and not equal to 0.";

    public static final String VALIDATION_REGEX = "^\\s*\\d+(\\.\\d)?\\s*$";

    public final String weight;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight in kilograms (kg).
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        String trimmedWeight = weight.trim();
        this.weight = trimmedWeight;
    }

    /**
     * Returns true if a given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        test = test.trim();
        return test.matches(VALIDATION_REGEX) && !test.equals("0") && !test.equals("0.0");
    }

    @Override
    public String toString() {
        return weight + " kg";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && weight.equals(((Weight) other).weight)); // state check
    }

    @Override
    public int hashCode() {
        return weight.hashCode();
    }
}
