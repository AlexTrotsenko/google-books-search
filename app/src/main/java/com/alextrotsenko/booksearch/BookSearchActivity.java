package com.alextrotsenko.booksearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.ButterKnife;

public class BookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        //todo use Dependency injection
        if (!ImageLoader.getInstance().isInited()) {
            final DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .build();

            final ImageLoaderConfiguration imageConfig = new ImageLoaderConfiguration.Builder(getApplicationContext())
                    .threadPriority(Thread.MIN_PRIORITY)
                    .defaultDisplayImageOptions(imageOptions)
                    .denyCacheImageMultipleSizesInMemory()
                    .build();

            ImageLoader.getInstance().init(imageConfig);
        }
    }
}
