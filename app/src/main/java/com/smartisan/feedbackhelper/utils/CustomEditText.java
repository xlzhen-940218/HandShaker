package com.smartisan.feedbackhelper.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;

/* loaded from: classes.dex */
public class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        this(context, null);
    }

    public CustomEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public CustomEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(CustomEditText.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(CustomEditText.class.getName());
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        switch (i) {
            case 2097152:
                CharSequence charSequence = bundle != null ? bundle.getCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE") : null;
                setText(charSequence);
                if (charSequence != null && charSequence.length() > 0) {
                    setSelection(charSequence.length());
                }
                return true;
            default:
                return super.performAccessibilityAction(i, bundle);
        }
    }
}
