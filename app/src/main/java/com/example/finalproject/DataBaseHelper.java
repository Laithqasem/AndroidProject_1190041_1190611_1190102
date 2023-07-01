package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
//        context.deleteDatabase(name);
    }

    Context context;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, " +
                "FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE COURSES(ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID TEXT, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT, IMAGE BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE TRAINEE(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, " +
                "FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT, ADDRESS TEXT,MOBILE_NUMBER TEXT)");


//        sqLiteDatabase.execSQL("CREATE TABLE COURSES(COURSE_ID TEXT PRIMARY KEY, COURSE_NAME TEXT, PREREQUISITES TEXT, " +
//                "START_DATE TEXT, END_DATE TEXT, START_REG TEXT, END_REG TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE Trainee(email TEXT PRIMARY KEY, password TEXT, firstName TEXT, " +
                "lastName TEXT, mobileNumber TEXT, address TEXT, image BLOB)");

        sqLiteDatabase.execSQL("CREATE TABLE TraineeToSection(traineeToSectionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sectionID INTEGER, traineeEmail TEXT, status INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE Notifications(notID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " notText TEXT, status INTEGER, traineeEmail TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE TOPICS(TOPIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, COURSE_ID INTEGER, TOPIC_NAME TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE INSTRUCTOR(" +
                "EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, FIRST_NAME TEXT, LAST_NAME TEXT, PERSONAL_PHOTO TEXT, MOBILE_NUMBER TEXT," +
                "ADDRESS TEXT, SPECIALIZATION TEXT, canTeach TEXT)");


        sqLiteDatabase.execSQL("CREATE TABLE SECTION(" +
                "SECTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, INSTRUCTOR_EMAIL TEXT, COURSE_ID INTEGER, MAX_TRAINEES INTEGER, " +
                "START_TIME TEXT, END_TIME TEXT, DAYS TEXT, ROOM TEXT, START_DATE TEXT, END_DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertSection(Section section) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("INSTRUCTOR_EMAIL", section.getInstructorEmail());
        contentValues.put("COURSE_ID", section.getCourseID());
        contentValues.put("MAX_TRAINEES", section.getMaxTrainees());
        contentValues.put("START_TIME", section.getStartTime());
        contentValues.put("END_TIME", section.getEndTime());
        contentValues.put("DAYS", section.getDays());
        contentValues.put("ROOM", section.getRoom());
        contentValues.put("START_DATE", section.getStartDate());
        contentValues.put("END_DATE", section.getEndDate());
        sqLiteDatabase.insert("SECTION", null, contentValues);
    }
    public Boolean getLoginPassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);



        return true;

    }
    public void insertAdmin(Admin admin) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", admin.getEmail());
        contentValues.put("PASSWORD", admin.getPassword());
        contentValues.put("LAST_NAME", admin.getLastName());
        contentValues.put("FIRST_NAME", admin.getFirstName());
        contentValues.put("PERSONAL_PHOTO", admin.getPersonal_photo());
        sqLiteDatabase.insert("ADMIN", null, contentValues);
    }

    public void insertTrainee(Trainee trainee) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", trainee.getEmail());
        contentValues.put("PASSWORD", trainee.getPassword());
        contentValues.put("FIRST_NAME", trainee.getFirstName());
        contentValues.put("LAST_NAME", trainee.getLastName());
        contentValues.put("PERSONAL_PHOTO", trainee.getPersonal_photo());
        contentValues.put("ADDRESS", trainee.getAddress());
        contentValues.put("MOBILE_NUMBER", trainee.getMobileNumber());
        sqLiteDatabase.insert("TRAINEE", null, contentValues);
    }

    // This will return all sections who's course id = courseID
    public Cursor getAllOfferings (String courseID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE COURSE_ID = ?", new String[]{courseID});
        return cursor;
    }

    public void deleteCourse(String courseID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("COURSES", "ID = ?", new String[]{courseID});
        sqLiteDatabase.delete("SECTION", "COURSE_ID = ?", new String[]{courseID});
    }

    public void updateSection(Section section) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("INSTRUCTOR_EMAIL", section.getInstructorEmail());
        contentValues.put("COURSE_ID", section.getCourseID());
        contentValues.put("MAX_TRAINEES", section.getMaxTrainees());
        contentValues.put("START_TIME", section.getStartTime());
        contentValues.put("END_TIME", section.getEndTime());
        contentValues.put("DAYS", section.getDays());
        contentValues.put("ROOM", section.getRoom());
        contentValues.put("START_DATE", section.getStartDate());
        contentValues.put("END_DATE", section.getEndDate());
        sqLiteDatabase.update("SECTION", contentValues, "SECTION_ID = ?", new String[]{String.valueOf(section.getSectionID())});
    }
