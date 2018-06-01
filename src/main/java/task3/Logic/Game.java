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

    public boolean makeTurn(int x, int y, Turns turn) {
        int color = field[y][x];
        boolean firstIsAttack = canAttack(color);
        if (field[y][x] == 0)
            throw new IllegalArgumentException();
        switch (turn) {
            case RIGHT:
                return goRight(x, y) && !(firstIsAttack && canAttack(color));
            case LEFT:
                return goLeft(x, y) && !(firstIsAttack && canAttack(color));
            case BACKRIGHT:
                return goBackRight(x, y) && !(firstIsAttack && canAttack(color));
            case BACKLEFT:
                return goBackLeft(x, y) && !(firstIsAttack && canAttack(color));
            default:
                return false;
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

    public int getCheckerAt(int x, int y) {
        if (x >= fieldSize || y >= fieldSize || x < 0 || y < 0)
            throw new IllegalArgumentException();
        return field[y][x];
    }

    private boolean goRight(int x, int y) {
        if (field[y][x] > 0) {
            if (y == 0 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (!canAttack(1) && field[y - 1][x + 1] == 0) {
                field[y - 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == 0)
                    flip(x + 1, y - 1);
                return true;
            } else if (field[y - 1][x + 1] < 0) {
                if (x + 2 < 8 && y - 2 >= 0 && field[y - 2][x + 2] == 0) {
                    field[y - 2][x + 2] = field[y][x];
                    field[y - 1][x + 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y - 2 == 0)
                        flip(x + 2, y - 2);
                    return true;
                }
            }
        } else {
            if (y == fieldSize - 1 || x == 0)
                throw new IllegalArgumentException();
            if (!canAttack(-1) && field[y + 1][x - 1] == 0) {
                field[y + 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == fieldSize - 1)
                    flip(x - 1, y + 1);
                return true;
            } else if (field[y + 1][x - 1] > 0) {
                if (x - 2 >= 0 && y + 2 < 8 && field[y + 2][x - 2] == 0) {
                    field[y + 2][x - 2] = field[y][x];
                    field[y + 1][x - 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y + 2 == fieldSize - 1)
                        flip(x - 2, y + 2);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean goLeft(int x, int y) {
        if (field[y][x] > 0) {
            if (y == 0 || x == 0)
                throw new IllegalArgumentException();
            if (!canAttack(1) && field[y - 1][x - 1] == 0) {
                field[y - 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y - 1 == 0)
                    flip(x - 1, y - 1);
                return true;
            } else if (field[y - 1][x - 1] < 0) {
                if (x - 2 >= 0 && y - 2 >= 0 && field[y - 2][x - 2] == 0) {
                    field[y - 2][x - 2] = field[y][x];
                    field[y - 1][x - 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y - 2 == 0)
                        flip(x - 2, y - 2);
                    return true;
                }
            }
        } else {
            if (y == fieldSize - 1 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (!canAttack(-1) && field[y + 1][x + 1] == 0) {
                field[y + 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == fieldSize - 1)
                    flip(x + 1, y + 1);
                return true;
            } else if (field[y + 1][x + 1] > 0) {
                if (x + 2 >= 0 && y + 2 < 8 && field[y + 2][x + 2] == 0) {
                    field[y + 2][x + 2] = field[y][x];
                    field[y + 1][x + 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y + 2 == fieldSize - 1)
                        flip(x + 2, y + 2);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean goBackRight(int x, int y) {
        if (field[y][x] > 0) {
            if (y == fieldSize - 1 || x == fieldSize - 1)
                throw new IllegalArgumentException();
            if (field[y + 1][x + 1] == 0) {
                field[y + 1][x + 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == 0)
                    flip(x + 1, y + 1);
                return true;
            } else if (field[y + 1][x + 1] < 0) {
                if (x + 2 < 8 && y + 2 >= 0 && field[y + 2][x + 2] == 0) {
                    field[y + 2][x + 2] = field[y][x];
                    field[y + 1][x + 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y + 2 == 0)
                        flip(x + 2, y + 2);
                    return true;
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
                return true;
            } else if (field[y - 1][x - 1] > 0) {
                if (x - 2 >= 0 && y - 2 < 8 && field[y - 2][x - 2] == 0) {
                    field[y - 2][x - 2] = field[y][x];
                    field[y - 1][x - 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y - 2 == fieldSize - 1)
                        flip(x - 2, y - 2);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean goBackLeft(int x, int y) {
        if (field[y][x] > 0) {
            if (y == fieldSize - 1 || x == 0)
                throw new IllegalArgumentException();
            if (field[y + 1][x - 1] == 0) {
                field[y + 1][x - 1] = field[y][x];
                field[y][x] = 0;
                if (y + 1 == 0)
                    flip(x + 1, y - 1);
                return true;
            } else if (field[y + 1][x - 1] < 0) {
                if (x - 2 >= 0 && y + 2 >= 0 && field[y + 2][x - 2] == 0) {
                    field[y + 2][x - 2] = field[y][x];
                    field[y + 1][x - 1] = 0;
                    field[y][x] = 0;
                    whiteScore++;
                    if (y + 2 == 0)
                        flip(x - 2, y + 2);
                    return true;
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
                return true;
            } else if (field[y - 1][x + 1] > 0) {
                if (x + 2 >= 0 && y - 2 < 8 && field[y - 2][x + 2] == 0) {
                    field[y - 2][x + 2] = field[y][x];
                    field[y - 1][x + 1] = 0;
                    field[y][x] = 0;
                    blackScore++;
                    if (y - 2 == fieldSize - 1)
                        flip(x + 2, y - 2);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canAttack(int color) {
        for (int y = 2; y < fieldSize - 2; y++) {
            for (int x = 2; x < fieldSize - 2; x++) {
                if (field[y][x] != 0 && field[y][x] > 0 == color > 0) {
                    if (color > 0) {
                        if (field[y - 1][x - 1] < 0 && field[y - 2][x - 2] == 0
                                || field[y - 1][x + 1] < 0 && field[y - 2][x + 2] == 0) {
                            return true;
                        } else if (color == 2 && (field[y + 1][x - 1] < 0 && field[y + 2][x - 2] == 0
                                || field[y + 1][x + 1] < 0 && field[y + 2][x + 2] == 0)) {
                            return true;
                        }

                    } else if (color < 0) {
                        if (field[y + 1][x - 1] > 0 && field[y + 2][x - 2] == 0
                                || field[y + 1][x + 1] > 0 && field[y + 2][x + 2] == 0) {
                            return true;
                        } else if (color == -2 && (field[y - 1][x - 1] > 0 && field[y - 2][x - 2] == 0
                                || field[y - 1][x + 1] > 0 && field[y - 2][x + 2] == 0)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
