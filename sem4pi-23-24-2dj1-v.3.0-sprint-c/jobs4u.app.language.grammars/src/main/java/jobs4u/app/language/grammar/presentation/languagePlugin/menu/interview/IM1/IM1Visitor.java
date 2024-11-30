package jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.IM1;// Generated from C:/Users/35193/Desktop/universidade/4Semestre/LPROG/TestGrammar/src/main/antlr/interview/IM1/IM1.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IM1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IM1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IM1Parser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(IM1Parser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#questionOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionOne(IM1Parser.QuestionOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#answerOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerOne(IM1Parser.AnswerOneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerOne}
	 * labeled alternative in {@link IM1Parser#answerOneText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerOne(IM1Parser.VerifyAnswerOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#questionTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTwo(IM1Parser.QuestionTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#answerTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerTwo(IM1Parser.AnswerTwoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerTwo}
	 * labeled alternative in {@link IM1Parser#answerTwoText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerTwo(IM1Parser.VerifyAnswerTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#questionThree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionThree(IM1Parser.QuestionThreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#answerThree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerThree(IM1Parser.AnswerThreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerThree}
	 * labeled alternative in {@link IM1Parser#answerThreeText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerThree(IM1Parser.VerifyAnswerThreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link IM1Parser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(IM1Parser.LanguageContext ctx);
}