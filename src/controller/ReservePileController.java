package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vfinal.MoveCardMove;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class ReservePileController extends MouseAdapter {
	private Solitaire game;
	private PileView reservePileView;
	
	public ReservePileController(Solitaire game, PileView p) {
		this.game = game;
		this.reservePileView = p;
	}
	
	public void mousePressed(MouseEvent me){
		// Ask PileView to retrieve the top card as a CardView
				// Widget
		CardView cardView = reservePileView.getCardViewForTopCard(me);

		// Have container track this object now. Record where it came from
		Container c = game.getContainer();
		c.setActiveDraggingObject (cardView, me);
		c.setDragSource(this.reservePileView);

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		reservePileView.redraw();
	}
	
	public void mouseReleased(MouseEvent me) {
		Container c = game.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) return;

		/** Must be the CardView widget. */
		CardView cardView = (CardView) w;
		//Card theCard = (Card) cardView.getModelElement();

		/** Recover the From Pile */
		PileView fromPileView = (PileView) c.getDragSource();
		//Pile fromPile = (Pile) fromPileView.getModelElement();

		// Determine the To Pile
		// Try to make the move
		fromPileView.returnWidget (cardView);

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
