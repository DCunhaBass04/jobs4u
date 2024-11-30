package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1;// Generated from C:/Users/35193/Desktop/universidade/4Semestre/LPROG/TestGrammar/src/main/antlr/requirements/Req1/Req1.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Req1Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, INT=15, NEWLINE=16;
	public static final int
		RULE_prog = 0, RULE_questionOne = 1, RULE_answerOne = 2, RULE_answerOneText = 3, 
		RULE_questionTwo = 4, RULE_answerTwo = 5, RULE_answerTwoText = 6, RULE_questionThree = 7, 
		RULE_answerThree = 8, RULE_answerThreeText = 9, RULE_language = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "questionOne", "answerOne", "answerOneText", "questionTwo", "answerTwo", 
			"answerTwoText", "questionThree", "answerThree", "answerThreeText", "language"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'# Enter the number of years of experience (integer)'", "'Experience-years: '", 
			"'# Select one degree (None; Bachelor; Master; PhD)'", "'Academic-degree: '", 
			"'None'", "'Bachelor'", "'Master'", "'PhD'", "'# Select one or more programming languages you are proficient in (java; javascript; python)'", 
			"'Programming-languages: '", "', '", "'java'", "'javascript'", "'python'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "INT", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Req1.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Req1Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public QuestionOneContext questionOne() {
			return getRuleContext(QuestionOneContext.class,0);
		}
		public QuestionTwoContext questionTwo() {
			return getRuleContext(QuestionTwoContext.class,0);
		}
		public QuestionThreeContext questionThree() {
			return getRuleContext(QuestionThreeContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(Req1Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Req1Parser.NEWLINE, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			questionOne();
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(23);
				match(NEWLINE);
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			questionTwo();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(30);
				match(NEWLINE);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			questionThree();
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(37);
				match(NEWLINE);
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionOneContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(Req1Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Req1Parser.NEWLINE, i);
		}
		public AnswerOneContext answerOne() {
			return getRuleContext(AnswerOneContext.class,0);
		}
		public QuestionOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionOne; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterQuestionOne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitQuestionOne(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitQuestionOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionOneContext questionOne() throws RecognitionException {
		QuestionOneContext _localctx = new QuestionOneContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_questionOne);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__0);
			setState(44);
			match(NEWLINE);
			setState(45);
			answerOne();
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46);
					match(NEWLINE);
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerOneContext extends ParserRuleContext {
		public AnswerOneTextContext answerOneText() {
			return getRuleContext(AnswerOneTextContext.class,0);
		}
		public AnswerOneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerOne; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterAnswerOne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitAnswerOne(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitAnswerOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerOneContext answerOne() throws RecognitionException {
		AnswerOneContext _localctx = new AnswerOneContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_answerOne);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__1);
			setState(53);
			answerOneText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerOneTextContext extends ParserRuleContext {
		public AnswerOneTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerOneText; }
	 
		public AnswerOneTextContext() { }
		public void copyFrom(AnswerOneTextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VerifyAnswerOneContext extends AnswerOneTextContext {
		public List<TerminalNode> INT() { return getTokens(Req1Parser.INT); }
		public TerminalNode INT(int i) {
			return getToken(Req1Parser.INT, i);
		}
		public VerifyAnswerOneContext(AnswerOneTextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterVerifyAnswerOne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitVerifyAnswerOne(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitVerifyAnswerOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerOneTextContext answerOneText() throws RecognitionException {
		AnswerOneTextContext _localctx = new AnswerOneTextContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_answerOneText);
		int _la;
		try {
			_localctx = new VerifyAnswerOneContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				match(INT);
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionTwoContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(Req1Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Req1Parser.NEWLINE, i);
		}
		public AnswerTwoContext answerTwo() {
			return getRuleContext(AnswerTwoContext.class,0);
		}
		public QuestionTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionTwo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterQuestionTwo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitQuestionTwo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitQuestionTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTwoContext questionTwo() throws RecognitionException {
		QuestionTwoContext _localctx = new QuestionTwoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionTwo);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__2);
			setState(61);
			match(NEWLINE);
			setState(62);
			answerTwo();
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63);
					match(NEWLINE);
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerTwoContext extends ParserRuleContext {
		public AnswerTwoTextContext answerTwoText() {
			return getRuleContext(AnswerTwoTextContext.class,0);
		}
		public AnswerTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerTwo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterAnswerTwo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitAnswerTwo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitAnswerTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerTwoContext answerTwo() throws RecognitionException {
		AnswerTwoContext _localctx = new AnswerTwoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_answerTwo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__3);
			setState(70);
			answerTwoText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerTwoTextContext extends ParserRuleContext {
		public AnswerTwoTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerTwoText; }
	 
		public AnswerTwoTextContext() { }
		public void copyFrom(AnswerTwoTextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VerifyAnswerTwoContext extends AnswerTwoTextContext {
		public VerifyAnswerTwoContext(AnswerTwoTextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterVerifyAnswerTwo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitVerifyAnswerTwo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitVerifyAnswerTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerTwoTextContext answerTwoText() throws RecognitionException {
		AnswerTwoTextContext _localctx = new AnswerTwoTextContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_answerTwoText);
		int _la;
		try {
			_localctx = new VerifyAnswerTwoContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 480L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionThreeContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(Req1Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Req1Parser.NEWLINE, i);
		}
		public AnswerThreeContext answerThree() {
			return getRuleContext(AnswerThreeContext.class,0);
		}
		public QuestionThreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionThree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterQuestionThree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitQuestionThree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitQuestionThree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionThreeContext questionThree() throws RecognitionException {
		QuestionThreeContext _localctx = new QuestionThreeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionThree);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__8);
			setState(75);
			match(NEWLINE);
			setState(76);
			answerThree();
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(77);
					match(NEWLINE);
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerThreeContext extends ParserRuleContext {
		public AnswerThreeTextContext answerThreeText() {
			return getRuleContext(AnswerThreeTextContext.class,0);
		}
		public AnswerThreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerThree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterAnswerThree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitAnswerThree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitAnswerThree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerThreeContext answerThree() throws RecognitionException {
		AnswerThreeContext _localctx = new AnswerThreeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_answerThree);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__9);
			setState(84);
			answerThreeText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerThreeTextContext extends ParserRuleContext {
		public AnswerThreeTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerThreeText; }
	 
		public AnswerThreeTextContext() { }
		public void copyFrom(AnswerThreeTextContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VerifyAnswerThreeContext extends AnswerThreeTextContext {
		public List<LanguageContext> language() {
			return getRuleContexts(LanguageContext.class);
		}
		public LanguageContext language(int i) {
			return getRuleContext(LanguageContext.class,i);
		}
		public VerifyAnswerThreeContext(AnswerThreeTextContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterVerifyAnswerThree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitVerifyAnswerThree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitVerifyAnswerThree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerThreeTextContext answerThreeText() throws RecognitionException {
		AnswerThreeTextContext _localctx = new AnswerThreeTextContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_answerThreeText);
		int _la;
		try {
			_localctx = new VerifyAnswerThreeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			language();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(87);
				match(T__10);
				setState(88);
				language();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LanguageContext extends ParserRuleContext {
		public LanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).enterLanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Req1Listener) ((Req1Listener)listener).exitLanguage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Req1Visitor) return ((Req1Visitor<? extends T>)visitor).visitLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageContext language() throws RecognitionException {
		LanguageContext _localctx = new LanguageContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_language);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0010a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0005"+
		"\u0000\u0019\b\u0000\n\u0000\f\u0000\u001c\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000 \b\u0000\n\u0000\f\u0000#\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u00010\b\u0001\n\u0001\f\u00013\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0004\u00039\b\u0003"+
		"\u000b\u0003\f\u0003:\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004A\b\u0004\n\u0004\f\u0004D\t\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0005\u0007O\b\u0007\n\u0007\f\u0007R\t\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0005\tZ\b\t\n\t\f\t]\t\t\u0001\n\u0001"+
		"\n\u0001\n\u0000\u0000\u000b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0000\u0002\u0001\u0000\u0005\b\u0001\u0000\f\u000e]\u0000"+
		"\u0016\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u00044\u0001"+
		"\u0000\u0000\u0000\u00068\u0001\u0000\u0000\u0000\b<\u0001\u0000\u0000"+
		"\u0000\nE\u0001\u0000\u0000\u0000\fH\u0001\u0000\u0000\u0000\u000eJ\u0001"+
		"\u0000\u0000\u0000\u0010S\u0001\u0000\u0000\u0000\u0012V\u0001\u0000\u0000"+
		"\u0000\u0014^\u0001\u0000\u0000\u0000\u0016\u001a\u0003\u0002\u0001\u0000"+
		"\u0017\u0019\u0005\u0010\u0000\u0000\u0018\u0017\u0001\u0000\u0000\u0000"+
		"\u0019\u001c\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000\u0000\u0000"+
		"\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u001d\u0001\u0000\u0000\u0000"+
		"\u001c\u001a\u0001\u0000\u0000\u0000\u001d!\u0003\b\u0004\u0000\u001e"+
		" \u0005\u0010\u0000\u0000\u001f\u001e\u0001\u0000\u0000\u0000 #\u0001"+
		"\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000"+
		"\u0000\"$\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000$(\u0003\u000e"+
		"\u0007\u0000%\'\u0005\u0010\u0000\u0000&%\u0001\u0000\u0000\u0000\'*\u0001"+
		"\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000"+
		")\u0001\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000+,\u0005\u0001"+
		"\u0000\u0000,-\u0005\u0010\u0000\u0000-1\u0003\u0004\u0002\u0000.0\u0005"+
		"\u0010\u0000\u0000/.\u0001\u0000\u0000\u000003\u0001\u0000\u0000\u0000"+
		"1/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u00002\u0003\u0001\u0000"+
		"\u0000\u000031\u0001\u0000\u0000\u000045\u0005\u0002\u0000\u000056\u0003"+
		"\u0006\u0003\u00006\u0005\u0001\u0000\u0000\u000079\u0005\u000f\u0000"+
		"\u000087\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000:;\u0001\u0000\u0000\u0000;\u0007\u0001\u0000\u0000\u0000"+
		"<=\u0005\u0003\u0000\u0000=>\u0005\u0010\u0000\u0000>B\u0003\n\u0005\u0000"+
		"?A\u0005\u0010\u0000\u0000@?\u0001\u0000\u0000\u0000AD\u0001\u0000\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000C\t\u0001\u0000"+
		"\u0000\u0000DB\u0001\u0000\u0000\u0000EF\u0005\u0004\u0000\u0000FG\u0003"+
		"\f\u0006\u0000G\u000b\u0001\u0000\u0000\u0000HI\u0007\u0000\u0000\u0000"+
		"I\r\u0001\u0000\u0000\u0000JK\u0005\t\u0000\u0000KL\u0005\u0010\u0000"+
		"\u0000LP\u0003\u0010\b\u0000MO\u0005\u0010\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Q\u000f\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"ST\u0005\n\u0000\u0000TU\u0003\u0012\t\u0000U\u0011\u0001\u0000\u0000"+
		"\u0000V[\u0003\u0014\n\u0000WX\u0005\u000b\u0000\u0000XZ\u0003\u0014\n"+
		"\u0000YW\u0001\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000\\\u0013\u0001\u0000\u0000\u0000"+
		"][\u0001\u0000\u0000\u0000^_\u0007\u0001\u0000\u0000_\u0015\u0001\u0000"+
		"\u0000\u0000\b\u001a!(1:BP[";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}