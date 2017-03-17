package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import move.DealOneCardMove;
import move.ResetDeckMove;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

public class DeckController extends MouseAdapter{
	private MultiDeck deck;
	private Pile wastePile;
	private Solitaire game;
	private int resetTime = 2;
	
	public DeckController(Solitaire game, MultiDeck deck, Pile wastePile) {
		this.deck = deck;
		this.wastePile = wastePile;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent me){
		Move m = null;
		if(deck.empty()) {
			if(resetTime > 0) {
				m = new ResetDeckMove(deck, wastePile);
				resetTime--;
			}
		}else {
			m = new DealOneCardMove(this.deck, this.wastePile);
		}
		
		if(m != null && m.doMove(game)){
			game.refreshWidgets();
		}
		
	}

}
