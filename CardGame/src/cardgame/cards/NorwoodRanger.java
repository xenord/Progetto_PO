/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fecarrar
 */
public class NorwoodRanger implements Card {
    /*implemento i metodi di card e quindi creo la carta*/
    public String name() { return "Norwood Ranger";} /* nome della creatura*/
    
    public String type() { return "creature";} /* tipo della creatura*/
    
    public String rule_text() /*descrizione della carta*/
    { return "The song of the forest is in perfect harmony.If a single note is out of place, the elves will find its source.";}
    
    public boolean isInstant() { return false;} /*questa carta non è istantantea*/
    
    public String toString() { return name() + "[" + rule_text() + "]";} /*ridefinisco toString() ritornando il testo della carta*/
    
    public Effect get_effect(Player owner) { return new NorwoodRangerEffect(owner,this);}/*restituisco l'effetto della carta*/
    
    private class NorwoodRangerEffect extends AbstractCreatureCardEffect
    { /*creo l'effetto della carta*/
        public NorwoodRangerEffect(Player p,Card c) {super(p,c);}/* il costruttore eredita i parametri di AbstractCreatureCardEffect*/
        
        protected Creature create_creature() { return new NorwoodRangerCreature(owner);}/*il seguente metodo restituisce l'effetto di invocare (posizionare in campo) la carta*/
    }
    
    private class NorwoodRangerCreature extends AbstractCreature
    {
        /*creo la creatura implementando i metodi di AbstractCreature*/
        public String name() { return "Norwood Ranger";}/* nome della creatura*/
        public void attack() {}/*il metodo mi dice che la carta di attaccare durante la fase di combat (da implementare)*/
        public void defend(Creature c) {}/*la creatura si difende (da implementare)*/
        public int get_power() {return 2;}/*attacco della creatura*/
        public int get_toughness() {return 1;}/*resistenza della creatura*/
        
        public List<Effect> effects() {return all_effects;}/*effetti della creatura*/
        public List<Effect> avaliable_effects() {return (is_tapped)?tap_effects:all_effects;}/*effetti disponibili della creatura */
        
        ArrayList<Effect> all_effects= new ArrayList<>();/*lista degli effetti della creatura*/
        ArrayList<Effect> tap_effects= new ArrayList<>();/*lista degli effetti della creatura quando è tapped*/
    
        NorwoodRangerCreature(Player owner)/*costruttore della creatura*/
        {
            super(owner);/*eredito il costruttore*/
            all_effects.add(new Effect() /*uso la classe anonima per creare un nuovo effetto*/
            {
                public boolean play()
                {
                    CardGame.instance.get_stack().add(this);/* aggiungo l'effetto di invocare la creatura nello stack*/
                    return tap();
                    
                }
                
                public void resolve(){}/*risoluzione degli effetti nello stack*/
                public String toString() {return "Norwood Ranger non ha effetti";}/* descrizione degli effetti*/
            }
            );
        }
    }    
}
