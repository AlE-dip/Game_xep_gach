package com.dipale.xepgach;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView frame, gTemp;
    ImageView touch;
    CellAdapter cellAdapter, cellAdapterTemp;
    Gach gach, temp;
    Tuong tuong;
    TextView lines, score;
    MediaPlayer nen, boc, zzz, rap;
    TextView thongBao;
    ImageView reload;

    //Tinh diem
    long sc;
    int li;

    //xac dinh vi tri cham
    float x0 = 0, x1 = 0;
    float y0 = 0, y1 = 0;
    int direction = 0;
    int click100ms = 0;

    //Danh dau CountDownTimer
    boolean left = false, right = false, down = false, x = false;

    //LanDau
    boolean lanDau = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        temp = RanDomGach();

        if(lanDau == false){
            reload.setImageResource(R.drawable.play);
            thongBao.setText("Chơi");
            touch.setBackgroundResource(R.drawable.nen_mo);
        }

        sc = 0;
        li = 0;

        tuong = new Tuong();

        refreshGridView();

        frame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ("" + position), Toast.LENGTH_SHORT).show();
                view.setBackgroundColor(0);
            }
        });

        final CountDownTimer quaTrai = new CountDownTimer(10000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (gach == null) {
                    return;
                }
                gach.TurnLeft(tuong);
                gach.setCanh();

                refreshGridView();
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
        final CountDownTimer quaPhai = new CountDownTimer(10000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (gach == null) {
                    return;
                }
                gach.TurnRight(tuong);
                gach.setCanh();

                refreshGridView();
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
        final CountDownTimer diXuong = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (gach == null) {
                    return;
                }
                gach.MoveDown(tuong);
                gach.setCanh();

                refreshGridView();

                //Tang diem neu xuong nhanh
                sc++;
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
        final CountDownTimer click = new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                click100ms++;
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        };

        touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = event.getX();
                        y0 = event.getY();
                        click.start();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x1 = event.getX();
                        y1 = event.getY();
                        click100ms += 200;
                        //Qua trai
                        if ((x0 - x1) > 60) {
                            if (direction == 0) {
                                direction = 1;
                            }
                            if (direction == 1 && left != true) {
                                left = true;
                                quaTrai.start();
                            }
                        } else {
                            left = false;
                            quaTrai.cancel();
                        }
                        if ((x0 - x1) < -60) {
                            if (direction == 0) {
                                direction = 2;
                            }
                            if (direction == 2 && right != true) {
                                right = true;
                                quaPhai.start();
                            }
                        } else {
                            right = false;
                            quaPhai.cancel();
                        }
                        if ((y0 - y1) < -60) {
                            if (direction == 0) {
                                direction = 3;
                            }
                            if (direction == 3 && down != true) {
                                down = true;
                                diXuong.start();
                            }
                        } else {
                            down = false;
                            diXuong.cancel();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        left = false;
                        right = false;
                        down = false;
                        quaTrai.cancel();
                        quaPhai.cancel();
                        diXuong.cancel();
                        direction = 0;

                        //Thay cho onClick
                        if (click100ms <= 100) {
                            if (gach == null) {
                                return false;
                            }
                            gach.Rotate(tuong);
                            gach.setCanh();
                            boc.start();

                            refreshGridView();
                        }
                        click100ms = 0;
                        click.cancel();
                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:

                }
                return true;
            }
        });

        refreshGridViewTemp();
        final CountDownTimer chuyenDongGach = new CountDownTimer(10000, 500) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (gach == null) {
                    gach = temp;
                    temp = RanDomGach();
                    refreshGridViewTemp();

                    if(gach.Check(tuong) == false){
                        this.cancel();
                        thongBao.setText("Kết Thúc");
                        thongBao.setVisibility(View.VISIBLE);
                        reload.setVisibility(View.VISIBLE);
                        touch.setBackgroundResource(R.drawable.nen_mo);
                    }
                    //gach = new Gach("j");
                    x = false;
                }
                refreshGridView();
                int sct;
                if(x) {

                    if (gach.MoveDown(tuong) == false) {
                        tuong.AddWall(gach);
                        rap.start();
                        tuong.LinesDrop();
                        if (tuong.Fall()) {
                            zzz.start();
                        }

                        sct = tuong.getLinesDrop().size();
                        li += sct;
                        sc += sct * sct * 10;

                        xetDiem(sc, li);

                        tuong.linesDrop = null;

                        gach = null;
                    }
                    refreshGridView();
                }else {
                    x = true;
                }
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lanDau == false) {
                    lanDau = true;
                    touch.setBackgroundResource(0);
                    reload.setVisibility(View.GONE);
                    thongBao.setVisibility(View.GONE);
                    chuyenDongGach.start();
                    reload.setImageResource(R.drawable.reload);
                    thongBao.setText("Chơi lại");
                    nen.start();
                    nen.setLooping(true);
                }else {
                    touch.setBackgroundResource(0);
                    tuong = new Tuong();
                    reload.setVisibility(View.GONE);
                    thongBao.setVisibility(View.GONE);
                    chuyenDongGach.start();
                    x = false;
                    sc = 0;
                    li = 0;
                    xetDiem(sc, li);
                }
            }
        });

        gTemp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void xetDiem(long sc, int li) {
        String ssc = "", sli = "";
        for (int i = 8; i != 0; i--) {
            if (sc != 0) {
                ssc = (sc % 10) + ssc;
                sc /= 10;
            } else {
                ssc = "0" + ssc;
            }
            if (li != 0 && sli.length() != 3) {
                sli = (li % 10) + sli;
                li /= 10;
            } else {
                if (sli.length() != 3) {
                    sli = "0" + sli;
                }
            }
        }
        lines.setText(sli);
        score.setText(ssc);
    }

    public Gach RanDomGach() {
        int x = 0;
        Random rd = new Random();
        x = rd.nextInt((7 - 1 + 1) + 1);
        return x == 1 ? new Gach("s") :
                x == 2 ? new Gach("q") :
                        x == 3 ? new Gach("o") :
                                x == 4 ? new Gach("t") :
                                        x == 5 ? new Gach("i") :
                                                x == 6 ? new Gach("l") :
                                                        new Gach("j");
    }

    private void AnhXa() {
        frame = (GridView) findViewById(R.id.frame);
        gTemp = (GridView) findViewById(R.id.temp);
        touch = (ImageView) findViewById(R.id.imvTouch);
        lines = (TextView) findViewById(R.id.txvLinesNum);
        score = (TextView) findViewById(R.id.txvScoreNum);
        thongBao = (TextView) findViewById(R.id.txvThongBao);
        reload = (ImageView) findViewById(R.id.imvReload);
        nen = MediaPlayer.create(MainActivity.this,R.raw.nen);
        boc = MediaPlayer.create(MainActivity.this, R.raw.boc);
        zzz = MediaPlayer.create(MainActivity.this, R.raw.zzz);
        rap = MediaPlayer.create(MainActivity.this, R.raw.rap);
    }

    public void refreshGridView() {
        cellAdapter = new CellAdapter(
                MainActivity.this,
                R.layout.cell,
                gach,
                tuong,
                200);
        frame.setAdapter(cellAdapter);
    }

    public void refreshGridViewTemp(){
        int[] ct;
        ct = temp.convertGridview_4_2();
        Gach tmp = new Gach("i");
        tmp.setTam(ct[3]);
        tmp.setCanh(ct[0], ct[1], ct[2]);
        Log.d("cv","c0:"+tmp.getCanh()[0]+" c1:"+tmp.getCanh()[1]+" c2:"+tmp.getCanh()[2]+" t:"+tmp.getTam());
        cellAdapterTemp = new CellAdapter(
                MainActivity.this,
                R.layout.cell,
                tmp,
                tuong,
                8);
        gTemp.setAdapter(cellAdapterTemp);
    }
}
