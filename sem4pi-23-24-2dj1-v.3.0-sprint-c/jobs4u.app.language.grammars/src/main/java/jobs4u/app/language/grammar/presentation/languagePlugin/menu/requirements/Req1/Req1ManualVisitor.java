package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1;

import java.util.List;

public class Req1ManualVisitor extends Req1BaseVisitor<String> {
    @Override public String visitVerifyAnswerOne(Req1Parser.VerifyAnswerOneContext ctx) {
        int answer = Integer.parseInt(String.valueOf(ctx.getText()));
        if(answer < 2)
            throw new IllegalStateException("A minimum of 2 years of experience is required for the job position.");
        return visitChildren(ctx);
    }
    @Override public String visitAnswerTwo(Req1Parser.AnswerTwoContext ctx) {
        String answer = ctx.answerTwoText().getText();
        if(answer.equalsIgnoreCase("None"))
            throw new IllegalStateException("A minimum Bachelor degree is required for the job position.");
        return visitChildren(ctx);
    }
    @Override public String visitAnswerThree(Req1Parser.AnswerThreeContext ctx) {
        List<String> languages = List.of(ctx.answerThreeText().getText().split(", "));
        if(languages.size() > 3)
            throw new IllegalArgumentException("There are more languages in the answer than the permitted amount");
        if(!languages.contains("python"))
            throw new IllegalStateException("Python knowledge is required for the job position.");
        return visitChildren(ctx);
    }
}
