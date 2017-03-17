package move;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

public class DealOneCardMove extends Move{
	
	private MultiDeck deck;
	private Pile wastePile;
	
	public DealOneCardMove(MultiDeck deck, Pile wastePile) {
		this.deck = deck;
		this.wastePile = wastePile;
	}
	@Override
	public boolean doMove(Solitaire game) {
		// TODO Auto-generated method stub
		// VALIDATE:
        if (valid(game) == false)
            return false;
        
		this.wastePile.add(deck.get());
		game.updateNumberCardsLeft(-1);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		// TODO Auto-generated method stu
		return false;
	}

	@Override
	public boolean valid(Solitaire game) {
		if(!deck.empty()) {
			return true;
		}
		return false;
	}
	
}
