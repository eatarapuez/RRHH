package workflows.postfunctions

import com.atlassian.jira.config.util.JiraHome
import com.atlassian.jira.component.ComponentAccessor

JiraHome jiraHome = ComponentAccessor.getComponent(JiraHome.class);
def customFieldManager = ComponentAccessor.getCustomFieldManager()


Properties config=(Properties)evaluate (new File(String.format("%s/scripts/JiraCluster1/config.groovy",jiraHome.getHomePath())))
long IdNumeroDocumento =Long.parseLong(config.getProperty("fields.numeroDocumento.id"))

def cfNumeroDocumento = customFieldManager.getCustomFieldObject(IdNumeroDocumento)
def numeroDocumento=issue.getCustomFieldValue(cfNumeroDocumento)

log.error "id cf: "+IdNumeroDocumento
log.error "valor Numero: "+ numeroDocumento

issue.setCustomFieldValue(cfNumeroDocumento,numeroDocumento)  