//
//    public void insertInstructor(Instructor instructor){
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("EMAIL", instructor.getEmail());
//        contentValues.put("PASSWORD", instructor.getPassword());
//        contentValues.put("FIRST_NAME", instructor.getFirstName());
//        contentValues.put("LAST_NAME", instructor.getLastName());
//        contentValues.put("PERSONAL_PHOTO", instructor.getPersonal_photo());
//        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
//        contentValues.put("ADDRESS", instructor.getAddress());
//        contentValues.put("SPECIALIZATION", instructor.getSpecialization());
//        contentValues.put("canTeach", instructor.getCanTeach());
//        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
//    }
    public void insertInstructor(Instructor instructor){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("canTeach", instructor.getCanTeach());
        contentValues.put("PASSWORD", instructor.getPassword());
        contentValues.put("MOBILE_NUMBER", instructor.getMobileNumber());
        contentValues.put("ADDRESS", instructor.getAddress());
        contentValues.put("EMAIL", instructor.getEmail());
        contentValues.put("LAST_NAME", instructor.getLastName());
        contentValues.put("SPECIALIZATION", instructor.getSpecialization());

        contentValues.put("FIRST_NAME", instructor.getFirstName());
        contentValues.put("PERSONAL_PHOTO", instructor.getPersonal_photo());
        sqLiteDatabase.insert("INSTRUCTOR", null, contentValues);
    }


    public void insertTrainee(Trainee trainee){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", trainee.getEmail());
        contentValues.put("password", trainee.getPassword());
        contentValues.put("firstName", trainee.getFirstName());
        contentValues.put("lastName", trainee.getLastName());
        contentValues.put("mobileNumber", trainee.getMobileNumber());
        contentValues.put("address", trainee.getAddress());
        contentValues.put("image", trainee.getImage());
        sqLiteDatabase.insert("Trainee", null, contentValues);
    }

    public void insertCourses(Course course){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COURSE_ID", course.getCourseID());
        contentValues.put("COURSE_NAME", course.getCourseName());
        contentValues.put("PREREQUISITES", course.getPrerequisites());
        contentValues.put("START_DATE", course.getStartDate());
        contentValues.put("END_DATE", course.getEndDate());
        contentValues.put("START_REG", course.getRegistrationStart());
        contentValues.put("END_REG", course.getRegistrationEnd());
        contentValues.put("IMAGE", course.getImage());
        sqLiteDatabase.insert("COURSES", null, contentValues);
    }

    public void insertTopics(Topic topic){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COURSE_ID", topic.getCourseID());
        contentValues.put("TOPIC_NAME", topic.getTopicName());
        sqLiteDatabase.insert("TOPICS", null, contentValues);
    }

    public boolean checkCourseId(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE COURSE_ID = \"" + id + "\"", null);
        int cnt = 0;
        while(cursor.moveToNext()){
            cnt++;
        }
        return cnt == 0;
    }

    public Cursor getSections() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION", null);
    }

    public Cursor getAllCourses() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES", null);
    }

    public Cursor getAllTrainees() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Trainee", null);
    }

    public Cursor getOneTrainee(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Trainee Where " + "email = \"" + Email + "\"", null);
    }

    public Cursor getAllInstructor() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR", null);
    }
    public Cursor getAllTrainee() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TRAINEE", null);
    }
    public Cursor getAllAdmin() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ADMIN", null);
    }
    public Cursor getAllTopics() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TOPICS", null);
    }

    public Cursor getCourseId(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES Where " + "ID = \"" + id + "\"", null);
    }

    public Cursor getSectionsForTrainee(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection Where " + "sectionID = " + id, null);
    }
    //function to delete trainee from trainee2section table based on section id and trainee email
    public void deleteTraineeFromSection(String email, String sectionId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("TraineeToSection", "traineeEmail = ? AND sectionID = ?", new String[]{email, sectionId});
    }
    public Cursor checkExistence(String email, String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\" " +
                "AND sectionID = " + sectionId, null);

        return cursor;
    }

    public void insertTraineeSection(TraineeToSection traineeToSection){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traineeEmail", traineeToSection.getTraineeEmail());
        contentValues.put("sectionID", traineeToSection.getSectionID());
        contentValues.put("status", traineeToSection.getStatus());
        sqLiteDatabase.insert("TraineeToSection", null, contentValues);
    }

    public int getTraineeCount(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);

        return cursor.getCount();
    }

    public int countStudentsInSection(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);

        return cursor.getCount();
    }

    // This function will return a cursor to show the trainees in a specific section
    public Cursor getTraineesInSection(String sectionId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "sectionID = " + sectionId, null);
    }

    public Cursor getTraineeSections(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\"", null);
    }
  
    public Cursor getSecId(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\" ", null);

        return cursor;
    }

    public Cursor getSecInfo(int SecID){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + SecID, null);

        return cursor;
    }

    public boolean checkInstructor(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM INSTRUCTOR WHERE " +
                "EMAIL = \"" + email + "\"", null);
        int cnt = 0;
        while(cursor.moveToNext()){
            cnt++;
        }
        return cnt == 1;
    }


    public void deleteSection(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("SECTION", "SECTION_ID = ?", new String[]{valueOf});
    }

    public String getTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Trainee WHERE " +
                "email = \"" + email + "\"", null);
        String name = "";
        while(cursor.moveToNext()){
            name = cursor.getString(2) + " " + cursor.getString(3);
        }
        return name;
    }

    public void updateTraineeInSection(int sectionID, String traineeEmail) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", "1");
        sqLiteDatabase.update("TraineeToSection", contentValues, "sectionID = ? AND traineeEmail = ?", new String[]{String.valueOf(sectionID), traineeEmail});
    }

    public Cursor getSection(String sectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + sectionID, null);
    }

    public Cursor getAllSectionsOfCourse(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "COURSE_ID = " + valueOf, null);
    }


    public Cursor getAllSectionsOfTrainee(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection WHERE " +
                "traineeEmail = \"" + email + "\"", null);
    }

    public Cursor getCourse(String valueOf) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + valueOf, null);
    }

    public void insertNotifications(Notification notification) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("notText", notification.getNotText());
        contentValues.put("status", notification.getStatus());
        contentValues.put("traineeEmail", notification.getTraineeEmail());
        sqLiteDatabase.insert("Notifications", null, contentValues);
    }

    public Cursor getAllNotifications() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Notifications", null);
    }

    public int getCourseIDForSection(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + id, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getInt(2);
    }

    public Cursor getTraineeToSection() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM TraineeToSection", null);
    }

    public String getStartDate(int courseID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + courseID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(4);
    }

    public String getEndDate(int courseID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE " +
                "ID = " + courseID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(5);
    }

    public String getStartTime(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(4);
    }

    public String getEndTime(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(5);
    }

    public String getDays(int otherSectionID) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SECTION WHERE " +
                "SECTION_ID = " + otherSectionID, null);
        assert cursor.getCount() == 1;
        cursor.moveToNext();
        return cursor.getString(6);
    }
}