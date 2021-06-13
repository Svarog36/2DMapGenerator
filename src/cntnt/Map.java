package cntnt;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Map {

    int mapGrid[][];
    static int maxDepth = 0, maxHeight = 0;
    List<Integer> mapStats;
    Asteroid asteroid;

    Group mapGroup = new Group();

    Map(){

        asteroid = new Asteroid();

        mapStats = new ArrayList<>();

    }

    int [][]generateMap(int mapSize, int numberOfAsteroids, int minAsteroidSize, int maxAsteroidSize){
        int mapGrid[][] = new int[mapSize][mapSize];

        for(int i = 0; i < numberOfAsteroids; i++){

            mapGrid = addAsteroidToMap( mapGrid, asteroid.generateAsteroid(asteroid.getRandom(maxAsteroidSize - minAsteroidSize) + minAsteroidSize));

        }

        return mapGrid;

    }

    int [][]addAsteroidToMap(int [][]map, int [][] asteroidGrid){




        int posX = asteroid.getRandom(map[0].length + asteroidGrid[0].length) - asteroid.getRandom(asteroidGrid[0].length / 2);
        int posY = asteroid.getRandom(map.length + asteroidGrid.length) - asteroid.getRandom(asteroidGrid.length / 2);



        int random = asteroid.getRandom(2);

        for(int j = 0; j < asteroidGrid.length; j++){

            for(int i = 0; i < asteroidGrid[j].length; i++){

                if(i + posX >= 0 && j + posY >= 0 && i + posX < map[0].length && j + posY < map.length){

                    if(random == 0) {
                        map[j + posY][i + posX] -= asteroidGrid[j][i];

                        if(map[j + posY][i + posX] < maxDepth)
                            maxDepth = map[j + posY][i + posX];

                    }else {
                        map[j + posY][i + posX] += asteroidGrid[j][i];

                        if(map[j + posY][i + posX] > maxHeight)
                            maxHeight = map[j + posY][i + posX];
                    }
                }

            }

        }

        return map;

    }

    void addMapToGroup(int [][]grid){

        for(int j = 0; j < grid.length; j++) {

            for (int i = 0; i < grid[0].length; i++) {

                if (grid[j][i] > 0) {
                    mapGroup.getChildren().add(new AsteroidRect(i * Main.rectWidth, j * Main.rectWidth).getRectangle(grid[j][i]));
                } else if (grid[j][i] < 0) {
                    mapGroup.getChildren().add(new AsteroidRect(i * Main.rectWidth, j * Main.rectWidth).getRectangle(grid[j][i]));
                }else {
                    mapGroup.getChildren().add(new AsteroidRect(i * Main.rectWidth, j * Main.rectWidth).getRectangle(grid[j][i]));
                }

            }
        }

    }

    void showMapInConsole(int [][]grid){
        for(int j = 0; j < grid.length; j++){

            for(int i = 0; i < grid[0].length; i++){

                if(grid[j][i] != 0){
                    System.out.print("# ");
                }else {
                    System.out.print("  ");
                }

            }
            System.out.println();
        }
    }


}
