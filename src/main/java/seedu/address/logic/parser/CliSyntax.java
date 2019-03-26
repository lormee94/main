package seedu.address.logic.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DIRECTORY = new Prefix("d/");
    public static final Prefix PREFIX_FILE = new Prefix("f/");
    public static final Prefix PREFIX_TAG_NEW = new Prefix("t/");
    public static final Prefix PREFIX_TAG_REMOVE = new Prefix("-t/");
    public static final Prefix PREFIX_DEADLINE_NEW = new Prefix("date/");
    public static final Prefix PREFIX_DEADLINE_COMPLETE = new Prefix("done");
    public static final Prefix PREFIX_DEADLINE_REMOVE = new Prefix("remove");
    public static final int PREFIX_COUNT = 8;

    public static Prefix[] getAllPrefixes() {
        return Stream.of(PREFIX_NAME, PREFIX_DIRECTORY,
                PREFIX_FILE, PREFIX_TAG_NEW, PREFIX_TAG_REMOVE, PREFIX_DEADLINE_NEW,
                PREFIX_DEADLINE_COMPLETE, PREFIX_DEADLINE_REMOVE)
                .collect(Collectors.toList())
                .toArray(new Prefix[PREFIX_COUNT]);

    }
}
