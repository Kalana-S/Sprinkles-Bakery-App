package com.example.sprinklesbakery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.sprinklesbakery.data.model.Member;

@Dao
public interface MemberDao {
    @Insert
    void insert(Member member);

    @Query("SELECT * FROM members WHERE email = :email AND password = :password LIMIT 1")
    Member login(String email, String password);
}