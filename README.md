# LendingClub
LendingClub API usage descriptions and documentations for personal use information.
# Environment
Java 8
Gradle 2.4
Junit 4.4
Groovy 2.3.11
# Available data structures
<p>Note https://www.lendingclub.com/developers/notes-owned.action</p>
<p>Account https://www.lendingclub.com/developers/summary.action</p>
<p>Portfolio https://www.lendingclub.com/developers/portfolios-owned.action</p>
<p>Graph
Vertex
Edge</p>
<p>Design UML</p>
![ScreenShot](https://raw.githubusercontent.com/tczhaodachuan/LendingClub/master/img/LendingClub.jpg "UML")

# Get started
<p>API adapts spring framke work as the java beans management, a sample spring file is under resources folder.
The *filter-context.xml file contains the filter configuration like String filter, Integer filter for all the possible
attributes in Note which returned from the server.
spring-main-context.xml file contains the bean for Trader bean, it controls the start and stop of the API.
To start the process we can do
java Main spring-main-context.xml API 1.0
</p>
