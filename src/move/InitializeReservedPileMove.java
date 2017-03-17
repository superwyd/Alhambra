package move;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

public class InitializeReservedPileMove extends Move{
	
	private Solitaire game;
	
	public InitializeReservedPileMove(Solitaire game) {
		this.game = game;
		
	}
	@Override
	public boolean doMove(Solitaire game) {
		Pile upPile1 = (Pile)game.getModelElement("upPile1");
		Pile upPile2 = (Pile)game.getModelElement("upPile2");
		Pile upPile3 = (Pile)game.getModelElement("upPile3");
		Pile upPile4 = (Pile)game.getModelElement("upPile4");
		Pile downPile1 = (Pile)game.getModelElement("downPile1");
		Pile downPile2 = (Pile)game.getModelElement("downPile2");
		Pile downPile3 = (Pile)game.getModelElement("downPile3");
		Pile downPile4 = (Pile)game.getModelElement("downPile4");
		Pile reservedPile1 = (Pile)game.getModelElement("reservePile1");
		Pile reservedPile2 = (Pile)game.getModelElement("reservePile2");
		Pile reservedPile3 = (Pile)game.getModelElement("reservePile3");
		Pile reservedPile4 = (Pile)game.getModelElement("reservePile4");
		Pile reservedPile5 = (Pile)game.getModelElement("reservePile5");
		Pile reservedPile6 = (Pile)game.getModelElement("reservePile6");
		Pile reservedPile7 = (Pile)game.getModelElement("reservePile7");
		Pile reservedPile8 = (Pile)game.getModelElement("reservePile8");
		Pile wastePile = (Pile) game.getModelElement("wastePile");
		MultiDeck deck = (MultiDeck)game.getModelElement("deck");
		Card a1 = new Card(Card.ACE, Card.SPADES);
		Card a2 = new Card(Card.ACE, Card.HEARTS);
		Card a3 = new Card(Card.ACE, Card.DIAMONDS);
		Card a4 = new Card(Card.ACE, Card.CLUBS);
		
		Card k1 = new Card(Card.KING, Card.SPADES);
		Card k2 = new Card(Card.KING, Card.HEARTS);
		Card k3 = new Card(Card.KING, Card.DIAMONDS);
		Card k4 = new Card(Card.KING, Card.CLUBS);
		
		upPile1.add(a1);
		upPile2.add(a2);
		upPile3.add(a3);
		upPile4.add(a4);
		
		downPile1.add(k1);
		downPile2.add(k2);
		downPile3.add(k3);
		downPile4.add(k4);
		
		for(int i = 0; i < 4; i++) {
			reservedPile1.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile2.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile3.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile4.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile5.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile6.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile7.add(deck.get());
		}
		
		for(int i = 0; i < 4; i++) {
			reservedPile8.add(deck.get());
		}
		
		wastePile.add(deck.get());
		game.updateNumberCardsLeft(-33);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean valid(Solitaire game) {
		// TODO Auto-generated method stub
		return true;
	}

}
