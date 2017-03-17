package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import move.ReserveToUpFoundationMove;
import move.WasteToUpFoundationMove;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class UpPileController extends MouseAdapter{
	private Solitaire game;
	private Pile upPile;
	public UpPileController(Solitaire game, Pile upPile) {
		this.game = game;
		this.upPile = upPile;
	}
	
	public void mouseReleased(MouseEvent me) {
		Container c = game.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) return;

		/** Must be the CardView widget. */
		CardView cardView = (CardView) w;
		Card theCard = (Card) cardView.getModelElement();

		/** Recover the From Pile */
		PileView fromPileView = (PileView) c.getDragSource();
		Pile fromPile = (Pile) fromPileView.getModelElement();
		
		Move m = null;
		if(fromPile.getName().startsWith("waste")) {
			m = new WasteToUpFoundationMove(fromPile, theCard, upPile);
		}else {
			m = new ReserveToUpFoundationMove(fromPile, theCard, upPile);
		}
		// Determine the To Pile
		// Try to make the move
		if(!m.doMove(game)) {
			fromPileView.returnWidget (cardView);
		}

		// Since we could be released over a widget, or over the container, 
		// we must repaintAll() clipped to the region we are concerned about.
		// first refresh the last known position of the card being dragged.
		// then the widgets.
		game.refreshWidgets(); 

		// release the dragging object since the move is now complete (this 
		// will reset container's dragSource).
		c.releaseDraggingObject();
		c.repaint();
	}
	
}
