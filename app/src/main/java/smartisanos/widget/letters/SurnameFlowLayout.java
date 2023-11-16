package smartisanos.widget.letters;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SurnameFlowLayout extends ViewGroup {

    /* renamed from: a */
    private List<List<View>> f4551a;

    /* renamed from: b */
    private List<Integer> f4552b;

    public SurnameFlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4551a = new ArrayList();
        this.f4552b = new ArrayList();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        Log.e("FlowLayout", size + "," + size2);
        int i6 = 0;
        int childCount = getChildCount();
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int measuredHeight = marginLayoutParams.bottomMargin + childAt.getMeasuredHeight() + marginLayoutParams.topMargin;
            if (i8 + measuredWidth > size) {
                i3 = i6 + i7;
                i4 = Math.max(i8, measuredWidth);
                i5 = measuredHeight;
            } else {
                measuredWidth += i8;
                int max = Math.max(i7, measuredHeight);
                i3 = i6;
                i4 = i9;
                i5 = max;
            }
            if (i10 == childCount - 1) {
                i4 = Math.max(i4, measuredWidth);
                i3 += i5;
            }
            i10++;
            i7 = i5;
            i8 = measuredWidth;
            i9 = i4;
            i6 = i3;
        }
        if (mode != 1073741824) {
            size = i9;
        }
        setMeasuredDimension(size, mode2 == 1073741824 ? size2 : i6);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        this.f4551a.clear();
        this.f4552b.clear();
        int width = getWidth();
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (marginLayoutParams.leftMargin + measuredWidth + marginLayoutParams.rightMargin + i7 > width) {
                this.f4552b.add(Integer.valueOf(i6));
                this.f4551a.add(arrayList);
                i7 = 0;
                arrayList = new ArrayList();
            }
            int i9 = measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7;
            int max = Math.max(i6, marginLayoutParams.bottomMargin + marginLayoutParams.topMargin + measuredHeight);
            arrayList.add(childAt);
            i8++;
            i6 = max;
            i7 = i9;
        }
        this.f4552b.add(Integer.valueOf(i6));
        this.f4551a.add(arrayList);
        int i10 = 0;
        int size = this.f4551a.size();
        int i11 = 0;
        int i12 = 0;
        while (i11 < size) {
            List<View> list = this.f4551a.get(i11);
            int intValue = this.f4552b.get(i11).intValue();
            int i13 = 0;
            while (i13 < list.size()) {
                View view = list.get(i13);
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i14 = marginLayoutParams2.leftMargin + i10;
                    int i15 = marginLayoutParams2.topMargin + i12;
                    int measuredWidth2 = view.getMeasuredWidth() + i14;
                    int measuredHeight2 = view.getMeasuredHeight() + i15;
                    Log.e("FlowLayout", view + " , l = " + i14 + " , t = " + i2 + " , r =" + measuredWidth2 + " , b = " + measuredHeight2);
                    view.layout(i14, i15, measuredWidth2, measuredHeight2);
                    i5 = view.getMeasuredWidth() + marginLayoutParams2.rightMargin + marginLayoutParams2.leftMargin + i10;
                } else {
                    i5 = i10;
                }
                i13++;
                i10 = i5;
            }
            i10 = 0;
            i11++;
            i12 += intValue;
        }
    }
}
