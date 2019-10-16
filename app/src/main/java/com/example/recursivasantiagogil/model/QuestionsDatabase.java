package com.example.recursivasantiagogil.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Question.class, Answer.class, Result.class}, version = 1)
public abstract class QuestionsDatabase extends RoomDatabase {

    public abstract QuestionDAO questionDAO();
    public abstract AnswerDAO answerDAO();

    private static QuestionsDatabase instance;

    public static synchronized QuestionsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuestionsDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionDAO questionDAO;
        private AnswerDAO answerDAO;

        public InitialDataAsyncTask(QuestionsDatabase questionsDatabase) {
            this.questionDAO = questionsDatabase.questionDAO();
            this.answerDAO = questionsDatabase.answerDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            questionDAO.insert(new Question(1,"¿Cuál es la capital de Honduras?", true));
            questionDAO.insert(new Question(2,"¿En qué continente queda Madagascar", true));
            questionDAO.insert(new Question(3,"¿Cuál es la montaña más alta del mundo", true));
            questionDAO.insert(new Question(4,"¿Cuál es la capital de Brasil", true));
            questionDAO.insert(new Question(5,"¿Dónde quedan las Cataratas de Iguazú", true));
            questionDAO.insert(new Question(6,"¿En qué países se habla principalmente el idioma inglés?", false));
            questionDAO.insert(new Question(7,"¿Qué países limitan con Francia?", false));
            questionDAO.insert(new Question(8,"¿Cuáles de estos países pertenecen al Mercosur?", false));
            questionDAO.insert(new Question(9,"¿Cuáles de los siguientes países poseen reconocidas pirámides?", false));
            questionDAO.insert(new Question(10,"¿Cuáles ciudades fueron centro de grandes civilizaciones de la antigüedad?", false));

            answerDAO.insert(new Answer(1,'a', "Honduras", false));
            answerDAO.insert(new Answer(1,'b', "Tegucigalpa", true));
            answerDAO.insert(new Answer(1,'c', "Madrileña", false));

            answerDAO.insert(new Answer(2,'a', "África", true));
            answerDAO.insert(new Answer(2,'b', "América",false));
            answerDAO.insert(new Answer(2,'c',"Asia",false));

            answerDAO.insert(new Answer(3,'a',"Monte Everest", true));
            answerDAO.insert(new Answer(3,'b',"Kilimanjaro",false));
            answerDAO.insert(new Answer(3,'c', "Makalu", false));

            answerDAO.insert(new Answer(4,'a',"Río de Janeiro", false));
            answerDAO.insert(new Answer(4,'b',"Brasilia",true));
            answerDAO.insert(new Answer(4,'c', "San Pablo", false));

            answerDAO.insert(new Answer(5,'a', "Brasil y Uruguay", false));
            answerDAO.insert(new Answer(5,'b', "Argentina y Brasil",true));
            answerDAO.insert(new Answer(5,'c',"Argentina y Uruguay",false));

            answerDAO.insert(new Answer(6,'a', "España", false));
            answerDAO.insert(new Answer(6,'b', "Inglaterra",true));
            answerDAO.insert(new Answer(6,'c',"China",false));
            answerDAO.insert(new Answer(6,'d',"USA",true));

            answerDAO.insert(new Answer(7,'a', "España", true));
            answerDAO.insert(new Answer(7,'b', "Italia",true));
            answerDAO.insert(new Answer(7,'c',"Rusia",false));
            answerDAO.insert(new Answer(7,'d',"Finlandia",false));

            answerDAO.insert(new Answer(8,'a', "Argentina", true));
            answerDAO.insert(new Answer(8,'b', "Brasil",true));
            answerDAO.insert(new Answer(8,'c',"México",false));
            answerDAO.insert(new Answer(8,'d',"Panamá",false));

            answerDAO.insert(new Answer(9,'a', "Egipto", true));
            answerDAO.insert(new Answer(9,'b', "Italia",false));
            answerDAO.insert(new Answer(9,'c',"Rusia",false));
            answerDAO.insert(new Answer(9,'d',"México",true));

            answerDAO.insert(new Answer(10,'a', "Roma", true));
            answerDAO.insert(new Answer(10,'b', "New York",false));
            answerDAO.insert(new Answer(10,'c',"Atenas",true));
            answerDAO.insert(new Answer(10,'d',"Buenos Aires",false));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("database", "database loaded");
        }
    }
}
