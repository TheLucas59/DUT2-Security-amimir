package fr.ulille.iut.amimir.dao;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import fr.ulille.iut.amimir.dto.MessageDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface MessageDAO {
    @SqlUpdate("create table if not exists messages (id varchar(128) primary key, message varchar not null, author varchar, dest varchar, time text)")
    void createTable();

    @SqlQuery("select * from messages where author = :author and dest = :dest")
    @RegisterBeanMapper(MessageDTO.class)
    List<MessageDTO> getAllMessagesById(UUID author, UUID dest);

    @SqlUpdate("insert into messages (id, message, author, dest, time) values (:id, :content, :author, :dest, :d)")
    void addNewMsg(@Bind("content") String content, @Bind("author") UUID author, @Bind("dest") UUID dest, @Bind("id") UUID id, @Bind("d") Date d);
}
