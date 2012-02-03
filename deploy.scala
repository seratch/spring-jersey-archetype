
import scala.sys.process._
import scala.io.Source
import java.io.PrintWriter

val homeDir = System.getProperty("user.home")
val rootDir = "./spring-jersey"
val generatedArchetypeDir = rootDir + "/target/generated-sources/archetype"

("rm -rf " + rootDir + "/derby.log " + rootDir + "/db " + rootDir + "/*.ipr " + rootDir + "/*.iws" !)
("mvn clean archetype:create-from-project -f " + rootDir + "/pom.xml" !)
("mv " + generatedArchetypeDir + "/pom.xml " + rootDir + "/pom.xml_bk" !)

val pom = Source.fromFile(rootDir + "/pom.xml_bk")
val lines = pom.getLines map { 
  case "</project>" => """
    <distributionManagement>
        <repository>
            <id>repo</id>
            <url>http://seratch.github.com/mvn-repo/releases</url>
        </repository>
        <snapshotRepository>
            <id>snapshot-repo</id>
            <url>http://seratch.github.com/mvn-repo/snapshots</url>
        </snapshotRepository>
    </distributionManagement>
</project>
"""
  case line => line
} 
val out = new PrintWriter(generatedArchetypeDir + "/pom.xml")
lines foreach { out.println }
out.close()

("rm -f " + rootDir + "/pom.xml_bk" !)
("rm -f " + generatedArchetypeDir + "/target/classes/archetype-resources/deploy.scala" !)
("mvn -DaltDeploymentRepository=release-repo::default::file:" + homeDir + "/github/seratch.github.com/mvn-repo/releases clean deploy -f " + generatedArchetypeDir + "/pom.xml" !)


