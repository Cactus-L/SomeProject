package com.example.fragment;

import com.example.rollcall.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 
 * @author Cactus
 *
 */
public class CallFragment extends Fragment {

	public Context context;
	private LinearLayout l;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		System.out.println("CallFragment");
		return inflater.inflate(R.layout.main_call, container, false);
	}

	@SuppressLint("NewApi")
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		Fragment showClass = new ShowClassFragment();
		FragmentTransaction tran = getFragmentManager().beginTransaction();
		tran.replace(R.id.main_call, showClass);
		tran.commit();
	}

}