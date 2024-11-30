package jobs4u.app.language.grammar.presentation.languagePlugin.menu;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.InterviewPluginUI;
import jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.RequirementPluginUI;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguagePluginMenu extends AbstractUI {

    @Autowired
    private Menu languagePluginMenu;
    @Autowired
    private InterviewPluginUI interviewPluginUI;
    @Autowired
    private RequirementPluginUI requirementPluginUI;

    @Override
    protected boolean doShow() {
        buildMenu();
        languagePluginMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Language Plugin menu";
    }

    private void buildMenu() {
        languagePluginMenu.addOption(interviewPluginUI);
        languagePluginMenu.addOption(requirementPluginUI);
    }

}
