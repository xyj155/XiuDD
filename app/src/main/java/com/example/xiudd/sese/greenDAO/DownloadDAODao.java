package com.example.xiudd.sese.greenDAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOWNLOAD_DAO".
*/
public class DownloadDAODao extends AbstractDao<DownloadDAO, Long> {

    public static final String TABLENAME = "DOWNLOAD_DAO";

    /**
     * Properties of entity DownloadDAO.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "name");
        public final static Property Path = new Property(2, String.class, "path", false, "path");
        public final static Property Url = new Property(3, String.class, "url", false, "url");
        public final static Property Image = new Property(4, String.class, "image", false, "image");
        public final static Property Down = new Property(5, int.class, "down", false, "down");
    }


    public DownloadDAODao(DaoConfig config) {
        super(config);
    }
    
    public DownloadDAODao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOWNLOAD_DAO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"name\" TEXT," + // 1: name
                "\"path\" TEXT," + // 2: path
                "\"url\" TEXT," + // 3: url
                "\"image\" TEXT," + // 4: image
                "\"down\" INTEGER NOT NULL );"); // 5: down
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOWNLOAD_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DownloadDAO entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(3, path);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(4, url);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(5, image);
        }
        stmt.bindLong(6, entity.getDown());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DownloadDAO entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(3, path);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(4, url);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(5, image);
        }
        stmt.bindLong(6, entity.getDown());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DownloadDAO readEntity(Cursor cursor, int offset) {
        DownloadDAO entity = new DownloadDAO( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // path
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // url
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // image
            cursor.getInt(offset + 5) // down
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DownloadDAO entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImage(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDown(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DownloadDAO entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DownloadDAO entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DownloadDAO entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}