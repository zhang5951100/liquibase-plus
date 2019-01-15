package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 10:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("column")
public class Column {
	@XStreamAsAttribute
	@XStreamAlias("name")
	private String name;

	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

	private Constraints constraints;
}
