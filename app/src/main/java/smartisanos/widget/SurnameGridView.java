package smartisanos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/* loaded from: classes.dex */
public class SurnameGridView extends GridView {

    /* renamed from: a */
    private int f4405a;

    /* renamed from: b */
    private int f4406b;

    public SurnameGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4405a = -1;
        this.f4406b = -1;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = -1;
        int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.f4405a = pointToPosition;
                i = pointToPosition;
                break;
            case 2:
                if (this.f4405a == pointToPosition) {
                    i = this.f4405a;
                    break;
                }
                break;
        }
        if (this.f4406b != i) {
            this.f4406b = i;
        }
        return super.onTouchEvent(motionEvent);
    }
}
