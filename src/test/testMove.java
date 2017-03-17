package test;

import ywang8.Alhambra;
import controller.DeckController;
import controller.DownPileController;
import controller.ReservePileController;
import controller.UpPileController;
import controller.WastePileController;
import move.DealOneCardMove;
import move.InitializeReservedPileMove;
import move.ReserveToDownFoundationMove;
import move.ReserveToUpFoundationMove;
import move.ReserveToWasteMove;
import move.ResetDeckMove;
import move.WasteToDownFoundationMove;
import move.WasteToUpFoundationMove;
import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

public class testMove extends TestCase{
	private GameWindow gw;
	private Solitaire sol;

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
	
	public class Dummy extends Solitaire {

		
		
		
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
			

		}

		private void initializeControllers() {
			// TODO Auto-generated method stub
			deckView.setMouseAdapter(new DeckController(this, deck, wastePile));
			wasteView.setMouseAdapter(new WastePileController(this, wasteView));
			for(PileView p: reservePileView){
				p.setMouseAdapter(new ReservePileController(this, p));
			}
			
			for(PileView p: upView) {
				p.setMouseAdapter(new UpPileController(this,(Pile)p.getModelElement()));
			}
			
			for(PileView p: downView) {
				p.setMouseAdapter(new DownPileController(this, (Pile)p.getModelElement()));
			}
		}

		private void initializeView() {

			CardImages ci = getCardImages();
			int cardWidth = ci.getWidth();
			int cardHeight = ci.getHeight();
			
			upView = new PileView[4];
			for(int i = 0; i < upView.length;i++) {
				upView[i] = new PileView(upPiles[i]);
				upView[i].setBounds(20+i*20+i*cardWidth, 20, cardWidth, cardHeight);
				addViewWidget(upView[i]);
			}
			
			downView = new PileView[4];
			for(int i = 0; i < downView.length;i++) {
				downView[i] = new PileView(downPiles[i]);
				downView[i].setBounds(35+(i+4)*20+(i+4)*cardWidth, 20, cardWidth, cardHeight);
				addViewWidget(downView[i]);
			}
			
			reservePileView = new PileView[8];
			for(int i = 0; i < reservePileView.length;i++) {
				reservePileView[i] = new PileView(reservePiles[i]);
				reservePileView[i].setBounds(20+i*20+i*cardWidth, 40+cardHeight, cardWidth, cardHeight);
				addViewWidget(reservePileView[i]);
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
			
			upPiles = new Pile[4];
			downPiles = new Pile[4];
			reservePiles = new Pile[8];
			
			upPiles[0] = new Pile("upPile1");
			upPiles[1] = new Pile("upPile2");
			upPiles[2] = new Pile("upPile3");
			upPiles[3] = new Pile("upPile4");
			

			downPiles[0] = new Pile("downPile1");
			downPiles[1] = new Pile("downPile2");
			downPiles[2] = new Pile("downPile3");
			downPiles[3] = new Pile("downPile4");
			
			reservePiles[0] = new Pile("reservePile1");
			reservePiles[1] = new Pile("reservePile2");
			reservePiles[2] = new Pile("reservePile3");
			reservePiles[3] = new Pile("reservePile4");
			reservePiles[4] = new Pile("reservePile5");
			reservePiles[5] = new Pile("reservePile6");
			reservePiles[6] = new Pile("reservePile7");
			reservePiles[7] = new Pile("reservePile8");
			
			wastePile = new Pile("wastePile");
			
			//add waste pile
			this.addModelElement(wastePile);
			//add deck
			this.addModelElement(deck);
			//add up piles
			for(Pile p: upPiles) {
				this.addModelElement(p);
			}
			//add down piles
			for(Pile p: downPiles) {
				this.addModelElement(p);
			}
			//add reserve piles
			for(Pile p: reservePiles) {
				this.addModelElement(p);
			}
		}

	}

	
	protected void setUp() {
		// Seed is to ensure we get the same initial cards every time.
		gw = Main.generateWindow(sol = new Dummy(), 117);
	}
	
	protected void tearDown() {
		gw.setVisible(false);
		sol = null;
		gw.dispose();		
	}
	public void testDealOneCardMove() {
		new DealOneCardMove(deck, wastePile).doMove(sol);
		assertEquals(1,wastePile.count());
	}
	
	public void testInitializeReservedPileMove(){
		new InitializeReservedPileMove(sol).doMove(sol);
		assertEquals(4, this.reservePiles[0].count());
		assertEquals(4, this.reservePiles[1].count());
		assertEquals(4, this.reservePiles[2].count());
		assertEquals(4, this.reservePiles[3].count());
		assertEquals(4, this.reservePiles[4].count());
		assertEquals(4, this.reservePiles[5].count());
		assertEquals(4, this.reservePiles[6].count());
		assertEquals(4, this.reservePiles[7].count());
	}
	
	public void testReserveToDownFoundationMove(){
		new InitializeReservedPileMove(sol).doMove(sol);
		int suit = this.upPiles[0].peek().getSuit();
		new ReserveToDownFoundationMove(this.reservePiles[0], new Card(Card.QUEEN, suit),this.downPiles[0]).doMove(sol);
		assertEquals(new Card(Card.QUEEN, suit), this.downPiles[0].peek());
	}
	
	public void testReserveToUpFoundationMove() {
		new InitializeReservedPileMove(sol).doMove(sol);
		int suit = this.upPiles[0].peek().getSuit();
		new ReserveToUpFoundationMove(this.reservePiles[0], new Card(Card.TWO, suit),this.upPiles[0]).doMove(sol);
		assertEquals(new Card(Card.TWO, suit), this.upPiles[0].peek());
	}
	
	public void testReserveToWasteMove() {
		new InitializeReservedPileMove(sol).doMove(sol);
		Card tempCard =  this.wastePile.peek();
		int suit = tempCard.getSuit();
		int rank = tempCard.getRank()+1;
		new ReserveToWasteMove(this.reservePiles[0], new Card(rank, suit),this.wastePile).doMove(sol);
		assertEquals(new Card(rank, suit), this.wastePile.peek());
	}
	
	public void testResetDeckMove() {
		new InitializeReservedPileMove(sol).doMove(sol);
		new ResetDeckMove(deck, wastePile).doMove(sol);
		assertEquals(1, wastePile.count());
	}
	
	public void testWasteToDownFoundationMove() {
		new InitializeReservedPileMove(sol).doMove(sol);
		int suit = this.upPiles[0].peek().getSuit();
		new WasteToDownFoundationMove(this.wastePile, new Card(Card.QUEEN, suit),this.downPiles[0]).doMove(sol);
		assertEquals(new Card(Card.QUEEN, suit), this.downPiles[0].peek());
	}
	
	public void testWasteToUpFoundationMove() {
		new InitializeReservedPileMove(sol).doMove(sol);
		int suit = this.upPiles[0].peek().getSuit();
		new WasteToUpFoundationMove(this.reservePiles[0], new Card(Card.TWO, suit),this.upPiles[0]).doMove(sol);
		assertEquals(new Card(Card.TWO, suit), this.upPiles[0].peek());
	}
	
}
