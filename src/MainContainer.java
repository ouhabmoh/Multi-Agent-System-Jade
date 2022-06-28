import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;


public class MainContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();

            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");
            Profile profile = new ProfileImpl(properties);

            AgentContainer mainContainer = runtime.createMainContainer(profile);
            Produit[] produits = new Produit[]{new Produit("p1", 2), new Produit("p2", 1)};
            AgentController agentController = null;

            agentController = mainContainer.createNewAgent("AC", "AgentCentral", produits);
            String[] arg1 = {"1"};
            AgentController agentController1 = mainContainer.createNewAgent("AN1", "AgentAnexe", arg1);
//            String[] arg2 = {"2"};
//            AgentController agentController2 = mainContainer.createNewAgent("AN2", "AgentAnexe", arg2);
//            String[] arg3 = {"3"};
//            AgentController agentController3 = mainContainer.createNewAgent("AN3", "AgentAnexe", arg3);
//
            mainContainer.start();

            agentController1.start();
            agentController.start();
//            agentController2.start();
//            agentController3.start();

        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        } catch (ControllerException e) {
            throw new RuntimeException(e);
        }
    }
}
