package com.dipale.xepgach;

import android.util.Log;

import java.io.Console;
import java.util.ArrayList;

public class Gach {
    private int tam;
    private int[] canh;
    private int goc;
    private String chu;

    public Gach(String chu) {
        this.chu = chu;
        tam = 4;
        goc = 0;
        canh = setCanh();
    }

    //cong thuc i j chuyen qua so
    // j*10+i
    public int[] setCanh() {
        canh = new int[3];
        switch (chu) {
            case "s":
                switch (goc) {
                    case 0:
                    case 180:
                        canh[0] = tam + 1;
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[1] - 1;
                        break;
                    case 90:
                    case 270:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = tam + 1;
                        canh[2] = ((canh[1] - (canh[1] % 10)) / 10 + 1) * 10 + (canh[1] % 10);
                        break;
                }
                break;
            case "q":
                switch (goc) {
                    case 0:
                    case 180:
                        canh[0] = tam - 1;
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[1] + 1;
                        break;
                    case 90:
                    case 270:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = tam - 1;
                        canh[2] = ((canh[1] - (canh[1] % 10)) / 10 + 1) * 10 + (canh[1] % 10);
                        break;
                }
                break;
            case "o":
                canh[0] = tam + 1;
                canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                canh[2] = canh[1] + 1;
                break;
            case "t":
                switch (goc) {
                    case 0:
                        canh[0] = tam + 1;
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = tam - 1;
                        break;
                    case 90:
                        canh[0] = tam + 1;
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        break;
                    case 180:
                        canh[0] = tam + 1;
                        canh[1] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[2] = tam - 1;
                        break;
                    case 270:
                        canh[0] = tam - 1;
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        break;
                }
                break;
            case "i":
                switch (goc) {
                    case 0:
                    case 180:
                        canh[0] = tam - 1;
                        canh[1] = tam + 1;
                        canh[2] = tam + 2;
                        break;
                    case 90:
                    case 270:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = ((tam - (tam % 10)) / 10 + 2) * 10 + (tam % 10);
                        break;
                }
                break;
            case "l":
                switch (goc) {
                    case 0:
                        canh[0] = tam - 1;
                        canh[1] = tam + 1;
                        canh[2] = ((canh[1] - (canh[1] % 10)) / 10 - 1) * 10 + (canh[1] % 10);
                        break;
                    case 90:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[0] - 1;
                        break;
                    case 180:
                        canh[0] = tam - 1;
                        canh[1] = tam + 1;
                        canh[2] = ((canh[0] - (canh[0] % 10)) / 10 + 1) * 10 + (canh[0] % 10);
                        break;
                    case 270:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[1] + 1;
                        break;
                }
                break;
            case "j":
                switch (goc) {
                    case 0:
                        canh[0] = tam - 1;
                        canh[1] = tam + 1;
                        canh[2] = ((canh[0] - (canh[0] % 10)) / 10 - 1) * 10 + (canh[0] % 10);
                        break;
                    case 90:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[1] - 1;
                        break;
                    case 180:
                        canh[0] = tam - 1;
                        canh[1] = tam + 1;
                        canh[2] = ((canh[1] - (canh[1] % 10)) / 10 + 1) * 10 + (canh[1] % 10);
                        break;
                    case 270:
                        canh[0] = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                        canh[1] = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                        canh[2] = canh[0] + 1;
                        break;
                }
                break;
        }
        return canh;
    }

    public int[] convertGridview_4_2(){
        for(int i=0; i<3; i++){
            if(canh[i]<0){
                tam = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
                setCanh();
                break;
            }
        }
        tam = (tam % 10 - 3) + (tam / 10) * 4;
        int ct[] = new int[4];
        for(int i=0; i<3; i++){
            ct[i] = (canh[i] % 10 - 3) + (canh[i] / 10) * 4;
        }
        ct[3] = tam;
        //Log.d("ct","c0:"+ct[0]+" c1:"+ct[1]+" c2:"+ct[2]);
        tam = 4;
        setCanh();
        return ct;
    }

