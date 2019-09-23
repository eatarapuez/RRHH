import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.security.roles.ProjectRoleManager
 import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.user.ApplicationUser


String [] roles = ["Administrators"]
//String [] roles = ["Entrevistador"]
String [] grupos = ["jira-administrators"]
//String [] grupos = ["acceso_administracion"]
def valor=true
if( jiraHelper.project?.key == "RRHH"){
	 valor=false
if((existeEnGrupo(grupos,).equals("Existe")||existeEnRol(roles).equals("Existe"))){
    valor=true    
	}
}

return valor

//------------------------------------------metodos------------
String existeEnRol(String [] roles){       
       String existe = "No existe"
       for(int i =0 ;i<roles.size();i++){
           def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser() 
           def projectRoleManager = ComponentAccessor.getComponentOfType(ProjectRoleManager)
           def role = projectRoleManager.getProjectRole(roles[i])
           if(projectRoleManager.isUserInProjectRole(currentUser, role, issue.getProjectObject())){
               existe = "Existe"
               break
           }
       }
   return existe
}
//------------------------------------------metodos------------
String existeEnGrupo(String [] grupos){
    String existe = "No existe"
        def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser() 
        def groupManager = ComponentAccessor.getGroupManager()
    
    for(int i =0 ;i<grupos.size();i++){
        String usuarios=groupManager.getUsersInGroup(grupos[i])
        if(usuarios.contains(currentUser as String) ){
           existe = "Existe"
               break 
        }
    }
return existe
}

