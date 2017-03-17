package move;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;

public class ReserveToWasteMove extends Move {
	private Pile from;
	private Pile to;
	private Card movingCard;
	
	public ReserveToWasteMove(Pile from, Card c, Pile to) {
		this.from = from;
		this.movingCard = c;
		this.to = to;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if(!valid(game)) {
			return false;
		}
		// TODO Auto-generated method stub
		this.to.add(movingCard);
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
		Pile waste = (Pile) game.getModelElement("wastePile");
		Card top = waste.peek();
		if(!top.sameSuit(movingCard)) {
			return false;
		}
		
		int topRank = top.getRank();
		int movingCardRank = movingCard.getRank();
		if(!(Math.abs(topRank - movingCardRank) == 1)) {
			return false;
		}
		return true;
	}

}
