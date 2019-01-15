package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 09:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("changeSet")
public class ChangeSet {
	@XStreamAsAttribute
	@XStreamAlias("id")
	private String id;

	@XStreamAsAttribute
	@XStreamAlias("author")
	private String author;

	private TagDatabase tagDatabase;

	private CreateTable createTable;

	private AddUniqueConstraint addUniqueConstraint;

	private CreateIndex createIndex;

	private AddForeignKeyConstraint addForeignKeyConstraint;
}
