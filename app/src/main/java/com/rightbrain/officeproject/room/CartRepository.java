package com.rightbrain.officeproject.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rightbrain.officeproject.model.ModelCartRoom;

import java.util.List;

public class CartRepository{
    private RoomDao roomDao;
    private MyRoomDataBase roomDataBase;
    private LiveData<List<ModelCartRoom>> allData;
    private LiveData<ModelCartRoom> searchData;
    private Context context;


    private  LiveData<List<ModelCartRoom>> singleData;
    String name;

    public CartRepository(Context context) {
        this.context = context;
        roomDataBase = MyRoomDataBase.getInstance(context);
        roomDao = roomDataBase.roomDao();
        allData = roomDao.getAllData();
       // singleData = roomDao.getNote(name);
       // p_id = roomDao.getP_id(p_id);
    }



    public LiveData<List<ModelCartRoom>> getAllData(){
        return  this.allData;
    }


    public LiveData<ModelCartRoom> getSearchData(String name){
      LiveData<ModelCartRoom> room = roomDao.getNote(name);
        return room;
    }
    public ModelCartRoom getSearchDatas(String name){
        ModelCartRoom room = roomDao.getNotes(name);
        return room;
    }





   /* public int getSearchData(String name){
        int room = roomDao.getNote(name);
        return room;
    }
*/

    public LiveData<List<ModelCartRoom>> getSingleData(String data){
        this.name = data;
        return  this.singleData;

    }


    public void insertSingleData(ModelCartRoom cartdb)
    {
        new InsetData(roomDao).execute(cartdb);
    }

    public void  deleteAll(){
       new DeleteAllData(roomDao).execute();
    }


    private class DeleteAllData extends AsyncTask<Void, Void, Void> {
        RoomDao roomDao;
        public DeleteAllData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            roomDao.deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(context, "Add to Cart", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateRowData(int qyt,String id){

          //  roomDao.updaterow(qty,id);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                roomDao.updaterow(qyt,id);
                return null;
            }
        }.execute();

    }




    public void update(ModelCartRoom cartdb)
    {
        new UpdateData(roomDao).execute(cartdb);
    }



    public void delete(ModelCartRoom cartdb)
    {
        new DeleteData(roomDao).execute(cartdb);
    }





    private class InsetData extends AsyncTask<ModelCartRoom, Void, Void> {
        RoomDao roomDao;
        public InsetData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(ModelCartRoom... modelCartRooms) {
            roomDao.insertSingleData(modelCartRooms[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(context, "Add to Cart", Toast.LENGTH_SHORT).show();
        }
    }


    private class UpdateData extends AsyncTask<ModelCartRoom, Void, Void> {
        RoomDao roomDao;


        public  UpdateData(RoomDao roomDao) {
            this.roomDao = roomDao;

        }




        @Override
        protected Void doInBackground(ModelCartRoom... modelCartRooms) {
            roomDao.updateSingleData(modelCartRooms[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
         //  Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();
        }
    }




    private class DeleteData extends AsyncTask<ModelCartRoom, Void, Void> {
        RoomDao roomDao;
        public DeleteData(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(ModelCartRoom... modelCartRooms) {
            roomDao.DeleteSingleData(modelCartRooms[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
        }
    }


}
