import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class TetrisPiece {

    public int[][] pieceArray;
    public int piece = 0;
    public int xPos = 0;
    public int yPos = 0;
    public int rotation = 0;
    public int colorRGB = 0;

    TetrisPiece(int inPiece, int INxPos, int INyPos) {
        piece = inPiece;
        switch (inPiece) {
            case 0: pieceArray = new int[][]{
                    {0, 0},
                    {1, 0},
                    {0, 1},
                    {1, 1}

            };
            colorRGB = Color.yellow.getRGB();
            break;
            case 1: pieceArray = new int[][]{
                    {0, 0},
                    {0, 1},
                    {0, 2},
                    {0, 3},

            };
            colorRGB = Color.cyan.getRGB();
            break;

            case 2:  pieceArray = new int[][]{
                    {0, 0},
                    {0, 1},
                    {1, 1},
                    {2, 1}

            };
            colorRGB = Color.blue.getRGB();
            break;
            case 3: pieceArray = new int[][]{
                    {2, 0},
                    {0, 1},
                    {1, 1},
                    {2, 1}

            };
            colorRGB = Color.orange.getRGB();
            break;
            case 4: pieceArray = new int[][]{
                    {0, 1},
                    {1, 1},
                    {1, 0},
                    {2, 0}

            };
            colorRGB = Color.green.getRGB();
            break;
            case 5: pieceArray = new int[][]{
                    {0, 0},
                    {1, 0},
                    {1, 1},
                    {2, 1}
            };
            colorRGB = Color.red.getRGB();
            break;
            case 6: pieceArray = new int[][]{
                    {1, 0},
                    {0, 1},
                    {1, 1},
                    {2, 1}

            };
            colorRGB = Color.pink.getRGB();
            break;
        }
        xPos = INxPos;
        yPos = INyPos;
    }

    public boolean updatePos(int xDirection, int yDirection) {
        boolean check = true;


        for (int i = 0; i < pieceArray.length; i++) {
            if (pieceArray[i][0] + xPos + xDirection < 0 || pieceArray[i][0] + xPos + xDirection > TetrisMain.xGridSize - 1 || pieceArray[i][1] + yPos + yDirection > TetrisMain.yGridSize - 1) {
                check = false;
            } else if (TetrisMain.grid[pieceArray[i][0] + xPos + xDirection][pieceArray[i][1] + yPos + yDirection] != -1) {
                check = false;
            }
        }


        if (check) {


            yPos += yDirection;
            xPos += xDirection;


        }

        System.out.println(xPos + "," + yPos);
        //region draw
        for (int i = 0; i < pieceArray.length; i++) {
            TetrisMain.displayGrid[pieceArray[i][0] + xPos][pieceArray[i][1] + yPos] = 1;
            TetrisMain.colorGrid[pieceArray[i][0] + xPos][pieceArray[i][1] + yPos] = colorRGB;
        }
        System.out.println("draw");
        //endregion


        return check;
    }

    public void rotate(int INDirection) {
        int[][] tempPieceArray = new int[4][2];
        rotation += INDirection;
        System.out.println(rotation);
        int rDirection = 0;

        if (piece != 0) {
            switch (piece) {

                case 1: //line shit
                    rDirection = Math.abs(rotation) % 2;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {0, 1},
                                {0, 2},
                                {0, 3},
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {2, 0},
                                {3, 0},
                        };

                    }
                    break;
                case 2: //spiegel L shit
                    rDirection = Math.abs(rotation) % 4;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {0, 1},
                                {1, 1},
                                {2, 1}
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {1, 0},
                                {1, 1},
                                {0, 2},
                                {1, 2},
                        };
                        case 2 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {2, 0},
                                {2, 1},
                        };
                        case 3 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {0, 1},
                                {0, 2},
                        };

                    }
                    break;
                case 3: //L shit
                    rDirection = Math.abs(rotation) % 4;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {2, 0},
                                {0, 1},
                                {1, 1},
                                {2, 1}
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {1, 1},
                                {1, 2},
                        };
                        case 2 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {2, 0},
                                {0, 1},
                        };
                        case 3 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {0, 1},
                                {0, 2},
                                {1, 2},
                        };

                    }
                    break;
                case 4: //des komische shit
                    rDirection = Math.abs(rotation) % 2;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {0, 1},
                                {1, 1},
                                {1, 0},
                                {2, 0}
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {0, 1},
                                {1, 1},
                                {1, 2},
                        };


                    }
                    break;
                case 5: //spiegel des komische shit
                    rDirection = Math.abs(rotation) % 2;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {1, 1},
                                {2, 1}
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {1, 0},
                                {0, 1},
                                {1, 1},
                                {0, 2},
                        };


                    }
                    break;
                case 6: //T shit
                    rDirection = Math.abs(rotation) % 4;
                    switch (rDirection) {
                        case 0 -> tempPieceArray = new int[][]{
                                {1, 0},
                                {0, 1},
                                {1, 1},
                                {2, 1}
                        };
                        case 1 -> tempPieceArray = new int[][]{
                                {1, 0},
                                {0, 1},
                                {1, 1},
                                {1, 2},
                        };
                        case 2 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {1, 0},
                                {2, 0},
                                {1, 1},
                        };
                        case 3 -> tempPieceArray = new int[][]{
                                {0, 0},
                                {0, 1},
                                {1, 1},
                                {0, 2},
                        };


                    }
                    break;

            }


            boolean check = true;

            for (int i = 0; i < pieceArray.length; i++) {
                if (tempPieceArray[i][0] + xPos < 0 || tempPieceArray[i][0] + xPos > TetrisMain.xGridSize - 1 || tempPieceArray[i][1] + yPos > TetrisMain.yGridSize - 1) {
                    check = false;
                } else if (TetrisMain.grid[tempPieceArray[i][0] + xPos ][tempPieceArray[i][1] + yPos ] != -1) {
                    check = false;
                }
            }



            if(check)
            {
                for (int i = 0; i < pieceArray.length; i++) {
                    for (int n = 0; n <= 1; n++) {
                        pieceArray[i][n] = tempPieceArray[i][n];
                    }
                }
            }
            else {
                rotation -= INDirection;
            }
        }




    }


}

