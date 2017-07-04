# Ultimate TicTacToe

This is my first android app for AndroidMonk Hackathon and we won that hackathon.

## Basic Idea
Ultimate tic-tac-toe also known as super tic-tac-toe is a board game composed of nine tic-tac-toe boards arranged in a 3-by-3 grid.  Players take turns playing in the smaller tic-tac-toe boards until one of them wins in the larger tic-tac-toe board.

## Instructions
1. First player plays with Red Cross(X). Second player plays with green naughts(O).
2. First player can make his first move anywhere on the board.
3. First move of player 2 is influenced by move of Player 1. Player 1 move 'sends' his opponent to its relative location on global block.
4. Suppose, first player has made his move on third row first column of first block. Then, player 2 has to move somewhere in block located at third row, first column of global block.
5. From now onwards, move of opponent will send the opponent to it's relative location on global block.
6. Legal blocks are blocks where result hasn't been decided.
7. If a player sends opponents on a block where result has been decided(won/loss/draw), opponent can move anywhere on global board (the next block must be legal, Obviously).
8. To help user in making moves, next active block will be colored. If no block is colored, opponent can make it's move anywhere.

## Example

On global board, 9 3*3 blocks are present.  Assume that they are numbered through 1 to 9 and each block contains three rows and three column. Now, suppose that Player 1 had made it's first move on 2nd block, 3rd row, 2nd column. Then, next move of player 2 must be on block located at 3rd row, 2nd column of global block (i.e. 8th block). Now, player 2 is free to move anywhere on 8th block. Suppose that he makes his move on 2nd row, 5th column of 8th block. Then Player 1 has to make his move on the block located at 2nd row, 5th column(fifth block) of global block. Next move of player 2 will be determined by move made by first player in fifth block and game continues like this.

## Developed By
1. Piyush Sikarwal.
2. Neelansh Sahai.
3. Harshit Jain.
4. Prakash Rai.
