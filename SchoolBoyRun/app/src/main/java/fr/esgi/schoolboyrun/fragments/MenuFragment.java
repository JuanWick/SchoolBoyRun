package fr.esgi.schoolboyrun.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.schoolboyrun.R;
import fr.esgi.schoolboyrun.activities.ScoresActivity;
import fr.esgi.schoolboyrun.activities.GameActivity;
import fr.esgi.schoolboyrun.activities.ScoresGoogleActivity;
import fr.esgi.schoolboyrun.activities.SettingsActivity;
import fr.esgi.schoolboyrun.fragments.interfaces.IAskNameDialogFragment;
import fr.esgi.schoolboyrun.manager.UserManager;

import static android.app.Activity.RESULT_OK;
import static fr.esgi.schoolboyrun.helpers.ViewUtil.initFragment;
import static fr.esgi.schoolboyrun.helpers.ViewUtil.isDualPane;

public class MenuFragment extends Fragment {
    @BindView(R.id.fragment_menu_play_btn) Button mPlayButton;
    @BindView(R.id.fragment_menu_score_btn) Button mScoreButton;
    @BindView(R.id.fragment_menu_param_btn) Button mParamButton;
    @BindView(R.id.fragment_menu_score_google_btn) Button mScoreGoogleButton;
    private static int GAME_REQUEST_CODE = 33;
    private boolean mIsDualPane;

    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /** initialisation de la library butterknife **/
        ButterKnife.bind(this,getView());

        mIsDualPane = isDualPane(getActivity());

        /** Not yet implemented **/
        mScoreGoogleButton.setEnabled(false);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager userManager = UserManager.getCurrentUserManager();

                if(userManager.checkUserIsSet()){
                    Intent gameIntent = new Intent(getActivity(), GameActivity.class);
                    startActivity(gameIntent);
                } else {
                    userManager.askUserName(getActivity(),"game");
                }

            }
        });

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mIsDualPane){
                    Intent scoresIntent = new Intent(getActivity().getApplicationContext(), ScoresActivity.class);
                    startActivity(scoresIntent);
                } else {
                    initFragment(getActivity(), ScoresFragment.newInstance(), R.id.custom_fragment);
                }
            }
        });

        mScoreGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mIsDualPane){
                    Intent scoresGoogleIntent = new Intent(getActivity().getApplicationContext(), ScoresGoogleActivity.class);
                    startActivity(scoresGoogleIntent);
                } else {
                    initFragment(getActivity(), ScoresGoogleFragment.newInstance(), R.id.custom_fragment);
                }
            }
        });

        mParamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mIsDualPane){
                    Intent settingsIntent = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                    startActivity(settingsIntent);
                } else {
                    initFragment(getActivity(), SettingsFragment.newInstance(), R.id.custom_fragment);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
