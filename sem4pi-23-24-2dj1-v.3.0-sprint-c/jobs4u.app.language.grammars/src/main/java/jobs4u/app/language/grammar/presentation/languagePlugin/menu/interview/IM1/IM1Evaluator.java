package jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.IM1;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IM1Evaluator implements IMEvaluator {
    @Override
    public String exportTemplate() {
        return String.format("# ISEP was founded in the year 1910 (true/false)%nFounding-year: %n" +
                "# Select one degree (None; Bachelor; Master; PhD)%nAcademic-degree: %n" +
                "# Select one or more programming languages you are proficient in (Java; C#; PHP; Javascript; Typescript)%nProgramming-languages: %n");
    }

    @Override
    public String evaluateTextFile(String fileContent) throws IOException {
        IM1Lexer lexer = new IM1Lexer(new ANTLRInputStream(new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8))));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        IM1Parser parser = new IM1Parser(tokens);
        ParseTree tree = parser.prog();
        IM1ManualVisitor visitor = new IM1ManualVisitor();
        return visitor.visit(tree);
    }
}
