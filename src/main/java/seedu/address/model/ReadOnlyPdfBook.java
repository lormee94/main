package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.pdf.Pdf;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPdfBook extends Observable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Pdf> getPdfList();

}
