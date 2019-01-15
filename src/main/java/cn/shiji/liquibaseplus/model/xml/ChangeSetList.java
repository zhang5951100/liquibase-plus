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
 * @date: 2019-01-15 09:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("databaseChangeLog")
public class ChangeSetList {
	@XStreamAsAttribute
	@XStreamAlias("xmlns")
	private String xmlns;
	@XStreamAsAttribute
	@XStreamAlias("xmlns:ext")
	private String xmlnsSet;

	@XStreamAsAttribute
	@XStreamAlias("xmlns:xsi")
	private String xmlns1Xsi;

	@XStreamAsAttribute
	@XStreamAlias("xsi:schemaLocation")
	private String xsiSchemaLocation;


	@XStreamImplicit(itemFieldName = "changeSet")
	private List<ChangeSet> changeSetList;
}
