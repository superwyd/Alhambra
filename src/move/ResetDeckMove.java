package move;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

public class ResetDeckMove extends Move {
	private MultiDeck deck;
	private Pile wastePile;
	
	public ResetDeckMove(MultiDeck d, Pile p) {
		this.deck = d;
		this.wastePile = p;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		// TODO Auto-generated method stub
		if(!valid(game)) return false;
		deck.push(wastePile);
		wastePile.removeAll();
		
		game.getNumLeft().setValue(deck.count());
		game.refreshWidgets();
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
		if(deck.empty()) {
			return true;
		}
		return false;
	}

}
