package smartisanos.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/* loaded from: classes.dex */
public class PasswordEditText extends androidx.appcompat.widget.AppCompatEditText {

    /* renamed from: a */
    private HandlerC0924a f4225a;

    /* renamed from: b */
    private Runnable f4226b;

    /* renamed from: c */
    public boolean f4227c;

    /* renamed from: d */
    public int f4228d;

    /* renamed from: e */
    public int f4229e;

    /* renamed from: f */
    private int f4230f;

    /* renamed from: g */
    private int f4231g;

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.TextView
    public void setInputType(int i) {
        m77a(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m77a(int i, boolean z) {
        super.setInputType(i);
        int i2 = i & 4095;
        if (i2 == 129 || i2 == 225 || i2 == 18) {
            this.f4228d = i2;
            this.f4229e = i2 | 144;
            this.f4227c = false;
        } else if ((i2 & 144) != 0) {
            this.f4228d = i2 - 144;
            if (this.f4228d == 1) {
                this.f4228d |= 128;
            } else if (this.f4228d == 2) {
                this.f4228d |= 16;
            }
            this.f4229e = i2;
            this.f4227c = true;
        }
        if (z) {
            this.f4225a.m71b();
        }
    }

    @Override // android.widget.TextView
    public int getCompoundPaddingRight() {
        int compoundPaddingRight = super.getCompoundPaddingRight();
        Drawable m72a = this.f4225a.m72a();
        if (m72a != null) {
            return Math.max(compoundPaddingRight, this.f4230f) + m72a.getIntrinsicWidth();
        }
        return compoundPaddingRight;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        Drawable m72a = this.f4225a.m72a();
        int i2 = 0;
        if (getParent() != null) {
            try {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((View) getParent()).getLayoutParams();
                if (marginLayoutParams == null) {
                    i = 0;
                } else {
                    i = marginLayoutParams.rightMargin;
                }
                i2 = i;
            } catch (ClassCastException e) {
            }
        }
        if (m72a != null) {
            int intrinsicHeight = m72a.getIntrinsicHeight();
            int intrinsicWidth = m72a.getIntrinsicWidth();
            int height = ((getHeight() - intrinsicHeight) / 2) + this.f4231g;
            int width = ((getWidth() - intrinsicWidth) - (this.f4230f - i2)) + getScrollX();
            m72a.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
            m72a.draw(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: smartisanos.widget.PasswordEditText$a */
    /* loaded from: classes.dex */
    public final class HandlerC0924a extends Handler {

        /* renamed from: a */
         /* synthetic */ PasswordEditText f4232a;

        /* renamed from: b */
        private  int f4233b;

        /* renamed from: c */
        private  AnimationDrawable f4234c;

        /* renamed from: d */
        private int f4235d;

        /* renamed from: e */
        private int f4236e;

        public HandlerC0924a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    m68e();
                    return;
                case 1:
                    m68e();
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        final Drawable m72a() {
            return this.f4234c.getFrame(this.f4235d);
        }

        /* renamed from: b */
        final void m71b() {
            this.f4235d = this.f4232a.f4227c ? 0 : this.f4233b - 1;
        }

        /* renamed from: c */
        final void m70c() {
            this.f4236e = this.f4232a.f4227c ? 1 : -1;
        }

        /* renamed from: d */
        final int m69d() {
            int duration = this.f4234c.getDuration(this.f4235d);
            if (this.f4236e > 0) {
                return duration * (this.f4233b - this.f4235d);
            }
            if (this.f4236e < 0) {
                return duration * this.f4235d;
            }
            return 0;
        }

        /* renamed from: e */
        private void m68e() {
            removeMessages(1);
            int i = this.f4235d + this.f4236e;
            if (i >= 0 && i < this.f4233b) {
                int duration = this.f4234c.getDuration(this.f4235d);
                this.f4235d = i;
                sendEmptyMessageDelayed(1, duration);
            }
            this.f4232a.invalidate();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Drawable m72a = this.f4225a.m72a();
        if (m72a != null) {
            if (((int) motionEvent.getX()) + getScrollX() >= m72a.getBounds().left) {
                if (motionEvent.getAction() == 1) {
                    this.f4225a.m70c();
                    this.f4225a.sendEmptyMessage(0);
                    if (this.f4226b == null) {
                        this.f4226b = new RunnableC0932c(this);
                    }
                    postDelayed(this.f4226b, this.f4225a.m69d() / 2);
                    return true;
                }
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
