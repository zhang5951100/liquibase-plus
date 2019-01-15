package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 10:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("constraints")
public class Constraints {
	@XStreamAsAttribute
	@XStreamAlias("primaryKey")
	private String primaryKey;

	@XStreamAsAttribute
	@XStreamAlias("primaryKeyName")
	private String primaryKeyName;
}
