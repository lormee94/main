package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.getAllPrefixes;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pdf.Directory;
import seedu.address.model.pdf.Name;
import seedu.address.model.pdf.Pdf;
import seedu.address.model.pdf.Size;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final String MESSAGE_NO_FILE_SELECTED = "No file selected";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        File file = null;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, getAllPrefixes());

        if (CliSyntax.arePrefixesPresent(argMultimap, CliSyntax.getInvalidPrefixesForCommand(PREFIX_FILE))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        if (CliSyntax.arePrefixesPresent(argMultimap, PREFIX_FILE) && argMultimap.getPreamble().isEmpty()) {
            if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
                file = ParserUtil.parseFile(argMultimap.getValue(PREFIX_FILE).get());
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

        } else if (!CliSyntax.arePrefixesPresent(argMultimap, PREFIX_FILE) && argMultimap.getPreamble().isEmpty()) {

            Optional<File> fileContainer = new AddGuiParser().selectPdf();

            if (!fileContainer.isPresent()) {
                throw new ParseException(AddCommandParser.MESSAGE_NO_FILE_SELECTED);
            } else {
                file = fileContainer.get();
            }
        } else if (!CliSyntax.arePrefixesPresent(argMultimap, PREFIX_FILE)
                && !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        try {
            Name name = new Name(file.getName());
            Directory directory = new Directory(file.getParent());
            Size size = new Size(Long.toString(file.length()));

            Pdf pdf = new Pdf(name, directory, size, new HashSet<>());
            return new AddCommand(pdf);

        } catch (Exception e) {
            throw new ParseException(AddCommand.MESSAGE_INVALID_SELECTION);
        }
    }
}
