package smartisanos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class PopupMenuStandardListItem extends RelativeLayout {

    /* renamed from: a */
    private ImageView f4237a;

    public PopupMenuStandardListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(PopupMenuStandardListItem.class.getName());
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(this.f4237a.getVisibility() == View.VISIBLE);
    }
}
