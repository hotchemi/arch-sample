/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package todo.db;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@Module
public final class DbModule {
  @Provides @Singleton SQLiteOpenHelper provideOpenHelper(Application application) {
    return new DbOpenHelper(application);
  }

  @Provides @Singleton SqlBrite provideSqlBrite() {
    return new SqlBrite.Builder()
        .logger(new SqlBrite.Logger() {
          @Override public void log(String message) {
            Timber.tag("Database").v(message);
          }
        })
        .build();
  }

  @Provides @Singleton BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
    BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    db.setLoggingEnabled(true);
    return db;
  }
}
