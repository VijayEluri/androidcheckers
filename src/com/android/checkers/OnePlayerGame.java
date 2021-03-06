package com.android.checkers;

import android.util.Log;

/**
 * @author dhyanesh
 * 
 * TODO: Need to properly handle undoMove i.e. undo should go back until it is the player's move.
 *
 */
public class OnePlayerGame extends AbstractGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Player player;
	private AbstractBot bot;

	public OnePlayerGame(Player player) {
		super();
		this.player = player;
		bot = new NativeBot(gameCore);

		if (player == Player.BLACK) {
			bot.playBotMove();
		}
	}

	@Override
	public void doMove(int x, int y) {
		doPlayerMove(x, y);
		Log.i("OnePlayerGame", "Player move done.");
		while (player != gameCore.getCurrentPlayer()) {
			Log.i("OnePlayerGame", "Starting bot move.");
			if (!bot.playBotMove()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Log.i("OnePlayerGame", "No more valid bot moves.");
				// There are no more valid moves that the bot can make.
				break;
			}
		}
		Log.i("OnePlayerGame", "Done bot moves.");
	}
}
