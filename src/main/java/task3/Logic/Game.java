package task3.Logic;

public class Game {
    private int[][] field;
    private final int fieldSize = 8;

    private int whiteScore = 0;
    private int blackScore = 0;

    public Game() {
        field = new int[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = 0;
            }
        }

        // black
        for (int i = 0; i < 3; i++) {
            for (int j = fieldSize - 1 - i % 2; j >= 0; j -= 2) {
                field[i][j] = -1;
            }
        }
        //white
        for (int i = 5; i < 8; i++) {
            for (int j = fieldSize - 1 - i % 2; j >= 0; j -= 2) {
                field[i][j] = 1;
            }
        }
    }

    public void makeTurn(int x, int y, Turns turn) {
        if (field[y][x] == 0)
            throw new IllegalArgumentException();
        switch (turn) {
            case RIGHT:
                goRight(x, y);
                break;
            case LEFT:
                goLeft(x, y);
                break;
            case BACKRIGHT:
                goBackRight(x, y);
                break;
            case BACKLEFT:
                goBackLeft(x, y);
                break;
        }
    }

    public boolean gameOver() {
        return (whiteScore == 12 || blackScore == 12);
    }

    public int getBlackScore() {
        return blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getChekerAt(int x, int y) {
        if (x >= fieldSize || y >= fieldSize || x < 0 || y < 0)
            throw new IllegalArgumentException();
        return field[y][x];
    }

    private void goRight(int x, int y) {
        if (field[y][x] > 0) {
            if (y == 0 || x == fieldSize - 1)
                //неккоркт ход
                throw new IllegalArgumentException();
            if (field[y - 1][x + 1] == 0) {
                field[y - 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == 0)
                    flip(x + 1, y - 1);
            } else if (field[y - 1][x + 1] < 0) {
                if (x + 2 < 8 && y - 2 >= 0 && field[y - 2][x + 2] == 0) {
                    field[y - 2][x + 2] = field[y][x];
                    field[y - 1][x + 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y - 2 == 0)
                        flip(x + 2, y - 2);
                }
            }
        } else {
            if (y == fieldSize - 1 || x == 0)
                throw new IllegalArgumentException();
            if (field[y + 1][x - 1] == 0) {
                field[y + 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == fieldSize - 1)
                    flip(x - 1, y + 1);
            } else if (field[y + 1][x - 1] > 0) {
                if (x - 2 >= 0 && y + 2 < 8 && field[y + 2][x - 2] == 0) {
                    field[y + 2][x - 2] = field[y][x];
                    field[y + 1][x - 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y + 2 == fieldSize - 1)
                        flip(x - 2, y + 2);
                }
            }
        }
    }

    private void goLeft(int x, int y) {
        if (field[y][x] > 0) {
            if (y == 0 || x == 0)
                throw new IllegalArgumentException();
            if (field[y - 1][x - 1] == 0) {
                field[y - 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == 0)
                    flip(x - 1, y - 1);
            } else if (field[y - 1][x - 1] < 0) {
                if (x - 2 >= 0 && y - 2 >= 0 && field[y - 2][x - 2] == 0) {
                    field[y - 2][x - 2] = field[y][x];
                    field[y - 1][x - 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y - 2 == 0)
                        flip(x - 2, y + 2);
                }
            }
        } else {
            if (y == fieldSize - 1 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (field[y + 1][x + 1] == 0) {
                field[y + 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == fieldSize - 1)
                    flip(x + 1, y + 1);
            } else if (field[y + 1][x + 1] > 0) {
                if (x + 2 >= 0 && y + 2 < 8 && field[y + 2][x + 2] == 0) {
                    field[y + 2][x + 2] = field[y][x];
                    field[y + 1][x + 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y + 2 == fieldSize - 1)
                        flip(x + 2, y + 2);
                }
            }
        }
    }

    private void goBackRight(int x, int y) {
        if (field[y][x] > 0) {
            if (y == fieldSize - 1 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (field[y + 1][x + 1] == 0) {
                field[y + 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == 0)
                    flip(x + 1, y + 1);
            } else if (field[y + 1][x + 1] < 0) {
                if (x + 2 < 8 && y + 2 >= 0 && field[y + 2][x + 2] == 0) {
                    field[y + 2][x + 2] = field[y][x];
                    field[y + 1][x + 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y + 2 == 0)
                        flip(x + 2, y + 2);
                }
            }
        } else {
            if (y == 0 || x == 0)
                throw new IllegalArgumentException();
            if (field[y - 1][x - 1] == 0) {
                field[y - 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == fieldSize - 1)
                    flip(x - 1, y - 1);
            } else if (field[y - 1][x - 1] > 0) {
                if (x - 2 >= 0 && y - 2 < 8 && field[y - 2][x - 2] == 0) {
                    field[y - 2][x - 2] = field[y][x];
                    field[y - 1][x - 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y - 2 == fieldSize - 1)
                        flip(x - 2, y - 2);
                }
            }
        }
    }

    private void goBackLeft(int x, int y) {
        if (field[y][x] > 0) {
            if (y == fieldSize - 1 || x == 0)
                throw new IllegalArgumentException();
            if (field[y + 1][x - 1] == 0) {
                field[y + 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == 0)
                    flip(x + 1, y - 1);
            } else if (field[y + 1][x - 1] < 0) {
                if (x - 2 >= 0 && y + 2 >= 0 && field[y + 2][x - 2] == 0) {
                    field[y + 2][x - 2] = field[y][x];
                    field[y + 1][x - 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y + 2 == 0)
                        flip(x - 2, y + 2);
                }
            }
        } else {
            if (y == 0 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (field[y - 1][x + 1] == 0) {
                field[y - 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == fieldSize - 1)
                    flip(x + 1, y - 1);
            } else if (field[y - 1][x + 1] > 0) {
                if (x + 2 >= 0 && y - 2 < 8 && field[y - 2][x + 2] == 0) {
                    field[y - 2][x + 2] = field[y][x];
                    field[y - 1][x + 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y - 2 == fieldSize - 1)
                        flip(x + 2, y - 2);
                }
            }
        }
    }

    private void flip(int x, int y) {
        if (field[y][x] == 1) {
            field[y][x] = 2;
        } else if (field[y][x] == -1) {
            field[y][x] = -2;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Белые: ").append(whiteScore).append(" Черные: ").append(blackScore).append("\n");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] >= 0) {
                    sb.append("  ").append(field[i][j]).append(' ');
                } else {
                    sb.append(' ').append(field[i][j]).append(' ');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.toString());
        game.makeTurn(1, 2, Turns.RIGHT);
        System.out.println(game.toString());
    }

}
