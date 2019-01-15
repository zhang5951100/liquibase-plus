package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 13:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("addUniqueConstraint")
public class AddUniqueConstraint {

	@XStreamAsAttribute
	@XStreamAlias("columnNames")
	private String columnNames;

	@XStreamAsAttribute
	@XStreamAlias("constraintName")
	private String constraintName;

	@XStreamAsAttribute
	@XStreamAlias("tableName")
	private String tableName;
}
