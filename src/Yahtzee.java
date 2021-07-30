
public class Yahtzee {

	// generate necessary queues and variables
	CircularQueue q1 = new CircularQueue(100);
	CircularQueue q2 = new CircularQueue(100);
	CircularQueue player1 = new CircularQueue(100);
	CircularQueue player2 = new CircularQueue(100);
	CircularQueue tempq1 = new CircularQueue(100);
	CircularQueue tempq2 = new CircularQueue(100);

	int score1 = 0, score2 = 0;

	Yahtzee() {

		int turn = 0;
		do {

			turn++;
			System.out.println("Turn : " + turn);
			System.out.println();

			// call method to add numbers to player queues
			addNumbersToPlayers();

			// call method to print player queues
			printPlayers();

			System.out.println("------------------------------------------------------------------------\n");

			if (turn < 15) {

				// if both rules are available to apply, apply Yahtzee first
				// After applying the rules, place of the remaining numbers may change

				// call method for rule 1 - Yahtzee
				applyYahtzeeRule();
				// call method for rule 2 - Large Straight
				applyLargeStraightRule();
			}

		} while (turn != 15);

		if (score1 > score2)
			System.out.println("Game Over. Player 1 is the winner.");
		else if (score1 < score2)
			System.out.println("Game Over. Player 2 is the winner.");
		else
			System.out.println("Game Over. It's a draw.");

	}

	// add random numbers (from 1 to 6) to player queues
	public void addNumbersToPlayers() {

		for (int i = 1; i <= 3; i++) {

			player1.enqueue((int) (Math.random() * 6 + 1));

		}
		for (int i = 1; i <= 3; i++) {

			player2.enqueue((int) (Math.random() * 6 + 1));

		}

	}

	// print players and scores
	public void printPlayers() {

		// print Player 1
		System.out.print("Player 1 : ");

		for (int i = 0; i < player1.size(); i++) {

			System.out.print(player1.Peek() + " ");
			player1.enqueue(player1.dequeue());

		}
		// keep score1 in same location on console
		for (int i = 0; i < 25 - player1.size(); i++)
			System.out.print("  ");
		System.out.println("Score : " + score1);
		System.out.println();

		// print Player 2
		System.out.print("Player 2 : ");
		for (int i = 0; i < player2.size(); i++) {

			System.out.print(player2.Peek() + " ");
			player2.enqueue(player2.dequeue());

		}
		// keep score2 in same location on console
		for (int i = 0; i < 25 - player2.size(); i++)
			System.out.print("  ");
		System.out.println("Score : " + score2);
		System.out.println();

	}

	// rule 1 - Yahtzee Rule
	public void applyYahtzeeRule() {

		// apply Yahtzee for Player 1
		int size1 = player1.size();
		for (int i = 0; i < size1; i++) {

			int compare1 = (int) player1.Peek();
			int same1 = 0;

			int tempSize1 = player1.size();
			for (int j = 0; j < tempSize1; j++) {

				if (compare1 == (int) player1.Peek()) {
					same1++;
				}
				player1.enqueue(player1.dequeue());

			}
			if (same1 >= 4) {

				int kick1 = 4;
				while (kick1 > 0) {

					if (compare1 == (int) player1.Peek()) {
						player1.dequeue();
						kick1--;
					} else
						player1.enqueue(player1.dequeue());

				}
				score1 += 20;

			} else
				player1.enqueue(player1.dequeue());

		}

		// apply Yahtzee for Player 2
		int size2 = player2.size();
		for (int i = 0; i < size2; i++) {

			int compare2 = (int) player2.Peek();
			int same2 = 0;

			int tempSize2 = player2.size();
			for (int j = 0; j < tempSize2; j++) {

				if (compare2 == (int) player2.Peek()) {
					same2++;
				}
				player2.enqueue(player2.dequeue());

			}
			if (same2 >= 4) {

				int kick2 = 4;
				while (kick2 > 0) {

					if (compare2 == (int) player2.Peek()) {
						player2.dequeue();
						kick2--;
					} else
						player2.enqueue(player2.dequeue());

				}
				score2 += 20;

			} else
				player2.enqueue(player2.dequeue());

		}

	}

	// rule 2 - Large Straight Rule
	public void applyLargeStraightRule() {

		// apply Large Straight for Player 1
		int size1 = player1.size();
		for (int i = 0; i < size1; i++) {

			q1.enqueue(player1.Peek());
			player1.enqueue(player1.dequeue());

		}

		while (!q1.isEmpty()) {
			int qNumber = (Integer) q1.dequeue();
			int count = q1.size();
			for (int i = 0; i < count; i++) {
				if (qNumber == (Integer) q1.Peek())
					q1.dequeue();
				else
					q1.enqueue(q1.dequeue());
			}
			tempq1.enqueue(qNumber);
		}
		int distinctNumbers = tempq1.size();

		if (distinctNumbers == 6) {

			int kickDistincts = 1;
			while (kickDistincts <= 6) {
				if ((int) player1.Peek() == kickDistincts) {
					player1.dequeue();
					kickDistincts++;
				} else
					player1.enqueue(player1.dequeue());
			}
			score1 += 10;

		}
		while (!tempq1.isEmpty())
			tempq1.dequeue();

		// apply Large Straight for Player 2
		int size2 = player2.size();
		for (int i = 0; i < size2; i++) {

			q2.enqueue(player2.Peek());
			player2.enqueue(player2.dequeue());

		}

		while (!q2.isEmpty()) {
			int qNumber = (Integer) q2.dequeue();
			int count = q2.size();
			for (int i = 0; i < count; i++) {
				if (qNumber == (Integer) q2.Peek())
					q2.dequeue();
				else
					q2.enqueue(q2.dequeue());
			}
			tempq2.enqueue(qNumber);
		}
		int distinctNumbers2 = tempq2.size();

		if (distinctNumbers2 == 6) {

			int kickDistincts = 1;
			while (kickDistincts <= 6) {
				if ((int) player2.Peek() == kickDistincts) {
					player2.dequeue();
					kickDistincts++;
				} else
					player2.enqueue(player2.dequeue());
			}
			score2 += 10;

		}
		while (!tempq2.isEmpty())
			tempq2.dequeue();

	}

}
