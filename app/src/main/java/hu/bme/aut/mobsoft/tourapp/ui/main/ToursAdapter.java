package hu.bme.aut.mobsoft.tourapp.ui.main;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.model.Tour;

/**
 * Created by vcsontos on 2017. 05. 12..
 */

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.CustomViewHolder> {

    private List<Tour> tours;
    private Context context;

    public ToursAdapter(Context context, List<Tour> tours) {
        this.tours = tours;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tour_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Tour tour = tours.get(i);
        customViewHolder.tourName.setText(fromHtml(tour.getTourName()));
        customViewHolder.startDate.setText(fromHtml(tour.getStartDate().toString()));
        customViewHolder.difficulty.setText(fromHtml(tour.getDifficulty().toString()));
        customViewHolder.category.setImageResource(0); // TODO select correct category image
        customViewHolder.members.setText(fromHtml(tour.getMembers().size() + "people joined"));
    }

    @Override
    public int getItemCount() {
        return (null != tours ? tours.size() : 0);
    }

    public Tour getItem(int position) {
        if (getItemCount() < position) {
            return null;
        } else {
            return tours.get(position);
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tourName;
        protected TextView startDate;
        protected ImageView category;
        protected TextView difficulty;
        protected TextView members;

        public CustomViewHolder(View view) {
            super(view);
            // TODO findViewById from tour_item.xml
            this.tourName = null;
            this.startDate = null;
            this.category = null;
            this.difficulty = null;
            this.members = null;
        }
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
