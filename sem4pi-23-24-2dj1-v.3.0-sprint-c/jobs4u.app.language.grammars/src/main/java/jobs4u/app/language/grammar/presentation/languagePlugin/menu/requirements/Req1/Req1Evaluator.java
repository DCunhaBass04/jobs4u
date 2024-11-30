package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Req1Evaluator implements ReqEvaluator {
    @Override
    public String exportTemplate() {
        return String.format("# Enter the number of years of experience (integer)%nExperience-years: %n" +
                "# Select one degree (None; Bachelor; Master; PhD)%nAcademic-degree: %n" +
                "# Select one or more programming languages you are proficient in (java; javascript; python)%nProgramming-languages: %n");
    }

    @Override
    public void evaluateTextFile(String fileContent) throws IOException {
        Req1Lexer lexer = new Req1Lexer(new ANTLRInputStream(new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8))));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Req1Parser parser = new Req1Parser(tokens);
        ParseTree tree = parser.prog();
        Req1ManualVisitor visitor = new Req1ManualVisitor();
        visitor.visit(tree);
    }
}
