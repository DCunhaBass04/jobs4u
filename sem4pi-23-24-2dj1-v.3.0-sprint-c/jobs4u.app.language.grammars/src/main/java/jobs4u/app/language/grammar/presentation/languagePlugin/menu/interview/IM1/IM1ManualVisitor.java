package jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.IM1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IM1ManualVisitor extends IM1BaseVisitor<String> {
    private int score = 0; //0-100
    private final int MAX_SCORE = 100;
    private final List<Integer> scorePerQuestion = new ArrayList<>();
    private final List<Integer> MAX_SCORE_PER_QUESTION = Arrays.asList(20, 40, 40);
    @Override public String visitProg(IM1Parser.ProgContext ctx) {
        visitChildren(ctx);
        for(Integer eachScore : scorePerQuestion) score += eachScore;
        return String.format("Score: %d/%d%nQ1: %d/%d%nQ2: %d/%d%nQ3: %d/%d", score, MAX_SCORE,
                scorePerQuestion.get(0), MAX_SCORE_PER_QUESTION.get(0),
                scorePerQuestion.get(1), MAX_SCORE_PER_QUESTION.get(1),
                scorePerQuestion.get(2), MAX_SCORE_PER_QUESTION.get(2));
    }

    @Override public String visitVerifyAnswerOne(IM1Parser.VerifyAnswerOneContext ctx) { //0 or 20
        if(!Boolean.getBoolean(ctx.getText()))
            scorePerQuestion.add(0);
        else scorePerQuestion.add(20);
        return visitChildren(ctx);
    }
    @Override public String visitVerifyAnswerTwo(IM1Parser.VerifyAnswerTwoContext ctx) { //0 to 40
        switch (ctx.getText()){
            case "None" -> scorePerQuestion.add(0);
            case "Bachelor" -> scorePerQuestion.add(20);
            case "Master" -> scorePerQuestion.add(30);
            case "PhD" -> scorePerQuestion.add(40);
        }
        return visitChildren(ctx);
    }
    @Override public String visitVerifyAnswerThree(IM1Parser.VerifyAnswerThreeContext ctx) {
        List<String> languages = List.of(ctx.getText().split(", "));
        if(languages.contains("Javascript"))
            if(languages.contains("Typescript"))
                scorePerQuestion.add(40);
            else scorePerQuestion.add(16);
        else if(languages.contains("Typescript")) scorePerQuestion.add(32);
        return visitChildren(ctx);
    }
}
