package com.example.administrator.gitdemo;

import android.app.Activity;
import android.app.Dialog;
import android.view.WindowManager;

public class SafeDialog {
    private Dialog mDialog;
    private DialogCreator mDialogCreator;


    public SafeDialog setDialogCreator(DialogCreator dialogCreator){
        mDialogCreator = dialogCreator;
        return this;
    }


    public boolean show(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()
                || (mDialog == null && mDialogCreator == null)) {
            return false;
        }

        mDialog = mDialog == null ? mDialogCreator.create(activity) : mDialog;

        if (mDialog != null) {
            try {
                mDialog.show();
            } catch (WindowManager.BadTokenException b) {
                return false;
            }
        }
        return true;
    }

    public void dismiss(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed() || mDialog == null || !mDialog.isShowing()) {
            return;
        }
        try {
            mDialog.dismiss();
        } catch (IllegalStateException e) {

        }
    }
}
