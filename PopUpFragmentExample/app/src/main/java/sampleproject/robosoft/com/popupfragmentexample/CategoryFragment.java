package sampleproject.robosoft.com.popupfragmentexample;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Gadagool Krishna on 2/5/2016.
 */
public class CategoryFragment extends Fragment {
    private Context mContext;
    private RelativeLayout mCategoryLyt;

    public CategoryFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View categoryView = inflater.inflate(R.layout.category_fragment, container, false);
        mCategoryLyt = (RelativeLayout) categoryView.findViewById(R.id.category_lyt);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setBottomTranslteAnimation();
        }
        return categoryView;

    }

    private void setBottomTranslteAnimation() {
        if (mCategoryLyt != null) {
            ObjectAnimator mAnimatorGlobal = ObjectAnimator.ofFloat(mCategoryLyt, "translationY", 500, 0);
            mAnimatorGlobal.setDuration(350);
            mAnimatorGlobal.start();
        }
    }
}
