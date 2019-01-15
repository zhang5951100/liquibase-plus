package cn.shiji.liquibaseplus.model.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Eric
 * @date: 2019-01-15 10:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("tagDatabase")
public class TagDatabase {
	@XStreamAsAttribute
	@XStreamAlias("tag")
	private String tag;
}
