package com.jbaker.android.doctornim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int count;
    protected int fullCount;
    protected int marble = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        TextView textView = (TextView) findViewById(R.id.marbles);
        textView.setText(String.valueOf(marble));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                count++;
                marble--;


//                gridview.setEnabled(false);
//
//                if (position == fullCount) {
//                    gridview.getChildAt(position).setEnabled(true);
//                }

                if (count == 3 || marble == 0) {
                    gridview.setEnabled(false);
                }

                ImageView imageView = (ImageView) v;
                //imageView.setImageResource(R.drawable.ic_cancel_black_48dp);
                imageView.setVisibility(View.GONE);

                TextView textView = (TextView) findViewById(R.id.marbles);
                textView.setText(String.valueOf(marble));

                fullCount++;
            }
        });

        final Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count > 3) {
                    playButton.setEnabled(false);
                } else {
                    playButton.setEnabled(true);
                    //marble = marble - count;
                    switch (count) {
                        case 1:
                            marble = marble - 3;
                            ImageView imageView = (ImageView) gridview.getChildAt(fullCount);
                            //imageView.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView.setVisibility(View.GONE);

                            ImageView imageView2 = (ImageView) gridview.getChildAt(fullCount +1);
                            //imageView2.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView2.setVisibility(View.GONE);

                            ImageView imageView3 = (ImageView) gridview.getChildAt(fullCount + 2);
                            //imageView3.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView3.setVisibility(View.GONE);
                            fullCount = fullCount + 3;
                            break;
                        case 2:
                            marble = marble - 2;
                            ImageView imageView4 = (ImageView) gridview.getChildAt(fullCount);
                            //imageView4.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView4.setVisibility(View.GONE);

                            ImageView imageView5 = (ImageView) gridview.getChildAt(fullCount +1);
                            //imageView5.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView5.setVisibility(View.GONE);
                            fullCount = fullCount + 2;
                            break;
                        case 3:
                            marble = marble - 1;
                            ImageView imageView6 = (ImageView) gridview.getChildAt(fullCount);
                            //imageView6.setImageResource(R.drawable.ic_cancel_black_48dp);
                            imageView6.setVisibility(View.GONE);
                            fullCount = fullCount + 1;
                            break;
                        default: break;
                    }
                    count = 0;
                    TextView textView = (TextView) findViewById(R.id.marbles);
                    textView.setText(String.valueOf(marble));

                }
                if (marble < 1) {
                    gridview.setEnabled(false);
                } else {
                    gridview.setEnabled(true);
                }
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                return true;
            case R.id.quit:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
