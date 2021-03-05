package fr.ulille.iut.amimir.dao;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import fr.ulille.iut.amimir.beans.Message;

public interface MessageDao {

	@SqlUpdate("CREATE TABLE IF NOT EXISTS messages ("
			+ "id VARCHAR(128) PRIMARY KEY, "
			+ "author VARCHAR(128), "
			+ "dest VARCHAR(128), "
			+ "content VARCHAR NOT NULL)")
	void createTable();
	
	@SqlUpdate("INSERT INTO ingredients (id, author, dest, content) VALUES (:id, :author, :dest, :content)")
    void insert(@BindBean Message m);
	
	@SqlQuery("SELECT * FROM messages")
	@RegisterBeanMapper(Message.class)
	List<Message> getAll();
	
    @SqlQuery("SELECT * FROM messages WHERE id = :id")
    @RegisterBeanMapper(Message.class)
    Message findById(@Bind("id") UUID id);
    
    @SqlQuery("SELECT * FROM messages WHERE dest = :dest AND author = :author")
    @RegisterBeanMapper(Message.class)
    List<Message> findByAuthorDest(@Bind("author") UUID author, @Bind("dest") UUID dest);
}
