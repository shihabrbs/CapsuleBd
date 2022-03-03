package com.rightbrain.officeproject.room;

import android.view.View;

public interface RecyclerViewItemClick {
  /*  void onItemClick(int position);*/
    void onClick(View view, int position);
    void onClickSize(View view, int position);
    void onClickImageGallery(View view, int position);
}
