package ywang8;

import controller.DeckController;
import controller.DownPileController;
import controller.ReservePileController;
import controller.UpPileController;
import controller.WastePileController;
import move.InitializeReservedPileMove;
import heineman.Klondike;
import ks.common.games.Solitaire;
import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
import ks.common.view.CardImages;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

public class Alhambra extends Solitaire {

	MultiDeck deck;
	DeckView deckView;

	Pile wastePile;
	PileView wasteView;

	Pile[] reservePiles; //should be 8
	PileView[] reservePileView; //middle
	
	Pile[] upPiles; //should be 4
	PileView[] upView; //start from a
	
	Pile[] downPiles; //should be 4
	PileView[] downView; //start from k

	IntegerView scoreView;
	IntegerView numLeftView;
	
	
	
	
	@Override
	public String getName() {
		return "ywang8_Alhambra";
	}

	@Override
	public boolean hasWon() {
		return false;
	}

	@Override
	public void initialize() {
		// initialize model
		initializeModel(getSeed());
		initializeView();
		initializeControllers();

		// prepare game by dealing facedown cards to all columns, then one face up
		updateScore(0);
		updateNumberCardsLeft (+104);
		new InitializeReservedPileMove(this).doMove(this);
		

	}

	private void initializeControllers() {
		// TODO Auto-generated method stub
		this.deckView.setMouseAdapter(new DeckController(this, this.deck, this.wastePile));
		this.wasteView.setMouseAdapter(new WastePileController(this, this.wasteView));
		for(PileView p: this.reservePileView){
			p.setMouseAdapter(new ReservePileController(this, p));
		}
		
		for(PileView p: this.upView) {
			p.setMouseAdapter(new UpPileController(this,(Pile)p.getModelElement()));
		}
		
		for(PileView p: this.downView) {
			p.setMouseAdapter(new DownPileController(this, (Pile)p.getModelElement()));
		}
	}

	private void initializeView() {

		CardImages ci = getCardImages();
		int cardWidth = ci.getWidth();
		int cardHeight = ci.getHeight();
		
		this.upView = new PileView[4];
		for(int i = 0; i < upView.length;i++) {
			this.upView[i] = new PileView(this.upPiles[i]);
			this.upView[i].setBounds(20+i*20+i*cardWidth, 20, cardWidth, cardHeight);
			this.addViewWidget(this.upView[i]);
		}
		
		this.downView = new PileView[4];
		for(int i = 0; i < downView.length;i++) {
			this.downView[i] = new PileView(this.downPiles[i]);
			this.downView[i].setBounds(35+(i+4)*20+(i+4)*cardWidth, 20, cardWidth, cardHeight);
			this.addViewWidget(this.downView[i]);
		}
		
		this.reservePileView = new PileView[8];
		for(int i = 0; i < reservePileView.length;i++) {
			this.reservePileView[i] = new PileView(this.reservePiles[i]);
			this.reservePileView[i].setBounds(20+i*20+i*cardWidth, 40+cardHeight, cardWidth, cardHeight);
			this.addViewWidget(this.reservePileView[i]);
		}
		
		// deck
		deckView = new DeckView (deck);
		deckView.setBounds (80+4*ci.getWidth(),120+2*ci.getHeight(), ci.getWidth(), ci.getHeight());
		this.addViewWidget(deckView);

		// waste pile
		wasteView = new PileView (wastePile);
		wasteView.setBounds (100+5*ci.getWidth(),120+2*ci.getHeight(),ci.getWidth(),ci.getHeight());
		this.addViewWidget(wasteView);

		// score
		scoreView = new IntegerView (getScore());
		scoreView.setFontSize(14);
		scoreView.setBounds (20,150+3*ci.getHeight(), 100, 60);
		container.addWidget (scoreView);

		// numleft
		numLeftView = new IntegerView (getNumLeft());
		numLeftView.setFontSize (14);
		numLeftView.setBounds (140, 150+3*ci.getHeight(), 100, 60);
		container.addWidget (numLeftView);
		
		
	}

	private void initializeModel(int seed) {
		deck = new MultiDeck("deck", 2);
		deck.create(seed);
		
		this.upPiles = new Pile[4];
		this.downPiles = new Pile[4];
		this.reservePiles = new Pile[8];
		
		this.upPiles[0] = new Pile("upPile1");
		this.upPiles[1] = new Pile("upPile2");
		this.upPiles[2] = new Pile("upPile3");
		this.upPiles[3] = new Pile("upPile4");
		

		this.downPiles[0] = new Pile("downPile1");
		this.downPiles[1] = new Pile("downPile2");
		this.downPiles[2] = new Pile("downPile3");
		this.downPiles[3] = new Pile("downPile4");
		
		this.reservePiles[0] = new Pile("reservePile1");
		this.reservePiles[1] = new Pile("reservePile2");
		this.reservePiles[2] = new Pile("reservePile3");
		this.reservePiles[3] = new Pile("reservePile4");
		this.reservePiles[4] = new Pile("reservePile5");
		this.reservePiles[5] = new Pile("reservePile6");
		this.reservePiles[6] = new Pile("reservePile7");
		this.reservePiles[7] = new Pile("reservePile8");
		
		this.wastePile = new Pile("wastePile");
		
		//add waste pile
		this.addModelElement(this.wastePile);
		//add deck
		this.addModelElement(deck);
		//add up piles
		for(Pile p: this.upPiles) {
			this.addModelElement(p);
		}
		//add down piles
		for(Pile p: this.downPiles) {
			this.addModelElement(p);
		}
		//add reserve piles
		for(Pile p: this.reservePiles) {
			this.addModelElement(p);
		}
	}
	
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new Alhambra(), Deck.OrderBySuit);
	}

}
