package com.example.a16046562.p12_mydatabook;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class VaccinationFragment extends Fragment {
    Button btnedit;
    TextView tv;
    FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    SharedPreferences prefs;
    String str = "";

    public VaccinationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccination, container, false);

        btnedit = (Button) view.findViewById(R.id.btnedit);
        tv = (TextView) view.findViewById(R.id.tv);
        fab = view.findViewById(R.id.fab);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawerList = (ListView) getActivity().findViewById(R.id.left_drawer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //action to open navigation drawer
                drawerLayout.openDrawer(drawerList);
            }
        });
        prefs = getActivity().getSharedPreferences("prefsVac", MODE_PRIVATE);
        str = prefs.getString("vac", "");
        tv.setText(str);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflaterdialog = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout passPhrase = (LinearLayout) inflaterdialog.inflate(R.layout.bioedit_dialog, null);
                final EditText et = (EditText) passPhrase.findViewById(R.id.editText);
                et.setText(str);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Vaccination")
                        .setView(passPhrase)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                tv.setText(et.getText().toString());
                                str = et.getText().toString();
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("vac", et.getText().toString());
                                editor.apply();
                            }
                        })
                        //1 way of dismiss dialog
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        //2nd way of doing nothing
                        //.setNeutralButton("Cancel",null);
                        ;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }
}