/**
 * 
 */

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import jade.core.Agent;
import jade.util.Logger;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

/**
 * @author Jeremy
 *
 */
public class FirstSimTest extends Agent{

	private static final long serialVersionUID = 1L;

	protected void setup() {
		AgentContainer c = getContainerController();
		try {
//			Logger logger = Logger.getMyLogger(this.getClass().getName());
//			logger.log(Level.SEVERE, "---- Start Simulation "+new Date().toString()+" ----", this);
//
//			AgentController Analyser = c.createNewAgent("Analyser", "sma.tools.analyse.AgentAnalyser", null);
			
			String[] argsA = {"1"};
			String[] argsB = {"2"};
			String[] argsC = {"3"};

			List<Produit> produits = Arrays.asList(new Produit("p1",2), new Produit("p6",1));
			Formulaire formulaire = new Formulaire(produits);
			Produit[] p= new Produit[]{new Produit("p1",2), new Produit("p6",1)};
			System.out.println(p.length);
			AgentController AgentAC = c.createNewAgent("AgentAC", "AgentCentral", p);
			AgentController AgentN1 = c.createNewAgent("AgentN1", "AgentAnexe", argsA);
			AgentController AgentN2 = c.createNewAgent("AgentN2", "AgentAnexe", argsB);
			c.start();
			
			AgentController AgentN3 = c.createNewAgent("AgentN3", "AgentAnexe", argsC);

//			Analyser.start();
			AgentAC.start();
			AgentN1.start();
			AgentN2.start();
			AgentN3.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
			System.out.println("ffffffffffff");
		} catch (ControllerException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String toString() {
		return getLocalName();
	}

}
