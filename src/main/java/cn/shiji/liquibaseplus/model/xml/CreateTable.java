package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Eric
 * @date: 2019-01-15 10:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("createTable")
public class CreateTable {
	@XStreamAsAttribute
	@XStreamAlias("tableName")
	private String tableName;

	@XStreamImplicit(itemFieldName = "column")
	private List<Column> columnList;
}
