package com.itec_4550.tictactoe;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GameLogic gameLogic;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLogic = new GameLogic();

        // Set up button listeners for the grid
        int[][] buttonIds = {
                {R.id.button1, 0, 0},
                {R.id.button2, 0, 1},
                {R.id.button3, 0, 2},
                {R.id.button4, 1, 0},
                {R.id.button5, 1, 1},
                {R.id.button6, 1, 2},
                {R.id.button7, 2, 0},
                {R.id.button8, 2, 1},
                {R.id.button9, 2, 2}
        };

        for (int[] buttonId : buttonIds) {
            findViewById(buttonId[0]).setOnClickListener(v -> makeMove(buttonId[1], buttonId[2]));
        }

        // Initialize the new game button
        findViewById(R.id.new_game_button).setOnClickListener(v -> resetGame());
    }

    private void makeMove(int row, int col) {
        if (gameLogic.makeMove(row, col)) {
            // Update button text
            int buttonId = getResources().getIdentifier("button" + (row * 3 + col + 1), "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setText(gameLogic.getCurrentPlayer());

            // Check for winner
            String winner = gameLogic.checkWinner();
            if (winner != null) {
                // Show winner and reset the game
                Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
                resetGame();
            } else {
                gameLogic.switchPlayer();
            }
        }
    }

    private void resetGame() {
        gameLogic.resetGame();
        // Clear all button texts
        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setText("");
        }
    }
}
