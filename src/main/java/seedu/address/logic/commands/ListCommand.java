package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PDFS;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Lists all PDFs in the PDF book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all PDFs.";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPdfList(PREDICATE_SHOW_ALL_PDFS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
