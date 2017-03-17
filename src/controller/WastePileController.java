package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import move.ReserveToWasteMove;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class WastePileController extends MouseAdapter {
	private Solitaire game;
	private PileView wastePileView;
	
	public WastePileController(Solitaire game, PileView p) {
		this.game = game;
		this.wastePileView = p;
	}
	
	public void mousePressed(java.awt.event.MouseEvent me) {
		// Ask PileView to retrieve the top card as a CardView
		// Widget
		CardView cardView = wastePileView.getCardViewForTopCard(me);

		// Have container track this object now. Record where it came from
		Container c = game.getContainer();
		c.setActiveDraggingObject (cardView, me);
		c.setDragSource(this.wastePileView);

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		wastePileView.redraw();
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

		// Determine the To Pile
		Pile toPile = (Pile) this.wastePileView.getModelElement();

		// Try to make the move
		Move m = new ReserveToWasteMove (fromPile, theCard, toPile);
		if (!m.doMove (game)) {
			// invalid move! Return to the pile from whence it came.
			// Rely on the ability of each Widget to support this method.
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
