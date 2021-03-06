package com.ta.solamo.bottomsheetdialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ta.solamo.MainActivity;
import com.ta.solamo.MenuActivity;
import com.ta.solamo.OrderActivity;
import com.ta.solamo.R;
import com.ta.solamo.model.CartModel;
import com.ta.solamo.temp.Temp;

public class BottomSheetDialogMenuDetail extends BottomSheetDialogFragment {

    String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet_menu, container, false);

        ImageView imageViewThumb = v.findViewById(R.id.img_thumbDetail);
        TextView textViewTittle = v.findViewById(R.id.tv_tittleDetail);
        TextView textViewPrice = v.findViewById(R.id.tv_priceDetail);
        ImageButton imageButtonDismiss = v.findViewById(R.id.btn_closeDetail);
        AppCompatButton appCompatButtonBuy = v.findViewById(R.id.btn_buyDetail);

        Bundle bundle = getArguments();
        final String tittle = bundle.getString("posisiTittle");
        final String image = bundle.getString("posisiImage");
        final int price = bundle.getInt("posisiPrice");

        textViewTittle.setText(String.valueOf(tittle));
        textViewPrice.setText("Rp. " + String.valueOf(price));
        //imageViewThumb.setImageDrawable();

        Glide.with(getContext())
                .load(image)
                .into(imageViewThumb);

        imageButtonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        appCompatButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temp.cartModels.add(new CartModel(++Temp.count_id, tittle, price, Temp.count_quantity, image));
                Log.d(TAG, "onClick: " + Temp.cartModels.size());
                for (int i = 0; i < Temp.cartModels.size(); i++) {
                    Log.d(TAG, "onClick: " + Temp.cartModels.get(i).getCart_id());
                    Log.d(TAG, "onClick: " + Temp.cartModels.get(i).getCart_nameItem());
                    Log.d(TAG, "onClick: " + Temp.cartModels.get(i).getCart_priceItem());
                    Log.d(TAG, "onClick: " + Temp.cartModels.get(i).getCart_quantity());
                    Log.d(TAG, "onClick: " + Temp.cartModels.get(i).getCart_image());
                }
                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(android.R.id.content), "Added to Cart", Snackbar.LENGTH_LONG);
                snackbar.show();
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListenerDetail {
        void onBottomSheetClickedDetail();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            BottomSheetListenerDetail bottomSheetListener = (BottomSheetListenerDetail) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Implemet BottomSheetListener interface");
        }
    }
}
