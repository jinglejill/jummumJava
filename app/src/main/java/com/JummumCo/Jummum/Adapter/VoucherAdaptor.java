package com.JummumCo.Jummum.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Interface.RecyclerViewListener;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.android.jummum.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoucherAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;

    private List<HotDealData> items;
    private LayoutInflater inflater;
    private @LayoutRes
    int layout;

    private RecyclerViewListener<HotDealData> recyclerViewListener;

    public VoucherAdaptor(List<HotDealData> items, RecyclerView recyclerView, int perPage, @LayoutRes int layout) {
        this.items = items;
        visibleThreshold = perPage;

        this.layout = layout;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                        if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                            recyclerViewListener.onLoadMore();
                            loading = true;
                        }

                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        View itemView;
        if (viewType == VIEW_ITEM) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
            return new ViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            return new ProgressViewHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final HotDealData item = items.get(position);
            ((ViewHolder) holder).bindView(item);
            ((ViewHolder) holder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewListener.onClick(item);
                }
            });
        } else {
            ((ProgressViewHolder) holder).setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setListener(RecyclerViewListener<HotDealData> listener) {
        this.recyclerViewListener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        loading = false;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.list_item)
        LinearLayout listItem;


        @BindView(R.id.tv_title_type)
        TextView tvTitleType;
        @BindView(R.id.tv_desc_type)
        TextView tvDescType;
        @BindView(R.id.image_view_type)
        ImageView imageViewType;

        @BindView(R.id.container_type)
        LinearLayout containerType;
        @BindView(R.id.container)
        LinearLayout container;


        @BindView(R.id.tv_title_reward)
        TextView tvTitleReward;
        @BindView(R.id.tv_desc_reward)
        TextView tvDescReward;
        @BindView(R.id.txt_point)
        TextView txtPoint;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.container_reward)
        LinearLayout containerReward;
        @BindView(R.id.img_title_reward)
        ImageView imgTitleReward;

        private int typeUI = 1;
        private CountDownTimer timer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bindView(final HotDealData item)
        {

            if (item.getType().equals("1"))
            {
                containerType.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                containerReward.setVisibility(View.GONE);
                tvTitle.setText(item.getHeader());
                tvDesc.setText(item.getSubTitle());

                new CommonRepository().getImage(item.getImageUrl(), "3", "0", new IHttpCallback<List<ImageResultData>>() {
                    @Override
                    public void onSuccess(List<ImageResultData> response) {
//                        String imageName = item.getBranchImageUrl();
                        if (response.get(0).getBase64StringImage() != null) {
                            byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                            Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                            imageView.setImageBitmap(decode);
                        }else{
                            new CommonRepository().getImage(item.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
                                @Override
                                public void onSuccess(List<ImageResultData> response) {
                                    byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                    Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                    imageView.setImageBitmap(decode);
                                }

                                @Override
                                public void onError(String message) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onError(String message) {
                        new CommonRepository().getImage(item.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
                            @Override
                            public void onSuccess(List<ImageResultData> response) {
                                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                imageView.setImageBitmap(decode);
                            }

                            @Override
                            public void onError(String message) {

                            }
                        });
                    }
                });
            }
            else
            {
                if (item.getRewardRedemptionID() != null){
                    containerType.setVisibility(View.GONE);
                    container.setVisibility(View.GONE);
                    containerReward.setVisibility(View.VISIBLE);


                    tvTitleReward.setText(item.getHeader());
                    tvDescReward.setText(item.getSubTitle());


                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String yourFormattedString = formatter.format(Integer.parseInt(item.getPoint()));
                    txtPoint.setText(yourFormattedString);


                    if (typeUI == 1) {
                        txtDate.setVisibility(View.VISIBLE);
                        if (item.getWithInPeriod() == null){
                            item.setWithInPeriod(" ");
                        }
                    }else{
                        txtDate.setVisibility(View.GONE);
                    }


                    new CommonRepository().getImage(item.getImageUrl(), "3", "0", new IHttpCallback<List<ImageResultData>>() {
                        @Override
                        public void onSuccess(List<ImageResultData> response) {
//                        String imageName = item.getBranchImageUrl();
                            if (response.get(0).getBase64StringImage() != null) {
                                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                imgTitleReward.setImageBitmap(decode);
                            }else{
                                new CommonRepository().getImage(item.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
                                    @Override
                                    public void onSuccess(List<ImageResultData> response) {
                                        byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                        Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                        imgTitleReward.setImageBitmap(decode);
                                    }

                                    @Override
                                    public void onError(String message) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(String message) {
                            new CommonRepository().getImage(item.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
                                @Override
                                public void onSuccess(List<ImageResultData> response) {
                                    byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                    Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                    imgTitleReward.setImageBitmap(decode);
                                }

                                @Override
                                public void onError(String message) {

                                }
                            });
                        }
                    });

                }
                else
                {
                    containerType.setVisibility(View.VISIBLE);
                    container.setVisibility(View.GONE);
                    tvTitleType.setText(item.getHeader());
                    tvDescType.setText(item.getSubTitle());

                    containerReward.setVisibility(View.GONE);

                    new CommonRepository().getImage(item.getImageUrl(), "3", "0", new IHttpCallback<List<ImageResultData>>() {
                        @Override
                        public void onSuccess(List<ImageResultData> response) {
                            if (response.get(0).getBase64StringImage() != null) {
                                byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                imageViewType.setImageBitmap(decode);
                            } else {
                                new CommonRepository().getImage(item.getImageUrl(), "4", "0", new IHttpCallback<List<ImageResultData>>() {
                                    @Override
                                    public void onSuccess(List<ImageResultData> response) {
                                        byte[] decodeString = Base64.decode(response.get(0).getBase64StringImage(), Base64.DEFAULT);
                                        Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                        imageView.setImageBitmap(decode);
                                    }

                                    @Override
                                    public void onError(String message) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Log.i("debug = ", message);
                        }
                    });
                }
            }
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            listItem.setOnClickListener(onClickListener);
        }
    }


    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar1)
        ProgressBar progressBar1;

        ProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setIndeterminate(boolean indeterminate) {
            progressBar1.setIndeterminate(indeterminate);
        }
    }
}