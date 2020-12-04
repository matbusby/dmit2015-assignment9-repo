package dmit2015.matbusby.assignment09.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;

@DataSourceDefinitions({
        @DataSourceDefinition(
                name="java:app/datasources/h2databaseDS",
                className="org.h2.jdbcx.JdbcDataSource",
				url="jdbc:h2:file:~/matBusby-assignment9-db",
//		        url="jdbc:h2:file:./data/mbusby-assignment07and8b-db",
//                url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                user="sa",
                password="sa"),
})

@ApplicationScoped
@FacesConfig
public class ApplicationConfig {
}
