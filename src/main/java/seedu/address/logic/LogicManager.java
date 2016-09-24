package seedu.address.logic;

import com.google.common.eventbus.Subscribe;
import javafx.collections.ObservableList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.ModelChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.Parser;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.storage.Storage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private static final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) {
        Command command = parser.parseCommand(commandText);
        command.setData(model);
        return command.execute();
    }

    /**
     * Creates the file if it is missing before saving.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving
     */
    @Subscribe
    public void handleModelChangedEvent(ModelChangedEvent mce) {
        logger.info("Local data changed, saving to primary data file");
        try {
            storage.saveAddressBook(mce.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }
}