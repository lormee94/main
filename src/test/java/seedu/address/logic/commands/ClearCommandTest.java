package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPdfs.getTypicalPdfBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PdfBook;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Test
    public void execute_emptyPdfBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitPdfBook();

        assertCommandSuccess(new ClearCommand(), model,
                EMPTY_COMMAND_HISTORY, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPdfBook_success() {
        Model model = new ModelManager(getTypicalPdfBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPdfBook(), new UserPrefs());
        expectedModel.setPdfBook(new PdfBook());
        expectedModel.commitPdfBook();

        assertCommandSuccess(new ClearCommand(), model,
                EMPTY_COMMAND_HISTORY, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
