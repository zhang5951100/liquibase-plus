package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 13:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("addForeignKeyConstraint")
public class AddForeignKeyConstraint {

	@XStreamAsAttribute
	@XStreamAlias("baseColumnNames")
	private String baseColumnNames;

	@XStreamAsAttribute
	@XStreamAlias("baseTableName")
	private String baseTableName;

	@XStreamAsAttribute
	@XStreamAlias("constraintName")
	private String constraintName;

	@XStreamAsAttribute
	@XStreamAlias("deferrable")
	private String deferrable;

	@XStreamAsAttribute
	@XStreamAlias("initiallyDeferred")
	private String initiallyDeferred;

	@XStreamAsAttribute
	@XStreamAlias("onDelete")
	private String onDelete;

	@XStreamAsAttribute
	@XStreamAlias("onUpdate")
	private String onUpdate;

	@XStreamAsAttribute
	@XStreamAlias("referencedColumnNames")
	private String referencedColumnNames;

	@XStreamAsAttribute
	@XStreamAlias("referencedTableName")
	private String referencedTableName;

	@XStreamAsAttribute
	@XStreamAlias("validate")
	private String validate;
}
