package com.drapic.bookbuddies.utils;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import com.drapic.bookbuddies.entities.Book;
import com.drapic.bookbuddies.entities.BookClub;
import com.drapic.bookbuddies.entities.BookFav;
import com.drapic.bookbuddies.entities.ClubRequest;
import com.drapic.bookbuddies.entities.User;
 
//https://shekhargulati.com/2018/01/09/programmatically-generating-database-schema-with-hibernate-5/
public class HibernateDatabaseGenerator {
 
//    public static void main(String[] args) {
//        Map<String, String> settings = new HashMap<>();
//        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
//        settings.put("dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
//        settings.put("hibernate.connection.url", "jdbc:mysql://localhost/testdb?useSSL=false");
//        settings.put("hibernate.connection.username", "root");
//        settings.put("hibernate.connection.password", "");
//        settings.put("hibernate.hbm2ddl.auto", "create");
//        settings.put("show_sql", "true");
// 
//        MetadataSources metadata = new MetadataSources(
//                new StandardServiceRegistryBuilder()
//                        .applySettings(settings)
//                        .build());
////        metadata.addAnnotatedClass(User.class);
////        metadata.addAnnotatedClass(Task.class);
//        SchemaExport schemaExport = new SchemaExport(
//                (MetadataImplementor) metadata.buildMetadata()
//        );
//        schemaExport.setHaltOnError(true);
//        schemaExport.setFormat(true);
//        schemaExport.setDelimiter(";");
//        schemaExport.setOutputFile("db-schema.sql");
//        schemaExport.execute(true, true, false, true);
//    }
//    


	public static void main(String[] args) {
		
		Map settings = new HashMap();
		settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
		settings.put("hibernate.format_sql", "true");
		
		
		MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder().applySettings(settings).build());
		for (Class clazz : new Class[] { 
				Book.class,
				BookClub.class,
				BookFav.class,
				ClubRequest.class,
				User.class
				}
		) {
			
			metadata.addAnnotatedClass(clazz);
		}
//		metadata.addPackage("com.jcocktail.bpa.model");
//		Package packageRef = Package.getPackage("com.jcocktail.bpa.model");
//		metadata.addPackage(packageRef);
		SchemaExport schemaExport = new SchemaExport();
		schemaExport.setHaltOnError(true);
		schemaExport.setFormat(true);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("book_buddies-db-schema.sql");

//		schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
		schemaExport.create(EnumSet.of(TargetType.STDOUT, TargetType.SCRIPT), metadata.buildMetadata());
		
	}

    
}