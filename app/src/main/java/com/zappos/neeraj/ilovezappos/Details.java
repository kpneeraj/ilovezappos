package com.zappos.neeraj.ilovezappos;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.zappos.neeraj.ilovezappos.databinding.ActivityTestBinding;

public class Details extends AppCompatActivity  implements LoadImageTask.Listener{
    public final static String EXTRA_MESSAGE = "com.zappos.neeraj.ilovezappos.Product";
    ImageView image;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_product_details1);
        final Details thisObject =this;
        Intent intent = getIntent();

        ActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        Product product = (Product) intent.getSerializableExtra(EXTRA_MESSAGE);
        binding.setProduct(product);

        image = (ImageView) findViewById(R.id.productImage);
        new LoadImageTask(thisObject).execute(product.thumbnailImageUrl);

        final View floatingButton = findViewById(R.id.floatingActionButton2);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimation(thisObject,view);
            }
        });

        View addToCart = findViewById(R.id.button);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnimation(thisObject,floatingButton );
            }
        });

    }

    public void doAnimation(Details thisObj, View view){
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(thisObj, R.anim.hyperspace_jump);
        view.startAnimation(hyperspaceJumpAnimation);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        System.out.println("Error during image download");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        //handle all config changes here.
    }
}
