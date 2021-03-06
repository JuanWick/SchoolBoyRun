package fr.esgi.schoolboyrun.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.schoolboyrun.R;
import fr.esgi.schoolboyrun.fragments.interfaces.IAskNameDialogFragment;
import fr.esgi.schoolboyrun.manager.UserManager;

import static fr.esgi.schoolboyrun.helpers.ViewUtil.initFragment;

/**
 * Created by JUAN_work on 07/01/2018.
 */

public class AskNameDialogFragment extends DialogFragment {

    private IAskNameDialogFragment iAskNameDialogFragment;
    public String from;

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.ask_name)
                .setPositiveButton(R.string.lets_start, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText mName = (EditText) getDialog().findViewById(R.id.AskNameDialog_username);

                        UserManager.getCurrentUserManager().setLastUser(getActivity(),mName.getText().toString());

                 if(null != iAskNameDialogFragment){
                     iAskNameDialogFragment.onDialogPositiveClick(getDialog(),from);
                 }
                    }
                });
        builder.setView(R.layout.dialog_ask_name);
        builder.setCancelable(false);
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IAskNameDialogFragment) {
            iAskNameDialogFragment = (IAskNameDialogFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iAskNameDialogFragment = null;
    }
}

