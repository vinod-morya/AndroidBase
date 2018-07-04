package com.company.product.Support.RoomDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.company.product.Support.RoomDatabase.UserRoom.UserDatabase;

public class DatabaseCreator
  {
	 private static UserDatabase sUserDatabase;
	 private static final Object LOCK = new Object();

	 public synchronized static UserDatabase getPersonDatabase(Context context)
		{
		  if(sUserDatabase == null)
		  {
			 synchronized(LOCK)
			 {
				if(sUserDatabase == null)
				{
				  sUserDatabase = Room.databaseBuilder(context, UserDatabase.class, "user.db").build();
				}
			 }
		  }
		  return sUserDatabase;
		}
  }
