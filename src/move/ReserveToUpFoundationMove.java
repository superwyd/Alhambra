package move;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;

public class ReserveToUpFoundationMove extends Move {
	private Pile from;
	private Pile to;
	private Card movingCard;
	
	public ReserveToUpFoundationMove(Pile from, Card c, Pile to) {
		this.from = from;
		this.movingCard = c;
		this.to = to;
	}
	@Override
	public boolean doMove(Solitaire game) {
		// TODO Auto-generated method stub
		if(!valid(game)) return false;
		to.add(movingCard);
		game.updateScore(20);

		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean valid(Solitaire game) {
		Card topCard = to.peek();
		if(!topCard.sameSuit(movingCard)) {
			return false;
		}
		if( !(topCard.getRank()+1 == movingCard.getRank()) ) {
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

}
