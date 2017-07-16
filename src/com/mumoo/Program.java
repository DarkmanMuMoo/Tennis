package com.mumoo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Program {

	public static void main(String[] args) throws URISyntaxException, IOException {
		Program program = new Program();
		Path path = Paths.get(program.getClass().getClassLoader().getResource("input.txt").toURI());
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				String[] tupple = line.trim().split(":");
				program.play(tupple[0], tupple[1]);

			});
		}

	}

	private int getPoint(int player) {
		return player <= 15 ? player + 15 : player + 10;
	}

	private boolean isWin(int player1, int player2) {

		return player1 > 40 && (player1 - player2 >= 20);
	}

	private String showScore(int player) {
		return player <= 40 ? player + "" : "ADV";
	}

	private boolean isDuce(int player1, int player2) {

		return player1 >= 40 && player2 >= 40 && player1 == player2;
	}

	private void play(String game, String log) {
		System.out.println(game);
		int A = 0;
		int B = 0;
		for (char whoGotPoint : log.toCharArray()) {

			if (whoGotPoint == 'A') {
				A = this.getPoint(A);
				System.out.println(whoGotPoint + ": " + (this.isWin(A, B) ? "WIN" : showScore(A)));
			} else {
				B = this.getPoint(B);
				System.out.println(whoGotPoint + ": " + (this.isWin(B, A) ? "WIN" : showScore(B)));
			}
			if (this.isDuce(A, B)) {
				System.out.println("DUCE");
			}
		}

	}

}
