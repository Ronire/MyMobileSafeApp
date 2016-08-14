package com.lv.mymobilesafeapp.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.bean.LinkMan;
import com.lv.mymobilesafeapp.listener.MyRadioListener;
import com.lv.mymobilesafeapp.view.CharacterParser;
import com.lv.mymobilesafeapp.view.PinyinComparator;
import com.lv.mymobilesafeapp.view.SideBar;
import com.lv.mymobilesafeapp.view.SortAdapter;

public class MainActivity1 extends Activity implements MyRadioListener{

	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;

	

	private CharacterParser characterParser;
	private List<LinkMan> SourceDateList;
	

	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		initViews();
	}

	private void initViews() {

		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);


		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {

				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {

				Toast.makeText(getApplication(), ((LinkMan) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});

		//SourceDateList = filledData(getResources().getStringArray(R.array.date));
		SourceDateList = filledData(new String[]{"李四"});


		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);


	}
		





	private List<LinkMan> filledData(String [] date){
		List<LinkMan> mSortList = new ArrayList<LinkMan>();
		
		for(int i=0; i<date.length; i++){
			LinkMan sortModel = new LinkMan();
			sortModel.setName(date[i]);

			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			

			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}





	private void filterData(String filterStr){
		List<LinkMan> filterDateList = new ArrayList<LinkMan>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(LinkMan sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		

		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	@Override
	public boolean isShown(boolean flag) {
		return false;
	}
}
