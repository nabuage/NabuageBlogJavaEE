package org.nabuage.blog.jaas;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;
import org.nabuage.blog.utility.Password;

/**
 *
 * @author George
 * JAVA_OPTS=$JAVA_OPTS "-Djava.security.auth.login.config==$CATALINA_BASE/conf/jaas.conf"
 * set JAVA_OPTS=%JAVA_OPTS% -Djava.security.auth.login.config"=%CATALINA_HOME%\conf\jaas.conf"
 */
public class BlogLoginModule implements LoginModule {
    
    private static final String LOGIN_SQL = "SELECT g.name, password, salt FROM userprofile up INNER JOIN userprofilegroup upg ON up.userprofileid = upg.userprofileid INNER JOIN \"group\" g ON upg.groupid = g.groupid WHERE up.username = ? AND up.datedeleted IS NULL"; 
    
    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;
    
    //@Resource(name="loginModuleBlogDataSource")
    DataSource dataSource;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        boolean validLogin = false;
        
        try {
            this.handler.handle(callbacks);
            String username = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            
            InitialContext initialContext = new InitialContext();
            //java:openejb/Resource/
            //java:comp/env/
            dataSource = (DataSource) initialContext.lookup("java:openejb/Resource/loginModuleBlogDataSource");
                            
            PreparedStatement statement = dataSource.getConnection().prepareStatement(LOGIN_SQL);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            int counter = 0;

            while (resultSet.next()) {
                if (counter == 0) {
                    Password passwordValidator = new Password();
                    
                    validLogin = passwordValidator.valid(password, resultSet.getString("password"), resultSet.getString("salt"));
                    
                    if (validLogin) {
                        this.login = username;
                        this.userGroups = new ArrayList<String>();
                    }
                    else {
                        throw new LoginException("Authentication failed.");
                    }

                    counter++;
                }

                if (validLogin) {                        
                    this.userGroups.add(resultSet.getString("name"));
                }
                /*resultSet.getString("name");
                resultSet.getString("password");
                resultSet.getString("salt");*/
            }
            
            if (validLogin) {
                return true;
            }
            else {
                throw new LoginException("Authentication failed.");
            }
            
            /*if (username != null && password != null) {
                this.login = username;
                this.userGroups = new ArrayList<String>();
                this.userGroups.add("admin");
                return true;
                
            }*/
        } 
        catch (IOException ex) {
            throw new LoginException(ex.getMessage());
        } 
        catch (UnsupportedCallbackException ex) {
            throw new LoginException(ex.getMessage());
        } 
        catch (NamingException ex) {
            throw new LoginException(ex.getMessage());
            //Logger.getLogger(BlogLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            throw new LoginException(ex.getMessage());
            //Logger.getLogger(BlogLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public boolean commit() throws LoginException {
        
        this.userPrincipal = new UserPrincipal(this.login);
        this.subject.getPrincipals().add(userPrincipal);
        
        if (this.userGroups != null && this.userGroups.size() > 0) {
            
            for (String groupName : userGroups) {
                this.rolePrincipal = new RolePrincipal(groupName);
                this.subject.getPrincipals().add(this.rolePrincipal);
            }
            
        }
        
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().remove(userPrincipal);
        this.subject.getPrincipals().remove(rolePrincipal);
        return true;
    }
    
}
