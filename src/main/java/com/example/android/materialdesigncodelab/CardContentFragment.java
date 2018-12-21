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
public class CardContentFragment extends Fragment {


    public CardContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view,
                container,
                false);
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getActivity());
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

        private static final int LENGTH = 18;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlacePictures;

        public MyRecyclerAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = new Drawable[a.length()];
            for (int i = 0; i < mPlacePictures.length; i++) {
                mPlacePictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(
                            R.layout.item_card,
                            parent,
                            false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
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

            public ImageView picture;
            public TextView name;
            public TextView description;

            public MyViewHolder(View itemView) {
                super(itemView);
                picture = (ImageView) itemView.findViewById(R.id.card_image);
                name = (TextView) itemView.findViewById(R.id.card_title);
                description = (TextView) itemView.findViewById(R.id.card_text);
            }
        }
    }
}
