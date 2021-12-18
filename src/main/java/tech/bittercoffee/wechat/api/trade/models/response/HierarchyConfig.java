package tech.bittercoffee.wechat.api.trade.models.response;

import java.util.List;

public final class HierarchyConfig {

	private String propertyName;
	private Class<?> propertyType;
	private String countPropertyName;
	private List<HierarchyConfig> childs;

	public HierarchyConfig(String propertyName, Class<?> propertyType, String countPropertyName) {
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.countPropertyName = countPropertyName;
	}

	public HierarchyConfig(String propertyName, Class<?> propertyType, String countPropertyName,
			List<HierarchyConfig> childs) {
		this(propertyName, propertyType, countPropertyName);
		this.childs = childs;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public String getCountPropertyName() {
		return countPropertyName;
	}

	public List<HierarchyConfig> getChilds() {
		return childs;
	}

}
