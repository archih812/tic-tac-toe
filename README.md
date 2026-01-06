# Tic Tac Toe

## Description
A simple CLI based Tic Tac Toe game. There is a 3x3 matrix. Each cell is denoted by a number. There are two players: `O` and `X`. The player `O` starts the game. Each player gets a turn. To win the game, you have to put your move in three consecutive cells (this includes diagonally as well). If there are no turns left, the game ends in a draw situation.

## How to play

- There are some prerequisites:
    - You need to have Java installed in your machine
    - I've used Java 25. If you have any other version, mention it in `pom.xml` file
- Compile the project
    ```bash
    mvn compile
    ```
- Execute the project
    ```bash
    mvn exec:java -Dexec.mainClass="com.archihalder.TicTacToe"
    ```
- If you want to run it again, run the above command again
