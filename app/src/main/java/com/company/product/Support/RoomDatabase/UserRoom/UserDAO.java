package com.company.product.Support.RoomDatabase.UserRoom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.company.product.LoginModule.Model.UserLoginModel;

import java.util.List;

@Dao
interface UserDAO
  {
	 @Insert(onConflict = OnConflictStrategy.REPLACE)
	 long insertPerson(UserLoginModel.ResultBean person);

	 @Update
	 void updatePerson(UserLoginModel.ResultBean person);

	 @Delete
	 void deletePerson(UserLoginModel.ResultBean person);

	 @Query("SELECT * FROM MasterUser")
	 LiveData<List<UserLoginModel.ResultBean>> getAllPersons();

	 @Query("SELECT * FROM MasterUser where userPhone = :mobileIn")
	 LiveData<UserLoginModel.ResultBean> getPersonByMobile(String mobileIn);

	 @Query("SELECT * FROM MasterUser where userLocation In (:cityIn)")
	 List<UserLoginModel.ResultBean> getPersonByCities(List<String> cityIn);
  }