    public boolean Check(Tuong tuong){
        for (int cell: tuong.getWall()) {
            if(tam == cell || canh[0] == cell || canh[1] == cell || canh[2] == cell){
                return false;
            }
        }
        return true;
    }

    public void Rotate(Tuong tuong) {
        if (goc == 270) {
            goc = 0;
        } else {
            goc += 90;
        }

        int temp = tam;
        tam = (tam - (tam % 10)) / 10 * 10 + 4;
        int[] y = setCanh();
        tam = temp;
        int[] x = setCanh();

        if (((x[0] - (x[0] % 10)) / 10) != ((y[0] - (y[0] % 10)) / 10) ||
                ((x[1] - (x[1] % 10)) / 10) != ((y[1] - (y[1] % 10)) / 10) ||
                ((x[2] - (x[2] % 10)) / 10) != ((y[2] - (y[2] % 10)) / 10)) {
            if (goc == 0) {
                goc = 270;
            } else {
                goc -= 90;
            }
            return;
        }
        if (x[0] > 199 || x[1] > 199 || x[2] > 199) {
            if (goc == 0) {
                goc = 270;
            } else {
                goc -= 90;
            }
            return;
        }
        for (int cell : tuong.getWall()) {
            if (x[0] == cell || x[1] == cell || x[2] == cell) {
                if (goc == 0) {
                    goc = 270;
                } else {
                    goc -= 90;
                }
                return;
            }
        }
    }

    public void TurnLeft(Tuong tuong) {
        //vi tri ban dau
        int[] x = setCanh();
        tam -= 1;
        canh = setCanh();
        if (((x[0] - (x[0] % 10)) / 10) != ((canh[0] - (canh[0] % 10)) / 10) ||
                ((x[1] - (x[1] % 10)) / 10) != ((canh[1] - (canh[1] % 10)) / 10) ||
                ((x[2] - (x[2] % 10)) / 10) != ((canh[2] - (canh[2] % 10)) / 10)) {
            tam += 1;
            canh = setCanh();
            return;
        }
        for (int cell : tuong.getWall()) {
            if (cell == canh[0] || cell == canh[1] || cell == canh[2]) {
                tam += 1;
                canh = setCanh();
                return;
            }
        }
    }

    public void TurnRight(Tuong tuong) {
        //vi tri ban dau
        int[] x = setCanh();
        tam += 1;
        canh = setCanh();
        if (((x[0] - (x[0] % 10)) / 10) != ((canh[0] - (canh[0] % 10)) / 10) ||
                ((x[1] - (x[1] % 10)) / 10) != ((canh[1] - (canh[1] % 10)) / 10) ||
                ((x[2] - (x[2] % 10)) / 10) != ((canh[2] - (canh[2] % 10)) / 10)) {
            tam -= 1;
            canh = setCanh();
            return;
        }
        for (int cell : tuong.getWall()) {
            if (cell == canh[0] || cell == canh[1] || cell == canh[2]) {
                tam -= 1;
                canh = setCanh();
                return;
            }
        }
    }

    public boolean MoveDown(Tuong tuong) {
        tam = ((tam - (tam % 10)) / 10 + 1) * 10 + (tam % 10);
        canh = setCanh();
        if (canh[0] > 199 || canh[1] > 199 || canh[2] > 199) {
            tam = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
            canh = setCanh();
            return false;
        }
        for (int cell : tuong.getWall()) {
            if (canh[0] == cell || canh[1] == cell || canh[2] == cell || tam == cell) {
                tam = ((tam - (tam % 10)) / 10 - 1) * 10 + (tam % 10);
                canh = setCanh();
                return false;
            }
        }
        return true;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public int[] getCanh() {
        return canh;
    }

    public void setCanh(int c1, int c2, int c3) {
        this.canh[0] = c1;
        this.canh[1] = c2;
        this.canh[2] = c3;
    }
}
