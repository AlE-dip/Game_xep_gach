package com.dipale.xepgach;

import android.util.Log;

import java.util.ArrayList;
import java.util.Queue;

public class Tuong {
    private ArrayList<Integer> wall;
    private int one;
    ArrayList<Integer> linesDrop;

    public Tuong() {
        wall = new ArrayList<>();
        one = 0;
    }

    public void AddWall(Gach gach) {
        wall.add(gach.getTam());
        for (int i = 0; i < 3; i++) {
            if(gach.getCanh()[i] < 0){
                continue;
            }
            wall.add(gach.getCanh()[i]);
        }
    }

    public void LinesDrop() {
        linesDrop = new ArrayList<>();
        int x;
        int[][] matrix = new int[10][20];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int cell : wall) {
            matrix[cell % 10][(cell - (cell % 10)) / 10] = 1;
        }
        for (int j = 0; j < 20; j++) {
            x = 0;
            if (matrix[0][j] == 1) {
                for (int i = 0; i < 10; i++) {
                    if (matrix[i][j] == 1) {
                        x++;
                    }
                }
                if (x == 10) {
                    linesDrop.add(j);
                    for (int i = 0; i < 10; i++) {
                        wall.remove(new Integer(j * 10 + i));
                    }
                }

            }
        }
    }

    public boolean Fall() {
        ArrayList<Integer> tmpWall;
        boolean down = false;
        boolean oneDown = false;

        if (linesDrop.size() > 0) {
            for (int line : linesDrop) {
                tmpWall = new ArrayList<>();
                for (int cell : wall) {
                    if (cell < line * 10) {
                        //Di chuyen xuong
                        tmpWall.add(((cell - (cell % 10)) / 10 + 1) * 10 + (cell % 10));
                    } else {
                        tmpWall.add(cell);
                    }
                }
                wall = tmpWall;
            }
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getLinesDrop() {
        return linesDrop;
    }

    public void setLinesDrop(ArrayList<Integer> linesDrop) {
        this.linesDrop = linesDrop;
    }

    public ArrayList<Integer> getWall() {
        return wall;
    }

    public void setWall(ArrayList<Integer> wall) {
        this.wall = wall;
    }
}
