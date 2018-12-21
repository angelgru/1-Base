package com.example.android.materialdesigncodelab;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListContentFragment extends Fragment {


    public ListContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView =
                (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter contentAdapter = new ContentAdapter(getContext());
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }

    public class ContentAdapter
            extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

        private static final int LENGTH = 18;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlaceAvators;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(
                                    R.layout.item_list,
                                    parent,
                                    false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            holder.name.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView avator;
            public TextView name;
            public TextView description;

            public MyViewHolder(final View itemView) {
                super(itemView);
                avator = (ImageView) itemView.findViewById(R.id.list_avatar);
                name = (TextView) itemView.findViewById(R.id.list_title);
                description = (TextView) itemView.findViewById(R.id.list_desc);
            }
        }
    }

}
