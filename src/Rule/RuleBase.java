package Rule;

import java.util.Vector;

public interface RuleBase {
    void setDisplay (JTextArea txtArea);
    void trace (String text);
    void displayVariables (JTextArea textArea);
    void displayRules (ITextArea textArea);
    void reset();
    void backwardChain(String goalVarName);
    void forwardChain();
    Vector getGoalVariables();


}
