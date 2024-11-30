package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1;

import java.io.IOException;

public interface ReqEvaluator {
    /**
     * Method that returns the
     * @return
     */
    String exportTemplate();
    void evaluateTextFile(String content) throws IOException;
}
