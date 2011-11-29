
import scala.sys.process._
import scala.io.Source
import java.io.PrintWriter

("rm -rf ./derby.log ./db" !)
("mvn archetype:create-from-project" !)
("mv target/generated-sources/archetype/pom.xml pom.xml_bk" !)

val pom = Source.fromFile("pom.xml_bk")
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
val out = new PrintWriter("target/generated-sources/archetype/pom.xml")
lines foreach { out.println }
out.close()

("rm -f pom.xml_bk" !)
("mvn -DaltDeploymentRepository=release-repo::default::file:${HOME}/github/seratch.github.com/mvn-repo/releases clean deploy -f target/generated-sources/archetype/pom.xml" !)

