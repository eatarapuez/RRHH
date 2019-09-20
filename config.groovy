import java.io.File
import java.util.Properties
import com.atlassian.jira.config.util.JiraHome
import com.atlassian.jira.component.ComponentAccessor


JiraHome jiraHome = ComponentAccessor.getComponent(JiraHome.class);
Properties properties = new Properties()
File propertiesFile = new File(String.format("%s/scripts/JiraCluster1/config/config.properties",jiraHome.homePath))
propertiesFile.withInputStream {
	properties.load(it)
}
return properties
