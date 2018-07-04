package com.company.product.LoginModule.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.product.LoginModule.Model.UserLoginModel;
import com.company.product.Support.RoomDatabase.UserRoom.UserRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel
  {
	 private UserRepository personRepository = new UserRepository(this.getApplication());
	 private final Executor executor = Executors.newFixedThreadPool(2);

	 private final MediatorLiveData<List<UserLoginModel.ResultBean>> personsByCity = new MediatorLiveData<>();

	 private final MediatorLiveData<String> mobileNo = new MediatorLiveData<>();

	 //transformation applied so that observer to this LiveData can be added only once
	 private final LiveData<UserLoginModel.ResultBean> personsByMobile = Transformations.switchMap(mobileNo, (mobile) -> {
		return personRepository.getPersonByMobile(mobile);
	 });

	 public LiveData<List<UserLoginModel.ResultBean>> getPersonsByCityLive()
		{
		  return personsByCity;
		}

	 public UserViewModel(@NonNull Application application)
		{
		  super(application);
		}

	 //Room DAO call needs to be run on background thread
	 //This example uses Executor
	 public void addPerson(UserLoginModel.ResultBean p)
		{
		  executor.execute(() -> {
			 personRepository.addPerson(p);
		  });
		}

	 public void updatePerson(UserLoginModel.ResultBean p)
		{
		  executor.execute(() -> {
			 personRepository.updatePerson(p);
		  });
		}

	 public void deletePerson(UserLoginModel.ResultBean p)
		{
		  executor.execute(() -> {
			 personRepository.deletePerson(p);
		  });
		}

	 //Since room DAO returns LiveData, it runs on background thread.
	 public LiveData<List<UserLoginModel.ResultBean>> getAllUsers()
		{
		  return personRepository.getAllPersons();
		}

	 //Room DAO call needs to be run on background thread
	 //This example uses AsyncTask
	 public void getUsersByCity(List<String> cities)
		{
		  new AsyncTask<Void, Void, List<UserLoginModel.ResultBean>>()
			 {
				@Override
				protected List<UserLoginModel.ResultBean> doInBackground(Void... params)
				  {
					 return personRepository.getPersonsByCity(cities);
				  }

				@Override
				protected void onPostExecute(List<UserLoginModel.ResultBean> personLst)
				  {
					 Log.d("", "persons by city " + personLst.size());
					 personsByCity.setValue(personLst);
				  }
			 }.execute();
		}

	 //sets the mobile number to LiveData object,
	 //transformation using switchMap intern calls room DAO method which return LiveData
	 public void setMobile(String mobile)
		{
		  mobileNo.setValue(mobile);
		}

	 public LiveData<UserLoginModel.ResultBean> getPersonsByMobile()
		{
		  return personsByMobile;
		}
  }
