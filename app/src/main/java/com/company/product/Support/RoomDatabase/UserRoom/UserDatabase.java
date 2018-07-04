package com.company.product.Support.RoomDatabase.UserRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.company.product.LoginModule.Model.UserLoginModel;

@Database(entities = {UserLoginModel.ResultBean.class}, version = 1, exportSchema = true)
public abstract class UserDatabase extends RoomDatabase
  {
	 public abstract UserDAO PersonDatabase();
  }
