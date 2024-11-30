package jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.IM1;

import java.io.IOException;

public interface IMEvaluator {
    /**
     * Method that returns the
     * @return
     */
    String exportTemplate();
    String evaluateTextFile(String content) throws IOException;
}
