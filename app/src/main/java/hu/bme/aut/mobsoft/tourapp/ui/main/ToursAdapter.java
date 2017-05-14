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

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;
import hu.bme.aut.mobsoft.tourapp.utils.StringUtil;

/**
 * Created by vcsontos on 2017. 05. 12..
 */

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.CustomViewHolder> {

    private static final String TAG = Constants.LOG_PREFIX + ToursAdapter.class.getSimpleName();

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
        if (tour != null) {
            customViewHolder.tourName.setText(fromHtml(tour.getTourName()));
            customViewHolder.startDate.setText(fromHtml(StringUtil.dateFormatter(tour.getStartDate())));
            customViewHolder.difficulty.setText(fromHtml(tour.getDifficulty().toString().toLowerCase()));
            if (tour.getMembers() != null) {
                customViewHolder.members.setText(fromHtml(String.valueOf(tour.getMembers().size())));
            } else {
                customViewHolder.members.setText(fromHtml("0"));
            }
            addCategoryImageToCVH(tour, customViewHolder);
        }
    }

    private void addCategoryImageToCVH(Tour tour, CustomViewHolder customViewHolder) {

        if (tour.getCategory() != null) {
            switch (tour.getCategory()) {
                case WALKING:
                    customViewHolder.category.setImageResource(R.drawable.category_walking_tour);
                    break;
                case CYCLING:
                    customViewHolder.category.setImageResource(R.drawable.category_cycling_tour);
                    break;
                case WATER:
                    customViewHolder.category.setImageResource(R.drawable.category_water_tour);
                    break;
                case MOUNTAIN:
                    customViewHolder.category.setImageResource(R.drawable.category_mountain_tour);
                    break;
            }
        }
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

    public void setFilter(List<Tour> filteredTours) {
        tours = new ArrayList<>();
        tours.addAll(filteredTours);
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tourName;
        protected TextView startDate;
        protected ImageView category;
        protected TextView difficulty;
        protected TextView members;

        public CustomViewHolder(View view) {
            super(view);
            this.tourName = (TextView) view.findViewById(R.id.tour_name_tv);
            this.startDate = (TextView) view.findViewById(R.id.tour_start_date_tv);
            this.category = (ImageView) view.findViewById(R.id.tour_category_img);
            this.difficulty = (TextView) view.findViewById(R.id.tour_difficulty_tv);
            this.members = (TextView) view.findViewById(R.id.tour_members_tv);
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
