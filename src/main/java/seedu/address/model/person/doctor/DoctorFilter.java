package seedu.address.model.person.doctor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.patient.Patient;

/**
 * Filter Class for passing to DoctorContainsKeywordsPredicate
 */
public class DoctorFilter {

    //Filter fields
    private final String nameFilter;
    private final String phoneFilter;
    private final String emailFilter;
    private final String specialtyFilter;
    private final String yoeFilter;
    private final Set<String> tagsFilter;
    private final Patient patientFilter;

    /**
     * Constructs a {@code DoctorFilter}.
     *
     * @param name name in string (can be empty string).
     * @param phone phone in string (can be empty string).
     * @param email email in string (can be empty string).
     * @param specialty specialty in string (can be empty string).
     * @param yoe yoe in string (can be empty string).
     * @param tags set of tags in string (can be empty list).
     * @param patient a patient object (can be null).
     */
    public DoctorFilter(String name, String phone, String email,
                        String specialty, String yoe,
                        Set<String> tags, Patient patient) {
        this.nameFilter = name;
        this.phoneFilter = phone;
        this.emailFilter = email;
        this.specialtyFilter = specialty;
        this.yoeFilter = yoe;
        this.tagsFilter = tags;
        this.patientFilter = patient;
    }

    /**
     * Constructs a {@code DoctorFilter}.
     *
     * @param name name in string (can be empty string).
     * @param phone phone in string (can be empty string).
     * @param email email in string (can be empty string).
     * @param specialty specialty in string (can be empty string).
     * @param yoe yoe in string (can be empty string).
     * @param tags set of tags in string (can be empty list).
     */
    public DoctorFilter(String name, String phone, String email,
                        String specialty, String yoe, Set<String> tags) {
        this(name, phone, email, specialty, yoe, tags, null);
    }

    /**
     * Constructs a {@code DoctorFilter}.
     *
     * @param patient a patient object (can be null).
     */
    public DoctorFilter(Patient patient) {
        this("", "", "", "",
                "", new HashSet<>(), patient);
    }

    /**
     * Matches filter fields to doctor
     * Returns true if each filter field is a substring of the respective field of doctor
     */
    public boolean isMatch(Doctor doctor) {

        boolean result = StringUtil.containsSubstringIgnoreCase(doctor.getName().fullName, nameFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getPhone().value, phoneFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getEmail().value, emailFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getSpecialty().specialty, specialtyFilter)
                && StringUtil.containsSubstringIgnoreCase(doctor.getYoe().value, yoeFilter);

        if (!tagsFilter.isEmpty()) {
            Set<String> tagStringSet = doctor
                    .getTags()
                    .stream()
                    .map(tag -> tag.tagName.toLowerCase())
                    .collect(Collectors.toSet());
            result = result && tagStringSet.containsAll(tagsFilter
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet()));
        }

        if (patientFilter != null) {
            result = result && doctor.hasPatient(patientFilter);
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoctorFilter // instanceof handles nulls
                && this.nameFilter.equals(((DoctorFilter) other).nameFilter)
                && this.phoneFilter.equals(((DoctorFilter) other).phoneFilter)
                && this.emailFilter.equals(((DoctorFilter) other).emailFilter)
                && this.specialtyFilter.equals(((DoctorFilter) other).specialtyFilter)
                && this.yoeFilter.equals(((DoctorFilter) other).yoeFilter)
                && this.tagsFilter.equals(((DoctorFilter) other).tagsFilter))
                && this.patientFilter.equals(((DoctorFilter) other).patientFilter); // state check
    }
}
