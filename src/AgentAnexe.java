import Rule.BooleanRuleBase;
import Rule.Magasin1;
import Rule.RB;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.*;

public class AgentAnexe extends Agent {
    private Map<String, Produit> produitMap = new HashMap<>();
    private ResponseToAC responseToAC;

    private BooleanRuleBase rb = new BooleanRuleBase("magasin");
    private RB r;
    // The GUI by means of which the user can add books in the catalogue

    // Put agent initializations here
    protected void setup() {
        // Create the catalogue


        // Create and show the GUI
//        myGui = new BookSellerGui(this);
//        myGui.showGui();
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String i = (String) args[0];

            List<Produit > produits = Manager.getProducts(Integer.valueOf(i));
            r = Manager.getRB(Integer.valueOf(i));
            for(Produit produit:produits){
                produitMap.put(produit.getNom(),produit);
            }

        }

        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("book-selling");
        sd.setName("JADE-book-trading");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }

        // Add the behaviour serving queries from buyer agents
        addBehaviour(new OfferRequestsServer());

        // Add the behaviour serving purchase orders from buyer agents
        addBehaviour(new PurchaseOrdersServer());
    }

    // Put agent clean-up operations here
    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
//        myGui.dispose();
        // Printout a dismissal message
        System.out.println("Seller-agent "+getAID().getName()+" terminating.");
    }



    /**
     Inner class OfferRequestsServer.
     This is the behaviour used by Book-seller agents to serve incoming requests
     for offer from buyer agents.
     If the requested book is in the local catalogue the seller agent replies
     with a PROPOSE message specifying the price. Otherwise a REFUSE message is
     sent back.
     */
    private class OfferRequestsServer extends CyclicBehaviour {

        public double calculateReduction(List<Produit> produits){
            String[] p = new String[produits.size()];
            int i = 0;
            int qteTotal = 0;
            double prixTotal = 0;
            for(Produit produit:produits){
                p[i++] = produit.getNom();
                qteTotal += produit.getQte();
                prixTotal += produit.getPrix()*produit.getQte();
            }

            r.init(rb,p,qteTotal, prixTotal);
            rb.forwardChain();
            double redaction = Double.valueOf(rb.variableList.get("redaction").getValue());
            return redaction;
        }

        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                // CFP Message received. Process it
                Formulaire formulaire;
                try {
                    formulaire = (Formulaire) msg.getContentObject();
                } catch (UnreadableException e) {
                    throw new RuntimeException(e);
                }
                List<Produit> availableProducts = new ArrayList<>();
                for(Produit produit:formulaire.getProduits()){
                    Produit p = produitMap.get(produit.getNom());
                    if(p != null)
                        if(produit.getQte() <= p.getQte()){
                            availableProducts.add(produit);
                            produit.setPrix(p.getPrix());
                        }

                }
                ACLMessage reply = msg.createReply();
                if(availableProducts.isEmpty()){
                    reply.setPerformative(ACLMessage.REFUSE);
                    reply.setContent("not-available");
                    return;
                }




                responseToAC = new ResponseToAC(availableProducts,calculateReduction(availableProducts));



                    // The requested book is available for sale. Reply with the price
                    reply.setPerformative(ACLMessage.PROPOSE);
                try {
                    reply.setContentObject(responseToAC);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                myAgent.send(reply);
            }



            else {
                block();
            }
        }
    }  // End of inner class OfferRequestsServer

    /**
     Inner class PurchaseOrdersServer.
     This is the behaviour used by Book-seller agents to serve incoming
     offer acceptances (i.e. purchase orders) from buyer agents.
     The seller agent removes the purchased book from its catalogue
     and replies with an INFORM message to notify the buyer that the
     purchase has been sucesfully completed.
     */
    private class PurchaseOrdersServer extends CyclicBehaviour {
        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                // ACCEPT_PROPOSAL Message received. Process it

                ACLMessage reply = msg.createReply();
                for(Produit produit:responseToAC.getAvailableProducts()){
                    Produit p = produitMap.get(produit.getNom());


                    if(p != null){
                        System.out.println("produit a achete");
                        System.out.println(p.getNom());
                        System.out.println("produit qte avant");
                        System.out.println(p.getQte());
                        p.updateQte(produit.getQte());
                        System.out.println("produit qte apres");
                        System.out.println(p.getQte());
                    }

                }
                reply.setPerformative(ACLMessage.INFORM);
                System.out.println("   ");

                myAgent.send(reply);
            }
            else {
                block();
            }
        }
    }  // End of inner class OfferRequestsServer
}
