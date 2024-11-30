package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1;// Generated from C:/Users/35193/Desktop/universidade/4Semestre/LPROG/TestGrammar/src/main/antlr/requirements/Req1/Req1.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Req1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Req1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Req1Parser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(Req1Parser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#questionOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionOne(Req1Parser.QuestionOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#answerOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerOne(Req1Parser.AnswerOneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerOne}
	 * labeled alternative in {@link Req1Parser#answerOneText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerOne(Req1Parser.VerifyAnswerOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#questionTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTwo(Req1Parser.QuestionTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#answerTwo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerTwo(Req1Parser.AnswerTwoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerTwo}
	 * labeled alternative in {@link Req1Parser#answerTwoText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerTwo(Req1Parser.VerifyAnswerTwoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#questionThree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionThree(Req1Parser.QuestionThreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#answerThree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerThree(Req1Parser.AnswerThreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verifyAnswerThree}
	 * labeled alternative in {@link Req1Parser#answerThreeText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerifyAnswerThree(Req1Parser.VerifyAnswerThreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Req1Parser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(Req1Parser.LanguageContext ctx);
}