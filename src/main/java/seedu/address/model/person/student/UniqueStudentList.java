package seedu.address.model.person.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueStudentList extends UniquePersonList {

    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(students);
    }

    /**
     * Converts {@code ObservableList<Person>} to {@code ObservableList<Student>}.
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableStudentList() {
        ObservableList<Student> castToStudentList = FXCollections.observableArrayList();
        for (Person p : internalList) {
            Student s = (Student) p;
            castToStudentList.add(s);
        }
        return FXCollections.unmodifiableObservableList(castToStudentList);
    }

    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSamePerson(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
