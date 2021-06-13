package cntnt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Asteroid {

    int[][] generateAsteroid(int xySize) {

        int[][] baseGrid = new int[xySize][xySize];

        initialiseGrid(baseGrid);
        baseGrid[xySize / 2][xySize / 2]++;

        for (int i = 0; i < xySize / 2 - 1; i++) {
            growSeed(baseGrid);
        }


        return baseGrid;
    }

    void growSeed(int[][] grid) {
        List<Seed> startSeeds = new ArrayList<>();


        for (int j = 0; j < grid.length; j++) {

            for (int i = 0; i < grid[j].length; i++) {

                if (grid[j][i] != 0) {
                    startSeeds.add(new Seed(i, j));
                }

            }

        }

        for (Seed seed : startSeeds) {

            for (int i = 0; i < getRandom(4); i++) {

                int rndm = getRandom(4);

                if (rndm == 0) {
                    grid[seed.y][seed.x - 1]++;
                } else if (rndm == 1) {
                    grid[seed.y][seed.x + 1]++;
                } else if (rndm == 2) {
                    grid[seed.y - 1][seed.x]++;
                } else if (rndm == 3) {
                    grid[seed.y + 1][seed.x]++;
                }


            }
        }

        startSeeds.clear();

    }


    void initialiseGrid(int[][] grid) {
        for (int[] ints : grid) {
            Arrays.fill(ints, 0);
        }

    }


    int getRandom(int possibleValues) {
        return (int) (Math.random() * possibleValues);
    }


    private static class Seed {
        int x, y;

        Seed(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
