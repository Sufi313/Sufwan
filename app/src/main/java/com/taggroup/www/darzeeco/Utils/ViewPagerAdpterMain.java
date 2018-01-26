package com.taggroup.www.darzeeco.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.taggroup.www.darzeeco.R;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by muhammad.sufwan on 11/14/2017.
 */

public class ViewPagerAdpterMain extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<SliderUtils> sliderImg;
    private ImageLoader imageLoader;
    //private Integer[] images={R.drawable.design,R.drawable.design1,R.drawable.design2,R.drawable.design3,R.drawable.design4};

    public ViewPagerAdpterMain(List<SliderUtils> sliderImg,Context context) {
        this.sliderImg = sliderImg;
        this.context = context;
    }


    @Override
    public int getCount() {
        return sliderImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,null);

        SliderUtils utils = sliderImg.get(position);

        ImageView imageView = view.findViewById(R.id.imageView2);
        //imageView.setImageResource(images[position]);

        imageLoader = CustomeVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(utils.getSliderImageUrl(), ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Toast.makeText(context,"first item",Toast.LENGTH_SHORT).show();
                }
                if (position == 1){
                    Toast.makeText(context,"Second item",Toast.LENGTH_SHORT).show();
                }
                if (position == 2){
                    Toast.makeText(context,"Third item",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
