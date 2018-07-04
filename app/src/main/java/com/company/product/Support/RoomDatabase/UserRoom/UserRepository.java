package com.company.product.Support.RoomDatabase.UserRoom;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.company.product.LoginModule.Model.UserLoginModel;
import com.company.product.Support.RoomDatabase.DatabaseCreator;

import java.util.List;

public class UserRepository
  {

	 private final UserDAO userDAO;

	 public UserRepository(Context context)
		{
		  userDAO = DatabaseCreator.getPersonDatabase(context).PersonDatabase();
		}

	 public void addPerson(UserLoginModel.ResultBean p)
		{
		  long rec = userDAO.insertPerson(p);
		  Log.d("db insert ", "added " + rec);
		}

	 public void updatePerson(UserLoginModel.ResultBean p)
		{
		  userDAO.updatePerson(p);
		}

	 public void deletePerson(UserLoginModel.ResultBean p)
		{
		  userDAO.deletePerson(p);
		}

	 public LiveData<List<UserLoginModel.ResultBean>> getAllPersons()
		{
		  return userDAO.getAllPersons();
		}

	 public List<UserLoginModel.ResultBean> getPersonsByCity(List<String> cities)
		{
		  return userDAO.getPersonByCities(cities);
		}

	 public LiveData<UserLoginModel.ResultBean> getPersonByMobile(String mobile)
		{
		  return userDAO.getPersonByMobile(mobile);
		}
  }