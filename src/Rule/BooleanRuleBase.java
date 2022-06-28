package Rule;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

public class BooleanRuleBase implements RuleBase {
    String name;
   public Hashtable<String, RuleVariable> variableList = new Hashtable<>();
    Clause[] clauseVarList;
    Vector ruleList = new Vector();

    Vector conclusionVarList = new Vector<>();
    Rule rulePtr;
    Clause clausePtr;
    Stack goalClauseStack = new Stack();
    Hashtable effectors;
    Hashtable sensors;
    Vector factList;
    JTextArea textAreal;

    public BooleanRuleBase(String name) {
        this.name = name;

    }

    public void setVariableValue(String var, String value){
        RuleVariable ruleVariable = variableList.get(var);
       ruleVariable.setValue(value);

    }

    @Override
    public void setDisplay(JTextArea txtArea) {

    }

    @Override
    public void trace(String text) {

    }

    @Override
    public void displayVariables(JTextArea textArea) {

    }

    @Override
    public void displayRules(ITextArea textArea) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void backwardChain(String goalVarName) {

    }

    public void forwardChain() {
        Vector conflictRuleSet = new Vector();

        conflictRuleSet = match(true);
        while (conflictRuleSet.size() > 0) {
            Rule selected = selectRule(conflictRuleSet);
            selected.fire();
            System.out.println(selected.name);
            conflictRuleSet = match(false);
        }
    }

    @Override
    public Vector getGoalVariables() {
        return null;
    }

    public Vector match(boolean test) {
        Vector matchList = new Vector();
        Enumeration enm = ruleList.elements();

        while (enm.hasMoreElements()) {
            Rule testRule = (Rule) enm.nextElement();
            if (test)
                testRule.check();
            if (testRule.truth == null)
                continue;
            if ((testRule.truth.booleanValue() == true) && (testRule.fired == false)) {
                matchList.addElement(testRule);
            }


        }
     //   displayConflictSet(matchList);
        return matchList;
    }

    public Rule selectRule(Vector ruleSet) {
        Enumeration enm = ruleSet.elements();
        long numClauses;
        Rule nextRule;
        Rule bestRule = (Rule) enm.nextElement();
        long max = bestRule.numAntecedents();
        while (enm.hasMoreElements()) {
            nextRule = (Rule) enm.nextElement();

            if ((numClauses = nextRule.numAntecedents()) > max) {
                max = numClauses;
                bestRule = nextRule;
            }


        }
        return bestRule;
    }

    public void addVariable(RuleVariable ruleVariable) {
        variableList.put(ruleVariable.getName(),ruleVariable);
    }
}
