package Rule;

public class Magasin1 implements RB{
    public void init(BooleanRuleBase rb, String[] produits, int qteT, double prixTotale){
        RuleVariable pr = new RuleVariable(rb, "produits");
        String l = String.join(" ",produits);
        System.out.println(l);
        pr.setLabels(l);


        RuleVariable p1 = new RuleVariable(rb, "p1");
        p1.setLabels("p1");

        RuleVariable p2 = new RuleVariable(rb, "p2");
        p1.setLabels("p2");


        RuleVariable qte = new RuleVariable(rb, "qte");
        qte.setLabels("2 3 4 5 6 7 8 9 10");


        RuleVariable prix = new RuleVariable(rb,"prix");


        RuleVariable redaction = new  RuleVariable(rb, "redaction");
        redaction.setLabels("0.1 0.2 0.3 0.4 0.5");
        redaction.setValue("0");
        Condition cEquals = new Condition("=");
        Condition cNotEquals = new Condition("!=");
        Condition cLessThan = new Condition("<");
        Condition cMore = new Condition(">");

        Rule r1 = new Rule(rb, "promo1",
                new Clause[]{
                        new Clause(pr, cEquals, "p1"),
                        new Clause(pr, cEquals, "p2")},
                new Clause(redaction, cEquals, "0.1"));

//        Rule r2 = new Rule(rb, "promo2",
//                new Clause(qte, cLessThan, String.valueOf(qteT)),
//                new Clause(redaction, cEquals, "0.2"));
//
//        Rule r3 = new Rule(rb, "promo3",
//                new Clause(prix, cLessThan, String.valueOf(prixTotale)),
//                new Clause(redaction, cEquals, "0.3"));

        pr.setValue(l);
        p1.setValue("p1");
        p2.setValue("p2");
        qte.setValue("5");
        prix.setValue("1000");
    }
}
