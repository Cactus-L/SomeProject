package com.example.fragment;

import com.example.rollcall.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 
 * @author Cactus
 *
 */
public class FragmentAdapter extends FragmentPagerAdapter {

	public final static int TAB_NUM = 3;

	public FragmentAdapter(FragmentManager fm)
	{
		super(fm);
		// TODO Auto-generated constructor stub
	}
	/**
	* 获取给定位置对应的Fragment。
	*
	* @param position 给定的位置
	* @return 对应的Fragment
	*/

	@Override
	public Fragment getItem(int id)
	{
		switch (id)
		{
		case MainActivity.TAB_CALL:
			CallFragment callFragment = new CallFragment();
			return callFragment;
		case MainActivity.TAB_COUNT:
			CountFragment countFragment = new CountFragment();
			return countFragment;
		case MainActivity.TAB_MORE:
			MoreFragment moreFragment = new MoreFragment();
			return moreFragment;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount()
	{
		return TAB_NUM;
	}

}
