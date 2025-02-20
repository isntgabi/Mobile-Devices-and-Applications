package com.example.recapp5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void insertStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM studenti")
    List<Student> getStudenti();

    @Query("SELECT * FROM studenti WHERE specializare = :spec")
    List<Student> getStudentiINFO(String spec);
}
