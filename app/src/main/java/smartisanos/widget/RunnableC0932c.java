package smartisanos.widget;

/* compiled from: PasswordEditText.java */
/* renamed from: smartisanos.widget.c */
/* loaded from: classes.dex */
final class RunnableC0932c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ PasswordEditText f4462a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0932c(PasswordEditText passwordEditText) {
        this.f4462a = passwordEditText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int inputType = this.f4462a.getInputType() & (-4096);
        int selectionEnd = this.f4462a.getSelectionEnd();
        if (this.f4462a.f4227c) {
            PasswordEditText passwordEditText = this.f4462a;
            i2 = this.f4462a.f4228d;
            passwordEditText.m77a(inputType | i2, false);
        } else {
            PasswordEditText passwordEditText2 = this.f4462a;
            i = this.f4462a.f4229e;
            passwordEditText2.m77a(inputType | i, false);
        }
        this.f4462a.setSelection(selectionEnd);
    }
}
