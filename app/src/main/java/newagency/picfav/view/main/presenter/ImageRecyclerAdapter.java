package newagency.picfav.view.main.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import newagency.picfav.R;
import newagency.picfav.view.main.presenter.model.ImageItemModel;

/**
 * Created by oroshka on 1/30/18.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageHolder> {

    public interface ImageRecyclerAdapterCallback {

        void onChangeCountSelected(int count);
    }

    private List<ImageItemModel> mImageItemList;

    private Context mContext;

    private ImageRecyclerAdapterCallback mCallback;

    public ImageRecyclerAdapter(Context context, ImageRecyclerAdapterCallback callback) {
        mContext = context;
        this.mCallback = callback;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        holder.bind(mImageItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageItemList != null ? mImageItemList.size() : 0;
    }

    public void addAll(List<ImageItemModel> imageItemList) {
        if (this.mImageItemList == null) {
            this.mImageItemList = new ArrayList<>();
        }
        this.mImageItemList.clear();
        this.mImageItemList.addAll(imageItemList);
        notifyDataSetChanged();
    }

    public void add(ImageItemModel imageItemModel) {
        if (mImageItemList == null) {
            mImageItemList = new ArrayList<>();
        }
        mImageItemList.add(imageItemModel);
        notifyItemInserted(mImageItemList.size() - 1);
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        CardView mWrapperCv;

        public ImageHolder(final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.game_iv);
            mWrapperCv = itemView.findViewById(R.id.wrapper_image_cv);

            mWrapperCv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int positionClicked = getAdapterPosition();
                    if (positionClicked != -1) {
                        ImageItemModel imageItemModel = mImageItemList.get(positionClicked);
                        imageItemModel.setSelected(!imageItemModel.isSelected());
                        notifyDataSetChanged();
                    }
                    return false;
                }
            });
        }

        public void bind(ImageItemModel imageItem) {
            int colorBackground = imageItem.isSelected()
                    ? ContextCompat.getColor(mContext, R.color.colorAccent)
                    : ContextCompat.getColor(mContext, R.color.colorWhite);
            mWrapperCv.setCardBackgroundColor(colorBackground);

            Glide.with(mContext)
                    .load(imageItem.getUrl())
                    .into(mImageView);
        }
    }
}
