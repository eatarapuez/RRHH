import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.security.IssueSecurityLevelManager
import com.atlassian.jira.issue.security.IssueSecuritySchemeManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cfDepartamento = customFieldManager.getCustomFieldObject(51700 as long)
String valor=issue.getCustomFieldValue(cfDepartamento)    

log.error "El valor es: "+valor

issue.setSecurityLevelId(securityId(issue,"RRHH",valor))
log.error "Nivel de seguridad es: "+ securityId(issue,"RRHH",valor)
//------------------------------------Metodos no modificar------------------
long securityId(Issue issue, String nombreEsquema,String nivelSeguridad){
    def issueSecuritySchemeManager = ComponentAccessor.getComponent(IssueSecuritySchemeManager)
    def issueSecurityLevelManager = ComponentAccessor.getComponent(IssueSecurityLevelManager)

    def schemeFromName = issueSecuritySchemeManager.getSchemeObjects().find { it.name == "${issue.getProjectObject().getOriginalKey()}" }
    def schemeFromProject = issueSecuritySchemeManager.getSchemeFor(ComponentAccessor.projectManager.getProjectByCurrentKey(nombreEsquema))

    
    def securityLvl = issueSecurityLevelManager.getIssueSecurityLevels(schemeFromName.id).find { it ->
        it.name == nivelSeguridad
    }?.id
    return securityLvl as long
}