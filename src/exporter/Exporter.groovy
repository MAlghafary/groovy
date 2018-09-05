
package exporter


import groovy.sql.Sql



def url ="jdbc:mysql://localhost/employees"
def user ="root"
def password = "root"
def driverClassName = "com.mysql.jdbc.Driver"


def sql = Sql.newInstance(url, user, password, driverClassName)

sql.execute('DROP TABLE Author')
sql.execute '''
  CREATE TABLE Author (
    id          INT not null,
    firstname   varchar(30),
    lastname    VARCHAR(30),
    PRIMARY KEY (id)
  );
''';

// CRUD operations 
// Insert 
def insertSql = '	INSERT INTO Author (ID,FIRSTNAME,LASTNAME) VALUES (?,?,?)';
def params = [1,'Jon', 'Skeet']
sql.executeInsert(insertSql, params);

// Reading rows >> print xml 
sql.eachRow('select * from Author') {row -> 
	 def id =  row[0]
	 def firstName = row[1]
	 def lastName = row[2]
	 println """
     <name>
         <firstName>${firstName}</firstName>
         <lastName>${lastName}</lastName>
     </name> 
    """
	}
	
	// http://groovy-lang.org/databases.html#_executing_sql >> 3. Basic CRUD operations 


sql.close();


